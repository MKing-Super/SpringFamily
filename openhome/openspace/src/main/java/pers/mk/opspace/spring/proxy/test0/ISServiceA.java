package pers.mk.opspace.spring.proxy.test0;

/**
 * @describe: TODO
 * @Author MK
 * @PackageName openhome
 * @Package pers.mk.opspace.spring.proxy
 * @Date 2023/5/8 17:31
 * @Version 1.0
 */
public class ISServiceA implements ISService{
    @Override
    public void m1() {
        System.out.println("ISServiceA - m1");
    }

    @Override
    public void m2() {
        System.out.println("ISServiceA - m2");
    }

    @Override
    public void m3() {
        System.out.println("ISServiceA - m3");
    }
}
