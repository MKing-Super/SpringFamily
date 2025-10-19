# StarRocks

## 一、简介

### 1 概述

​		StarRocks 是**新一代极速全场景 MPP (Massively Parallel Processing) 数据库**。StarRocks 的愿景是能够让用户的**数据分析变得更加简单和敏捷**。

​		StarRocks 兼容 MySQL 协议，支持标准 SQL 语法，易于对接使用，全系统无外部依赖，高可用，易于运维管理。

​		StarRocks 可以满足企业级用户的多种分析需求，包括 OLAP (Online Analytical Processing) 多维分析、定制报表、实时数据分析。

### 2 架构

​		StarRocks 架构简洁，整个系统的核心只有  FE（Frontend）、BE（Backend）两类进程，不依赖任何外部组件，方便部署与维护。FE 和 BE  模块都可以在线水平扩展，元数据和业务数据都有副本机制，确保整个系统无单点。StarRocks 提供 MySQL 协议接口，支持标准 SQL  语法。用户可通过 MySQL 客户端方便地查询和分析 StarRocks 中的数据。

#### FE

​		FE 是 StarRocks 的前端节点，负责管理元数据，管理客户端连接，进行查询规划，查询调度等工作。每个 FE 节点都会在内存保留一份完整的元数据，这样每个 FE 节点都能够提供无差别的服务。

​		FE 有三种角色：Leader FE，Follower FE 和 Observer FE。

- Leader
  - Leader 从 Follower 中自动选出，进行选主需要集群中有半数以上的 Follower 节点存活。如果 Leader 节点失败，Follower 会发起新一轮选举。
  - Leader FE 提供元数据读写服务。只有 Leader 节点会对元数据进行写操作，Follower 和 Observer  只有读取权限。Follower 和 Observer 将元数据写入请求路由到 Leader 节点，Leader 更新完数据后，会通过 BDB  JE 同步给 Follower 和 Observer。必须有半数以上的 Follower 节点同步成功才算作元数据写入成功。
- Follower
  - 只有元数据读取权限，无写入权限。通过回放 Leader 的元数据日志来异步同步数据。
  - 参与 Leader 选举，必须有半数以上的 Follower 节点存活才能进行选主。
- Observer
  - 主要用于扩展集群的查询并发能力，可选部署。
  - 不参与选主，不会增加集群的选主压力。
  - 通过回放 Leader 的元数据日志来异步同步数据。

#### BE

​		BE 是 StarRocks 的后端节点，负责数据存储、SQL执行等工作。

- 数据存储方面，StarRocks 的 BE 节点都是完全对等的，FE 按照一定策略将数据分配到对应的 BE 节点。BE 负责将导入数据写成对应的格式存储下来，并生成相关索引。
- 在执行 SQL 计算时，一条 SQL 语句首先会按照具体的语义规划成逻辑执行单元，然后再按照数据的分布情况拆分成具体的物理执行单元。物理执行单元会在对应的数据存储节点上执行，这样可以实现本地计算，避免数据的传输与拷贝，从而能够得到极致的查询性能。

### 3 数据管理

​		StarRocks  使用列式存储，采用分区分桶机制进行数据管理。一张表可以被划分成多个分区，如将一张表按照时间来进行分区，粒度可以是一天，或者一周等。一个分区内的数据可以根据一列或者多列进行分桶，将数据切分成多个 Tablet。Tablet 是 StarRocks 中最小的数据管理单元。每个 Tablet 都会以多副本 (replica)  的形式存储在不同的 BE 节点中。您可以自行指定 Tablet 的个数和大小。 StarRocks 会管理好每个 Tablet 副本的分布信息。

**层级关系：** `表 (Table)`-> `分区 (Partition)`-> `分桶 (Bucket)`-> **`Tablet`** (物理存储单元)

> 一个Tablet对应一个桶，桶是规划蓝图，Tablet是对桶的实现。





## 二、表设计

### 1 列式存储

![](.\images\starrocks-2-01.jpg)

