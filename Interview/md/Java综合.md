# Java综合

## 一、Java基础

### 1、怎样理解OOP面向对象

面向对象是利于语言对现实事物进行抽象。面向对象具有以下特征：

1. 继承**：**继承是从已有类得到继承信息创建新类的过程
2. 封装：封装是把数据和操作数据的方法绑定起来，对数据的访问只能通过已定义的接口
3. 多态性：多态性是指允许不同子类型的对象对同一消息作出不同的响应

> ### 多态的优点
>
> 1. **代码可扩展性**：可以轻松添加新的子类而不需要修改现有代码
> 2. **代码可维护性**：减少重复代码，提高代码复用
> 3. **接口统一**：可以使用父类/接口类型处理不同的子类对象
> 4. **灵活性**：运行时决定调用哪个方法，使程序更加灵活



### 2、重载与重写区别

1. 重载发生在本类，重写发生在父类与子类之间
2. 重载的方法名必须相同，重写的方法名、参数列表和返回类型必须完全相同
3. 重载的参数列表不同（参数类型、个数或顺序），重写的参数列表必须相同
4. 重写：重写的访问权限不能比父类中被重写的方法的访问权限更低
5. 重写：构造方法不能被重写
6. 重写：不能重写final、private和static方法
7. 重写：子类抛出的异常不能比父类更宽泛



### 3、接口与抽象类的区别

1. 抽象类要被子类继承；接口要被类实现
2. 接口可多继承接口；但类只能单继承
3. 抽象类可以有构造方法；接口不能有构造方法
4. 抽象类：除了不能实例化抽象类之外，它和普通Java类没有任何区别
5. 抽象类：抽象方法可以有public、protected和default这些修饰符；接口：只能是public
6. 抽象类：可以有成员变量；接口：只能声明常量(默认是`public static final`)
7. 抽象类：子类必须实现所有抽象方法(除非子类也是抽象类)
8. 接口：一个类可以实现多个接口

> 接口：
>
> 1.所有方法默认是`public abstract`(Java 8之前)
>
> 2.从Java 8开始可以有默认方法(`default`关键字)和静态方法
>
> 3.从Java 9开始可以有私有方法



### 4、深拷贝与浅拷贝的理解 

​		深拷贝和浅拷贝就是指对象的拷贝，一个对象中存在两种类型的属性，一种是基本数据类型，一种是实例对象的引用。

1. 浅拷贝是指，只会拷贝基本数据类型的值，以及实例对象的引用地址，并不会复制一份引用地址所指向的对象，也就是浅拷贝出来的对象，内部的类属性指向的是同一个对象
2. 深拷贝是指，既会拷贝基本数据类型的值，也会针对实例对象的引用地址所指向的对象进行复制，深拷贝出来的对象，内部的类执行指向的不是同一个对象

> **深拷贝实现方式1：重写clone方法**
>
> **深拷贝实现方式2：通过序列化**



### 5、sleep和wait区别

1.sleep方法

- `sleep()`是Thread类的静态方法，使当前线程暂停执行指定的时间。
- 让当前线程进入**TIMED_WAITING**状态
- 释放cpu给其它线程，不释放任何锁资源
- 需要处理InterruptedException
- sleep(1000) 等待超过1s被唤醒

2.wait方法

- `wait()`是Object类的方法，使当前线程等待，直到其他线程调用该对象的`notify()`或`notifyAll()`方法。
- 让当前线程进入**WAITING**或**TIMED_WAITING**状态
- 释放cpu给其它线程，同时释放锁资源
- wait 方法必须配合 synchronized 一起使用，不然在运行时就会抛出IllegalMonitorStateException异常

> ## 锁资源的概念
>
> **锁资源**指的是通过`synchronized`关键字或`Lock`对象获取的线程同步控制权。当一个线程持有锁时，其他试图获取同一锁的线程会被阻塞。

| 方法        | 作用             | 锁行为   | 唤醒范围     | 使用建议                         |
| ----------- | ---------------- | -------- | ------------ | -------------------------------- |
| wait()      | 使线程等待       | 释放锁   | -            | 必须用while循环检查条件          |
| notify()    | 唤醒一个等待线程 | 不释放锁 | 单个线程     | 可能导致信号丢失，不推荐优先使用 |
| notifyAll() | 唤醒所有等待线程 | 不释放锁 | 所有等待线程 | 更安全，推荐优先使用             |

**5.1 wait()为什么必须用while循环检查条件**

​		**虚假唤醒**是指线程在没有收到`notify()`/`notifyAll()`调用的情况下，也可能从`wait()`状态中被唤醒。这是Java语言规范允许的行为，虽然不常见但确实存在。

1. **防止虚假唤醒**：
   - 即使线程被意外唤醒，while循环会再次检查条件
   - 如果条件不满足，线程会继续等待
2. **条件可能再次改变**：
   - 在被唤醒和重新获取锁之间，其他线程可能已经改变了条件
   - while循环能确保条件仍然满足
3. **多消费者场景安全**：
   - 当多个消费者线程被notifyAll()唤醒时
   - 第一个获取锁的线程可能已经消费了资源
   - 后续线程需要重新检查条件

> if可能出现的问题：
>
> 生产者：p1\p2\p3
>
> 消费者：c1\c2\c3
>
> buffer缓冲区容量为1，共6条线程。
>
> p1生产0
>
> p2判断buffer已满，进入wait()等待
>
> p3判断buffer已满，进入wait()等待
>
> c2判断buffer已满，消费0，notifyAll()唤醒所有线程
>
> p2被唤醒，从wait()出继续执行，生产1
>
> p2被唤醒，从wait()出继续执行（由于用if判断，没有获取buffer最新容量），生产2，此时buffer溢出异常



### 6、什么是自动拆装箱 int和Integer有什么区别

基本数据类型，如int,float,double,boolean,char,byte,不具备对象的特征，不能调用方法。

1. 装箱：将基本类型转换成包装类对象
2. 拆箱：将包装类对象转换成基本类型的值



**java为什么要引入自动装箱和拆箱的功能？**

主要是用于java集合中，List<Integer> list=new ArrayList<Integer>();

list集合如果要放整数的话，只能放对象，不能放基本类型，因此需要将整数自动装箱成对象。

实现原理：javac编译器的语法糖，底层是通过Integer.valueOf()和Integer.intValue()方法实现。

区别：

1. Integer是int的包装类，int则是java的一种基本数据类型
2. Integer变量必须实例化后才能使用，而int变量不需要
3. Integer实际是对象的引用，当new一个Integer时，实际上是生成一个指针指向此对象；而int则是直接存储数据值
4. Integer的默认值是null，int的默认值是0



### 7、==和equals区别

1.==

如果比较的是基本数据类型，那么比较的是变量的值

如果比较的是引用数据类型，那么比较的是地址值（两个对象是否指向同一块内存）

2.equals

如果没重写equals方法比较的是两个对象的地址值

如果重写了equals方法后我们往往比较的是对象中的属性的内容

equals方法是从Object类中继承的，默认的实现就是使用==

> `Object` 的 `equals` 默认是 `==`（比较内存地址）。
>
> `String` 重写了 `equals`，使其比较字符串内容而非内存地址。



### 8、String能被继承吗 为什么用final修饰

1. 不能被继承，因为String类有final修饰符，而final修饰的类是不能被继承的。
2. String 类是最常用的类之一，为了效率，禁止被继承和重写。
3. 为了安全。String 类中有native关键字修饰的调用系统级别的本地方法，调用了操作系统的 API，如果方法可以重写，可能被植入恶意代码，破坏程序。Java 的安全性也体现在这里。
   

### 9、String buffer和String builder区别

1. StringBuffer 与 StringBuilder 中的方法和功能完全是等价的，
2. 只是StringBuffer 中的方法大都采用了 synchronized 关键字进行修饰，因此是线程安全的，而 StringBuilder 没有这个修饰，可以被认为是线程不安全的。
3. 在单线程程序下，StringBuilder效率更快，因为它不需要加锁，不具备多线程安全而StringBuffer则每次都需要判断锁，效率相对更低

**为什么StringBuffer 中的方法大都采用了 synchronized 关键字进行修饰，就认为是线程安全的**

**9.1`synchronized` 的作用**

- `synchronized` 是 Java 提供的一种**同步机制**，可以确保同一时间只有一个线程能访问被修饰的方法或代码块。
- 在 `StringBuffer` 中，几乎所有修改数据的方法（如 `append()`、`insert()`、`delete()` 等）都加了 `synchronized`，保证多线程环境下对内部数据的修改不会出现**竞态条件（Race Condition）**。

**9.2 为什么 `StringBuffer` 需要线程安全**

如果多个线程同时修改同一个 `StringBuffer`（例如拼接字符串），不加锁可能导致：

- **数据不一致**（如部分写入的字符串被覆盖）。
- **内存可见性问题**（一个线程的修改对另一个线程不可见）。

通过 `synchronized`，`StringBuffer` 可以安全地在多线程环境中使用。

**9.3`synchronized` 的代价**

- 虽然 `StringBuffer` 是线程安全的，但 `synchronized` 会带来性能开销：
  1. **锁竞争**：多线程争抢同一把锁时，会导致线程阻塞。
  2. **内存屏障**：`synchronized` 会触发 JVM 的内存同步操作，影响指令重排序。
- 因此，在不需要线程安全的场景下，`StringBuilder` 是更优选择。



### 10、final、finally、finalize

1. final：修饰符（关键字）有三种用法：修饰类、变量和方法。修饰类时，意味着它不能再派生出新的子类，即不能被继承，因此它和abstract是反义词。修饰变量时，该变量使用中不被改变，必须在声明时给定初值，在引用中只能读取不可修改，即为常量。修饰方法时，也同样只能使用，不能在子类中被重写。
2. finally：通常放在try…catch的后面构造最终执行代码块，这就意味着程序无论正常执行还是发生异常，这里的代码只要JVM不关闭都能执行，可以将释放外部资源的代码写在finally块中。
3. finalize：Object类中定义的方法，Java中允许使用finalize() 方法在垃圾收集器将对象从内存中清除出去之前做必要的清理工作。这个方法是由垃圾收集器在销毁对象时调用的，通过重写finalize() 方法可以整理系统资源或者执行其他清理工作。

