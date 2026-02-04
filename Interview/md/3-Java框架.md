# Java框架

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

**1. 什么是循环依赖？**

​		循环依赖是指两个或更多的 Bean 相互之间持有对方的引用，构成一个闭环。

例如：

- •Bean A 的创建需要先注入 Bean B。
- •而 Bean B 的创建又需要先注入 Bean A。
- •这就形成了一个 `A -> B -> A` 的循环。

**2. Spring 如何解决循环依赖？（核心机制）**

​		Spring 容器通过**三级缓存（三级缓存）** 的机制，解决了**部分场景**下的循环依赖问题。这里的“部分场景”主要指**属性注入（Setter/Field 注入）**，而**构造器注入**导致的循环依赖无法解决。

**3.三级缓存是什么？**

​		在 Spring 的 `DefaultSingletonBeanRegistry` 类中，定义了三个非常重要的 Map，俗称三级缓存：

1. 1.singletonObjects（一级缓存）：
   - •类型：`ConcurrentHashMap<String, Object>`
   - •用途：存放**已经完全初始化好的单例 Bean**。从该缓存取出的 Bean 可以直接使用。
   - •口诀：**成熟的Bean**
2. 2.earlySingletonObjects（二级缓存）：
   - •类型：`HashMap<String, Object>`
   - •用途：存放**提前暴露的、早期的 Bean 对象**。这个 Bean 的对象已经实例化（通过反射调用了构造方法），但还没有进行属性填充和初始化（`@PostConstruct`、`InitializingBean` 等方法未执行）。
   - •口诀：**半成品的Bean（已实例化，未填充属性）**
3. 3.singletonFactories（三级缓存）：
   - •类型：`HashMap<String, ObjectFactory<?>>`
   - •用途：存放**创建 Bean 的工厂（ObjectFactory）**。这个工厂可以返回一个早期的 Bean 引用（通过 SmartInstantiationAwareBeanPostProcessor 进行后处理，例如生成 AOP 代理）。
   - •口诀：**能生成半成品Bean的工厂**

**4.循环依赖解决流程（以属性注入为例）**

​		我们以最经典的 `A` 和 `B` 两个 Bean 的循环依赖为例，假设它们都使用 `@Autowired` 进行字段注入。

1. 1.

   **开始创建 A**：

   - •调用 `getBean(A)`，发现 A 不在任何一级缓存中，开始创建流程。
   - •**实例化 A**：通过反射调用 A 的构造方法，在堆内存中创建一个 A 对象（此时它的属性 `b` 还是 `null`）。
   - •**将 A 的工厂对象放入三级缓存**：Spring 将一个能返回这个早期 A 对象（半成品）的 `ObjectFactory` 放入 `singletonFactories`（三级缓存）。

2. 2.

   **填充 A 的属性（依赖注入）**：

   - •Spring 发现 A 依赖了 B，于是调用 `getBean(B)` 去获取 B。

3. 3.

   **开始创建 B**：

   - •调用 `getBean(B)`，发现 B 不在任何一级缓存中，开始创建流程。
   - •**实例化 B**：通过反射调用 B 的构造方法，在堆内存中创建一个 B 对象（此时它的属性 `a` 还是 `null`）。
   - •**将 B 的工厂对象放入三级缓存**：同样，将一个能返回早期 B 对象的 `ObjectFactory` 放入三级缓存。

4. 4.

   **填充 B 的属性（依赖注入）**：

   - •Spring 发现 B 依赖了 A，于是调用 `getBean(A)` 去获取 A。

5. 5.

   **获取 A（关键步骤）**：

   - •再次调用 `getBean(A)`。
   - •此时，在一级缓存 (`singletonObjects`) 中没有找到 A。
   - •**在二级缓存 (`earlySingletonObjects`) 中也没有找到 A。**
   - •**但是在三级缓存 (`singletonFactories`) 中找到了 A 的 ObjectFactory！**
   - •Spring 通过这个 `ObjectFactory.getObject()` **拿到早期的 A 对象（半成品）**。
   - •将这个早期 A 对象**放入二级缓存**（方便后续直接获取），**同时从三级缓存中移除 A 的工厂**。
   - •此时，`getBean(A)` 成功返回了这个**半成品的 A 对象**（虽然属性 `b` 还是 `null`，但对象引用已经存在）。

6. 6.

   **B 完成属性注入和初始化**：

   - •将获取到的早期 A 对象注入给 B。于是 B 的 `a` 属性被成功赋值。
   - •B 继续完成后续的初始化流程（如 `@PostConstruct`）。
   - •B 完全创建成功，**被放入一级缓存 (`singletonObjects`)**，同时从二级和三级缓存中清除。

7. 7.

   **A 继续完成属性注入和初始化**：

   - •此时，流程回到了第 2 步，`getBean(B)` 成功返回了已经完全初始化好的 B 对象。
   - •将 B 对象注入给 A。于是 A 的 `b` 属性被成功赋值。
   - •A 继续完成后续的初始化流程。
   - •A 完全创建成功，**被放入一级缓存 (`singletonObjects`)**，同时从二级和三级缓存中清除。