​		StarRocks 中的表由行和列构成。每行数据对应用户一条记录，每列数据具有相同的数据类型。所有数据行的列数相同，可以动态增删列。在  StarRocks 中，一张表的列可以分为维度列（也称为 Key 列）和指标列（也称为 Value  列）。维度列用于分组和排序，指标列的值可以通过聚合函数 [sum](https://docs.starrocks.io/zh/docs/2.5/sql-reference/sql-functions/aggregate-functions/sum/)、[count](https://docs.starrocks.io/zh/docs/2.5/sql-reference/sql-functions/aggregate-functions/count/)、[min](https://docs.starrocks.io/zh/docs/2.5/sql-reference/sql-functions/aggregate-functions/min/)、[max](https://docs.starrocks.io/zh/docs/2.5/sql-reference/sql-functions/aggregate-functions/max/)、[hll_union_agg](https://docs.starrocks.io/zh/docs/2.5/sql-reference/sql-functions/aggregate-functions/hll_union_agg/) 和 [bitmap_union](https://docs.starrocks.io/zh/docs/2.5/sql-reference/sql-functions/bitmap-functions/bitmap_union/) 等累加起来。

​		在 StarRocks  中，表数据按列存储。物理上，一列数据会经过分块编码、压缩等操作，然后持久化存储到非易失设备上。但在逻辑上，一列数据可以看成是由相同类型的元素构成的一个数组。  一行数据的所有列值在各自的数组中按照列顺序排列，即拥有相同的数组下标。数组下标是隐式的，不需要存储。表中所有的行按照维度列，做多重排序，排序后的位置就是该行的行号。

### 2 索引

​		StarRocks 通过前缀索引 (Prefix Index) 和列级索引，能够快速找到目标行所在数据块的起始行号。

![](.\images\starrocks-2-02.jpg)

一张表中的数据组织主要由三部分构成：

- 前缀索引

  表中每 1024 行数据构成一个逻辑数据块 (Data  Block)。每个逻辑数据块在前缀索引表中存储一个索引项，索引项的内容为数据块中第一行数据的维度列所构成的前缀，长度不超过 36  字节。前缀索引是一种稀疏索引。使用表中某行数据的维度列所构成的前缀查找前缀索引表，可以确定该行数据所在逻辑数据块的起始行号。

- 列级数据块

  表中每列数据都按 64 KB 分块存储。数据块作为一个单位单独编码、压缩，也作为 I/O 单位，整体写回设备或者读出。

- 列级索引

  表中每列数据都有一个独立的行号索引。行号索引表中，该列的数据块和行号一一对应。每个行号索引项由对应数据块的起始行号、位置和长度信息构成。用某行数据的行号查找行号索引表，可以获取包含该行号对应的数据块所在的位置，读取目标数据块后，可以进一步查找数据。

由此可见，通过某行数据的维度列所构成的前缀查找该行数据的过程包含以下五个步骤：

1. 先查找前缀索引表，获得逻辑数据块的起始行号。
2. 查找维度列的行号索引，定位到维度列的数据块。
3. 读取数据块。
4. 解压、解码数据块。
5. 从数据块中找到维度列前缀对应的数据项。

### 3 建表标准格式

```sql
CREATE [EXTERNAL] TABLE [IF NOT EXISTS] [database.]table_name
(column_definition1[, column_definition2, ...]
[, index_definition1[, index_definition2, ...]])
[ENGINE = [olap|mysql|elasticsearch|hive|iceberg|hudi|jdbc]]
[key_desc]
[COMMENT "table comment"]
[partition_desc]
distribution_desc
[rollup_index]
[PROPERTIES ("key"="value", ...)]
[BROKER PROPERTIES ("key"="value", ...)]
```





### 3 数据模型

- **明细模型**：表中会存在主键重复的数据行，并且与导入的数据是完全对应的。您可以召回所导入的全部历史数据。
- **聚合模型**：表中不存在主键重复的数据行，主键满足唯一性约束。导入的数据中主键重复的数据行聚合为一行，即具有相同主键的指标列，会通过聚合函数进行聚合。您只能召回导入的全部历史数据的聚合结果，但是无法召回历史明细数据。
- **主键模型** 和 **更新模型**：表中不存在主键重复的数据行，主键满足唯一性约束。最新导入的数据行，替换掉其他主键重复的数据行。这两种模型可以视为聚合模型的特殊情况，相当于在聚合模型中，为表的指标列指定聚合函数为 REPLACE，REPLACE 函数返回主键相同的一组数据中的最新数据。

#### 3.1 明细模型

```sql
CREATE TABLE IF NOT EXISTS detail (
    event_time DATETIME NOT NULL COMMENT "datetime of event",
    event_type INT NOT NULL COMMENT "type of event",
    user_id INT COMMENT "id of user",
    device_code INT COMMENT "device code",
    channel INT COMMENT ""
)
DUPLICATE KEY(event_time, event_type)
DISTRIBUTED BY HASH(user_id) BUCKETS 8
PROPERTIES (
	"replication_num" = "3"
);
```



#### 3.2 聚合模型

支持的聚合函数：SUM、MAX、MIN、REPLACE

```sql
CREATE TABLE IF NOT EXISTS example_db.aggregate_tbl (
    site_id LARGEINT NOT NULL COMMENT "id of site",
    date DATE NOT NULL COMMENT "time of event",
    city_code VARCHAR(20) COMMENT "city_code of user",
    pv BIGINT SUM DEFAULT "0" COMMENT "total page views"
)
AGGREGATE KEY(site_id, date, city_code)
DISTRIBUTED BY HASH(site_id) BUCKETS 8
PROPERTIES (
	"replication_num" = "3"
);
```



#### 3.3 更新模型

```sql
CREATE TABLE IF NOT EXISTS orders (
    create_time DATE NOT NULL COMMENT "create time of an order",
    order_id BIGINT NOT NULL COMMENT "id of an order",
    order_state INT COMMENT "state of an order",
    total_price BIGINT COMMENT "price of an order"
)
UNIQUE KEY(create_time, order_id)
DISTRIBUTED BY HASH(order_id) BUCKETS 8
PROPERTIES (
	"replication_num" = "3"
); 
```



#### 3.4 主键模型

```sql
create table users (
    user_id bigint NOT NULL,
    name string NOT NULL,
    email string NULL,
    address string NULL,
    age tinyint NULL,
    sex tinyint NULL,
    last_active datetime,
    property0 tinyint NOT NULL,
    property1 tinyint NOT NULL,
    property2 tinyint NOT NULL,
    property3 tinyint NOT NULL
) PRIMARY KEY (user_id)
DISTRIBUTED BY HASH(user_id) BUCKETS 4
PROPERTIES (
    "replication_num" = "3",
    "enable_persistent_index" = "true"
);
```

> `enable_persistent_index`参数
>
> - **作用**：控制是否启用持久化索引功能
> - **默认值**：从 StarRocks 2.3 版本开始默认启用（true）
> - **适用场景**：主要用于主键模型（Primary Key）表

### 什么是持久化索引？

- **传统索引**：通常存储在内存中，查询速度快但占用大量内存资源
- **持久化索引**：将索引数据存储在磁盘上，仅在需要时加载到内存
- **工作方式**：在查询时按需加载索引块，而不是一次性加载整个索引





## #、常见问题

### 1 starrocks创建明细表时replication_num属性

​		在 StarRocks 中创建明细表（或其他任何类型的表）时，`replication_num`属性用于指定**该表的数据副本数量**。（比如：replication_num = 3时，tablet的副本有3个分别存在于3个Backend服务器节点上）



**节点数量要求：** 你设置的 `replication_num`值**不能超过**集群中健康的 `Backend`节点的总数。例如，如果你的集群只有 2 个 `Backend`，那么 `replication_num`最大只能设置为 `2`。如果设置为 `3`，建表会失败。

**修改限制：** 表一旦创建成功，`replication_num`**不能直接修改**。如果需要更改副本数，通常需要创建新表（设置新的 `replication_num`），然后将数据导入新表，最后进行表重命名切换。

**自动维护：** StarRocks 会自动管理这些副本。如果一个 `Backend`节点下线或故障，StarRocks 会在其他健康的 `Backend`节点上自动补充缺失的副本，以维持配置的副本数。当故障节点恢复后，StarRocks 也可能（根据策略）进行副本均衡。



### 2 replication_num是指的tablet吗？Backend是指的BE吗？

​		**`replication_num`指的是 Tablet 的副本数量：** **完全正确！** 在 StarRocks 中，数据表在物理存储层面会被水平分割成许多较小的、可管理的单元，这些单元称为 **Tablet**。 当您为一个表设置 `replication_num = N`时，您实际上是在指定**该表中每个 Tablet 应该有多少个副本**。

​		例如，如果 `replication_num = 3`，那么表中的每一个 Tablet 都会被复制成 3 份完全相同的副本。这些副本会被 StarRocks 自动调度并存储在不同的 Backend 节点上。



​		**Backend 指的是 BE：** **完全正确！**

 `Backend`就是 `BE`的全称。

 **BE (Backend)** 是 StarRocks 集群中负责**数据存储**和**查询执行**的核心工作节点。 

主要职责包括： 存储 Tablet 的副本。 执行查询计划中分配给它的部分（如扫描、聚合、排序等）。 管理数据的写入、压缩、副本修复等后台任务。

​		一个 StarRocks 集群通常由多个 BE 节点组成，以实现存储和计算的分布式与高可用。

**总结与关系梳理：**

- **层级关系：** `表 (Table)`-> `分区 (Partition)`-> `分桶 (Bucket)`-> **`Tablet`** (物理存储单元)。
- **副本配置：** `replication_num`属性作用于**表级别**，但它实际控制的是该表中**每个 Tablet** 的副本数量。
- **存储位置：** 每个 Tablet 的 `N`个副本 (`replication_num = N`) 会被分布式地存储在不同的 **BE (Backend)** 节点上。
- **目的：** 这种设计（Tablet + 多副本 + 分布式 BE）确保了 StarRocks 的高性能（并行处理）、高可用性（副本容错）和可扩展性（增加 BE 即可扩容）。



### 3 如果replication_num=3，那tablet是分布在3台服务器上吗？

​		当 `replication_num = 3`时，**同一个 Tablet 的 3 个副本会分布在 3 台不同的 BE 服务器上**。

**目的：** 这样设计的主要目的就是为了**容错**。如果其中一台 BE 服务器宕机或发生网络分区，该 Tablet 在其他 2 台 BE 服务器上依然有完整的副本，数据仍然可读可写，保证了服务的连续性



### 4 一个tablet存储的是一个表中的一列吗？

​		不，**一个 Tablet 存储的不是表中的一列，而是表中一部分行的所有列的数据片段**。

​		StarRocks 的核心存储引擎是 **列式存储**。这意味着数据在磁盘上**不是按行存储**（一行接一行），而是**按列存储**。

**优点：** 对于只查询少数几列的 OLAP 分析查询，列存可以极大地减少 I/O（只需读取查询涉及的列），并利用列数据的同质性进行高效的压缩和向量化计算。

​		Tablet 是 StarRocks 中**数据分片、复制和负载均衡的最小物理单元**。

​		 一个 Tablet 包含了**某个逻辑数据分片（即表的一部分行）的所有列的数据**。

 **如何划分 Tablet？** 

- **分区 (Partitioning)：** 表首先会根据分区键（如日期 `dt`）被划分为多个**分区**。分区主要用于管理数据的生命周期（如按天分区，方便删除旧数据）。
-  **分桶 (Bucketing/Distribution)：** 在每个分区内部，数据会根据分桶键（如 `user_id`）进行哈希（或随机/范围）分桶。每个桶就是一个 Tablet 的**逻辑对应**。

**所以，一个 Tablet 包含：** 属于**某个特定分区**。 属于**某个特定分桶**。 包含该分桶内**所有行**的数据。 包含这些行**所有列**的数据（但按列式存储组织）。



### 5 一个分区中的桶里有多个tablet？

​		不，**一个分区中的一个桶（Bucket）只对应一个 Tablet**。这是 StarRocks 数据分布的核心逻辑。

让我再清晰地梳理一下层级关系：

1. **表 (Table)：** 这是逻辑上的顶层结构。

2. **分区 (Partition)：**

    表根据分区键（通常是日期或某些离散值）被水平划分为多个**分区**。

   分区主要用于**数据管理**，例如按时间分区方便删除旧数据（`DROP PARTITION`），或者优化查询（分区裁剪）。

3.**分桶 / 分布 (Bucketing / Distribution)：** 

​		在每个**分区内部**，数据会根据分桶键（Distribution Key）进行进一步划分。 

​		分桶键的选择非常重要，它决定了数据如何在集群中分布，影响数据本地性和查询性能（如 Join 和 Group By）。 

​		数据通过分桶键的哈希值（或其他分桶方式，如随机、范围）被分配到固定数量的**桶 (Bucket)** 中。

​		**关键点：`DISTRIBUTED BY HASH(bucket_key) BUCKETS N`中的 `N`指定了该分区内有多少个桶。**

4.**Tablet (数据分片)：** **每个桶 (Bucket) 直接对应一个 Tablet。**

 Tablet 是 StarRocks 中**物理存储、复制和计算的最小单元**。

 一个 Tablet 包含了： 属于**某个特定分区**。 属于**某个特定桶**。 该桶内的**所有行**。 这些行的**所有列**的数据（以列式格式存储）。 

当您设置 `replication_num = M`时，指的是**每个 Tablet（也就是每个桶）有 M 个副本**，这些副本存储在不同的 BE 上。



### 6 如果有3个表，每个表创建时有2个分区2个分桶，那这个starrocks中有多少个桶，有多少tablet？

在您描述的场景下（3个表，每个表2分区2分桶）：

- 整个 StarRocks 集群中有 **12 个逻辑桶 (Bucket)**。
- 整个 StarRocks 集群中有 **12 个逻辑 Tablet**。
- 如果 `replication_num = 3`，则集群中需要存储 **36 个 Tablet 的物理副本**。



### 7 fe和be必须是单数个吗？

**FE 节点必须是奇数个，而 BE 节点可以是任意数量（只要满足副本数要求）。**



7.1 FE (Frontend) 节点：必须是奇数个。

**是的，在生产环境中，FE 节点强烈建议部署为奇数个（通常是 3 个或 5 个）。**

**原因：** FE 节点之间通过 **Quorum（多数决）协议** 来选举 Leader 和进行元数据的强一致性同步。这种协议要求能够形成“多数派”才能做出决策，避免“脑裂”问题。

- **什么是多数派？** 总节点数为 N 时，多数派 = `floor(N/2) + 1`。

- **为什么需要奇数个？**

   **3 个 FE 节点：** 多数派是 2。集群可以容忍 **1 个** FE 节点故障。只要还有 2 个 FE 节点存活，集群就能正常选举 Leader 和进行元数据操作。

   **2 个 FE 节点：** 多数派也是 2。这意味着**不能容忍任何节点故障**。如果 1 个节点宕机，剩下的 1 个节点无法形成多数派（1 < 2），集群将无法选举出新的 Leader，导致元数据服务不可用，整个集群基本瘫痪。

  **4 个 FE 节点：** 多数派是 3。集群可以容忍 **1 个** 节点故障（因为 3 >= 3）。但是，它**不能容忍 2 个**节点故障（因为 2 < 3）。从容错能力上看，4 个节点的容错能力（1个）和 3 个节点的容错能力（1个）是一样的，但 4 个节点成本更高、内部通信开销更大。

**结论：** 部署 3 个 FE 节点可以在成本、性能和容错能力上达到最佳平衡。它和 4 个节点的容错能力相同，但更节省资源。因此，**FE 节点数量必须是奇数，并且 3 个是生产环境的最佳实践。**



7.2 BE (Backend) 节点：可以是任意数量

**BE 节点数量没有奇偶性要求，它主要取决于你的存储容量、计算性能和副本数需求。**

**决定 BE 节点数量的因素：**

1.**数据副本数 (`replication_num`)**：这是最重要的约束。**集群中健康的 BE 节点数量必须大于等于你为表设置的 `replication_num`**。

​		 例如，如果你希望表的 `replication_num = 3`，那么你的集群至少需要有 3 个 BE 节点。

​		如果只有 2 个 BE，你就无法创建 `replication_num = 3`的表。

2.**存储空间**：需要的总数据量 / 每个 BE 节点提供的磁盘空间。

3.**查询性能与并发**：更多的 BE 节点可以并行执行更多的查询任务，提高整个集群的吞吐量。

4.**高可用性**：虽然 BE 节点数量本身无奇偶要求，但从高可用角度考虑：

​		 如果 `replication_num = 3`，建议 BE 节点数量至少为 3，并且最好是大于 3（如 4, 5, 6...）。 

​		这样，当某个 BE 节点宕机时，系统在为其上的 Tablet 副本寻找新的目标 BE 节点时有更多选择，更容易实现数据均衡，避免在副本修复期间给少数几个 BE 节点带来过大压力。



### 8 tablet副本数包括自己吗？

**什么是副本？** 

​		副本是数据的**完全相同的拷贝**。 

​		对于同一个 Tablet，它的 `replication_num = N`意味着该 Tablet 的数据在集群中有 **N 份完全相同的拷贝**。

​		这 N 份拷贝存储在不同的 Backend (BE) 节点上。



**“自己”是副本吗？** 

​		**是的！** 当你谈论一个具体的 Tablet 副本时，它本身就是这 N 个副本中的一个。 

​		不存在一个“原始”或“非副本”的版本。**所有 N 个拷贝都是平等的副本 (peer replicas)**。它们之间没有主从之分（在 StarRocks 的默认多副本机制下）。