> `finalize()` 是 `Object` 类中的一个方法，用于在对象被垃圾回收（GC）之前执行一些清理操作（如释放系统资源）。

**10.1 finalize关键问题与注意事项**

1. **执行时机不确定**
   - `finalize()` 由垃圾回收器调用，但 GC 的时间由 JVM 决定，甚至可能永远不会调用（如程序正常退出时）。
2. **性能开销**
   - 重写了 `finalize()` 的对象会被 JVM 特殊处理（放入 `Finalizer` 队列），导致垃圾回收延迟，可能引发内存泄漏。
3. **异常吞没**
   - `finalize()` 中的异常会被忽略，不会传播到调用线程。

**10.2 为什么避免使用 `finalize()`**

- **不可靠**：无法保证及时释放资源。
- **性能差**：增加 GC 负担。
- **复杂度高**：可能导致隐蔽的 bug（如复活对象）。

**10.3 什么是GC**

​		**GC（Garbage Collection，垃圾回收）** 是 Java 等编程语言中自动管理内存的机制，用于**回收不再使用的对象占用的内存**，防止内存泄漏（Memory Leak）并优化内存使用。

**10.4 GC 的核心作用**

- **自动释放内存**：回收程序中不再被引用的对象（即“垃圾”）。
- **避免内存泄漏**：防止无用对象长期占用内存。
- **减少程序员负担**：开发者无需手动释放内存



### 11、Object中有哪些方法

| 方法                                            | 作用                                                         |
| ----------------------------------------------- | ------------------------------------------------------------ |
| protected native Object clone()                 | 创建并返回此对象的一个副本。                                 |
| public boolean equals(Object obj)               | 指示某个其他对象是否与此对象“相等                            |
| protected void finalize()                       | 当垃圾回收器确定不存在对该对象的更多引用时，由对象的垃圾回收器调用此方法。 |
| public final Class<? extends Object> getClass() | 返回一个对象的运行时类。获取运行类的相关信息                 |
| public native int hashCode();                   | 返回该对象的哈希码值。                                       |
| public final native void notify()               | 唤醒在此对象监视器上等待的单个线程。                         |
| public final native void notifyAll();           | 唤醒在此对象监视器上等待的所有线程。                         |
| public String toString()                        | 返回该对象的字符串表示。                                     |
| public final native void wait()                 | 导致当前的线程等待，直到其他线程调用此对象的 notify() 方法或 notifyAll() 方，或者超过指定的时间量。 |

**11.1 getClass() 与 `instanceof` 的区别**

| 方法/操作符 | 作用                           | 示例                           |
| ----------- | ------------------------------ | ------------------------------ |
| getClass()  | 返回对象的运行时类             | obj.getClass() == String.class |
| instanceof  | 检查对象是否属于某个类或其子类 | obj instanceof String          |

> ```java
> Object obj = "Hello";
> System.out.println(obj.getClass() == Object.class); // false
> System.out.println(obj.getClass() == String.class); // true
> System.out.println(obj instanceof Object); // true
> System.out.println(obj instanceof String); // true
> System.out.println(obj instanceof Serializable); // true
> ```
>
> 



### 12、说一下集合体系

**Collection（单列集合）**

```
Collection
├── List（有序可重复）
│   ├── ArrayList（动态数组，查询快，增删慢）
│   └── LinkedList（双向链表，增删快，查询慢）
│
├── Set（无序不重复）
│   ├── HashSet（基于 HashMap，无序，去重。快速去重）
│   │   └── LinkedHashSet（保持插入顺序。有序去重）
│   └── TreeSet（基于 TreeMap，自然排序。需要排序的集合）
│
└── Queue（队列）
    ├── PriorityQueue（优先级队列）
    ├── Deque（双端队列）
    │   ├── ArrayDeque（数组实现）
    │   └── LinkedList（链表实现）
    └── BlockingQueue（阻塞队列，线程安全）
        ├── ArrayBlockingQueue
        ├── LinkedBlockingQueue
        └── PriorityBlockingQueue
```

**Map（键值对集合）**

```
Map
├── HashMap（基于哈希表（数组+链表/红黑树），无序）
│   └── LinkedHashMap（保持插入顺序（链表+哈希表）。有序的Map）
├── TreeMap（基于红黑树，自然排序）
└── ConcurrentMap（线程安全）
    ├── ConcurrentHashMap（高并发优化）
    └── ConcurrentSkipListMap（跳表实现）
```

**12.1 `ArrayList` vs `LinkedList`？**

- `ArrayList` 查询快（随机访问），`LinkedList` 增删快（头尾操作）。

**12.2 HashMap` 的工作原理？**

- 基于哈希表，使用 `hashCode()` 和 `equals()` 确定键的唯一性，冲突时转链表或红黑树。

**12.3 如何保证集合线程安全？**

- 使用 `Collections.synchronizedXXX()` 或并发集合（如 `ConcurrentHashMap`）。

**12.4 `HashSet` 如何去重？**

- 基于 `HashMap`，利用 `equals()` 和 `hashCode()` 判断重复。

**12.5 哈希表的底层结构**

- **数组 + 链表 + 红黑树（JDK 8+）**
  - 默认情况下，每个桶是链表，但当链表长度 ≥ 8 时，转为红黑树（提高查询效率）。
  - 如果红黑树节点数 ≤ 6，退化为链表。

​		在哈希表（如 Java 的 `HashMap`）中，**桶（Bucket）** 是指哈希表中存储数据的基本单元，可以理解为数组中的一个位置（槽位）。每个桶对应一个哈希值或哈希值范围，用于存放哈希冲突的元素。

​		默认情况下，每个桶是一个链表。

```
桶数组: [null, null, ["A"→1 → "B"→2], null, ...]
              ↑
            桶 2（链表结构）