至此，循环依赖解决，两个 Bean 都成功创建。

**5. 为什么构造器注入无法解决循环依赖？**

​		从上面的流程可以看出，解决循环依赖的**关键**在于：**Spring 能够先把实例化后的对象（半成品）提前暴露出去**。

- •**属性注入（Setter/Field）**：是先实例化（调用构造方法），再注入属性。所以有机会在实例化后、注入属性前，把半成品暴露到三级缓存。
- •**构造器注入**：在调用构造方法的同时，必须完成参数的注入。也就是说，在实例化 A 的时候，就必须先得到 B。但此时 A 自己都还没有实例化完成（连半成品都不是），根本无法提前暴露，也就无法放入三级缓存。这导致了死锁，Spring 会直接抛出 `BeanCurrentlyInCreationException`。

> **结论：Spring 只能解决基于属性注入（Setter/Field）的单例 Bean 的循环依赖问题。**

**6. 如何避免和解决循环依赖？**

​		虽然 Spring 提供了机制，但循环依赖本质上是代码设计上的问题（高耦合），应尽量避免。

1. 1.

   **设计时避免**：

   - •**代码重构**：检查是否可以通过重新设计类之间的关系来打破循环。例如，将公共逻辑抽取到第三个类中。
   - •**使用接口**：依赖抽象接口而非具体实现，降低耦合度。
   - •**应用分层**：严格遵守 MVC、分层架构，避免跨层引用（如 Service 引用 Controller）。

2. 2.

   **使用时解决**：

   - •**使用 `@Lazy` 注解**：在其中一个注入点添加 `@Lazy`。这告诉 Spring 延迟初始化该依赖，先创建一个代理对象注入，等真正需要使用时再初始化。



### 7、介绍一下Spring bean 的生命周期、注入方式和作用域

**1、生命周期**

默认情况下，IOC容器中bean的生命周期分为五个阶段:

- 调用构造器 或者是通过工厂的方式创建Bean对象
- 给bean对象的属性注入值
- 调用初始化方法，进行初始化， 初始化方法是通过init-method来指定的.
- 使用
- IOC容器关闭时， 销毁Bean对象.

**2、注入方式**

- 通过 setter 方法注入
- 通过构造方法注入

**3、Bean的作用域**

**核心作用域**

Spring 默认支持以下五种作用域，其中前两种是任何 Spring 环境都可用的核心作用域。

| 作用域                   | 描述                                                         | 适用场景                                                     |
| :----------------------- | :----------------------------------------------------------- | :----------------------------------------------------------- |
| **singleton** (**单例**) | **默认作用域**。整个 IoC 容器中，只存在一个该 Bean 的共享实例。所有对它的请求都返回**同一个实例**。 | 无状态的Bean，如工具类、服务层（Service）、数据访问层（Repository/DAO）。这是最常用的作用域。 |
| **prototype** (**原型**) | 每次通过容器获取该 Bean 时，容器都会**创建一个新的实例**。   | 需要保持状态的Bean，比如代表一个用户请求或会话的对象。生命周期由客户端控制，容器只负责创建，不负责销毁。 |

**Web-aware 作用域**

​		以下三种作用域仅在 Web 应用的 Spring `ApplicationContext` 中（如 `WebApplicationContext`）中才有效，例如在 Spring MVC 应用中。

| 作用域                     | 描述                                                         | 适用场景                                                     |
| :------------------------- | :----------------------------------------------------------- | :----------------------------------------------------------- |
| **request** (**请求**)     | 为**每一个 HTTP 请求**创建一个新的 Bean 实例。请求结束后，实例销毁。 | 用于保存请求相关的状态，如表单数据。                         |
| **session** (**会话**)     | 为**每一个 HTTP Session** 创建一个新的 Bean 实例。Session 超时或失效后，实例销毁。 | 用于保存用户会话级别的状态，如用户的登录信息、购物车。       |
| **application** (**应用**) | 为**整个 Web 应用**创建一个 Bean 实例。它的生命周期与 `ServletContext` 相同，与应用共存亡。 | 用于存放全局共享的、应用级别的信息，类似于 `ServletContext` 属性。 |

**4、IOC容器**

​		控制反转（IOC），顾名思义，就是**将创建和管理依赖对象的控制权，从程序员手中“反转”给了框架或容器**。

​		程序不再主动创建依赖，而是被动地**接收**由外部容器提供的依赖。

**实现方式：依赖注入 (DI)**

 		依赖注入是实现控制反转最常见、最典型的方式。它的核心思想是：**对象之间的关系由容器在运行期决定**。

​		**IOC 容器就是一个负责实现控制反转和依赖注入功能的框架组件。在 Spring 框架中，这个容器就是 `ApplicationContext` 及其背后的 `BeanFactory`。**

**5、IOC 带来的巨大好处**

1. 1.**解耦**：组件之间的依赖关系减少，代码更加灵活、模块化。符合“面向接口编程，而不是面向实现”的原则。
2. 2.**可测试性**：可以轻松地将真实依赖替换为 Mock 对象进行单元测试。
3. 3.**可维护性**：代码更清晰，更容易理解和扩展。要更换实现，通常只需修改配置即可，无需改动业务代码。
4. 4.**通用性**：容器提供了许多企业级服务的统一管理方式（如事务、AOP），让开发者能更专注于业务逻辑。



