package per.mk.pirate.frame;

import java.net.ServerSocket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

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