```

​		当同一个桶中的链表长度 ≥ 8 时，链表会转换为红黑树，以提高查询效率（从 `O(n)` 优化到 `O(log n)`）。
**触发条件**：

- 链表长度 ≥ 8 **且** 哈希表总容量 ≥ 64。
- 否则，仅进行扩容（不转树）

**12.6 默认桶数组大小**

如果数据量增长不确定，可以依赖 `HashMap` 的自动扩容机制（但会带来扩容开销）。

```java
// 默认初始容量 16，按需扩容
Map<Integer, String> dynamicMap = new HashMap<>();
```

**12.6 如何合理设置桶数组大小？**

如果知道大概的数据量 `N`，初始化容量可以设为 `N / loadFactor`（默认负载因子 `0.75`）。

```java
int expectedSize = 100_000;
Map<String, Integer> map = new HashMap<>((int) (expectedSize / 0.75f));
```



### 13、ArrarList和LinkedList区别

1. ArrayList是实现了基于动态数组的数据结构，LinkedList基于链表的数据结构。
2. 对于随机访问get和set，ArrayList效率优于LinkedList，因为LinkedList要移动指针。
3. 对于新增和删除操作add和remove，LinkedList比较占优势，因为ArrayList要移动数据。 这一点要看实际情况的。若只对单条数据插入或删除，ArrayList的速度反而优于LinkedList。但若是批量随机的插入删除数据，LinkedList的速度大大优于ArrayList. 因为ArrayList每插入一条数据，要移动插入点及之后的所有数据。
   

### 14、HashMap底层是 数组+链表+红黑树，为什么要用这几类结构 

1. 数组 Node<K,V>[] table ,哈希表，根据对象的key的hash值进行在数组里面是哪个节点
2. 链表的作用是解决hash冲突，将hash值取模之后的对象存在一个链表放在hash值对应的槽位
3. 红黑树 JDK8使用红黑树来替代超过8个节点的链表，主要是查询性能的提升，从原来的O(n)到O(logn),
4. 通过hash碰撞，让HashMap不断产生碰撞，那么相同的key的位置的链表就会不断增长，当对这个Hashmap的相应位置进行查询的时候，就会循环遍历这个超级大的链表，性能就会下降，所以改用红黑树

**14.1 红黑树为什么比链表效率高**

1. 查询效率对比

- **链表查询**：O(n)时间复杂度
  - 最坏情况需要遍历整个链表
  - 平均需要遍历n/2个节点
- **红黑树查询**：O(log n)时间复杂度
  - 利用二叉搜索树特性快速定位
  - 100万个节点只需最多20次比较(log₂10⁶≈20)

2. 插入/删除效率对比

- **链表插入/删除**：O(1)找到位置 + O(n)查找
  - 虽然插入操作本身是O(1)，但需要先O(n)查找位置
- **红黑树插入/删除**：O(log n)
  - 查找位置更快
  - 需要额外O(1)时间维护平衡(旋转操作)



### 15、HashMap和HashTable区别

HashMap 和 HashTable 都是 Java 中基于哈希表的 Map 实现，但它们有几个关键区别：

1. 线程安全性

|                  | HashMap                                                      | HashTable                              |
| ---------------- | ------------------------------------------------------------ | -------------------------------------- |
| **线程安全**     | 非线程安全                                                   | 线程安全（方法使用 synchronized 修饰） |
| **并发替代方案** | 可以使用 `ConcurrentHashMap` 或 `Collections.synchronizedMap()` | 无，本身就是同步的                     |

2. 性能

|          | HashMap                   | HashTable                      |
| -------- | ------------------------- | ------------------------------ |
| **性能** | 更高（无同步开销）        | 较低（同步方法调用有性能损耗） |
| **优化** | JDK8+ 使用链表+红黑树结构 | 仅使用链表结构                 |

3. null 值处理

|           | HashMap          | HashTable                                     |
| --------- | ---------------- | --------------------------------------------- |
| **key**   | 允许一个 null 键 | 不允许 null 键（会抛出 NullPointerException） |
| **value** | 允许多个 null 值 | 不允许 null 值                                |

4. 继承关系

|          | HashMap            | HashTable                   |
| -------- | ------------------ | --------------------------- |
| **父类** | 继承 `AbstractMap` | 继承 `Dictionary`（已过时） |
| **接口** | 实现 `Map` 接口    | 实现 `Map` 接口             |

5. 初始容量和扩容

|                  | HashMap           | HashTable           |
| ---------------- | ----------------- | ------------------- |
| **默认初始容量** | 16                | 11                  |
| **扩容方式**     | 2n（16→32→64...） | 2n+1（11→23→47...） |

6. 迭代器

|              | HashMap                              | HashTable                   |
| ------------ | ------------------------------------ | --------------------------- |
| **迭代器**   | `Iterator` 是快速失败（fail-fast）的 | `Enumerator` 不是快速失败的 |
| **遍历方式** | 可以使用 `keySet()`、`entrySet()` 等 | 也可以使用但效率较低        |

使用建议

1. **单线程环境**：优先使用 `HashMap`（性能更好）
2. **多线程环境**：
   - 需要高并发：使用 `ConcurrentHashMap`
   - 不需要高并发：可以使用 `HashTable` 或 `Collections.synchronizedMap()`
3. **需要 null 值**：只能使用 `HashMap`
4. **遗留系统维护**：可能需要继续使用 `HashTable`

**15.1 hashmap的key允许有多个null值，那对应value取出来是什么**

`HashMap` **只能有一个 `null` key**，后存入的会覆盖之前的。

```java
HashMap<String, Integer> map = new HashMap<>();
map.put(null, 1);      // 存入 null key
map.put(null, 2);      // 覆盖之前的 null key 的 value
map.put("A", null);    // 存入 null value
map.put("B", null);    // 再次存入 null value
```



### 16、线程的创建方式

1. 继承Thread类
2. 实现Runnable接口
3. 使用 Callable 和 Future（可返回结果）
4. 使用线程池（推荐方式）

各方式对比

| 创建方式     | 优点         | 缺点           | 适用场景           |
| ------------ | ------------ | -------------- | ------------------ |
| 继承Thread   | 简单直接     | 无法继承其他类 | 简单任务           |
| 实现Runnable | 可继承其他类 | 无返回值       | 大多数场景         |
| 使用Callable | 可获取返回值 | 使用稍复杂     | 需要返回结果的任务 |
| 线程池       | 资源管理高效 | 需要手动关闭   | 高并发场景         |
| Lambda       | 代码简洁     | 复杂逻辑不适用 | 简单异步任务       |

最佳实践建议

1. **优先使用线程池**（避免频繁创建销毁线程）
2. **实现Runnable优于继承Thread**（更灵活）
3. **需要返回值时使用Callable**
4. **简单任务可使用Lambda表达式**
5. **注意资源释放**（特别是线程池需要shutdown）



### 17、线程的状态转换有什么（生命周期）

![](./images/a_17.png)

1.新建状态(New) ：线程对象被创建后，就进入了新建状态。例如，Thread thread = new Thread()。
2.就绪状态(Runnable): 也被称为“可执行状态”。线程对象被创建后，其它线程调用了该对象的start()方法，从而来启动该线程。例如，thread.start()。处于就绪状态的线程，随时可能被CPU调度执行。
3.运行状态(Running)：线程获取CPU权限进行执行。需要注意的是，线程只能从就绪状态进入到运行状态。
4.阻塞状态(Blocked)：阻塞状态是线程因为某种原因放弃CPU使用权，暂时停止运行。直到线程进入就绪状态，才有机会转到运行状态。阻塞的情况分三种：

​	4.1 等待阻塞 -- 通过调用线程的wait()方法，让线程等待某工作的完成。

​	4.2 同步阻塞 -- 线程在获取synchronized同步锁失败(因为锁被其它线程所占用)，它会进入同步阻塞状态。

​	4.3 其他阻塞 -- 通过调用线程的sleep()或join()或发出了I/O请求时，线程会进入到阻塞状态。当sleep()状态超时、join(）等待线程终止或者超时、或者I/O处理完毕时，线程重新转入就绪状态。

5.死亡状态(Dead)：线程执行完了或者因异常退出了run()方法，该线程结束生命周期。

**17.1 join()方法**

Thread类  public final void join()方法

​		`join()`方法是Java线程生命周期中实现线程同步的重要机制，它允许一个线程等待另一个线程执行完毕。

**17.2  join()与sleep()的区别**

| 特性     | join()                               | sleep()  |
| -------- | ------------------------------------ | -------- |
| 唤醒条件 | 目标线程终止                         | 时间到期 |
| 释放锁   | 会释放(如果是在synchronized块内调用) | 不会释放 |
| 用途     | 线程同步                             | 定时延迟 |

**17.3 yield()方法**

Thread类  public static native void yield()方法

​		`yield()`是Java线程调度中的一个重要方法，它用于提示线程调度器当前线程愿意让出CPU资源。

- **功能**：提示调度器当前线程可以暂停执行，让其他具有相同或更高优先级的线程运行
- **状态影响**：调用线程保持`RUNNABLE`状态（不会进入等待/阻塞状态）
- **非强制**：只是建议，调度器可以忽略这个提示

**17.4  yield()与sleep()的区别**

| 特性     | yield()          | sleep()           |
| -------- | ---------------- | ----------------- |
| 状态变化 | 保持RUNNABLE     | 进入TIMED_WAITING |
| 是否保证 | 不保证暂停       | 保证暂停指定时间  |
| 锁行为   | 不会释放锁       | 不会释放锁        |
| 使用场景 | 提高线程交替执行 | 需要精确延迟      |

**17.5 interrupt()()方法**

Thread类  public void interrupt()方法

​		`interrupt()`是Java线程控制中的一个重要方法，用于向线程发送中断信号。

​		`interrupt()`只是请求线程终止，线程是否真正结束取决于它的实现方式。



### 18、Java中有几种类型的流

操作主体为 计算机，站在计算机的角度思考。

**1.字节流(Byte Streams)**

​		字节流以8位字节(byte)为单位进行数据读写，适合处理二进制数据。

![](images/a_18_1.png)

核心字节流类

| 类                      | 描述                     |
| ----------------------- | ------------------------ |
| `InputStream`           | 所有字节输入流的抽象基类 |
| `OutputStream`          | 所有字节输出流的抽象基类 |
| `FileInputStream`       | 从文件读取字节           |
| `FileOutputStream`      | 向文件写入字节           |
| `ByteArrayInputStream`  | 从字节数组读取           |
| `ByteArrayOutputStream` | 向字节数组写入           |
| `BufferedInputStream`   | 带缓冲的输入流           |
| `BufferedOutputStream`  | 带缓冲的输出流           |
| `DataInputStream`       | 读取基本Java数据类型     |
| `DataOutputStream`      | 写入基本Java数据类型     |
| `ObjectInputStream`     | 对象反序列化             |
| `ObjectOutputStream`    | 对象序列化               |

字节流特点：

- 处理单位：8位字节(byte)
- 适合场景：图片、音频、视频等二进制文件
- 不自动处理字符编码
- 通常比字符流更底层

**2.字符流(Character Streams)**

​		字符流以16位Unicode字符(char)为单位进行数据读写，适合处理文本数据。

![](./images/a_18_2.png)

核心字符流类

| 类                   | 描述                         |
| -------------------- | ---------------------------- |
| `Reader`             | 所有字符输入流的抽象基类     |
| `Writer`             | 所有字符输出流的抽象基类     |
| `FileReader`         | 从文件读取字符(使用默认编码) |
| `FileWriter`         | 向文件写入字符(使用默认编码) |
| `CharArrayReader`    | 从字符数组读取               |
| `CharArrayWriter`    | 向字符数组写入               |
| `BufferedReader`     | 带缓冲的字符输入流           |
| `BufferedWriter`     | 带缓冲的字符输出流           |
| `InputStreamReader`  | 字节流到字符流的桥梁         |
| `OutputStreamWriter` | 字符流到字节流的桥梁         |
| `PrintWriter`        | 格式化的字符输出流           |

字符流特点：

- 处理单位：16位Unicode字符(char)
- 适合场景：文本文件处理
- 自动处理字符编码转换(可指定编码)
- 提供更方便的文本处理方法(如readLine())



**字节流与字符流的区别**

| 特性             | 字节流                           | 字符流                |
| ---------------- | -------------------------------- | --------------------- |
| **基本单位**     | 8位字节(byte)                    | 16位字符(char)        |
| **处理数据类型** | 二进制数据                       | 文本数据              |
| **编码处理**     | 不处理编码                       | 自动处理编码转换      |
| **缓冲机制**     | 需要显式缓冲                     | 通常内置缓冲          |
| **基类**         | InputStream/OutputStream         | Reader/Writer         |
| **典型实现**     | FileInputStream/FileOutputStream | FileReader/FileWriter |
| **性能**         | 较低层次，通常更快               | 更高层次，更方便      |
| **适用场景**     | 图片、音频、视频等               | 文本文件、字符串处理  |

**18.1 什么是I/O操作**

I/O操作指的是：

- **输入(Input)**：将数据从外部设备（如磁盘、键盘、网络等）传输到程序内存中
- **输出(Output)**：将数据从程序内存传输到外部设备（如显示器、磁盘、打印机等）

**18.2 I/O传输方式分类**

| 类型          | 特点                         | 适用场景         | Java实现                    |
| ------------- | ---------------------------- | ---------------- | --------------------------- |
| **同步I/O**   | 操作阻塞当前线程直到完成     | 简单逻辑，单线程 | 基本I/O流                   |
| **异步I/O**   | 操作立即返回，完成后回调     | 高并发，高性能   | NIO.2 (AsynchronousChannel) |
| **阻塞I/O**   | 调用线程被阻塞直到“数据就绪” | 传统I/O模型      | InputStream/OutputStream    |
| **非阻塞I/O** | 立即返回状态，需轮询检查     | 高并发网络编程   | NIO (Selector)              |

**18.3 阻塞I/O中“数据就绪”**

**数据就绪**指的是：

- 对于**输入操作**：外部数据已经到达并可以被程序读取
  - 例如：文件数据已从磁盘加载到内核缓冲区、网络数据包已到达网卡缓冲区
- 对于**输出操作**：输出目标已准备好接收数据
  - 例如：TCP窗口有可用空间、磁盘有足够存储空间

**18.4  什么是Base64**

​		Base64是一种将二进制数据编码为ASCII字符串的方法，常用于在文本协议（如JSON、XML）中传输图片等二进制数据。

**18.5 base64是i/o操作吗**

​		Base64本身不是I/O操作。Base64是一种**数据编码格式**，用于将二进制数据转换为ASCII字符。属于内存中的数据转换操作，不直接涉及输入/输出。

**18.5 Base64图片在Web中的应用**

​		HTML中直接显示Base64图片， 通过JSON传输。

**18.6 Base64转换实例**

编码图片为Base64字符串

```java
public class ImageToBase64 {
    public static void main(String[] args) throws Exception {
        // 读取图片文件为字节数组
        byte[] imageBytes = Files.readAllBytes(Paths.get("photo.jpg"));
        
        // 使用Base64编码器
        String base64String = Base64.getEncoder().encodeToString(imageBytes);
        
        System.out.println("Base64编码结果:");
        System.out.println(base64String);
    }
}
```

解码Base64字符串为图片

```java
public class Base64ToImage {
    public static void main(String[] args) throws Exception {
        // Base64编码字符串
        String base64String = "..." // 你的Base64字符串
        
        // 解码为字节数组
        byte[] imageBytes = Base64.getDecoder().decode(base64String);
        
        // 写入文件
        Files.write(Paths.get("restored.jpg"), imageBytes);
        
        System.out.println("图片已保存");
    }
}
```



### 19、请写出你最常见的5个RuntimeException

1.java.lang.NullPointerException

​		空指针异常；出现原因：调用了未经初始化的对象或者是不存在的对象。

2.java.lang.ClassNotFoundException

​		指定的类找不到；出现原因：类的名称和路径加载错误；通常都是程序试图通过字符串来加载某个类时可能引发异常。

3.java.lang.NumberFormatException

​		字符串转换为数字异常；出现原因：字符型数据中包含非数字型字符。

4.java.lang.IndexOutOfBoundsException

​		数组角标越界异常，常见于操作数组对象时发生。

5.java.lang.IllegalArgumentException

​		方法传递参数错误。

> // 非法月份值
> LocalDate.of(2023, 13, 1);  // 抛出DateTimeException（继承IllegalArgumentException）

6.java.lang.ClassCastException

​		数据类型转换异常。

> //数据强转
>
> Object obj = "Hello";
> Integer num = (Integer) obj;  // ClassCastException: String cannot be cast to Integer



### 20、谈谈你对反射的理解

​		反射(Reflection)是 Java 提供的一种强大机制，允许程序在运行时检查类、接口、字段和方法的信息，并能动态操作这些元素。通过这种能力可以彻底了解自身的情况为下一步的动作做准备。

​		Java的反射机制的实现要借助于4个类：class，Constructor，Field，Method;其中class代表的时类对  象，Constructor－类的构造器对象，Field－类的属性对象，Method－类的方法对象。通过这四个对象我们可以粗略的看到一个类的各个组成部分。

反射的核心类

| 类名          | 用途                             |
| ------------- | -------------------------------- |
| `Class`       | 表示类或接口的元数据             |
| `Field`       | 表示类的字段（成员变量）         |
| `Method`      | 表示类的方法                     |
| `Constructor` | 表示类的构造方法                 |
| `Modifier`    | 提供对修饰符的访问和解析方法     |
| `Array`       | 提供动态创建和访问数组的静态方法 |

**作用**

​		在Java运行时环境中，对于任意一个类，可以知道这个类有哪些属性和方法。对于任意一个对象，可以调用它的任意一个方法。这种动态获取类的信息以及动态调用对象的方法的功能来自于Java 语言的反射（Reflection）机制。

**Java 反射机制提供功能**

​		在运行时判断任意一个对象所属的类。

​		在运行时构造任意一个类的对象。

​		在运行时判断任意一个类所具有的成员变量和方法。

​		在运行时调用任意一个对象的方法。



### 21、什么是 java 序列化，如何实现 java 序列化 

1. 序列化是一种用来处理对象流的机制，所谓对象流也就是将对象的内容进行流化。可以对流化后的对象进行读写操作，也可将流化后的对象传输于网络之间。序列化是为了解决在对对象流进行读写操作时所引发的问题。
2. 序 列 化 的 实 现 ： 将 需 要 被 序 列 化 的 类 实 现 Serializable 接 口 ， 该 接 口 没 有 需 要 实 现 的 方 法 ， implements Serializable 只是为了标注该对象是可被序列化的，然后使用一个输出流(如：FileOutputStream)来构造一个ObjectOutputStream(对象流)对象，接着，使用 ObjectOutputStream 对象的 writeObject(Object obj)方法就可以将参数为 obj 的对象写出(即保存其状态)，要恢复的话则用输入流。



### 22、Http 常见的状态码

200 	OK      //客户端请求成功

301      Permanently Moved （永久移除)，请求的 URL 已移走。Response 中应该包含一个 Location URL, 说明资源现在所处的位置

302      Temporarily Moved  临时重定向

400      Bad Request //客户端请求有语法错误，不能被服务器所理解

401      Unauthorized //请求未经授权，这个状态代码必须和 WWW-Authenticate 报头域一起使用

403      Forbidden //服务器收到请求，但是拒绝提供服务

404      Not Found //请求资源不存在，eg：输入了错误的 URL

500      Internal Server Error //服务器发生不可预期的错误

502		Bad Gateway	//网关错误，反向代理服务器无法从上游获取响应

503      Server Unavailable //服务器当前不能处理客户端的请求，一段时间后可能恢复正常

### 23、GET 和POST 的区别

1. GET 请求的数据会附在URL 之后（就是把数据放置在 HTTP 协议头中），以?分割URL 和传输数据，参数之间以&相连，如：login.action?name=zhagnsan&password=123456。POST 把提交的数据则放置在是 HTTP 包的包体中。
2. GET 方式提交的数据最多只能是 1024 字节，理论上POST 没有限制，可传较大量的数据。其实这样说是错误的，不准确的：“GET 方式提交的数据最多只能是 1024 字节"，因为 GET 是通过 URL 提交数据，那么 GET 可提交的数据量就跟URL 的长度有直接关系了。而实际上，URL 不存在参数上限的问题，HTTP 协议规范没有对 URL 长度进行限制。这个限制是特定的浏览器及服务器对它的限制。IE 对URL 长度的限制是2083 字节(2K+35)。对于其他浏览器，如Netscape、FireFox 等，理论上没有长度限制，其限制取决于操作系统的支持。
3. POST 的安全性要比GET 的安全性高。注意：这里所说的安全性和上面 GET 提到的“安全”不是同个概念。上面“安全”的含义仅仅是不作数据修改，而这里安全的含义是真正的 Security 的含义，比如：通过 GET 提交数据，用户名和密码将明文出现在 URL 上，因为(1)登录页面有可能被浏览器缓存，(2)其他人查看浏览器的历史纪录，那么别人就可以拿到你的账号和密码了，除此之外，使用 GET 提交数据还可能会造成 Cross-site request forgery 攻击。
4. Get 是向服务器发索取数据的一种请求，而 Post 是向服务器提交数据的一种请求，在 FORM（表单）中，Method默认为"GET"，实质上，GET 和 POST 只是发送机制不同，并不是一个取一个发！
   

### 24、Cookie 和Session 的区别

1. Cookie 是 web 服务器发送给浏览器的一块信息，浏览器会在本地一个文件中给每个 web 服务器存储 cookie。以后浏览器再给特定的 web 服务器发送请求时，同时会发送所有为该服务器存储的 cookie
2. Session 是存储在 web 服务器端的一块信息。session 对象存储特定用户会话所需的属性及配置信息。当用户在应用程序的 Web 页之间跳转时，存储在 Session 对象中的变量将不会丢失，而是在整个用户会话中一直存在下去
3. Cookie 和session 的不同点

- 无论客户端做怎样的设置，session 都能够正常工作。当客户端禁用 cookie 时将无法使用 cookie


- 在存储的数据量方面：session 能够存储任意的java 对象，cookie 只能存储 String 类型的对象
  





## 二、Java高级篇

### 1、HashMap底层源码 

​		HashMap的底层结构在jdk1.7中由数组+链表实现，在jdk1.8中由数组+链表+红黑树实现。

​		HashMap基于哈希表的Map接口实现，是以key-value存储形式存在，即主要用来存放键值对。HashMap 的实现不是同步的，这意味着它不是线程安全的。它的key、value都可以为null。此外，HashMap中的映射不是有序的。

​		JDK1.8 之前 HashMap 由 数组+链表 组成的，数组是 HashMap 的主体，链表则是主要为了解决哈希冲突(两个对象调用的hashCode方法计算的哈希码值一致导致计算的数组索引值相同)而存在的（“拉链法”解决冲突）。JDK1.8 以后在解决哈希冲突时有了较大的变化，当链表长度大于阈值（或者红黑树的边界值，默认为 8）并且当前数组的长度大于64时，此时此索引位置上的所有数据改为使用红黑树存储。

补充：

​		将链表转换成红黑树前会判断，即使阈值大于8，但是数组长度小于64，此时并不会将链表变为红黑树。而是选择进行数组扩容。

​		这样做的目的是因为数组比较小，尽量避开红黑树结构，这种情况下变为红黑树结构，反而会降低效率，因为红黑树需要进行左旋，右旋，变色这些操作来保持平衡 。同时数组长度小于64时，搜索时间相对要快些。所以综上所述为了提高性能和减少搜索时间，底层在阈值大于8并且数组长度大于64时，链表才转换为红黑树。具体可以参考 treeifyBin方法。

​		当然虽然增了红黑树作为底层数据结构，结构变得复杂了，但是阈值大于8并且数组长度大于64时，链表转换为红黑树时，效率也变的更高效。


### 2、JVM的分区与作用

​		JVM的运行时数据区（Runtime Data Areas）是Java虚拟机在执行Java程序时会把它所管理的内存划分为若干不同的区域。这些区域各有用途，有着不同的创建和销毁时间。

简易：

![](.\images\b_02-1.png)

**一、核心内存分区与作用**

**1. 程序计数器（Program Counter Register）**

- •**作用**：**当前线程所执行的字节码的行号指示器**。它可以看作是线程的“执行进度条”，告诉线程下一步要执行哪条指令。
- •**特点**： •**线程私有**：每个线程都有自己独立的程序计数器，互不干扰。 •**不会内存溢出（OOM）**：这是唯一一个在Java虚拟机规范中没有规定任何`OutOfMemoryError`情况的区域。 •如果线程正在执行Java方法，计数器记录的是正在执行的虚拟机字节码指令地址；如果正在执行的是本地（Native）方法，则计数器值为空（`undefined`）。

**2. Java虚拟机栈（JVM Stack）**

- •**作用**：描述Java**方法执行的内存模型**。每个方法在执行的同时都会创建一个**栈帧（Stack Frame）**，用于存储**局部变量表**、**操作数栈**、**动态链接**、**方法出口**等信息。方法的调用和完成，对应着栈帧在虚拟机栈中的入栈和出栈过程。
- •**特点**： •**线程私有**，生命周期与线程相同。 •我们通常说的“栈内存”就是指它。 •如果线程请求的栈深度大于虚拟机所允许的深度（例如无限递归），将抛出`StackOverflowError`异常。 •如果栈可以动态扩展（大部分虚拟机都可以），但无法申请到足够内存时，会抛出`OutOfMemoryError`异常。
- •**重要概念：栈帧（Stack Frame）** •**局部变量表**：存放了编译期可知的各种基本数据类型（`boolean`, `byte`, `char`, `short`, `int`, `float`, `long`, `double`）、对象引用（`reference`类型）和返回地址（`returnAddress`类型）。 •**操作数栈**：用于执行字节码指令的工作区，就像CPU的寄存器。

**3. 本地方法栈（Native Method Stack）**

- •**作用**：与虚拟机栈非常相似，区别在于**虚拟机栈为执行Java方法服务，而本地方法栈则为虚拟机使用到的本地（Native）方法服务**（如用C/C++编写的方法）。
- •**特点**：HotSpot虚拟机等很多JVM实现选择将虚拟机栈和本地方法栈合二为一。

**4. 堆（Heap）**

- •**作用**：**存放对象实例和数组**。几乎所有通过`new`关键字创建的对象实例和数组都在这里分配内存。这是JVM管理中最大、最重要的一块区域，是**垃圾收集器（Garbage Collector, GC）管理的主要区域**，因此也被称作“GC堆”。
- •**特点**： •**线程共享**，因此存在线程安全问题。 •在虚拟机启动时创建。 •物理上可以是不连续的内存空间，只要逻辑上是连续的即可。 •如果堆中没有内存完成实例分配，并且堆也无法再扩展时，将抛出`OutOfMemoryError: Java heap space`。
- •**分区（基于分代垃圾回收策略）**： •**新生代（Young Generation）**：新创建的对象首先在这里分配。 •**Eden区**（伊甸园）：对象“诞生”的地方。 •**Survivor区**（幸存者区，通常有两个：S0和S1）：在Minor GC后存活的对象会从Eden区移动到Survivor区。  •**老年代（Old Generation / Tenured Generation）**：在新生代中经历了多次GC后仍然存活的对象（默认为15次），会被晋升到老年代。一些大对象（如很大的数组）也可能直接进入老年代。

**5. 方法区（Method Area）**

- •**作用**：存储已被虚拟机加载的**类型信息**、**常量**、**静态变量**、**即时编译器编译后的代码缓存**等数据。
- •**特点**： •**线程共享**。 •它有一个非常重要的部分叫做**运行时常量池（Runtime Constant Pool）**，用于存放编译期生成的各种字面量（如字符串字面量）和符号引用。
- •**演进**： •在JDK 1.7之前，方法区通常被开发者称为“永久代（PermGen）”，其内存大小受`-XX:MaxPermSize`参数限制，容易导致`OutOfMemoryError: PermGen space`。 •**从JDK 1.8开始，方法区的实现被彻底移除，取而代之的是元空间（Metaspace）**。元空间使用本地内存（Native Memory）而非JVM内存，因此其大小仅受本地内存限制，很大程度上避免了内存溢出问题。大小参数变为`-XX:MaxMetaspaceSize`。

**总结与比喻**

为了帮助你更好地理解，可以做一个简单的比喻：

- •**工厂（JVM）**：整个Java程序。
- •**工人（线程）**：工厂里的每个工人独立工作。
- •**工人的工具台（虚拟机栈）**：每个工人有自己的工具台，上面放着当前正在加工的零件（局部变量）和图纸（操作数栈、方法出口）。工具台是私人的，别人不能动。
- •**工人的指令手册（程序计数器）**：每个工人手里有一个手册，告诉他当前做到哪一步了。
- •**中央仓库（堆）**：所有工人共享的大仓库，生产出来的产品（对象实例）都放在这里。仓库管理員（GC）会定期清理没用的产品。
- •**仓库的设计图纸库（方法区）**：存放所有产品的设计蓝图（类信息）、标准规格（常量）和共享工具（静态变量）。

**总结与联系**

| 内存区域       | 线程共享？ | 作用                           | 异常                    |
| -------------- | ---------- | ------------------------------ | ----------------------- |
| **程序计数器** | **私有**   | 当前线程执行的字节码行号指示器 | **无**                  |
| **JVM栈**      | **私有**   | 存储方法调用的栈帧             | StackOverflowError, OOM |
| **本地方法栈** | **私有**   | 为Native方法服务               | StackOverflowError, OOM |
| **堆**         | **共享**   | **存放所有对象实例和数组**     | **OOM**                 |
| **方法区**     | **共享**   | 存储类信息、常量、静态变量等   | **OOM**                 |

**1 OOM ？**

​		**OOM** 是 **`OutOfMemoryError`** 的缩写。

​		**当 Java 虚拟机（JVM）因为没有足够的可用内存来分配对象，并且垃圾收集器（Garbage Collector, GC）也无法回收出足够的内存时，JVM 就会抛出 `OutOfMemoryError`。**

**2 导致OOM原因 ？**

- **情况一：流量太大，排水不及（内存泄漏/Memory Leak）**
  - 你的代码中存在一些隐蔽的引用，导致一些本该被回收的对象无法被GC回收。这些对象会持续占用内存，就像水池里的水因为某种原因排不出去。
  - 随着程序运行，这些无法释放的对象越积越多，最终撑爆内存。
  - **这是最需要警惕的情况**，因为是代码缺陷导致的。
- **情况二：水池太小，容不下正常流量（内存溢出）**
  - 你的程序本身是健康的，没有内存泄漏。但它要处理的数据量实在太大（例如，一次性从数据库加载百万条记录到内存），而分配给JVM的堆内存（`-Xmx`）又设置得过小。
  - 这种情况下，GC虽然很努力地回收垃圾，但存活的对象确实需要那么多空间，GC也无力回天。
  - 解决方法通常是调整JVM参数，增大堆内存。

### 3、Java中垃圾收集的方法有哪些

采用分区分代回收思想：

1.复制算法  

​	年轻代中使用的是Minor GC，这种GC算法采用的是复制算法(Copying)

​	a) 效率高，缺点：需要内存容量大，比较耗内存

​	b) 使用在占空间比较小、刷新次数多的新生区

2.标记-清除  

​	老年代一般是由标记清除或者是标记清除与标记整理的混合实现

​	a) 效率比较低，会差生碎片。

3.标记-整理  

​	老年代一般是由标记清除或者是标记清除与标记整理的混合实现

​	a) 效率低速度慢，需要移动对象，但不会产生碎片。

### **4、如何判断一个对象是否存活**

1.**引用计数法**

​		所谓引用计数法就是给每一个对象设置一个引用计数器，每当有一个地方引用这个对象时，就将计数器加一，引用失效时，计数器就减一。当一个对象的引用计数器为零时，说明此对象没有被引用，也就是“死对象”,将会被垃圾回收.

​		引用计数法有一个缺陷就是无法解决循环引用问题，也就是说当对象A引用对象B，对象B又引用者对象A，那么此时A,B对象的引用计数器都不为零，也就造成无法完成垃圾回收，所以主流的虚拟机都没有采用这种算法。
2.**引用链法**

​		该算法的基本思路就是通过一些被称为引用链（GC Roots）的对象作为起点，从这些节点开始向下搜索，搜索走过的路径被称为（Reference Chain)，当一个对象到GC Roots没有任何引用链相连时（即从GC Roots节点到该节点不可达），则证明该对象是不可用的。
​		在java中可以作为GC Roots的对象有以下几种：虚拟机栈中引用的对象、方法区类静态属性引用的对象、方法区常量池引用的对象、本地方法栈JNI引用的对象。

### 5、什么情况下会产生StackOverflowError（栈溢出）和OutOfMemoryError（堆溢出）怎么排查

1.引发 StackOverFlowError 的常见原因有以下几种

- 无限递归循环调用（最常见）
- 执行了大量方法，导致线程栈空间耗尽
- 方法内声明了海量的局部变量
- native 代码有栈上分配的逻辑，并且要求的内存还不小，比如 java.net.SocketInputStream.read0 会在栈上要求分配一个 64KB 的缓存（64位 Linux）。

2.引发 OutOfMemoryError的常见原因有以下几种

- 内存中加载的数据量过于庞大，如一次从数据库取出过多数据
- 集合类中有对对象的引用，使用完后未清空，使得JVM不能回收
- 代码中存在死循环或循环产生过多重复的对象实体
- 启动参数内存值设定的过小

### **6、什么是线程池，线程池有哪些（创建）**

​		线程池就是事先将多个线程对象放到一个容器中，当使用的时候就不用 new 线程而是直接去池中拿线程即可，节省了开辟子线程的时间，提高的代码执行效率。

​		在 JDK 的 java.util.concurrent.Executors 中提供了生成多种线程池的静态方法。

```java
ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();

ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(4);

ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(4);

ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
```

​		然后调用他们的 execute 方法即可。

​		这4种线程池底层 全部是ThreadPoolExecutor对象的实现，阿里规范手册中规定线程池采用ThreadPoolExecutor自定义的，实际开发也是。

**1.newCachedThreadPool**

​		创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。这种类型的线程池特点是：

​		工作线程的创建数量几乎没有限制(其实也有限制的,数目为Interger. MAX_VALUE), 这样可灵活的往线程池中添加线程。

​		如果长时间没有往线程池中提交任务，即如果工作线程空闲了指定的时间(默认为1分钟)，则该工作线程将自动终止。终止后，如果你又提交了新的任务，则线程池重新创建一个工作线程。

​		在使用CachedThreadPool时，一定要注意控制任务的数量，否则，由于大量线程同时运行，很有会造成系统瘫痪。

**2.newFixedThreadPool**

​		创建一个指定工作线程数量的线程池。每当提交一个任务就创建一个工作线程，如果工作线程数量达到线程池初始的最大数，则将提交的任务存入到池队列中。FixedThreadPool是一个典型且优秀的线程池，它具有线程池提高程序效率和节省创建线程时所耗的开销的优点。但是，在线程池空闲时，即线程池中没有可运行任务时，它不会释放工作线程，还会占用一定的系统资源。

**3.newSingleThreadExecutor**

​		创建一个单线程化的Executor，即只创建唯一的工作者线程来执行任务，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。如果这个线程异常结束，会有另一个取代它，保证顺序执行。单工作线程最大的特点是可保证顺序地执行各个任务，并且在任意给定的时间不会有多个线程是活动的。

**4.newScheduleThreadPool**

​		创建一个定长的线程池，而且支持定时的以及周期性的任务执行。例如延迟3秒执行。


### **7、为什么要使用线程池** 

​		线程池做的工作主要是控制运行的线程数量，处理过程中将任务放入队列，然后在线程创建后启动这些任务，如果线程数量超过了最 大数量，超出数量的线程排队等候，等其它线程执行完毕，再从队列中取出任务来执行。
​		主要特点:线程复用;控制最大并发数:管理线程。

第一:降低资源消耗。通过重复利用己创建的线程降低线程创建和销毁造成的消耗。

第二:提高响应速度。当任务到达时，任务可以不需要的等到线程创建就能立即执行。

第三:提高线程的可管理性。线程是稀缺资源，如果无限制的创建，不仅会消耗系统资源，还会降低系统的稳定性，使用线程池可以进 行统一的分配，调优和监控

### 8、线程池底层工作原理

1. 第一步：线程池刚创建的时候，里面没有任何线程，等到有任务过来的时候才会创建线程。当然也可以调用 prestartAllCoreThreads() 或者 prestartCoreThread() 方法预创建corePoolSize个线程
2. 第二步：调用execute()提交一个任务时，如果当前的工作线程数<corePoolSize，直接创建新的线程执行这个任务
3. 第三步：如果当时工作线程数量>=corePoolSize，会将任务放入任务队列中缓存
4. 第四步：如果队列已满，并且线程池中工作线程的数量<maximumPoolSize，还是会创建线程执行这个任务
5. 第五步：如果队列已满，并且线程池中的线程已达到maximumPoolSize，这个时候会执行拒绝策略，JAVA线程池默认的策略是AbortPolicy，即抛出RejectedExecutionException异常

### **9、ThreadPoolExecutor对象有哪些参数 怎么设定核心线程数和最大线程数 拒绝策略有哪些** 

```java
public ThreadPoolExecutor(
    int corePoolSize,
    int maximumPoolSize,
    long keepAliveTime,
    TimeUnit unit,
    BlockingQueue<Runnable> workQueue,
    ThreadFactory threadFactory,
    RejectedExecutionHandler handler
)
```

**参数与作用：**共7个参数

1. **corePoolSize：**核心线程数。核心线程会一直存活，哪怕是一直空闲着。
2. **maximumPoolSize：**最大线程数。
3. **keepAliveTime：**存活时间。当非核心空闲超过这个时间将被回收。非核心线程是指线程池中超出核心线程数的那部分线程，它们具有可回收的特性。
4. **unit：**keepAliveTime的单位。
5. **workQueue：**任务队列。常用有三种队列，即SynchronousQueue,LinkedBlockingDeque（无界队列）,ArrayBlockingQueue（有界队列）。
6. **threadFactory：**线程工厂。ThreadFactory是一个接口，用来创建worker。通过线程工厂可以对线程的一些属性进行定制。默认直接新建线程。
7. **RejectedExecutionHandler：**拒绝策略。是一个接口，只有一个方法，当线程池中的资源已经全部使用，添加新线程被拒绝时，会调用RejectedExecutionHandler的rejectedExecution法。默认是抛出一个运行时异常。

**核心线程数配置方案：**

- **CPU密集型任务**（如计算、加密）：
   核心线程数 ≈ CPU核数（避免过多线程竞争CPU）
   示例：`corePoolSize = Runtime.getRuntime().availableProcessors()`
- **I/O密集型任务**（如网络请求、文件读写）：
   核心线程数 ≈ CPU核数 × (1 + 平均等待时间/平均计算时间)
   经验值：`corePoolSize = CPU核数 * 2`

| **任务类型** | **corePoolSize** | **maximumPoolSize** | **队列容量**   |
| ------------ | ---------------- | ------------------- | -------------- |
| CPU密集型    | CPU核数          | CPU核数 + 1         | 较小（如100）  |
| I/O密集型    | CPU核数 × 2      | CPU核数 × (2~5)     | 较大（如1024） |

**拒绝策略：**

1. AbortPolicy：直接抛出异常，默认策略；
2. CallerRunsPolicy：用调用者所在的线程来执行任务；
3. DiscardOldestPolicy：丢弃阻塞队列中靠最前的任务，并执行当前任务；
4. DiscardPolicy：直接丢弃任务；当然也可以根据应用场景实现 RejectedExecutionHandler 接口，自定义饱和策略，如记录日志或持久化存储不能处理的任务。

### **10、常见线程安全的并发容器有哪些**

1. CopyOnWriteArrayList、CopyOnWriteArraySet、ConcurrentHashMap
2. CopyOnWriteArrayList、CopyOnWriteArraySet采用写时复制实现线程安全
3. ConcurrentHashMap采用分段锁的方式实现线程安全

### **11、Atomic原子类了解多少 原理是什么**

​		**Atomic原子类的主要作用是提供了一种无需加锁（如`synchronized`）即可实现线程安全操作的机制。** 它用于在多线程环境下，保证对单个变量（如整数、布尔值、对象引用）的“读-改-写”操作是原子性的、线程安全的。

```
举例：
public class Counter {
    private int count = 0;
    