### 8、请描述一下Spring 的事务管理 

**1、什么是Spring事务？**

​		**Spring 事务是 Spring  框架提供的一种统一、声明式的机制，用于管理数据库事务（甚至更广泛的分布式事务）。它的核心目的是保证一系列数据库操作要么全部成功（提交），要么全部失败回滚（回滚），确保数据的一致性和完整性，同时让开发者从复杂的事务 API 调用中解脱出来。**

你可以把它想象成一个 **“全自动的数据库操作打包器”**：

1. 1.你告诉 Spring：“**帮我把这些操作打包成一个整体**”（用 `@Transactional` 注解标记一个方法）。
2. 2.Spring 会在这个方法开始前**自动开启事务**。
3. 3.然后执行你方法里的所有数据库操作。
4. 4.最后： •如果方法**顺利执行完**，Spring **自动提交事务**，所有操作正式生效。 •如果方法执行中**抛出了异常**，Spring **自动回滚事务**，所有操作就像没发生过一样。

**2、为什么要用 Spring 事务？**

​		在没有 Spring 事务之前，使用原生 JDBC 管理事务非常繁琐且容易出错：

```java
Connection conn = dataSource.getConnection();
try {
    conn.setAutoCommit(false); // 1. 开启事务（手动）

    // 2. 执行一系列SQL操作（业务逻辑）
    statement1.executeUpdate("...");
    statement2.executeUpdate("...");

    conn.commit(); // 3. 成功则提交（手动）
} catch (SQLException e) {
    conn.rollback(); // 4. 失败则回滚（手动）
} finally {
    conn.close(); // 5. 最后关闭连接（手动）
}
```

**原生方式的痛点：**

1. 1.**样板代码**：每次都要写重复的 `try-catch-finally` 代码块。
2. 2.**污染业务代码**：事务管理（开启、提交、回滚）的代码和核心业务代码混杂在一起，难以维护。
3. 3.**资源泄露风险**：容易忘记关闭连接等资源。
4. 4.**难以切换**：如果从 JDBC 换成 Hibernate，事务 API 完全不同，需要重写所有事务代码。

**Spring 事务的优势：**

1. 1.**声明式管理**：通过简单的注解（如 `@Transactional`）即可管理事务，**业务代码变得非常纯净**，只关注业务逻辑。
2. 2.**统一抽象**：提供了一套通用的事务管理接口，无论底层用 JDBC、Hibernate、JPA 还是其他技术，上层用法完全一致。**更换持久层框架时，事务代码无需修改**。
3. 3.**自动资源管理**：自动处理连接的获取、释放和回滚，避免了资源泄露。
4. 4.**丰富的功能**：支持复杂的事务特性，如**传播行为**、**隔离级别**等。

**3、两种实现方式**

| 方式           | 描述                                                         | 优点                                   | 缺点                                              | 适用场景                             |
| :------------- | :----------------------------------------------------------- | :------------------------------------- | :------------------------------------------------ | :----------------------------------- |
| **声明式事务** | 通过 **`@Transactional`** 注解或 XML 配置来定义事务。        | **简单、非侵入性**、与业务代码解耦。   | 粒度较粗，基于 AOP 代理，需注意自调用失效等问题。 | **绝大多数场景**，是首选方式。       |
| **编程式事务** | 在代码中**显式**调用 Spring 提供的事务模板（`TransactionTemplate`）或 API 来管理事务。 | **粒度更细**，可以精确控制事务的边界。 | 代码侵入性强，样板代码多。                        | 需要非常精细控制事务的**特殊场景**。 |

**声明式事务示例（最常用）：**

```java
@Service
public class TransferService {

    @Autowired
    private AccountRepository accountRepository;

    // 一个转账业务方法，被@Transactional注解包裹
    @Transactional // 添加此注解后，此方法就成为了一个事务
    public void transferMoney(Long fromId, Long toId, BigDecimal amount) {
        // 业务逻辑：扣减转出账户金额，增加转入账户金额
        accountRepository.decreaseBalance(fromId, amount);
        accountRepository.increaseBalance(toId, amount);
        // 如果中间任何一步出错（如余额不足抛出异常），整个操作都会回滚
    }
}
```

**4、核心属性（通过 `@Transactional` 参数配置）**

- •**传播行为 (Propagation)**：**解决“业务方法在已有事务中调用时，该如何定义事务”的问题。**
  - •`REQUIRED`（**默认**）：如果当前没有事务，就新建一个；如果已存在，就加入它。
  - •`REQUIRES_NEW`：无论如何都会新建一个事务。如果当前有事务，则将其挂起。**两个事务互不干扰**。
  - •（还有其他如 `SUPPORTS`, `MANDATORY`, `NESTED` 等，但以上两种最常用）
