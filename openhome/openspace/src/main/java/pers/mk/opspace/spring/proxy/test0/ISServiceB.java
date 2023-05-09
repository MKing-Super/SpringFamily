package pers.mk.opspace.spring.proxy.test0;

/**
 * @describe: TODO
 * @Author MK
 * @PackageName openhome
 * @Package pers.mk.opspace.spring.proxy
 * @Date 2023/5/8 17:32
 * @Version 1.0
 */
public class ISServiceB implements ISService{
    @Override
    public void m1() {
        System.out.println("ISServiceB - m1");
    }

    @Override
    public void m2() {
        System.out.println("ISServiceB - m2");
    }

    @Override
    public void m3() {
        System.out.println("ISServiceB - m3");
    }
}