    public void increment() {
        count++; // 这行代码不是线程安全的！
    }
    
    public int getCount() {
        return count;
    }
}

count++ 这个操作看起来是一步，但实际上包含了三个步骤：
    1.读取当前 count 的值。
    2.将这个值加 1。
    3.将新值写回 count。
在多线程环境下，如果两个线程同时执行 increment()，可能会发生 竞态条件（Race Condition）：
    1.线程 A 读取 count 为 0。
    2.线程 B 也读取 count 为 0。
    3.线程 A 将 0+1 的结果 1 写入 count。
    4.线程 B 也将 0+1 的结果 1 写入 count。
最终结果应该是 2，但因为操作不是原子的，结果却是 1。
传统的解决方案是使用 synchronized 关键字对方法或代码块加锁，但这会带来性能开销（线程阻塞、上下文切换）。
```

​		**Atomic原子类使用了一种更高效的方式：CAS（Compare-And-Swap）操作，避免了加锁带来的性能损耗。**

**核心原理：CAS (Compare-And-Swap)**

CAS 是一种乐观锁机制，它包含三个操作数：

1. 1.**V**：需要读写的内存位置（变量的当前值）
2. 2.**A**：进行比较的预期原值
3. 3.**B**：希望写入的新值

**CAS 的操作逻辑是：“我认为位置 V 的值应该是 A，如果是，那么将 B 放到 V 位置。否则，不要修改它，并告诉我现在的值是多少。”**

这个过程是**硬件级别（CPU 指令）** 保证的原子性，效率非常高。

1.基本类型

使用原子的方式更新基本类型

- AtomicInteger：整型原子类
- AtomicLong：长整型原子类
- AtomicBoolean：布尔型原子类

2.数组类型

使用原子的方式更新数组里的某个元素

- AtomicIntegerArray：整形数组原子类
- AtomicLongArray：长整形数组原子类
- AtomicReferenceArray：引用类型数组原子类

3.引用类型

- AtomicReference：引用类型原子类
- AtomicStampedReference：原子更新引用类型里的字段原子类
- AtomicMarkableReference ：原子更新带有标记位的引用类型
- AtomicIntegerFieldUpdater：原子更新整形字段的更新器
- AtomicLongFieldUpdater：原子更新长整形字段的更新器
- AtomicStampedReference：原子更新带有版本号的引用类型。该类将整数值与引用关联起来，可用于解决原子的更新数据和数据的版本号，以及解决使用 CAS 进行原子更新时可能出现的 ABA 问题

**优缺点总结**

| **优点**                                                     | **缺点**                                                     |
| :----------------------------------------------------------- | :----------------------------------------------------------- |
| **高性能**：基于CAS自旋，避免了线程挂起和切换的开销，在低竞争环境下优势明显。 | **ABA问题**：一个值从A变成B，又变回A，CAS会误以为它没变。可用`AtomicStampedReference`解决。 |
| **使用简单**：提供了丰富的API，如`incrementAndGet`，语义清晰。 | **循环开销**：在高竞争环境下，线程可能长时间自旋，反复尝试CAS，消耗CPU。 |
| **避免死锁**：由于是无锁操作，从根本上避免了死锁问题。       | **只能保证一个变量的原子性**：如果需要保证多个变量共同操作的原子性，仍需使用锁或`AtomicReference`封装。 |

**适用场景**

1. 1.**计数器**：如网站访问次数、订单数量统计等。
2. 2.**状态标志**：用一个原子布尔值控制某个流程的开关。`AtomicBoolean stop = new AtomicBoolean(false);`
3. 3.**非阻塞算法**：实现高效的无锁队列（如`ConcurrentLinkedQueue`）、无锁栈等复杂数据结构。
4. 4.**累积器**：收集统计数据，但不需要非常精确的实时读值。

**简单来说，当你需要对一个单一的共享变量进行高效的、线程安全的操作时，Atomic原子类是你的首选工具。**

举例：

```
===== ABA问题演示 =====
线程1: CAS(0->1) 成功
线程2: 第一次读取值 = 1
线程1: CAS(1->0) 成功
线程2: CAS(0->2) 成功
最终值: 2
结果：虽然值回到了0，但中间经历了变化（ABA问题）