- •**隔离级别 (Isolation)**：解决多个事务并发执行时可能引发的数据问题（脏读、不可重复读、幻读）。
  - •通常使用默认级别 `DEFAULT`（即底层数据库的默认级别，如 MySQL 默认为 `REPEATABLE_READ`）。
- •**回滚规则 (Rollback Rules)**：指定哪些异常会触发回滚。
  - •**默认**：只在遇到**运行时异常**（`RuntimeException`）和 **Error** 时回滚。
  - •**自定义**：可通过 `@Transactional(rollbackFor = MyCheckedException.class)` 来指定遇到检查型异常（Checked Exception）时也回滚。
- •**只读 (readOnly)**：提示数据库该事务是否为只读操作（如查询），数据库可能会据此进行优化。`@Transactional(readOnly = true)`
- •**超时 (timeout)**：事务必须在指定时间内完成，否则自动回滚。

**5、Spring事务应用范围**

​		正因为有 `JtaTransactionManager` 和 JTA 的支持，Spring 事务可以扩展到多种资源场景：

**a) 数据库事务（最常用）**

​		这是默认和最主要的用途，用于保证一组 SQL 操作（`INSERT`, `UPDATE`, `DELETE`, `SELECT`...）的 ACID 特性。

- •**不只是更新**：即使是连续的 `SELECT` 查询，在 `REPEATABLE_READ` 或 `SERIALIZABLE` 隔离级别下，事务也能保证数据的一致性视图，防止幻读和不可重复读。
- •**示例**：银行转账、订单创建等。

**b) 消息队列事务（JMS）**

​		Spring 可以与 JMS 提供商（如 ActiveMQ, RabbitMQ, IBM MQ）集成，实现消息发送和接收的事务性。

- •**示例**：在一个事务中，同时向数据库插入一条记录**并向消息队列发送一条消息**。如果事务回滚，数据库插入和消息发送都会撤销，避免数据不一致。

```java
@Transactional
public void processOrder(Order order) {
    orderRepository.save(order); // 数据库操作
    jmsTemplate.convertAndSend("order.queue", order); // JMS 消息操作
    // 如果这个方法在结束时抛出异常，订单不会被保存，消息也不会发出
}
```

**c) 分布式事务（Multiple Resources）**

​			通过 JTA，一个 Spring 事务可以跨越多个不同的资源管理器，例如：

- •两个不同的数据库（如 MySQL 和 Oracle）
- •一个数据库和一个消息队列（JMS）
- •其他支持 XA 协议的资源

这种场景下，Spring 事务（通过 JTA）会使用 **两阶段提交（2PC）** 协议来保证所有参与资源的一致性。

```java
@Transactional
public void distributedOperation() {
    jdbcTemplate.update("UPDATE db1.table1 ..."); // 操作第一个数据库
    jdbcTemplate2.update("INSERT INTO db2.table2 ..."); // 操作第二个数据库
    jmsTemplate.convertAndSend("queue", "Message"); // 操作消息队列
    // 这是一个全局事务，三者要么全部成功，要么全部回滚
}
```

**6、不可重复读 (Non-Repeatable Read)**

是什么？

​		**一个事务内，两次读取同一行数据，结果不一致。**
 		这是因为在两次读取的间隔中，另一个**提交了的事务**修改（`UPDATE`）或删除（`DELETE`）了这行数据。

**生动比喻：**

​		你正在看一本书的某一页（开始事务）。这时，别人过来用笔修改了这一页上的几个字然后走开了（另一个事务提交了修改）。你再次阅读这一页（同一事务内第二次读），发现内容变了。

**根本原因**：另一个事务对**同一行数据**进行了 **`UPDATE`** 或 **`DELETE`** 并提交。

**7、幻读 (Phantom Read)**

是什么？

​		**一个事务内，两次执行相同的查询，返回的结果集行数不一致。**
​		这是因为在两次查询的间隔中，另一个**提交了的事务**插入（`INSERT`）或删除（`DELETE`）了满足查询条件的行。

**生动比喻：**

​		你统计一下教室里有多少个戴帽子的人（第一次查询，结果是3人）。这时，进来一个戴帽子的人（另一个事务插入数据并提交），然后又出去一个戴帽子的人（另一个事务删除数据并提交）。你再次统计（同一事务内第二次查询），发现变成了4人。你就像产生了“幻觉”，明明刚才还是3人。

**根本原因**：另一个事务进行了 **`INSERT`** 或 **`DELETE`** 并提交，**改变了满足条件的行数**。



### 9、MyBatis中 #{}和${}的区别是什么

\#{}是预编译处理，${}是字符串替换；

Mybatis在处理#{}时，会将sql中的#{}替换为?号，调用PreparedStatement的set方法来赋值；

Mybatis在处理${}时，就是把${}替换成变量的值；

使用#{}可以有效的防止SQL注入，提高系统安全性



### 10、Mybatis 中一级缓存与二级缓存 

**1、一级缓存**

**核心特性**

