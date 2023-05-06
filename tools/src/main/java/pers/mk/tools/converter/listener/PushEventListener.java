package pers.mk.tools.converter.listener;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pers.mk.tools.converter.model.event.PushEvent;

/**
 * @describe: TODO
 * @Author MK
 * @PackageName tools
 * @Package pers.mk.tools.converter.listener
 * @Date 2023/5/6 9:57
 * @Version 1.0
 */
@Service
public class PushEventListener {

    @EventListener
    public void demo1(PushEvent pushEvent){
        System.out.println(this.getClass().getSimpleName()+ "::" +pushEvent.getMsg());
    }

    @EventListener
    public void demo2(PushEvent pushEvent){
        System.out.println(this.getClass().getSimpleName()+ "::" +pushEvent.getMsg());
    }

}
