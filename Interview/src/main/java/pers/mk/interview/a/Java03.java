package pers.mk.interview.a;

/*
接口与抽象类的区别
 */
public class Java03 {

    public static void main(String[] args) {
        // 抽象类
        // Animal animal = new Animal();  // 错误！不能实例化抽象类

        Dog03 myDog = new Dog03("旺财");
        myDog.makeSound();  // 旺财说: 汪汪汪!
        myDog.eat();       // 旺财正在吃东西
        myDog.fetch();      // 旺财正在捡球

        Animal03.animalFact(); // 动物是生物的一个大类

        // 多态使用
        Animal03 animalRef = new Dog03("小黑");
        animalRef.makeSound();  // 小黑说: 汪汪汪!
        animalRef.eat();        // 小黑正在吃东西
        // animalRef.fetch();   // 错误！Animal类没有fetch方法

        System.out.println("-------------------------------------------------");

        // 接口
        Duck duck = new Duck();
        duck.fly();    // 鸭子拍打翅膀飞行
        duck.swim();   // 鸭子在水里游泳
        duck.land();   // 鸭子降落在水面上

        System.out.println("最大飞行高度: " + Flyable.MAX_ALTITUDE + "米");
        Flyable.aviationFact();  // 飞行是人类长期以来的梦想

        Airplane plane = new Airplane();
        plane.fly();    // 飞机使用引擎飞行
//        plane.cruise(); // 检查飞行高度... \n 巡航飞行中
        plane.land();   // 正在降落... (使用Flyable接口的默认实现)

    }

}



// 接口示例
interface Flyable {
    // 常量
    int MAX_ALTITUDE = 10000;  // 默认是public static final

    // 抽象方法(Java 8之前接口只能有抽象方法)
    void fly();

    // 默认方法(Java 8新增)
    default void land() {
        System.out.println("正在降落...");
    }

    // 静态方法(Java 8新增)
    static void aviationFact() {
        System.out.println("飞行是人类长期以来的梦想");
    }
}

interface Swimmable {
    void swim();
}

// 实现接口
class Duck implements Flyable, Swimmable {  // 实现多个接口
    @Override
    public void fly() {
        System.out.println("鸭子拍打翅膀飞行");
    }

    @Override
    public void swim() {
        System.out.println("鸭子在水里游泳");
    }

    // 可以选择重写默认方法
    @Override
    public void land() {
        System.out.println("鸭子降落在水面上");
    }
}

// Java 9接口私有方法示例
interface AdvancedFlyable extends Flyable {
//    private void checkAltitude() {
//        System.out.println("检查飞行高度...");
//    }

//    default void cruise() {
//        checkAltitude();
//        System.out.println("巡航飞行中");
//    }
}

class Airplane implements AdvancedFlyable {
    @Override
    public void fly() {
        System.out.println("飞机使用引擎飞行");
    }
}


// 抽象类示例
abstract class Animal03 {
    // 成员变量
    protected String name;

    // 构造方法
    public Animal03(String name) {
        this.name = name;
    }

    // 抽象方法(没有实现)
    public abstract void makeSound();

    // 具体方法(有实现)
    public void eat() {
        System.out.println(name + "正在吃东西");
    }

    // 可以包含静态方法
    public static void animalFact() {
        System.out.println("动物是生物的一个大类");
    }
}

// 继承抽象类
class Dog03 extends Animal03 {
    public Dog03(String name) {
        super(name);  // 调用父类构造方法
    }

    // 必须实现抽象方法
    @Override
    public void makeSound() {
        System.out.println(name + "说: 汪汪汪!");
    }

    // 可以添加新方法
    public void fetch() {
        System.out.println(name + "正在捡球");
    }
}