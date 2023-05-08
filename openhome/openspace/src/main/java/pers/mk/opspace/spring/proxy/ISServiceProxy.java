package pers.mk.opspace.spring.proxy;

/**
 * @describe: TODO
 * @Author MK
 * @PackageName openhome
 * @Package pers.mk.opspace.spring.proxy
 * @Date 2023/5/8 17:34
 * @Version 1.0
 */
public class ISServiceProxy implements ISService{

    private ISService target;

    public ISServiceProxy(ISService isService) {
        this.target = isService;
    }

    @Override
    public void m1() {
        long start = System.nanoTime();
        this.target.m1();
        long end = System.nanoTime();
        System.out.println(this.target.getClass() + ".m1()方法：：" + (end - start));
    }

    @Override
    public void m2() {
        long start = System.nanoTime();
        this.target.m2();
        long end = System.nanoTime();
        System.out.println(this.target.getClass() + ".m2()方法：：" + (end - start));
    }

    @Override
    public void m3() {
        long start = System.nanoTime();
        this.target.m3();
        long end = System.nanoTime();
        System.out.println(this.target.getClass() + ".m3()方法：：" + (end - start));
    }
}
