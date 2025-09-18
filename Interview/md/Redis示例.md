# Redis示例

## 一、常用命令

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