===== 使用AtomicStampedReference解决ABA问题 =====
线程1: CAS(0->1) 成功
线程2: 第一次读取值 = 1, 版本号 = 1
线程1: CAS(1->0) 成功
线程2: CAS(0->2) 失败
最终值: 0, 版本号: 2
结果：版本号变化检测到了中间状态，避免了ABA问题
```

**应用场景**

1. **数据库事务系统**：在乐观锁机制中，版本号或时间戳用于检测ABA问题

2. **内存管理系统**：对象分配和回收可能重用内存地址，导致ABA问题

3. **实时交易系统**：金融交易需要确保中间状态不被忽略



### **12、synchronized底层实现是什么 lock底层是什么 有什么区别**



### **13、了解ConcurrentHashMap吗 为什么性能比HashTable高，说下原理**

​		ConcurrentHashMap是线程安全的Map容器，JDK8之前，ConcurrentHashMap使用锁分段技术，将数据分成一段段存储，每个数据段配置一把锁，即segment类，这个类继承ReentrantLock来保证线程安全，JKD8的版本取消Segment这个分段锁数据结构，底层也是使用Node数组+链表+红黑树，从而实现对每一段数据就行加锁，也减少了并发冲突的概率。

​		hashtable类基本上所有的方法都是采用synchronized进行线程安全控制，高并发情况下效率就降低 ，ConcurrentHashMap是采用了分段锁的思想提高性能，锁粒度更细化


### **14、ConcurrentHashMap底层原理**



### **15、了解volatile关键字**

1. volatile是Java提供的最轻量级的同步机制，保证了共享变量的可见性，被volatile关键字修饰的变量，如果值发生了变化，其他线程立刻可见，避免出现脏读现象。
2. volatile禁止了指令重排，可以保证程序执行的有序性，但是由于禁止了指令重排，所以JVM相关的优化没了，效率会偏弱

### **16、synchronized和volatile有什么区别**

1. volatile本质是告诉JVM当前变量在寄存器中的值是不确定的，需要从主存中读取，synchronized则是锁定当前变量，只有当前线程可以访问该变量，其他线程被阻塞住。
2. volatile仅能用在变量级别，而synchronized可以使用在变量、方法、类级别。
3. volatile仅能实现变量的修改可见性，不能保证原子性；而synchronized则可以保证变量的修改可见性和原子性。
4. volatile不会造成线程阻塞，synchronized可能会造成线程阻塞。
5. volatile标记的变量不会被编译器优化，synchronized标记的变量可以被编译器优化。

### **17、Java类加载过程** 

​		Java 类加载过程可以分为三个主要阶段：**加载（Loading）**、**链接（Linking）** 和 **初始化（Initialization）**。链接阶段又可细分为验证、准备和解析三个子阶段。

**1. 加载（Loading）**

加载阶段主要完成三件事情：

1. 1.通过类的全限定名获取定义此类的二进制字节流
2. 2.将这个字节流所代表的静态存储结构转换为方法区的运行时数据结构
3. 3.在内存中生成该类的Class对象，作为该类的数据访问入口。

**2. 链接（Linking）**

**2.1 验证（Verification）**

​		确保Class文件的字节流包含的信息符合当前虚拟机要求，不会危害虚拟机自身安全。

- 文件格式验证：验证字节流是否符合Class文件的规范，如主次版本号是否在当前虚拟机范围内，常量池中的常量是否有不被支持的类型.
- 元数据验证:对字节码描述的信息进行语义分析，如这个类是否有父类，是否集成了不被继承的类等。
- 字节码验证：是整个验证过程中最复杂的一个阶段，通过验证数据流和控制流的分析，确定程序语义是否正确，主要针对方法体的验证。如：方法中的类型转换是否正确，跳转指令是否正确等。
- 符号引用验证：这个动作在后面的解析过程中发生，主要是为了确保解析动作能正确执行。

**2.2 准备（Preparation）**

​		为类变量（静态变量）分配内存并设置初始值。准备阶段是为类的静态变量分配内存并将其初始化为默认值，这些内存都将在方法区中进行分配。准备阶段不分配类中的实例变量的内存，实例变量将会在对象实例化时随着对象一起分配在Java堆中。

**2.3 解析（Resolution）**

​		将常量池内的符号引用替换为直接引用。该阶段主要完成符号引用到直接引用的转换动作。解析动作并不一定在初始化动作完成之前，也有可能在初始化之后。

**3. 初始化（Initialization）**

​		执行类构造器 `<clinit>()` 方法的过程，该方法由编译器自动收集类中的所有**类变量的赋值动作**和**静态语句块**中的语句合并产生。

​		初始化时类加载的最后一步，前面的类加载过程，除了在加载阶段用户应用程序可以通过自定义类加载器参与之外，其余动作完全由虚拟机主导和控制。到了初始化阶段，才真正开始执行类中定义的Java程序代码。



### **18、什么是类加载器，类加载器有哪些**

​		类加载器就是把类文件加载到虚拟机中，也就是说通过一个类的全限定名来获取描述该类的二进制字节流。

**主要有以下四种类加载器**

- 启动类加载器(Bootstrap ClassLoader)用来加载java核心类库，无法被java程序直接引用
- 扩展类加载器(extension class loader):它用来加载 Java 的扩展库。Java 虚拟机的实现会提供一个扩展库目录。该类加载器在此目录里面查找并加载 Java 类
- 系统类加载器（system class loader）也叫应用类加载器：它根据 Java 应用的类路径（CLASSPATH）来加载 Java 类。一般来说，Java 应用的类都是由它来完成加载的。可以通过 ClassLoader.getSystemClassLoader()来获取它
- 用户自定义类加载器，通过继承 java.lang.ClassLoader类的方式实现



**Java 类加载器的实际应用场景**

1. 热部署和热加载
2. 单元测试中的模拟和隔离
3. 动态扩展和插件架构

### **19、简述java内存分配与回收策略以及Minor GC和Major GC（full GC）** 

**栈区：**栈分为java虚拟机栈和本地方法栈

**堆区：**堆被所有线程共享区域，在虚拟机启动时创建，唯一目的存放对象实例。堆区是gc的主要区域，通常情况下分为两个区块年轻代和年老代。更细一点年轻代又分为Eden区，主要放新创建对象，From survivor 和 To survivor 保存gc后幸存下的对象，默认情况下各自占比 8:1:1。

**方法区：**被所有线程共享区域，用于存放已被虚拟机加载的类信息，常量，静态变量等数据。被Java虚拟机描述为堆的一个逻辑部分。习惯是也叫它永久代（permanment generation）

**程序计数器：**当前线程所执行的行号指示器。通过改变计数器的值来确定下一条指令，比如循环，分支，跳转，异常处理，线程恢复等都是依赖计数器来完成。线程私有的。



回收策略以及Minor GC和Major GC
    1.对象优先在堆的Eden区分配
    2.大对象直接进入老年代
    3.长期存活的对象将直接进入老年代

​		当Eden区没有足够的空间进行分配时，虚拟机会执行一次Minor GC.Minor GC通常发生在新生代的Eden区，在这个区的对象生存期短，往往发生GC的频率较高，回收速度比较快;Full Gc/Major GC 发生在老年代，一般情况下，触发老年代GC的时候不会触发Minor GC,但是通过配置，可以在Full GC之前进行一次Minor GC这样可以加快老年代的回收速度。


### **20、如何查看java死锁** 



### **21、Java死锁如何避免**

造成死锁的几个原因

**1. 互斥条件 (Mutual Exclusion)**

- •资源一次只能被一个线程占用
- •示例：`synchronized` 关键字创建独占锁

**2. 请求与保持 (Hold and Wait)**

- •线程持有至少一个资源，同时请求其他线程持有的资源
- •示例：线程A持有锁1，同时请求锁2

**3. 不剥夺条件 (No Preemption)**

- •资源只能由持有线程主动释放，不能被强制剥夺
- •示例：Java 锁不能被其他线程强制释放

**4. 循环等待 (Circular Wait)**

- •存在线程资源请求的环形链
- •示例：线程A等待线程B的资源，线程B等待线程A的资源



​		这是造成死锁必须要达到的4个条件，如果要避免死锁，只需要不满足其中某一个条件即可。而其中前3个条件是作为锁要符合的条件，所以要避免死锁就需要打破第4个条件，不出现循环等待锁的关系。



在开发过程中

1.要注意加锁顺序，保证每个线程按同样的顺序进行加锁

2.要注意加锁时限，可以针对锁设置一个超时时间

3.要注意死锁检查，这是一种预防机制，确保在第一时间发现死锁并进行解决



## 三、Java框架

### 1、简单的谈一下SpringMVC的工作流程

**1. 请求到达 DispatcherServlet**

```xml
// web.xml 配置示例
<servlet>
    <servlet-name>dispatcher</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <init-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>/WEB-INF/spring-mvc.xml</param-value>
    </init-param>
    <load-on-startup>1</load-on-startup>
