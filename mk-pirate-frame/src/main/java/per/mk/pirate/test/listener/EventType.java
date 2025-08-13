package per.mk.pirate.test.listener;

public enum EventType {
    USER_LOGIN,          // 用户登录事件
    ORDER_CREATED,       // 订单创建事件
    PAYMENT_COMPLETED,   // 支付完成事件
    SYSTEM_SHUTDOWN,     // 系统关闭事件
    DATA_UPDATED         // 数据更新事件
}