| **特性**         | **说明**                                                     |
| ---------------- | ------------------------------------------------------------ |
| **作用域**       | **`SqlSession` 级别**（同一个数据库会话）                    |
| **默认状态**     | **默认开启**，无需配置                                       |
| **生命周期**     | 与 `SqlSession` 绑定（Session 关闭则缓存失效）               |
| **共享范围**     | 同一个 `SqlSession` 内的多次查询共享缓存                     |
| **缓存清除条件** | 执行 **UPDATE/INSERT/DELETE** 操作、手动调用 `clearCache()` 或事务提交/回滚 |

**工作原理**

1.**首次查询**：
 		执行 SQL 查询，结果存入一级缓存（内存中）。

```java
User user1 = sqlSession.selectOne("getUserById", 1);  // 查数据库
```

2.**相同查询**：
 		同一 `SqlSession` 中执行相同的 SQL 和参数，直接从缓存返回结果。 ``

1. ```java
   User user2 = sqlSession.selectOne("getUserById", 1);  // 从缓存读取
   System.out.println(user1 == user2);  // true（同一对象引用）
   ```

> ⚠️ **注意**：两次查询需满足：
>
> - •相同的 SQL 语句 & 参数
> - •同一 Mapper 方法
> - •未执行增删改操作

**缓存失效场景**

| **场景**               | **示例**                                             |
| ---------------------- | ---------------------------------------------------- |
| **执行增删改**         | `sqlSession.update("updateUser", user);` // 清空缓存 |
| **手动清空缓存**       | `sqlSession.clearCache();`                           |
| **事务提交/回滚**      | `sqlSession.commit();` 或 `sqlSession.rollback();`   |
| **跨 SqlSession 操作** | 不同 SqlSession 互不影响缓存                         |

**2、二级缓存**

**核心特性**

| **特性**     | **说明**                                                  |
| ------------ | --------------------------------------------------------- |
| **作用域**   | **`Mapper` 级别**（相同命名空间下的所有 SqlSession 共享） |
| **默认状态** | **默认关闭**，需手动开启                                  |
| **生命周期** | 与应用生命周期相同（可配置策略如 LRU、FIFO）              |
| **共享范围** | 多个 SqlSession 共享（跨会话）                            |
| **存储位置** | 可集成 Redis、Ehcache 等第三方缓存                        |

**启用方式**

1.**全局开关**（`mybatis-config.xml`）：

```xml
<settings>    <setting name="cacheEnabled" value="true"/> <!-- 默认 true，可不配 --> </settings>
```

2.**Mapper 级配置**（XML 或注解）： ``

1. ```xml
   <!-- 在 Mapper.xml 中 -->
   <mapper namespace="com.example.UserMapper">
       <cache /> <!-- 启用二级缓存（默认使用内存存储）-->
   </mapper>
   ```

**工作流程**

![](.\images\c_10.png)

**3、一级缓存 vs 二级缓存 对比**

| **维度**     | **一级缓存**          | **二级缓存**                            |
| ------------ | --------------------- | --------------------------------------- |
| **作用域**   | SqlSession 内部       | 整个 Mapper 命名空间                    |
| **开启方式** | 默认开启              | 需在 Mapper 中显式声明 `<cache/>`       |
| **生命周期** | SqlSession 关闭则失效 | 长期有效（直到手动清除或策略淘汰）      |
| **共享性**   | 不可跨 Session        | 多个 SqlSession 共享                    |
| **存储位置** | JVM 堆内存            | 堆内存（默认）或第三方缓存（如 Redis）  |
| **适用场景** | 单个事务内重复查询    | 全局性、读多写少的热点数据              |
| **性能影响** | 轻量级，无序列化开销  | 需序列化/反序列化（除非 readOnly=true） |

缓存的查找顺序：二级缓存 => 一级缓存 => 数据库



### 11、MyBatis如何获取自动生成的(主)键值

在<insert>标签中使用 useGeneratedKeys和keyProperty 两个属性来获取自动生成的主键值。

示例:

```xml
<insert id="insertname" useGeneratedkeys="true" keyProperty="id">
    insert into names (name) values (#{name}) 
</insert>
```

> generated	/ˈdʒenəreɪtɪd/
>
> property	/ˈprɑːpərti/



### 12、简述Mybatis的动态SQL，列出常用的6个标签及作用

**动态 SQL 概述**

​		MyBatis 的动态 SQL 功能允许开发者根据条件**动态构建 SQL 语句**，无需在 Java 代码中拼接 SQL 字符串。这种特性使得 SQL 语句更加灵活、可维护，同时避免了 SQL 注入风险。

**动态 SQL 标签及作用**

**1. `<if>` - 条件判断**

**作用**：根据表达式结果决定是否包含 SQL 片段

**2. `<choose>/<when>/<otherwise>` - 多条件选择**

**作用**：实现类似 Java 的 switch-case 逻辑

```xml
<select id="findActiveUsers">
  SELECT * FROM users
  WHERE state = 'ACTIVE'
  <choose>
    <when test="role == 'admin'">
      AND access_level > 90
    </when>
    <when test="role == 'user'">
      AND access_level BETWEEN 30 AND 90
    </when>
    <otherwise>
      AND access_level < 30
    </otherwise>
  </choose>
</select>
```

**3.`<where>`**

