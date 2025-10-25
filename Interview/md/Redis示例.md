# Redis示例

## 一、概述

​		Redis是C语言开发的一个开源的（遵从[BSD](https://so.csdn.net/so/search?q=BSD&spm=1001.2101.3001.7020)协议）高性能键值对（key-value）的内存数据库，可以用作数据库、缓存、[消息中间件](https://so.csdn.net/so/search?q=消息中间件&spm=1001.2101.3001.7020)等。它是一种NoSQL（not-only sql，泛指非关系型数据库）的数据库。

**Redis的特点**

1. 性能优秀，数据在内存中，读写速度非常快，支持并发10W QPS；
2. 单进程单线程，是线程安全的，采用IO多路复用机制；
3. 丰富的数据类型，支持字符串（strings）、散列（hashes）、列表（lists）、集合（sets）、有序集合（sorted sets）等；
4. 支持数据持久化。可以将内存中数据保存在磁盘中，重启时加载；
5. 主从复制，哨兵，高可用；
6. 可以用作分布式锁；
7. 可以作为消息中间件使用，支持发布订阅。

> **QPS（Queries Per Second）**：每秒查询率，表示系统每秒钟能够处理的请求数量。
>
> **并发10W QPS** 意味着：
>
> - 系统每秒钟需要处理 **100,000 个请求**
> - 平均每个请求需要在 **10毫秒内完成**
> - 这是**高性能系统**的重要指标

### 1 五种类型

#### 1. String

String是Redis最基本的数据类型，一个key对应一个value。value是二进制安全的，可以存储jpg图片或者序列化对象，最大可存储512M。

```
set键值对：set key value
get键值对：get key
```

#### 2. Hash

hash类型存储键值对集合，**类似于Java中的HashMap类型**。hash类型特别适合存储对象，而且可以只修改对象中的某一个属性值。

```
set单个键值对：hset key field1 value1
set多个键值对：hmset key field1 value1 field2 value2
get单个键值对：hget key field1
get多个键值对：hmget key field1 field2
get所有的键值对：hgetall key
获取所有的key：hkeys key
删除一个或多个字段：hdel key field1 field2
查看哈希表中key中field是否存在：hexists key field1
为哈希表指定字段的整数值加上增量：hincrby key field2 increment
获取哈希表中字段的数量：hlen key
```

#### 3. List

List是字符串列表，**本质实现方式是双向链表**，插入的时候可以选择插入到队头或者队尾。在进行增删操作的时候效率很高，一般用于消息队列等场景。

```
往列表头push元素：lpush key value1 value2
往列表尾push元素：rpush key value3 value4
获取从左起列表范围内元素（右侧同理）：lrange key 0 10
从列表左侧pop元素（右侧同理）：lpop key value1
获取列表长度：llen key
```

#### 4. Set

Set是String的无序类型，本质是通过哈希表实现的，所以添加，删除，查找的复杂度都是 O(1)。Set可以用于判断共同好友、访问网站IP等需要去重的场合。

```
添加一个或多个成员：sadd key member1 member2
获取集合的成员数：scard key
获取集合中的所有成员：smembers key
移除并返回集合中一个随即元素：spop key
返回所有给定集合的交集：sinter key1 key2
返回所有给定集合的并集：sunion key1 key2
```

#### 5. ZSet

ZSet指的是有序集合，和Set一样是String类型元素的集合，且不允许重复的元素。不同的是每个元素都会关联一个double类型的分数score，Redis根据这个score来对集合中的元素进行排序，当你插入之后就会自动根据score排序。

ZSet内部根据HashMap和跳跃表来保证数据的存储和有序，HashMap里放的是成员到score的映射，而跳跃表里存放的是所有的成员，排序依据是HashMap里存的score，使用跳跃表的结构可以获得比较高的查找效率，并且在实现上比较简单。ZSet可用于计算排行榜、带权重的消息队列等。

跳表顾名思义，就是跳跃了一些元素，可以抽象多层。如下图所示，比如我们要查找8，先在最上层L2查找，发现在1和9之间；然后去L1层查找，发现在5和9之间；然后去L0查找，发现在7和9之间，然后找到8。当元素比较多时，使用跳表可以显著减少查找的次数。

> **跳跃表**是一种概率性的有序数据结构，它通过建立多级索引来实现快速查找，时间复杂度为 **O(log n)**。可以看作是"加了快速通道的链表"。

![](.\images\redis_01.png)

同list类似，Redis内部也不是直接使用的跳表，而是使用了一个自定义的数据结构来持有跳表。下图左边蓝色部分是skiplist，右边是4个zskiplistNode。zskiplistNode内部有很多层L1、L2等，指针指向这一层的下一个结点。BW是回退指针（backward），用于查找的时候回退。然后下面是score和对象本身object。

![](.\images\redis-02.png)

```
向zset中添加一个或多个成员，或者更新成员分数：zadd key score1 member1 score2 member2
获取有序集合的成员数：zcard key
通过索引区间返回有序集合指定区间的成员：zrange key start stop
```

### 2 缓存问题

#### 1.缓存和数据库数据一致性

​		分布式环境下非常容易出现缓存和数据库间数据一致性问题，针对这一点，如果项目对缓存的要求是**强一致性**的，那么就**不要使用缓存**。我们只能采取合适的策略来降低缓存和数据库间数据不一致的概率，而无法保证两者间的强一致性。合适的策略包括合适的缓存更新策略，更新数据库后及时更新缓存、缓存失败时增加重试机制。


#### 2.Redis雪崩

当缓存中**大面积的Key**同时失效，使得本来作用于缓存的请求全部落在了数据库上，导致数据库崩溃，重启数据库时又被新的流量弄崩溃了，这就是缓存雪崩。

**解决方案：**

1. 可以在批量往Redis存数据的时候，把每个Key的失效时间加个随机值，均匀分布缓存失效时间。
2. 定时更新缓存。



#### 3.Redis缓存穿透

缓存穿透是指缓存和数据库中都没有的数据，而用户（黑客）不断发起请求，导致一直在数据库里搜索，这样的不断攻击导致数据库压力很大，严重会击垮数据库。

解决方案：

在接口层增加校验，不合法的请求直接拦截掉。
Redis里还有一个高级用法**布隆过滤器（Bloom Filter）**这个也能很好的预防缓存穿透的发生，他的原理也很简单，就是利用高效的数据结构和算法快速判断出你这个Key是否在数据库中存在，不存在你return就好了，存在你就去查DB刷新KV再return。缓存击穿的话，设置热点数据永不过期，或者加上互斥锁就搞定了。



#### 4.Redis缓存击穿

缓存击穿是指**一个Key**非常热点，在不停地扛着大量的请求，大并发集中对这一个点进行访问，当这个Key在失效的瞬间，持续的大并发直接落到了数据库上，就在这个Key的点上击穿了缓存。

**解决方案：**

1. 对于热点请求的Key，设置数据永不过期。
2. 对热点Key限流，大于接受量时阻塞。或者加上互斥锁。

> **互斥锁**（Mutual Exclusion Lock）是并发编程中的基本同步机制，用于保护共享资源，确保在任意时刻只有一个线程可以访问临界区（Critical Section）。
>
> ### 关键特性：
>
> - **互斥性**：一次只允许一个线程持有锁
> - **原子性**：锁的获取和释放操作是原子的
> - **阻塞机制**：未获得锁的线程会进入等待状态



#### 5.Redis速度

Redis可以达到100000+的QPS（Query Per Second，每秒查询次数），但它却是单进程单线程的模型。因为Redis完全是基于内存的操作，CPU不是Redis的瓶颈，Redis的瓶颈最有可能是机器内存的大小或者网络带宽。既然单线程容易实现，而且CPU不会成为瓶颈，那就顺理成章的采用单线程的方案了。

Redis速度快的原因：

1. Redis完全基于内存，绝大部分请求是纯粹的内存操作，非常迅速，数据存在内存中，类似于HashMap，HashMap的优势就是查找和操作的时间复杂度是O(1)。
2. 数据结构简单，对数据操作也简单。
3. 采用单线程，避免了不必要的上下文切换和竞争条件，不存在多线程导致的CPU切换，不用去考虑各种锁的问题，不存在加锁释放锁操作，没有死锁问题导致的性能消耗。
4. 使用多路复用IO模型，非阻塞IO。Redis监听多个socket，可以一次性接受多个客户端请求，然后放到队列中，待命令完整发到服务端再去处理请求，不需要等待客户端的传输。



#### 6.Redis淘汰策略

​		在 redis 中，允许用户设置最大使用内存大小。server.maxmemory默认为0，没有指定最大缓存，如果有新的数据添加，超过最大内存，则会使redis崩溃，所以一定要设置。redis 内存数据集大小上升到一定大小的时候，就会实行数据淘汰策略。

redis 提供 6种数据淘汰策略：

- volatile-lru：从已设置过期时间的数据集（server.db[i].expires）中挑选最近最少使用的数据淘汰
- volatile-ttl：从已设置过期时间的数据集（server.db[i].expires）中挑选将要过期的数据淘汰
- volatile-random：从已设置过期时间的数据集（server.db[i].expires）中任意选择数据淘汰
- allkeys-lru：从数据集（server.db[i].dict）中挑选最近最少使用的数据淘汰
- allkeys-random：从数据集（server.db[i].dict）中任意选择数据淘汰
- no-enviction（驱逐）：禁止驱逐数据

注意这里的6种机制，volatile和allkeys规定了是对已设置过期时间的数据集淘汰数据还是从全部数据集淘汰数据，后面的lru、ttl以及random是三种不同的淘汰策略，再加上一种no-enviction永不回收的策略。

使用策略规则：

- 如果数据呈现幂律分布，也就是一部分数据访问频率高，一部分数据访问频率低，则使用allkeys-lru
- 如果数据呈现平等分布，也就是所有的数据访问频率都相同，则使用allkeys-random



#### 7.Redis的持久化

​		redis为了保证效率，数据缓存在了内存中，但是会周期性的把更新的数据写入磁盘或者把修改操作写入追加的记录文件中，以保证数据的持久化。

Redis的持久化策略有两种：

**1 RDB**

​		RDB快照形式是直接把内存中的数据保存到一个dump的文件中，定时保存，保存策略。默认Redis是会以快照"RDB"的形式将数据持久化到磁盘的一个二进制文件dump.rdb。

​		工作原理简单说一下：当Redis需要做持久化时，Redis会fork一个子进程，子进程将数据写到磁盘上一个临时RDB文件中。当子进程完成写临时文件后，将原来的RDB替换掉，这样的好处是可以copy-on-write。

​		RDB的优点是：这种文件非常适合用于备份：比如，你可以在最近的24小时内，每小时备份一次，并且在每个月的每一天也备份一个RDB文件。这样的话，即使遇上问题，也可以随时将数据集还原到不同的版本。RDB非常适合灾难恢复。RDB的缺点是：如果你需要尽量避免在服务器故障时丢失数据，那么RDB不合适你。

**2 AOF**

​		AOF把所有的对Redis的服务器进行修改的命令都存到一个文件里，命令的集合。AOF可以做到全程持久化，只需要在配置中开启 appendonly yes。这样redis每执行一个修改数据的命令，都会把它添加到AOF文件中，当redis重启时，将会读取AOF文件进行重放，恢复到redis关闭前的最后时刻。

​		使用AOF的优点是会让redis变得非常耐久。可以设置不同的fsync策略，aof的默认策略是每秒钟fsync一次，在这种配置下，就算发生故障停机，也最多丢失一秒钟的数据。缺点是对于相同的数据集来说，AOF的文件体积通常要大于RDB文件的体积。根据所使用的fsync策略，AOF的速度可能会慢于RDB。

​		Redis默认是快照RDB的持久化方式。当Redis重启的时候，它会优先使用AOF文件来还原数据集，因为AOF文件保存的数据集通常比RDB文件所保存的数据集更完整。你甚至可以关闭持久化功能，让数据只在服务器运行时存。

​		如果你非常关心你的数据，但仍然可以承受数分钟内的数据丢失，那么可以额只使用RDB持久。AOF将Redis执行的每一条命令追加到磁盘中，处理巨大的写入会降低Redis的性能，不知道你是否可以接受。数据库备份和灾难恢复：定时生成RDB快照非常便于进行数据库备份，并且RDB恢复数据集的速度也要比AOF恢复的速度快。当然了，redis支持同时开启RDB和AOF，系统重启后，redis会优先使用AOF来恢复数据，这样丢失的数据会最少。


#### 8.主从复制

​		redis单节点存在单点故障问题，为了解决单点问题，一般都需要对redis配置从节点，然后使用哨兵来监听主节点的存活状态，如果主节点挂掉，从节点能继续提供缓存功能。主从配置结合哨兵模式能解决单点故障问题，提高redis可用性。从节点仅提供读操作，主节点提供写操作。对于读多写少的状况，可给主节点配置多个从节点，从而提高响应效率。

**复制过程：**

1. 从节点执行slaveof [ masterIP ] [masterPort]，保存主节点信息
2. 从节点中的定时任务发现主节点信息，建立和主节点的socket连接
3. 从节点发送Ping信号，主节点返回Pong，两边能互相通信
4. 连接建立后，主节点将所有数据发送给从节点（数据同步）
5. 主节点把当前的数据同步给从节点后，便完成了复制的建立过程。接下来，主节点就会持续的把写命令发送给从节点，保证主从数据一致性。

redis2.8之前使用sync[runId][offset]同步命令，redis2.8之后使用psync[runId][offset]命令。两者不同在于，sync命令仅支持全量复制过程，psync支持全量和部分复制。

介绍同步之前，先介绍几个概念：

- runId：每个redis节点启动都会生成唯一的uuid，每次redis重启后，runId都会发生变化。
- offset：主节点和从节点都各自维护自己的主从复制偏移量offset，当主节点有写入命令时，offset=offset+命令的字节长度。从节点在收到主节点发送的命令后，也会增加自己的offset，并把自己的offset发送给主节点。这样，主节点同时保存自己的offset和从节点的offset，通过对比offset来判断主从节点数据是否一致。
- repl_backlog_size：保存在主节点上的一个固定长度的先进先出队列，默认大小是1MB。

​		主节点发送数据给从节点过程中，主节点还会进行一些写操作，这时候的数据存储在复制缓冲区中。从节点同步主节点数据完成后，主节点将缓冲区的数据继续发送给从节点，用于部分复制。
主节点响应写命令时，不但会把命名发送给从节点，还会写入复制积压缓冲区，用于复制命令丢失的数据补救。

**全量复制过程：**

1. 从节点发送psync ? -1命令（因为第一次发送，不知道主节点的runId，所以为?，因为是第一次复制，所以offset=-1）。
2. 主节点发现从节点是第一次复制，返回FULLRESYNC {runId} {offset}，runId是主节点的runId，offset是主节点目前的offset。
3. 从节点接收主节点信息后，保存到info中。
4. 主节点在发送FULLRESYNC后，启动bgsave命令，生成RDB文件（数据持久化）。
5. 主节点发送RDB文件给从节点。到从节点加载数据完成这段期间主节点的写命令放入缓冲区。
6. 从节点清理自己的数据库数据。
7. 从节点加载RDB文件，将数据保存到自己的数据库中。
8. 如果从节点开启了AOF，从节点会异步重写AOF文件。

**部分复制：**

1. 部分复制主要是Redis针对全量复制的过高开销做出的一种优化措施，使用psync[runId][offset]命令实现。当从节点正在复制主节点时，如果出现网络闪断或者命令丢失等异常情况时，从节点会向主节点要求补发丢失的命令数据，主节点的复制积压缓冲区将这部分数据直接发送给从节点，这样就可以保持主从节点复制的一致性。补发的这部分数据一般远远小于全量数据。
2. 主从连接中断期间主节点依然响应命令，但因复制连接中断命令无法发送给从节点，不过主节点内的复制积压缓冲区依然可以保存最近一段时间的写命令数据。
   当主从连接恢复后，由于从节点之前保存了自身已复制的偏移量和主节点的运行ID。因此会把它们当做psync参数发送给主节点，要求进行部分复制。
3. 主节点接收到psync命令后首先核对参数runId是否与自身一致，如果一致，说明之前复制的是当前主节点；之后根据参数offset在复制积压缓冲区中查找，如果offset之后的数据存在，则对从节点发送+COUTINUE命令，表示可以进行部分复制。因为缓冲区大小固定，若发生缓冲溢出，则进行全量复制。
4. 主节点根据偏移量把复制积压缓冲区里的数据发送给从节点，保证主从复制进入正常状态。



#### 9.哨兵

主从复制会存在以下问题：

1. 一旦主节点宕机，从节点晋升为主节点，同时需要修改应用方的主节点地址，还需要命令所有从节点去复制新的主节点，整个过程需要人工干预。
2. 主节点的写能力受到单机的限制。
3. 主节点的存储能力受到单机的限制。
4. 原生复制的弊端在早期的版本中也会比较突出，比如：redis复制中断后，从节点会发起psync。此时如果同步不成功，则会进行全量同步，主库执行全量备份的同时，可能会造成毫秒或秒级的卡顿。

![](.\images\redis_03.png)

​		如图，是Redis Sentinel（哨兵）的架构图。Redis Sentinel（哨兵）主要功能包括主节点存活检测、主从运行情况检测、自动故障转移、主从切换。Redis Sentinel最小配置是一主一从。Redis的Sentinel系统可以用来管理多个Redis服务器，该系统可以执行以下四个任务：

1. 监控：不断检查主服务器和从服务器是否正常运行。
2. 通知：当被监控的某个redis服务器出现问题，Sentinel通过API脚本向管理员或者其他应用程序发出通知。
3. 自动故障转移：当主节点不能正常工作时，Sentinel会开始一次自动的故障转移操作，它会将与失效主节点是主从关系的其中一个从节点升级为新的主节点，并且将其他的从节点指向新的主节点，这样人工干预就可以免了。
4. 配置提供者：在Redis Sentinel模式下，客户端应用在初始化时连接的是Sentinel节点集合，从中获取主节点的信息。

**工作原理：**

1. 每个Sentinel节点都需要定期执行以下任务：每个Sentinel以每秒一次的频率，向它所知的主服务器、从服务器以及其他的Sentinel实例发送一个PING命令。
2. 如果一个实例距离最后一次有效回复PING命令的时间超过down-after-milliseconds所指定的值，那么这个实例会被Sentinel标记为主观下线。
3. 如果一个主服务器被标记为主观下线，那么正在监视这个服务器的所有Sentinel节点，要以每秒一次的频率确认主服务器的确进入了主观下线状态。
4. 如果一个主服务器被标记为主观下线，并且有足够数量的Sentinel（至少要达到配置文件指定的数量）在指定的时间范围内同意这一判断，那么这个主服务器被标记为客观下线。
5. 一般情况下，每个Sentinel会以每10秒一次的频率向它已知的所有主服务器和从服务器发送INFO命令，当一个主服务器被标记为客观下线时，Sentinel向下线主服务器的所有从服务器发送INFO命令的频率，会从10秒一次改为每秒一次。
6. Sentinel和其他Sentinel协商客观下线的主节点的状态，如果处于SDOWN状态，则投票自动选出新的主节点，将剩余从节点指向新的主节点进行数据复制。
7. 当没有足够数量的Sentinel同意主服务器下线时，主服务器的客观下线状态就会被移除。当主服务器重新向Sentinel的PING命令返回有效回复时，主服务器的主观下线状态就会被移除。



## 二、常用命令

版本信息查询

redis_version:3.0.5

```bash
# 详情
INFO

# 版本详细信息
INFO server

# 是否集群
INFO cluster

# 查看当前节点槽范围（主节点标记为myself，从节点标记为slave）
CLUSTER NODES
```



### 1 String（字符串类型）

**特点**

- 最基本的数据类型
- 二进制安全，可以存储任何数据

- 最大容量 512MB

**常用命令**

```BASH
# 设置值
# SET key value [EX seconds|PX milliseconds|NX|XX]
SET name "John" EX 3600  # 设置值并设置过期时间(秒)
SET score 100 NX         # 仅当键不存在时设置
SET key "mk" EX 60 NX	# 在当前key不存在时，可设置值的过期时间60s；若key存在，则返回null

# 获取值
GET name                 # 返回 "John"

# 批量操作（在集群中要指定哈希槽）
MSET key1 value1 key2 value2
MGET key1 key2

# 数字操作
INCR score              # 值增加1 -> 101
INCRBY score 10         # 值增加10 -> 111
DECR score              # 值减少1 -> 110
DECRBY score 5          # 值减少5 -> 105

# 位操作
SETBIT bitmap 7 1       # 设置第7位为1
GETBIT bitmap 7         # 获取第7位的值
BITCOUNT bitmap         # 统计值为1的位数

# 其他
STRLEN key              # 获取值的长度
APPEND key "追加内容"    # 追加内容
GETRANGE key 0 4        # 获取子字符串
```

**应用场景**

- 缓存数据



### 2 Hash（哈希类型）

**特点**

- 字段值对的集合
- 适合存储对象

- 每个哈希可存储 2³² - 1 个字段值对

**常用命令**

```bash
# 设置字段值
HSET user:1000 name "John" age 30 email "john@example.com"
HSETNX user:1000 gender "male"  # 仅当字段不存在时设置

# 获取字段值
HGET user:1000 name            # 返回 "John"
HMGET user:1000 name age       # 批量获取多个字段

# 获取所有字段值
HGETALL user:1000              # 返回所有字段和值

# 字段操作
HEXISTS user:1000 name         # 检查字段是否存在 -> 1(存在)
HDEL user:1000 email           # 删除字段
HKEYS user:1000                # 获取所有字段名
HVALS user:1000                # 获取所有字段值
HLEN user:1000                 # 获取字段数量

# 数字操作
HINCRBY user:1000 age 1        # 年龄增加1 -> 31
HINCRBYFLOAT user:1000 score 1.5 # 浮点数增加
```

**应用场景**

- 存储对象数据（用户信息、商品信息）



### 3 List（列表类型）

**特点**

- 字符串元素的有序集合
- 按插入顺序排序

- 可从两端添加或删除元素

**常用命令**

```bash
# 添加元素
LPUSH queue task1              # 左侧添加 -> [task1]
RPUSH queue task2              # 右侧添加 -> [task1, task2]
LPUSH queue task0              # 左侧添加 -> [task0, task1, task2]
LPUSH queue mk0 mk1 mk2 		# 左添加多个string
RPUSH queue n0 n1 n2			# 右添加多个string

# 获取元素
LRANGE queue 0 -1              # 获取所有元素 -> [task0, task1, task2]
LINDEX queue 1                 # 获取索引1的元素 -> "task1"

# 弹出元素
LPOP queue                     # 左侧弹出 -> "task0"
RPOP queue                     # 右侧弹出 -> "task2"

# 阻塞操作
BLPOP queue 30                 # 左侧阻塞弹出，最多等待30秒
BRPOP queue 30                 # 右侧阻塞弹出

# 其他操作
LLEN queue                     # 获取列表长度
LTRIM queue 0 1               # 修剪列表，只保留前两个元素
LINSERT queue BEFORE "task1" "new_task" # 在指定元素前插入
LSET queue 0 "first_task"     # 设置指定索引的值
```

**应用场景**

- 消息队列
- 最新消息列表



### 4 Set（集合类型）

**特点**

- 字符串的无序集合
- 元素唯一，不允许重复

- 支持交集、并集、差集等操作

**常用命令**

```bash
# 添加元素
SADD tags "redis" "database" "nosql"
SADD tags "cache"             # 添加新元素

# 获取元素
SMEMBERS tags                 # 获取所有元素
SISMEMBER tags "redis"        # 检查元素是否存在 -> 1(存在)

# 单机Redis的集合运算
SADD tags1 "a" "b" "c"
SADD tags2 "b" "c" "d"
SINTER tags1 tags2            # 交集 -> ["b", "c"]
SUNION tags1 tags2            # 并集 -> ["a", "b", "c", "d"]
SDIFF tags1 tags2            # 差集(tags1有tags2没有) -> ["a"]

# 单机Redis其他操作
SCARD tags                   # 获取元素数量
SPOP tags                    # 随机弹出元素
SRANDMEMBER tags             # 随机获取但不弹出元素
SREM tags "nosql"            # 删除指定元素



# 集群Redis的集合运算（使用 {}强制让相关 key 分配到同一个槽位）
SADD {mk}tags1 "a" "b" "c"
SADD {mk}tags2 "b" "c" "d"
SINTER {mk}tags1 {mk}tags2
SUNION {mk}tags1 {mk}tags2
SDIFF {mk}tags1 {mk}tags2
SDIFF {mk}tags2 {mk}tags1

# 集群Redis其他操作
SCARD {mk}tags1                   # 获取元素数量
SPOP {mk}tags1                    # 随机弹出元素
SRANDMEMBER {mk}tags1             # 随机获取但不弹出元素
SREM tags "nosql"            # 删除指定元素

```

**应用场景**

- 标签系统



### 5 Sorted Set（有序集合）

**特点**

- 类似Set，但每个元素关联一个分数(score)
- 元素按分数排序

- 元素唯一，但分数可以重复

**常用命令**

```bash
# 添加元素
ZADD mk 100 "p1" 200 "p2" 150 "p3" 250 "p4"

# 获取排名
ZRANGE mk 0 -1 WITHSCORES  # 按分数升序获取所有
ZREVRANGE mk 0 2 WITHSCORES # 按分数降序获取前三名

# 范围查询
ZRANGEBYSCORE mk 100 200    # 获取分数在100-200之间的元素
ZREVRANGEBYSCORE mk 200 100 # 反向范围查询

# 排名操作
ZRANK mk "p1"          # 获取升序排名(从0开始)
ZREVRANK mk "p1"       # 获取降序排名

# 分数操作
ZINCRBY mk 20 "p1"     # 增加玩家分数 -> 150
ZSCORE mk "p1"        # 获取玩家分数 -> 150

# 统计操作
ZCARD mk                    # 获取元素数量
ZCOUNT mk 100 200          # 统计分数区间内的元素数量
ZREM mk "p2"          # 删除元素
```

**应用场景**

- 排行榜系统



### 6 其他常用命令

6.1 keys命令

```bash
# 查询所有 key（危险！）
# KEYS *

# 按模式查询
KEYS mk*      # 查询所有以 mk 开头的 key
KEYS *mk*   # 查询包含 mk 的 key
```

**严重警告**：

- 在生产环境**绝对不要使用** `KEYS *`
- 会阻塞 Redis 服务器，导致服务不可用

可使用SCAN命令代替

```bash
# 单机Redis
# 安全遍历所有 key
SCAN 0 MATCH * COUNT 100
SCAN 0 MATCH mk* COUNT 100
```

