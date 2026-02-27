# Resilience4j熔断器示例

​		Resilience4j 是轻量级、模块化的熔断框架（专为函数式编程设计，适配 Java 8+），核心原理基于**熔断器模式（Circuit Breaker Pattern）**。

## 一、Resilience4j 熔断器核心原理

熔断器的核心目的是**防止故障扩散**（比如下游服务超时 / 异常导致上游服务线程池耗尽），其工作机制类比家里的 “保险丝”—— 故障累积到阈值就 “跳闸”，阻断请求，待下游恢复后再 “合闸”。

### 1. 熔断器的 3 个状态（核心）

Resilience4j 的熔断器会在 3 个状态间切换，状态流转是其核心逻辑：

![](.\images\resilience4j_01.png)

**CLOSED（关闭态）**：

- 正常状态，所有请求都能通过熔断器调用下游服务；
- 熔断器会统计请求的失败数 / 失败率（比如异常、超时、返回错误结果）；
- 当失败指标达到预设阈值，熔断器切换为 OPEN 态。

**OPEN（打开态）**：

- 熔断器 “跳闸”，**直接拒绝所有请求**，不会调用下游服务；
- 此时请求会快速失败（抛出`CallNotPermittedException`），避免等待下游超时；
- 熔断器会记录 “打开” 的时间，等待预设的`waitDurationInOpenState`（比如 5 秒）后，切换为 HALF_OPEN 态。

**HALF_OPEN（半开态）**：

- 熔断器允许**少量测试请求**通过（比如仅允许 10 个请求），试探下游服务是否恢复；
- 如果测试请求的成功率达标（比如失败率 < 50%），则切换回 CLOSED 态；
- 如果测试请求仍失败，立刻切回 OPEN 态，继续等待下一个熔断窗口。

### 2. 核心统计逻辑

Resilience4j 通过**滑动窗口**统计请求指标（避免瞬间波动误判），有两种窗口模式：

- **基于计数的滑动窗口**：统计最近 N 个请求（比如最近 100 个请求）的失败率；

- 基于时间的滑动窗口：统计最近 T 时间内（比如 10 秒）的请求失败率。

  当窗口内的失败率 / 失败数达到阈值，熔断器就会打开。

  

## 二、SpringBoot 整合 Resilience4j 的核心配置

​		Resilience4j 在 SpringBoot 中支持两种使用方式：**注解式（最常用）** 和**编程式**，下面先讲核心依赖和配置，再讲注解使用。

### 1. 第一步：核心依赖（Maven/Gradle）

SpringBoot 项目需引入以下依赖（适配 SpringBoot 2.x/3.x）：

```xml
<!-- Resilience4j核心 + SpringBoot自动配置 -->
<dependency>
    <groupId>io.github.resilience4j</groupId>
    <artifactId>resilience4j-spring-boot3</artifactId> <!-- SpringBoot 2.x用resilience4j-spring-boot2 -->
    <version>2.1.0</version> <!-- 最新版可查Maven中央仓库 -->
</dependency>
<!-- 熔断器 + 重试 + 超时 核心模块（按需引入） -->
<dependency>
    <groupId>io.github.resilience4j</groupId>
    <artifactId>resilience4j-circuitbreaker</artifactId>
    <version>2.1.0</version>
</dependency>
<!-- 可选：actuator监控熔断器状态 -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

### 2. 第二步：核心配置（application.yml）

​		Resilience4j 的配置分为**全局默认配置**和**自定义实例配置**（可针对不同服务配置不同规则），下面是完整示例：

```yml
resilience4j:
  circuitbreaker:
    # 全局默认配置（所有熔断器实例默认继承）
    configs:
      default:
        # 1. 状态切换核心阈值
        failureRateThreshold: 50          # 失败率阈值（%），超过则打开熔断器（默认50）
        slidingWindowSize: 100            # 滑动窗口大小（基于计数），统计最近100个请求
        slidingWindowType: COUNT_BASED    # 窗口类型：COUNT_BASED（计数）/TIME_BASED（时间）
        minimumNumberOfCalls: 10          # 触发熔断的最小请求数（不足则不统计，默认10）
        waitDurationInOpenState: 5000     # 打开态→半开态的等待时间（ms，默认60000）
        permittedNumberOfCallsInHalfOpenState: 10  # 半开态允许的测试请求数（默认10）
        
        # 2. 失败判定规则（哪些情况算"失败"）
        recordExceptions:                 # 要统计为失败的异常（默认包含Exception）
          - java.lang.Exception
          - org.springframework.web.client.HttpServerErrorException
        ignoreExceptions:                 # 忽略的异常（不算失败）
          - org.springframework.web.client.HttpClientErrorException$NotFound
        
        # 3. 其他配置
        automaticTransitionFromOpenToHalfOpenEnabled: true  # 自动从打开态切半开态（默认true）
        maxWaitDurationInHalfOpenState: 10000               # 半开态最大等待时间（ms，默认0）
    # 自定义熔断器实例（比如针对"orderService"服务）
    instances:
      orderService:  # 熔断器实例名（注解中指定）
        baseConfig: default  # 继承全局默认配置
        failureRateThreshold: 30  # 覆盖：失败率30%就熔断
        waitDurationInOpenState: 3000  # 覆盖：打开态3秒后切半开态
        minimumNumberOfCalls: 5        # 覆盖：最少5个请求才统计