**作用**：智能处理 WHERE 子句（自动去除开头多余的 AND/OR）

 **4.`<set>`**

**作用**：智能处理 UPDATE 语句（自动去除结尾逗号）

```xml
<update id="updateUser">
  UPDATE users
  <set>
    <if test="name != null">name = #{name},</if>
    <if test="age != null">age = #{age},</if>
  </set>
  WHERE id = #{id}
</update>
```

**5.`<foreach>` - 循环遍历**

**作用**：遍历集合生成 SQL 片段（常用于 IN 条件）
 ​**​参数​**​：

- •`collection`：要遍历的集合
- •`item`：当前元素变量名
- •`index`：索引变量名
- •`open`：循环开始字符串
- •`close`：循环结束字符串
- •`separator`：元素间分隔符 /ˈsepəreɪtər/

```xml
<select id="findUsersByIds">
  SELECT * FROM users
  WHERE id IN
  <foreach item="id" collection="ids" 
           open="(" separator="," close=")">
    #{id}
  </foreach>
</select>
```

**6. `<sql>` 与 `<include>` - SQL 片段重用**

**作用**：定义和引用可重用的 SQL 片段

```xml
<!-- 定义片段 -->
<sql id="userColumns">
  id, username, email
</sql>

<!-- 引用片段 -->
<select id="getUsers">
  SELECT 
    <include refid="userColumns"/>
  FROM users
</select>
```

**动态 SQL 优势**

1. 1.**避免 SQL 拼接**：不再需要 Java 字符串拼接
2. 2.**防止 SQL 注入**：自动使用预编译语句
3. 3.**提高可读性**：SQL 与 Java 代码分离
4. 4.**条件灵活组合**：根据不同参数生成不同 SQL
5. 5.**减少重复代码**：通过 SQL 片段重用



### 13、Mybatis 如何完成MySQL的批量操作

​		MyBatis完成MySQL的批量操作主要是通过<foreach>标签来拼装相应的SQL语句



### 14、谈谈怎么理解SpringBoot框架

​		Spring Boot 是 Spring 开源组织下的子项目，是 Spring 组件一站式解决方案，主要是简化了使用 Spring 的难度，简省了繁重的配置，提供了各种启动器，开发者能快速上手。

**Spring Boot的优点**

**1.独立运行**

​		Spring Boot而且内嵌了各种servlet容器，Tomcat、Jetty等，现在不再需要打成war包部署到容器中，Spring Boot只要打成一个可执行的jar包就能独立运行，所有的依赖包都在一个jar包内。

**2.简化配置**

​		spring-boot-starter-web启动器自动依赖其他组件，简少了maven的配置。除此之外，还提供了各种启动器，开发者能快速上手。

**3.自动配置**

​		Spring Boot能根据当前类路径下的类、jar包来自动配置bean，如添加一个spring-boot-starter-web启动器就能拥有web的功能，无需其他配置。

**4.无代码生成和XML配置**

​		Spring Boot配置过程中无代码生成，也无需XML配置文件就能完成所有配置工作，这一切都是借助于条件注解完成的，这也是Spring4.x的核心功能之一。

**5.应用监控**

​		Spring Boot提供一系列端点可以监控服务及应用，做健康检测。

**Spring Boot缺点：**

​		Spring Boot虽然上手很容易，但如果你不了解其核心技术及流程，所以一旦遇到问题就很棘手，而且现在的解决方案也不是很多，需要一个完善的过程。


### 15、Spring Boot 的核心注解是哪个 它主要由哪几个注解组成的

**核心注解：@SpringBootApplication**

​		`@SpringBootApplication` 是 Spring Boot 最核心的注解，通常用于主启动类上。它是一个组合注解（composed annotation），包含了多个重要注解的功能。

**各组成部分的详细功能**

**@SpringBootConfiguration**

- •继承自 `@Configuration`，表示该类是一个配置类
- •允许在上下文中注册额外的 Bean 或导入其他配置类

**@EnableAutoConfiguration**

- •自动配置是 Spring Boot 的核心特性
- •根据类路径中的依赖自动配置 Spring 应用程序
- •例如：如果 classpath 下有 H2 数据库，则自动配置内存数据库

**@ComponentScan**

- •默认扫描当前包及其子包中的所有组件
- •可通过属性自定义扫描路径：`@ComponentScan(basePackages = "com.example")`
- •可排除特定组件：`@ComponentScan(excludeFilters = ...)`



### 16、Spring Boot自动配置原理是什么

**1.Spring Boot自动配置的核心原理是通过**：

1. 1.**自动发现机制**（配置来源：spring.factories）
2. 2.**条件过滤系统**（各种@Conditional注解，Spring Boot 使用一系列条件注解进行智能判断）
3. 3.**属性绑定机制**（使用@ConfigurationProperties 将`application.properties/yml`中的配置绑定到Bean）

这三者的协同工作，实现了根据应用环境智能配置Spring应用的能力。这种设计大幅减少了样板配置代码，同时保留了充分的灵活性，使开发者能够快速构建生产级应用。

**2.自动配置的本质**

