package pers.mk.opspace.spring.di;

import pers.mk.api.model.Son1;

/**
 * @describe: TODO
 * @Author MK
 * @PackageName openhome
 * @Package pers.mk.opspace.spring.di
 * @Date 2023/5/8 11:02
 * @Version 1.0
 */
public class Son1Event1 extends Son1 implements Son1Listener {

    @Override
    public void onEvent(Son1 son) {
        System.out.println("Son1Event1::" + son.getMsg());
    }
}