</servlet>

<servlet-mapping>
    <servlet-name>dispatcher</servlet-name>
    <url-pattern>/</url-pattern>
</servlet-mapping>
```

- •所有匹配 URL 模式的请求都由 `DispatcherServlet` 处理
- •`DispatcherServlet` 读取 Spring MVC 配置文件初始化应用上下文

**2. HandlerMapping 寻找处理器**

```java

// Controller 示例
@Controller
@RequestMapping("/users")
public class UserController {
    
    @GetMapping("/{id}")
    public String getUser(@PathVariable Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "user/profile";
    }
}
```

- •`HandlerMapping` 根据请求 URL 找到对应的 Controller 和方法
- •常用的 `HandlerMapping` 实现： •`RequestMappingHandlerMapping`（用于注解驱动的控制器） •`BeanNameUrlHandlerMapping`（基于 Bean 名称的映射） •`SimpleUrlHandlerMapping`（基于 URL 模式的映射）

**3. HandlerAdapter 执行处理器**

```java

// 自定义 HandlerAdapter 示例（了解原理）
public class CustomHandlerAdapter implements HandlerAdapter {
    
    @Override
    public boolean supports(Object handler) {
        return handler instanceof UserController;
    }
    
    @Override
    public ModelAndView handle(HttpServletRequest request, 
                              HttpServletResponse response, 
                              Object handler) throws Exception {
        // 调用控制器方法并返回 ModelAndView
        UserController controller = (UserController) handler;
        return controller.handleRequest(request, response);
    }
    
