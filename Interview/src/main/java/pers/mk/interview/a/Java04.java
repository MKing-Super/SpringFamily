package pers.mk.interview.a;

import java.io.*;

/*
深拷贝与浅拷贝举例
 */
public class Java04 {

    public static void main(String[] args) throws CloneNotSupportedException, IOException, ClassNotFoundException {
        // 1. 浅拷贝(Shallow Copy)
        Address address = new Address("北京", "长安街");
        Person original = new Person("张三", 25, address);

        // 浅拷贝
        Person cloned = (Person) original.clone();

        System.out.println("原始对象: " + original.getName() + ", " +
                original.getAddress().getCity());
        System.out.println("拷贝对象: " + cloned.getName() + ", " +
                cloned.getAddress().getCity());

        // 修改原始对象的引用类型字段
        original.getAddress().setCity("上海");

        System.out.println("\n修改原始对象的地址后:");
        System.out.println("原始对象: " + original.getName() + ", " +
                original.getAddress().getCity());
        System.out.println("拷贝对象: " + cloned.getName() + ", " +
                cloned.getAddress().getCity());
        // 可以看到拷贝对象的address也被修改了

        System.out.println("------ 深拷贝实现方式1：重写clone方法");


        // 2. 深拷贝(Deep Copy)
        // 深拷贝实现方式1：重写clone方法
        Address04_2 address04_2 = new Address04_2("北京", "长安街");
        Person04_2 original04_2 = new Person04_2("张三", 25, address04_2);

        // 深拷贝
        Person04_2 cloned04_2 = (Person04_2) original04_2.clone();

        System.out.println("原始对象: " + original04_2.getName() + ", " +
                original04_2.getAddress().getCity());
        System.out.println("拷贝对象: " + cloned04_2.getName() + ", " +
                cloned04_2.getAddress().getCity());

        // 修改原始对象的引用类型字段
        original04_2.getAddress().setCity("上海");

        System.out.println("\n修改原始对象的地址后:");
        System.out.println("原始对象: " + original04_2.getName() + ", " +
                original04_2.getAddress().getCity());
        System.out.println("拷贝对象: " + cloned04_2.getName() + ", " +
                cloned04_2.getAddress().getCity());
        // 可以看到拷贝对象的address没有被修改

        System.out.println("-------- 深拷贝实现方式2：通过序列化");

        // 深拷贝实现方式2：通过序列化
        Address04_3 address04_3 = new Address04_3("北京", "长安街");
        Person04_3 original04_3 = new Person04_3("张三", 25, address04_3);

        // 深拷贝
        Person04_3 cloned04_3 = original04_3.deepCopy();

        System.out.println("原始对象: " + original04_3.getName() + ", " +
                original04_3.getAddress().getCity());
        System.out.println("拷贝对象: " + cloned04_3.getName() + ", " +
                cloned04_3.getAddress().getCity());

        // 修改原始对象的引用类型字段
        original04_3.getAddress().setCity("上海");

        System.out.println("\n修改原始对象的地址后:");
        System.out.println("原始对象: " + original04_3.getName() + ", " +
                original04_3.getAddress().getCity());
        System.out.println("拷贝对象: " + cloned04_3.getName() + ", " +
                cloned04_3.getAddress().getCity());
        // 可以看到拷贝对象的address没有被修改


    }

}


class Person04_3 implements Serializable {
    private String name;
    private int age;
    private Address04_3 address;

    public Person04_3(String name, int age, Address04_3 address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    // 通过序列化实现深拷贝
    public Person04_3 deepCopy() throws IOException, ClassNotFoundException {
        // 将对象写入流中
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(this);

        // 从流中读出对象
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
        return (Person04_3) ois.readObject();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Address04_3 getAddress() {
        return address;
    }

    public void setAddress(Address04_3 address) {
        this.address = address;
    }
}

class Address04_3 implements Serializable {
    private String city;
    private String street;

    public Address04_3(String city, String street) {
        this.city = city;
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

}


class Person04_2 implements Cloneable {
    private String name;
    private int age;
    private Address04_2 address;

    public Person04_2(String name, int age, Address04_2 address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    // 深拷贝实现
    @Override
    public Object clone() throws CloneNotSupportedException {
        Person04_2 cloned = (Person04_2) super.clone();
        cloned.address = (Address04_2) address.clone();  // 对引用类型也进行拷贝
        return cloned;
    }

    // getter和setter方法
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public Address04_2 getAddress() { return address; }
    public void setAddress(Address04_2 address) { this.address = address; }
}

class Address04_2 implements Cloneable {
    private String city;
    private String street;

    public Address04_2(String city, String street) {
        this.city = city;
        this.street = street;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    // getter和setter方法
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }
}


class Person implements Cloneable {
    private String name;
    private int age;
    private Address address;  // 引用类型

    public Person(String name, int age, Address address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    // 浅拷贝实现
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();  // 使用Object的clone方法
    }

    // getter和setter方法
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public Address getAddress() { return address; }
    public void setAddress(Address address) { this.address = address; }
}

class Address {
    private String city;
    private String street;

    public Address(String city, String street) {
        this.city = city;
        this.street = street;
    }

    // getter和setter方法
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }
}