package pers.mk.opspace.java.jdk8;

import java.util.Collections;

/**
 * @describe: TODO
 * @Author MK
 * @PackageName openhome
 * @Package pers.mk.opspace.java.jdk8
 * @Date 2023/5/10 16:03
 * @Version 1.0
 */
public class Main {
    public static void main(String[] args) {
        School school = () -> {
            System.out.println("这里是mk");
            System.out.println("aaaaa");
        };
        System.out.println(school.play("mk"));
        school.study();
        

    }

}
