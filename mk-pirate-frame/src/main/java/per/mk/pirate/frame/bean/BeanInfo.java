package per.mk.pirate.frame.bean;

import java.lang.reflect.Method;

/**
bean 信息处理类
 **/
public class BeanInfo {
    String url;
    Object object;
    Method method;

    public BeanInfo(String url, Object object, Method method) {
        this.url = url;
        this.object = object;
        this.method = method;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

}
