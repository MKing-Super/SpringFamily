# Java基础

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

![](E:/MK/SpringFamily/Interview/md/images/a_17.png)

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

![](E:/MK/SpringFamily/Interview/md/images/a_18_1.png)

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

![](E:/MK/SpringFamily/Interview/md/images/a_18_2.png)

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