    @Override
    public long getLastModified(HttpServletRequest request, Object handler) {
        return -1;
    }
}
```

- •`HandlerAdapter` 负责实际调用控制器方法
- •Spring MVC 提供了多种适配器以支持不同类型的控制器

**4. 控制器处理请求**

```java

@Controller
public class UserController {
    
    @PostMapping("/create")
    public String createUser(@Valid UserForm form, 
                           BindingResult result, 
                           RedirectAttributes attributes) {
        
        if (result.hasErrors()) {
            return "user/create-form";
        }
        
        User user = userService.createUser(form);
        attributes.addFlashAttribute("message", "用户创建成功");
        return "redirect:/users/" + user.getId();
    }
}
```

- •控制器方法接收请求参数，执行业务逻辑
- •可以返回多种类型： •`ModelAndView`：包含模型数据和视图信息 •`String`：视图名称 •`void`：自行处理响应 •`@ResponseBody`：直接返回数据（RESTful API）

**5. 处理返回值**

```java

@Controller
public class ProductController {
    
    @GetMapping("/api/products/{id}")
    @ResponseBody
    public Product getProduct(@PathVariable Long id) {
        return productService.findById(id);
    }
    
    @GetMapping("/products/list")
    public ModelAndView listProducts() {
        List<Product> products = productService.findAll();
        ModelAndView mav = new ModelAndView("product/list");
        mav.addObject("products", products);
        return mav;
    }
}
```

- •对于视图返回，Spring 会创建 `ModelAndView` 对象
- •对于 `@ResponseBody`，使用 `HttpMessageConverter` 转换返回值为 HTTP 响应体

**6. ViewResolver 解析视图**

```java

