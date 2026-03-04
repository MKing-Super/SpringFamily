# MySQL-binlog监听示例

## 一、概述

### 1 简介

​		**binlog 监听**是指通过技术手段，实时地捕获和解析 MySQL 的二进制日志（binlog），从而获取数据库中的数据变更事件（增、删、改）。

​		可以把它理解为**给数据库安装了一个"窃听器"**，数据库执行的每一条变更操作都会被这个窃听器捕获并转发出去。



### 2 目的

| 应用场景         | 描述                                                         |
| ---------------- | ------------------------------------------------------------ |
| **数据同步**     | 将数据实时同步到其他系统： • **缓存更新**：更新 Redis 等缓存 • **搜索引擎**：同步数据到 Elasticsearch • **数据仓库**：同步到大数据平台（如 Hive、ClickHouse） |
| **主从复制**     | MySQL 自身的主从复制就是基于 binlog 监听实现的               |
| **实时数据分析** | 实时统计用户行为、计算实时指标                               |
| **业务解耦**     | 实现 CDC，将数据变更作为事件发布，供多个微服务消费           |
| **数据审计**     | 记录所有数据变更，用于安全审计和合规性检查                   |



### 3 工作原理

binlog 监听的核心是**模拟 MySQL 从库的行为**。

**工作流程：**

1. **建立连接**：监听程序（如 Canal、Debezium）像从库一样连接到 MySQL 主库。
2. **注册从库**：向主库发送指令，注册自己为一个"从库"，并指定从哪个 binlog 文件和位置开始读取。
3. **拉取 binlog 事件**：主库将 binlog 事件推送给监听程序，就像推送给真正的从库一样。
4. **解析 binlog**：监听程序接收到二进制的 binlog 数据后，根据 binlog 格式（ROW/STATEMENT/MIXED）进行解析。
5. **转换和转发**：将解析后的数据变更（如"id=1 的用户名称从 A 改为 B"）转换成易用的格式（如 JSON），然后转发到消息队列或直接处理。



### 4 注意事项

#### 1. 数据格式兼容性

- 必须使用 **ROW**格式的 binlog，才能获取到变更前后的完整数据

- 不同 MySQL 版本的 binlog 格式可能有差异

#### 2. 性能影响

- 监听本身对主库性能影响很小（类似于增加一个从库）

- 但要确保网络带宽和监听程序的处理能力能跟上数据库的写入速度

#### 3. 高可用与容错

- **断点续传**：监听程序需要持久化当前读取的 binlog 位置，以便重启后能从断点继续

- **故障转移**：主库故障时，需要能切换到新的主库继续监听

#### 4. 数据一致性

- 确保监听程序处理消息的**幂等性**，因为某些情况下可能会重复消费 binlog 事件

- 对于金融等敏感业务，需要严格保证数据顺序和一致性

#### 5.  schema 变更处理

- 当表结构发生变化（ALTER TABLE）时，监听程序需要能正确处理
- 有些工具支持自动检测 schema 变更并调整解析逻辑



## 二、实例

### binlog4j-core 实现 MySQL Binlog 监听

 		简单来说，`binlog4j`是一款轻量级的 MySQL binlog 客户端框架，能快速实现 binlog 的解析和监听，无需依赖 Canal 等复杂中间件，适合中小项目的 binlog 监听需求。

**核心结论先明确**

​		`binlog4j`的核心能力是：通过 TCP 连接 MySQL，实时读取并解析 binlog 事件（增 / 删 / 改），通过注解 / 接口回调的方式触发业务逻辑，支持单机 / 集群模式，且无需修改 MySQL 原有配置（仅需开启 binlog）。

**实现过程**：

​		你现在只引入了`binlog4j-core`依赖（非 SpringBoot 版本），想要实现对`order`表的 binlog 监听，核心思路是**手动创建 Binlog 配置、构建客户端、注册监听器并启动**，无需依赖 Spring 容器，代码可直接运行。

1 核心前提（先确认 MySQL 配置）

```sql
-- 在写代码前，务必确认 MySQL 已开启 binlog 且格式为 ROW（这是 binlog4j 的强制要求）：
-- 验证配置（登录MySQL执行）
SHOW VARIABLES LIKE 'log_bin'; -- 结果为ON
SHOW VARIABLES LIKE 'binlog_format'; -- 结果为ROW
```

2 完整代码实现（仅依赖 binlog4j-core）

