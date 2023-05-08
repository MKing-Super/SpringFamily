package pers.mk.opspace.spring.di;

import org.springframework.stereotype.Component;
import pers.mk.api.model.Son1;

/**
 * @describe: TODO
 * @Author MK
 * @PackageName openhome
 * @Package pers.mk.opspace.spring.di
 * @Date 2023/5/8 11:07
 * @Version 1.0
 */
@Component
public class Son1Event2 extends Son1 implements SonListener<Son1> {

    public void onEvent(Son1 son){
        System.out.println("Son1Event2::" + son.getMsg());
    }

}
