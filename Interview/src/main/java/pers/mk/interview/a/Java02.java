package pers.mk.interview.a;

/*
重载(Overload)与重写(Override)举例
 */
public class Java02 {

    public static void main(String[] args) {
        // 重载
        OverloadExample example = new OverloadExample();
        example.print();                  // 调用无参print()
        example.print("Hello");          // 调用print(String)
        example.print(10);               // 调用print(int)
        example.print(3.14);             // 调用print(double)
        example.print("Alice", 25);     // 调用print(String, int)
        example.print(30, "Bob");        // 调用print(int, String)

        System.out.println("----------------------------------------------------");

        // 重写
        Animal02 animal = new Dog02();  // 多态
        animal.makeSound();  // 输出"汪汪汪" - 调用子类重写的方法
        animal.eat("骨头");  // 输出"狗吃骨头" - 调用子类重写的方法
        animal.sleep();     // 输出"动物睡觉" - 调用父类final方法
        Animal02.run();       // 输出"动物奔跑" - 调用父类static方法
    }

}
class Animal02 {
    // 父类方法
    public void makeSound() {
        System.out.println("动物发出声音");
    }

    protected void eat(String food) {
        System.out.println("动物吃" + food);
    }

    // 不能被子类重写的方法
    public final void sleep() {
        System.out.println("动物睡觉");
    }

    private void secret() {
        System.out.println("动物的秘密");
    }

    public static void run() {
        System.out.println("动物奔跑");
    }
}

class Dog02 extends Animal02 {
    // 正确重写 - 方法签名完全相同
    @Override
    public void makeSound() {
        System.out.println("汪汪汪");
    }

    // 正确重写 - 访问修饰符可以更宽松
    @Override
    public void eat(String food) {
        System.out.println("狗吃" + food);
    }

    // 错误重写示例 - 取消注释会报错
    /*
    @Override
    private void secret() {  // 错误！不能重写private方法
        System.out.println("狗的秘密");
    }

    @Override
    public final void sleep() {  // 错误！不能重写final方法
        System.out.println("狗睡觉");
    }

    @Override
    public static void run() {  // 错误！不能重写static方法
        System.out.println("狗奔跑");
    }

    @Override
    public void makeSound(int volume) {  // 错误！参数不同，不是重写
        System.out.println("狗叫音量: " + volume);
    }
    */
}

class OverloadExample{
    // 1. 参数个数不同构成重载
    public void print() {
        System.out.println("无参数print方法");
    }

    public void print(String message) {
        System.out.println("带一个参数的print方法: " + message);
    }

    // 2. 参数类型不同构成重载
    public void print(int number) {
        System.out.println("打印整数: " + number);
    }

    public void print(double number) {
        System.out.println("打印浮点数: " + number);
    }

    // 3. 参数顺序不同构成重载
    public void print(String name, int age) {
        System.out.println("姓名: " + name + ", 年龄: " + age);
    }

    public void print(int age, String name) {
        System.out.println("年龄: " + age + ", 姓名: " + name);
    }

    // 4. 返回类型不同不构成重载(编译错误)
    /*
    public int print() {  // 错误！仅返回类型不同不构成重载
        return 0;
    }
    */

}