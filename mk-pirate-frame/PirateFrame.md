# PirateFrame

## 1.问题

### 1.1 springboot启动后不会停止

**为什么springboot项目启动后，main中的启动方法执行完成后，项目不会停止下来？**

当执行 `SpringApplication.run()` 时，Spring Boot 会：

1. 初始化 Spring 容器（加载 Bean、配置等）。
2. **启动嵌入式Web服务器**（默认是 Tomcat），并绑定到指定端口（如 `8080`）。
3. 主线程（`main` 方法）会继续执行后续代码（如果有），但 **Web 服务器会在后台以非守护线程运行**，持续监听请求。

> ### **为什么项目不会退出？**
>
> - **非守护线程（Non-Daemon Threads）**：
>   Spring Boot 启动的 Web 服务器线程是 **非守护线程**，而 JVM 只有在所有非守护线程结束后才会退出。
>   - 例如，Tomcat 的 `ThreadPoolExecutor` 会保持活跃状态等待请求。
> - **事件循环机制**：
>   嵌入式服务器（如 Netty 或 Tomcat）内部通过事件循环（Event Loop）持续监听端口，类似于一个 `while(true)` 循环



### 1.2 获取页面发送的请求信息

**使用纯java代码如何获取页面发送的请求信息**

使用Socket编程（底层TCP通信）

**本质**：

`ServerSocket`是 TCP/IP 协议栈中 **服务器端套接字** 的 Java 实现，负责绑定到特定端口，被动等待客户端发起连接请求。

**与 `Socket`的区别**：

- `ServerSocket`：仅用于 **监听端口** 和 **接受连接**（服务端专用）。

- `Socket`：用于 **实际数据传输**（服务端和客户端均需使用）

  

  

。



## 2.方案

### 2.1 非守护线程与事件循环实现

类

```java
// 线程安全的事件队列（先进先出）
LinkedBlockingQueue;
take();//消费队列
put();//向队列添加
clear();//清空队列

// 事件循环线程
Thread;

// 循环运行状态标记
AtomicBoolean
```

实现

Event

```java
public interface Event {
    void handle();
}
```

EventLoop

```java
public class EventLoop {
    // 线程安全的事件队列
    private final BlockingQueue<Event> eventQueue = new LinkedBlockingQueue<>();
    // 事件循环线程（非守护线程）
    private Thread eventThread;
    // 循环运行状态标记
    private final AtomicBoolean isRunning = new AtomicBoolean(false);

    /**
     * 启动事件循环（非守护线程）
     */
    public void start() {
        if (isRunning.compareAndSet(false, true)) {
            eventThread = new Thread(this::loop, "EventLoop-Thread");
            eventThread.setDaemon(false); // 明确设置为非守护线程
            eventThread.start();
            System.out.println("事件循环已启动");
        }
    }

    /**
     * 事件循环核心逻辑
     */
    private void loop() {
        while (isRunning.get()) {
            try {
                // 阻塞获取事件（队列为空时等待）
                Event event = eventQueue.take();
                // 处理事件
                event.handle();
            } catch (InterruptedException e) {
                // 处理中断信号
                Thread.currentThread().interrupt();
                System.out.println("事件循环被中断");
                break;
            } catch (Exception e) {
                System.err.println("事件处理出错: " + e.getMessage());
            }
        }
        System.out.println("事件循环已停止");
    }

    /**
     * 向事件队列添加事件
     */
    public void postEvent(Event event) {
        if (isRunning.get()) {
            try {
                eventQueue.put(event); // 阻塞添加（队列满时等待）
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("添加事件被中断");
            }
        } else {
            throw new IllegalStateException("事件循环未运行，无法添加事件");
        }
    }

    /**
     * 停止事件循环（优雅关闭）
     */
    public void stop() {
        if (isRunning.compareAndSet(true, false)) {
            // 中断事件循环线程
            if (eventThread != null) {
                eventThread.interrupt();
            }
            // 清空事件队列（可选操作，根据需求决定是否处理剩余事件）
            eventQueue.clear();
        }
    }

}
```



### 2.2 监听指定端口实现

类

```java
// 仅用于 监听端口 和 接受连接（服务端专用）。
ServerSocket;
new ServerSocket(8080);//开启服务监听指定接口
accept();//阻塞当前线程，直到有客户端连接请求到达

// 用于 实际数据传输（服务端和客户端均需使用）
Socket;
```

实现

```java
public class HttpServer {
    public static void main(String[] args) throws IOException {
        int port = 8080;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Listening on port " + port);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                handleRequest(clientSocket); // 处理请求
            }
        }
    }

    private static void handleRequest(Socket socket) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter writer = new PrintWriter(socket.getOutputStream())) {

            // 1. 解析请求行
            String requestLine = reader.readLine();
            System.out.println("Request Line: " + requestLine);

            // 2. 解析请求头
            Map<String, String> headers = new HashMap<>();
            String headerLine;
            while (!(headerLine = reader.readLine()).isEmpty()) {
                String[] parts = headerLine.split(": ", 2);
                if (parts.length == 2) {
                    headers.put(parts[0], parts[1]);
                }
            }
            System.out.println("Headers: " + headers);

            // 3. 解析请求体（如有）
            StringBuilder body = new StringBuilder();
            if (headers.containsKey("Content-Length")) {
                int contentLength = Integer.parseInt(headers.get("Content-Length"));
                char[] buffer = new char[contentLength];
                reader.read(buffer, 0, contentLength);
                body.append(buffer);
            }
            System.out.println("Body: " + body);

            // 4. 返回响应
            writer.println("HTTP/1.1 200 OK");
            writer.println("Content-Type: text/plain");
            writer.println();
            writer.println("Request received");
        }
    }
}
```