```java
import com.github.houbb.binlog4j.client.BinlogClient;
import com.github.houbb.binlog4j.client.config.BinlogConfig;
import com.github.houbb.binlog4j.model.enums.ConsumeModel;
import com.github.houbb.binlog4j.model.event.data.DeleteEventData;
import com.github.houbb.binlog4j.model.event.data.InsertEventData;
import com.github.houbb.binlog4j.model.event.data.UpdateEventData;

/**
 * 仅使用binlog4j-core监听order表的增删改事件
 */
public class OrderBinlogListener {

    public static void main(String[] args) {
        // 1. 构建Binlog配置（核心参数）
        BinlogConfig config = BinlogConfig.newInstance()
                // MySQL连接URL（替换为你的地址、端口、库名）
                .url("jdbc:mysql://127.0.0.1:3306/你的数据库名?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai")
                // 监听用户（需有REPLICATION权限）
                .username("binlog_user")
                .password("Binlog@123456")
                // 要监听的数据库名（与URL中的库名一致）
                .database("你的数据库名")
                // 监听的表名：仅监听order表（多个表用逗号分隔，如"order,user"）
                .tables("order")
                // 消费模式：LATEST=只监听新的binlog事件（推荐）；INITIAL=先读历史再监听新事件
                .consumeModel(ConsumeModel.LATEST)
                // 线程数：默认1即可，高并发可适当增加
                .threadCount(1);

        // 2. 创建Binlog客户端
        BinlogClient binlogClient = BinlogClient.create(config);

        // 3. 注册order表的监听事件（增/删/改分开监听，更清晰）
        // 3.1 监听插入事件（新增订单）
        binlogClient.registerListener(InsertEventData.class, event -> {
            // 过滤：仅处理order表的插入事件（防止多表监听时串扰）
            if ("order".equals(event.getTableName())) {
                System.out.println("【订单新增】时间：" + System.currentTimeMillis());
                System.out.println("新增订单数据：" + event.getAfterMap()); // key=列名，value=值
                // 这里写你的业务逻辑：比如同步到ES、发送MQ、更新缓存等
                // syncOrderToEs(event.getAfterMap());
            }
        });

        // 3.2 监听更新事件（修改订单）
        binlogClient.registerListener(UpdateEventData.class, event -> {
            if ("order".equals(event.getTableName())) {
                System.out.println("【订单更新】时间：" + System.currentTimeMillis());
                System.out.println("更新前数据：" + event.getBeforeMap());
                System.out.println("更新后数据：" + event.getAfterMap());
            }
        });

        // 3.3 监听删除事件（删除订单）
        binlogClient.registerListener(DeleteEventData.class, event -> {
            if ("order".equals(event.getTableName())) {
                System.out.println("【订单删除】时间：" + System.currentTimeMillis());
                System.out.println("删除的订单数据：" + event.getBeforeMap());
            }
        });

        // 4. 启动监听（核心步骤，启动后持续监听）
        binlogClient.start();

        // 5. 优雅关闭（可选，防止程序退出时监听线程未终止）
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("关闭binlog监听...");
            binlogClient.stop();
        }));

        // 防止主线程退出（监听线程是守护线程，主线程退出则监听停止）
        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 示例：业务逻辑方法（可根据需求实现）
    // private static void syncOrderToEs(Map<String, Object> orderData) {
    //     // 同步订单数据到Elasticsearch
    // }
}
```





## 三、问题

### 1 binlog4j-core 1.9.1 中BinlogClientConfig必须要配置serverId吗？

​		**该版本仍不强制手动配置`serverId`，但相比早期版本，1.9.1 对`serverId`的默认逻辑做了优化，生产环境依然建议手动指定唯一值，避免潜在冲突**。

**1.9.1 版本中 serverId 的默认逻辑（核心优化点）**

binlog4j 1.9.1 对`serverId`的自动生成规则做了升级，解决了早期版本 “纯随机值易重复” 的问题：

1. 自动生成规则：

   基于`MySQL地址+端口+数据库名+监听表名`做哈希计算，再映射到 1-65535 范围，生成固定且唯一的 serverId；

   比如：监听`127.0.0.1:3306/test`库的`order`表，每次启动生成的 serverId 都是同一个值。

2. 优势：

   单客户端场景下，无需手动配置，且不会因重启客户端导致 serverId 变化；

   多客户端场景下，只要监听的库 / 表不同，自动生成的 serverId 大概率不重复。

3. 仍存风险：

   若多个客户端监听同一个 MySQL 实例的同一个库 / 表，或自动生成的 serverId 恰好与 MySQL 主节点 / 从库的`server_id`重复，仍会导致连接被 MySQL 断开。

### 2 同一站点有多台服务器使用binlog监听，serverId如何处理？

- 同一站点有2台服务器使用binlog监听，serverId相同，导致binlog连接被 MySQL 断开。然后2天服务器各自重试重连循环，导致binlog异常。
- 同一站点有2台服务器使用binlog监听，serverId不同，导致binlog对同一个表中的数据监听变动重复，会导致数据被重复处理。

**解决方案：**

​		同一站点有2台服务器，只让一条服务器实现binlog监听，另一台服务器的binlog不连接mysql，使得监听数据变化只处理一次，避免重复处理。