​		Spring Boot 自动配置是通过 `@EnableAutoConfiguration` 实现的**智能条件装配系统**：

1. 1.**动态检测**：扫描类路径（Classpath）中的依赖库
2. 2.**条件判断**：根据存在的类/配置决定是否创建特定Bean
3. 3.**智能装配**：自动配置组件及其依赖关系

> 示例：当类路径存在 `H2` 数据库驱动时，自动配置内存数据库；存在 `MySQL` 驱动时配置连接池

**3.自动配置流程**

1. 1.**启动扫描**：`@EnableAutoConfiguration` 触发自动配置加载
2. 2.**读取配置**：加载所有 `META-INF/spring.factories` 中声明的配置类
3. 3.**条件过滤**：通过条件注解筛选出符合条件的配置类
4. 4.**Bean装配**：按顺序实例化并注册符合条件的Bean

**4.核心思想**

1. 1.**约定优于配置**：提供合理的默认值
2. 2.**开箱即用**：常见场景无需额外配置
3. 3.**逐步覆盖**：允许自定义配置覆盖默认值
4. 4.**条件智能**：根据环境动态调整配置
5. 5.**模块化设计**：每个starter提供独立配置



### 17、SpringBoot配置文件有哪些 怎么实现多环境配置

**1.Spring Boot 配置文件类型**

1.Properties 文件（.properties）

- •**格式**：键值对形式

2.YAML 文件（.yml 或 .yaml）

- •**格式**：层次结构，更易读

Spring Boot 按照以下顺序加载配置（优先级从高到低）：

1. 1.命令行参数（`--server.port=8081`）
2. 2.当前目录的 `/config` 子目录中的配置文件
3. 3.当前目录中的配置文件
4. 4.classpath 下的 `/config` 包中的配置文件
5. 5.classpath 根目录下的配置文件



**2.多环境配置实现方式**

1.创建环境特定配置文件

•**命名格式**：`application-{profile}.properties/yml`

•**示例文件**： •`application-dev.yml`（开发环境） •`application-test.yml`（测试环境） •`application-prod.yml`（生产环境）

2.激活特定环境

**方法一：主配置文件指定**

```yaml
# application.yml
spring:
  profiles:
    active: dev
```

**方法二：命令行激活**

```bash
java -jar myapp.jar --spring.profiles.active=prod
```



### 18、SpringBoot和SpringCloud是什么关系

Spring Boot 和 Spring Cloud 是**互补而非竞争**的关系，它们共同构成了现代微服务架构的基础：

- •**Spring Boot** = 微服务的**构建单元**
- •**Spring Cloud** = 微服务的**协调系统**

| 特性维度     | Spring Boot                      | Spring Cloud                          |
| ------------ | -------------------------------- | ------------------------------------- |
| **定位**     | 快速开发**单个**应用             | 构建**分布式系统**（微服务架构）      |
| **功能**     | 简化Spring应用初始化、开发、部署 | 提供分布式系统所需的通用模式实现      |
| **依赖关系** | 基础框架，可独立使用             | 建立在Spring Boot之上，增强分布式能力 |
| **核心目标** | 让开发变得简单快捷               | 让分布式系统的构建变得简单            |
| **典型特性** | 自动配置、起步依赖、内嵌服务器   | 服务发现、配置中心、熔断器、网关      |

​		Spring Boot 是 Spring 的一套快速配置脚手架，可以基于Spring Boot 快速开发单个微服务，Spring Cloud是一个基于Spring Boot实现的开发工具；Spring Boot专注于快速、方便集成的单个微服务个体，Spring Cloud关注全局的服务治理框架； Spring Boot使用了默认大于配置的理念，很多集成方案已经帮你选择好了，能不配置就不配置，Spring Cloud很大的一部分是基于Spring Boot来实现，必须基于Spring Boot开发。

 		可以单独使用Spring Boot开发项目，但是Spring Cloud离不开 Spring Boot。


### 19、SpringCloud都用过哪些组件 介绍一下作用

1.**Eureka** - 服务注册中心

**作用**：服务注册与发现的**核心组件**。每个微服务启动时，会向 Eureka Server 注册自己的元数据（如 IP、端口、健康状态）。消费者通过 Eureka Server 来查找所需服务的网络位置。

2.**Nacos** - 服务注册中心 

**作用**：一个更强大的、集**服务注册发现**和**配置中心**于一体的组件。**目前已成为国内最主流的选择**，相比 Eureka 功能更全面（如支持动态配置、更健康检查机制）。

3.**Gateway** - API网关

**作用**：统一入口、路由转发、过滤拦截

4.**OpenFeign** - 声明式REST客户端

**作用**：简化服务间HTTP调用。一个**声明式的 HTTP 客户端**。你只需要定义一个 Java 接口并用注解（如 `@FeignClient("service-name")`）修饰它，OpenFeign 就会自动帮你生成实现类，完成服务的调用。

5.**Dubbo**

**作用**：Dubbo 默认使用**基于接口的 RPC 协议**进行通信，性能通常优于 HTTP 方式的 Feign。适用于对性能要求极高的内部服务调用。

