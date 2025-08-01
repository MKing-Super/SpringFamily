package pers.mk.interview.a;

/*
多态性：多态性是指允许不同子类型的对象对同一消息作出不同的响应
 */
public class Java01 {

    public static void main(String[] args) {
        // 表现1：父类引用指向子类对象
        Animal myAnimal = new Dog();  // Animal引用指向Dog对象
        myAnimal.makeSound();  // 输出"汪汪汪"

        // myAnimal.fetch();  // 错误！Animal类没有fetch方法

        myAnimal = new Cat();  // 同一个引用现在指向Cat对象
        myAnimal.makeSound();  // 输出"喵喵喵"

        // 多态表现2：方法参数多态
        animalSound(new Dog());  // 输出"汪汪汪"
        animalSound(new Cat());  // 输出"喵喵喵"

        System.out.println("-----------------------------------------------------");

        // 表现2：运行时多态（动态绑定）
        // 创建一个Shape数组，包含不同的子类对象
        Shape[] shapes = new Shape[3];
        shapes[0] = new Circle();
        shapes[1] = new Rectangle();
        shapes[2] = new Triangle();

        // 遍历数组，调用draw方法
        for (Shape shape : shapes) {
            shape.draw();  // 运行时根据实际对象类型调用相应方法
        }

        System.out.println("-----------------------------------------------------");

        // 表现3：接口多态示例
        Payment payment = new CreditCardPayment();
        processPayment(payment, 100.0);

        payment = new AlipayPayment();
        processPayment(payment, 200.0);

        payment = new WeChatPayment();
        processPayment(payment, 300.0);

    }

    public static void processPayment(Payment payment, double amount) {
        payment.pay(amount);
    }

    public static void animalSound(Animal animal) {
        animal.makeSound();
    }

}

// --------------------------------------3
interface Payment {
    void pay(double amount);
}

class CreditCardPayment implements Payment {
    @Override
    public void pay(double amount) {
        System.out.println("使用信用卡支付: " + amount + "元");
    }
}

class AlipayPayment implements Payment {
    @Override
    public void pay(double amount) {
        System.out.println("使用支付宝支付: " + amount + "元");
    }
}

class WeChatPayment implements Payment {
    @Override
    public void pay(double amount) {
        System.out.println("使用微信支付: " + amount + "元");
    }
}

// ---------------------------------------- 2
class Shape {
    void draw() {
        System.out.println("绘制形状");
    }
}

class Circle extends Shape {
    @Override
    void draw() {
        System.out.println("绘制圆形");
    }
}

class Rectangle extends Shape {
    @Override
    void draw() {
        System.out.println("绘制矩形");
    }
}

class Triangle extends Shape {
    @Override
    void draw() {
        System.out.println("绘制三角形");
    }
}

// --------------------------------------------------------- 1
// 父类
class Animal {
    public void makeSound() {
        System.out.println("动物发出声音");
    }
}

// 子类1
class Dog extends Animal {
    @Override
    public void makeSound() {
        System.out.println("汪汪汪");
    }

    public void fetch() {
        System.out.println("狗叼回东西");
    }
}

// 子类2
class Cat extends Animal {
    @Override
    public void makeSound() {
        System.out.println("喵喵喵");
    }

    public void scratch() {
        System.out.println("猫抓东西");
    }
}