// Spring 配置示例
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
    
    @Bean
    public BeanNameViewResolver beanNameViewResolver() {
        return new BeanNameViewResolver();
    }
}
```

- •`ViewResolver` 根据视图名称解析为具体的 `View` 对象
- •常用实现： •`InternalResourceViewResolver`：用于 JSP •`ThymeleafViewResolver`：用于 Thymeleaf •`FreeMarkerViewResolver`：用于 FreeMarker •`ContentNegotiatingViewResolver`：根据内容类型选择视图

**7. 视图渲染**

```java

<!-- /WEB-INF/views/user/profile.jsp -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户信息</title>
</head>
<body>
    <h1>用户详情</h1>
    <p>用户名: ${user.username}</p>
    <p>邮箱: ${user.email}</p>
    <a href="/users/${user.id}/edit">编辑</a>
</body>
</html>
```

- •`View` 对象使用模型数据渲染最终响应
- •渲染结果写入 `HttpServletResponse`

**8. 返回响应给客户端**

- •完成所有处理后，响应通过 Servlet 容器返回给客户端
- •包括状态码、头部信息和响应体



Spring MVC 的工作流程可以概括为：

1. 1.**请求分发**：`DispatcherServlet` 接收所有请求
2. 2.**处理器映射**：`HandlerMapping` 找到对应的控制器方法
3. 3.**处理器适配**：`HandlerAdapter` 执行控制器方法
4. 4.**结果处理**：处理返回值，可能涉及模型填充和视图选择
5. 5.**视图解析**：`ViewResolver` 解析视图名称到具体视图
6. 6.**渲染响应**：视图使用模型数据渲染最终响应

### 2、说出Spring或者SpringMVC中常用的5个注解

**1.组件扫描**

```java
@Component // 通用组件注解
@Service   // 标记服务层组件
@Repository // 标记数据访问层组件（DAO）
@Controller // 标记控制器组件（Web层）
```

**2. 依赖注入注解**

```java
@Autowired // 自动装配（按类型）
@Qualifier("beanName") // 指定具体Bean名称
@Resource(name="beanName") // JSR-250规范，按名称注入
@Value("${property.name}") // 注入属性值
```

**3.控制器相关**

```java
@RestController // @Controller + @ResponseBody
@RequestMapping("/users") // 类级别URL映射
@GetMapping("/{id}")      // 方法级别GET映射
@PostMapping             // 方法级别POST映射
```

### 3、简述SpringMVC中如何返回JSON数据 

Step1：在项目中加入json转换的依赖，例如jackson，fastjson，gson等

Step2：在请求处理方法中将返回值改为具体返回的数据的类型， 例如数据的集合类List<Employee>等

Step3：在请求处理方法上使用@ResponseBody注解

### 4、谈谈你对Spring的理解

​		Spring 是一个开源框架，为简化企业级应用开发而生。Spring 是一个 IOC 和 AOP 容器框架。

Spring 容器的主要核心是：

​		控制反转（IOC），传统的 java 开发模式中，当需要一个对象时，我们会自己使用 new 或者 getInstance 等直接或者间接调用构造方法创建一个对象。而在 spring 开发模式中，spring 容器使用了工厂模式为我们创建了所需要的对象，不需要我们自己创建了，直接调用spring 提供的对象就可以了，这是控制反转的思想。

​		依赖注入（DI），spring 使用 javaBean 对象的 set 方法或者带参数的构造方法为我们在创建所需对象时将其属性自动设置所需要的值的过程，就是依赖注入的思想。

​		面向切面编程（AOP），在面向对象编程（oop）思想中，我们将事物纵向抽成一个个的对象。而在面向切面编程中，我们将一个个的对象某些类似的方面横向抽成一个切面，对这个切面进行一些如权限控制、事物管理，记录日志等公用操作处理的过程就是面向切面编程的思想。AOP 底层是动态代理，如果是接口采用 JDK 动态代理，如果是类采用CGLIB 方式实现动态代理。


### 5、Spring中常用的设计模式

**1. 依赖注入（Dependency Injection）模式**

**定义**

​		一种对象之间解耦的设计模式，依赖项由外部容器（如 Spring）提供，而不是由对象自己创建。

**核心特点**

- •控制反转（IoC）：对象不负责依赖项的创建，由容器管理
- •解耦：组件间不直接依赖，通过接口协作
- •可测试：易于模拟依赖进行单元测试



**2. 工厂模式（Factory Pattern）**

**场景**：Bean 创建、复杂对象实例化

**定义**

​		提供创建对象的接口，让子类决定实例化哪个类，将创建逻辑与使用逻辑分离。

**类型**

1. 1.**简单工厂**：一个工厂类创建多种产品
2. 2.**工厂方法**：定义创建对象的接口，由子类实现
3. 3.**抽象工厂**：创建相关对象族



**3. 单例模式（Singleton Pattern）**

**场景**：Bean 默认作用域，资源共享

**定义**

​		确保一个类只有一个实例，并提供全局访问点。

**Spring 实现特点**

- •Bean 默认作用域就是单例
- •通过容器管理单例生命周期
- •支持延迟初始化（@Lazy）



**4. 代理模式（Proxy Pattern）**

**场景**：AOP 编程、事务管理

**定义**

​		为其他对象提供一种代理，以控制对这个对象的访问。

**Spring 实现方式**

1. 1.**JDK 动态代理**：基于接口代理（实现相同接口）
2. 2.**CGLIB 代理**：基于继承代理（生成子类）



**5.模板方法模式（Template Method）**

**定义**

​		定义一个操作中的算法骨架，将某些步骤延迟到子类中实现。

**核心特点**

- •抽象类定义流程
- •具体子类实现特定步骤
- •模板方法确保流程顺序



### 6、Spring循环依赖问题







