6.**Zipkin** - 分布式链路追踪

**作用**：**分布式链路追踪**。Sleuth 为微服务调用生成唯一的**跟踪ID（Trace ID）** 和**跨度ID（Span ID）**，并嵌入到日志和请求头中。Zipkin 是一个图形化工具，用于收集、存储和展示这些追踪数据，帮助开发者可视化地分析请求的完整调用链和性能瓶颈。

7.**Resilience4j** - 熔断器（替代Hystrix）

**作用**：防止服务雪崩，提供弹性功能



### 20、Nacos作用以及注册中心的原理

**1.Nacos 的核心作用**

Nacos (**Na**ming and **Co**nfiguration **S**ervice) 有两个核心功能，可以概括为 **“1+1”**：

1. 1.**服务注册与发现 (Service Registry & Discovery)**
   - •**功能**：所有微服务启动时，都将自己的服务名、IP、端口等元信息**注册**到 Nacos。消费者需要调用某个服务时，向 Nacos **查询**（发现）该服务的所有健康实例的地址列表，从而实现远程调用。
2. 2.**动态配置管理 (Dynamic Configuration Management)**
   - •**功能**：将所有微服务的配置（如数据库连接、开关参数）集中到 Nacos 统一管理。配置修改后，Nacos 能**动态地、实时地**推送到相关服务，无需重启应用。

**2.Nacos 注册中心的工作原理**

**1. 服务注册 (Service Registration)**

- •服务提供者（Provider）在启动时，会向 Nacos Server 发送一个 REST API 请求，将自己提供的服务名称、自身 IP、端口等元信息注册到 Nacos。
- •Nacos Server 接收到注册请求后，会将这个服务实例信息存储在一个双层的内存结构中（类似 `Map<服务名, Group<实例列表>>`），同时也会将信息写入数据库（可选，为支持集群扩展）以实现持久化。

**2. 健康检查 (Health Check)**

- •这是保证服务列表可用性的**核心机制**。
- •**心跳模式**：服务注册后，会**定期（默认5秒）** 向 Nacos Server 发送一个心跳包，以此报告自己是健康的。
- •**服务端检查**：如果 Nacos Server 在**一段时间（默认15秒）** 内没有收到某个实例的心跳，则会将该实例标记为“不健康”。
- •**自动清理**：如果该实例超过**更长时间（默认30秒）** 仍然没有心跳，Nacos Server 会直接**将这个失效的实例从服务列表中删除**。

**3. 服务发现 (Service Discovery)**

- •服务消费者（Consumer）在需要调用某个服务（如 `user-service`）时，会向 Nacos Server 发送一个查询请求。
- •Nacos Server 会返回当前所有健康的、提供 `user-service` 的实例列表（IP和端口）给消费者。
- •消费者本地通常会**缓存这个服务列表**，以避免每次调用都去查询 Nacos Server，提升性能并降低 Nacos 压力。

**4. 负载均衡 & 调用**

- •消费者获取到服务实例列表后，会通过**负载均衡算法**（如 Ribbon 的轮询、随机等）从列表中选择一个实例，发起实际的远程调用（如通过 OpenFeign 发送 HTTP 请求）。

**5. 动态通知 (Event Push)**

- •这是 Nacos 的一大优势。当服务列表发生变化时（如某个实例下线或新实例上线），Nacos Server 会**主动推送一条消息**通知所有订阅了该服务的消费者。
- •消费者收到通知后，会**及时更新本地的缓存服务列表**，从而保证后续的调用不会发往已下线的实例，实现了高实时性。



### 21、Feign工作原理

​		Feign 是一个**声明式、模板化的 HTTP 客户端**，其核心目标是**让 Java 远程服务调用像调用本地方法一样简单**。它通过动态代理和注解处理，将接口的 Java 方法转化为 HTTP 请求。

**1、核心步骤详解**

**1.接口声明与注解解析**

- •开发者定义一个 Java 接口，用 `@FeignClient` 和 Spring MVC 注解（如 `@GetMapping`）声明远程调用：
- •**关键**：Feign 在启动时扫描这些接口，解析注解信息，生成**请求模板**（包含 URL、HTTP 方法、参数位置等）。

 **2.动态代理创建**

- •Feign 通过 **JDK 动态代理**为接口生成代理对象。
- •当消费者调用 `userService.getUser(1)` 时，实际调用的是代理对象的 `invoke()` 方法。

**3.请求构造与发送**

- •代理对象根据方法调用信息：
-  1.**参数处理**：将方法参数按注解规则填充到请求模板（如路径参数 `{id}` → `1`）。
-  2.**负载均衡**：从注册中心（如 Nacos）获取 `user-service` 的实例列表，通过 **Ribbon** 选择一个实例。 
- 3.**HTTP 请求发送**：使用底层 HTTP 客户端（默认 `java.net.HttpURLConnection`，可替换为 OkHttp 等）发送请求。

**4.响应解码**

- •收到 HTTP 响应后，Feign 使用配置的 **Decoder**（如 `JacksonDecoder`）将 JSON 数据反序列化为 Java 对象（如 `User` 类）



















