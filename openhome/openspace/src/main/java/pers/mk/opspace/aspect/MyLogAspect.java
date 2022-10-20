package pers.mk.opspace.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @Description: TODO
 * @Author: kun.ma
 * @Date: 2022/10/9 13:41
 */
@Aspect
@Component
@Slf4j
public class MyLogAspect {

    @Pointcut("@annotation(pers.mk.opspace.aspect.MyLogAnnotation)")
    public void pointcut() {

    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) {
        Object result = null;
        long beginTime = System.currentTimeMillis();
        try {
            result = point.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        long time = System.currentTimeMillis() - beginTime;
//        saveLog(point, time);
        return result;
    }

}
