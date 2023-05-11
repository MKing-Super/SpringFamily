package pers.mk.opspace.java.jdk8;

/**
 * @describe: TODO
 * @Author MK
 * @PackageName openhome
 * @Package pers.mk.opspace.java.jdk8
 * @Date 2023/5/10 16:04
 * @Version 1.0
 */
public interface School {
    void study();

    default String play(String name){
        return "我炸了 play";
    }
}
