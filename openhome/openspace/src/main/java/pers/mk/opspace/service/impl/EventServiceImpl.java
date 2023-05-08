package pers.mk.opspace.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Service;
import pers.mk.api.model.Father;
import pers.mk.api.model.Son1;
import pers.mk.opspace.spring.di.SonListener;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @describe: TODO
 * @Author MK
 * @PackageName openhome
 * @Package pers.mk.opspace.service.impl
 * @Date 2023/5/8 13:24
 * @Version 1.0
 */
@Service
@RequiredArgsConstructor
public class EventServiceImpl {

    private final List<SonListener> sonListenerList;

    public void startEvent(Father son){
        for (SonListener s : sonListenerList){
            s.onEvent(son);
        }
    }

}