```

### 3. 第三步：注解式使用（最常用）

通过`@CircuitBreaker`注解标记需要熔断的方法，指定熔断器实例名和降级方法：

```java
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    private final RestTemplate restTemplate;

    public OrderService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * 调用下游订单服务，开启熔断
     * name：熔断器实例名（对应yml中的orderService）
     * fallbackMethod：降级方法名（必须和原方法参数、返回值一致）
     */
    @CircuitBreaker(name = "orderService", fallbackMethod = "getOrderFallback")
    public String getOrderById(Long orderId) {
        // 调用下游服务（比如http接口）
        return restTemplate.getForObject("http://order-service/order/" + orderId, String.class);
    }

    /**
     * 降级方法（熔断器打开/调用失败时执行）
     * 注意：参数要包含原方法参数 + 异常参数（可选）
     */
    public String getOrderFallback(Long orderId, Exception e) {
        // 降级逻辑：返回默认值/缓存数据/友好提示
        return "订单服务暂时不可用，请稍后重试（异常：" + e.getMessage() + "）";
    }
}
```

### 4. 可选：结合超时 / 重试（Resilience4j 模块化特性）

Resilience4j 可与`@TimeLimiter`（超时）、`@Retry`（重试）组合使用，避免单次超时误判：

```java
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import java.util.concurrent.CompletableFuture;

// 组合使用：重试2次 → 超时3秒 → 熔断
@Retry(name = "orderService", fallbackMethod = "getOrderFallback")
@TimeLimiter(name = "orderService") // 超时控制（需返回CompletableFuture）
@CircuitBreaker(name = "orderService", fallbackMethod = "getOrderFallback")
public CompletableFuture<String> getOrderById(Long orderId) {
    return CompletableFuture.supplyAsync(() -> 
        restTemplate.getForObject("http://order-service/order/" + orderId, String.class)
    );
}

// 降级方法（适配CompletableFuture）
public CompletableFuture<String> getOrderFallback(Long orderId, Exception e) {
    return CompletableFuture.completedFuture("订单服务暂时不可用");
}
```

### 5. 监控熔断器状态（可选）

通过 Actuator 暴露熔断器指标（需开启端点）：

```yml
management:
  endpoints:
    web:
      exposure:
        include: circuitbreakers, health, info  # 暴露熔断器端点
  endpoint:
    circuitbreakers:
      enabled: true  # 启用熔断器端点
  metrics:
    tags:
      application: order-app
```

访问地址查看状态：`http://localhost:8080/actuator/circuitbreakers`，返回示例：

```json
{
  "circuitBreakers": {
    "orderService": {
      "state": "CLOSED",
      "failureRate": 0.0,
      "numberOfFailedCalls": 0,
      "numberOfSuccessfulCalls": 10
    }
  }
}
```

## 三、核心原理与配置总结

### 1. 核心原理关键点

- Resilience4j 熔断器通过**3 状态流转**（CLOSED→OPEN→HALF_OPEN）实现故障隔离；
- 基于**滑动窗口**统计请求失败率，达到阈值则 “跳闸”，避免故障扩散；
- 半开态通过 “测试请求” 试探下游恢复状态，实现自动恢复。

### 2. 核心配置关键点

- 全局配置（`configs.default`）：定义所有熔断器的默认规则，减少重复配置；
- 实例配置（`instances.xxx`）：针对不同服务定制熔断规则（失败率、等待时间等）；
- 失败判定（`recordExceptions/ignoreExceptions`）：精准控制哪些异常算 “失败”，避免误判；
- 注解式使用：`@CircuitBreaker` + 降级方法，快速集成到业务代码。

### 3. 最佳实践

- 熔断阈值需根据业务调整（短事务可设低失败率，长事务可放宽）；
- 降级方法要轻量（避免调用其他服务，防止级联失败）；
- 结合超时（`@TimeLimiter`）和重试（`@Retry`），减少偶发故障的影响；
- 开启监控，实时查看熔断器状态，及时发现下游服务问题。