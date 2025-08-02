package pers.mk.interview.a;

//finalize() 的基本用法
public class Java10 {

    private String resourceName;

    public Java10(String name) {
        this.resourceName = name;
        System.out.println("Resource '" + resourceName + "' created.");
    }

    // 重写 finalize()，在对象被回收时调用
    @Override
    protected void finalize() throws Throwable {
        try {
            System.out.println("Resource '" + resourceName + "' is being finalized.");
            // 模拟资源释放（如关闭文件、网络连接等）
        } finally {
            super.finalize(); // 调用父类的 finalize()
        }
    }

    public static void main(String[] args) {
        new Java10("test"); // 创建对象后立即失去引用
        System.gc(); // 建议 JVM 执行垃圾回收（不保证立即执行）
    }

}
