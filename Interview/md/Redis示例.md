# Redis示例

## 一、基本

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

# 批量操作
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

### 应用场景

- 缓存数据





















## 1.统计热门车辆

