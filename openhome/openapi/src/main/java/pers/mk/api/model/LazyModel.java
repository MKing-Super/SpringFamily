package pers.mk.api.model;

/**
 * @describe: TODO
 * @Author MK
 * @PackageName openhome
 * @Package pers.mk.api.model
 * @Date 2023/5/8 16:07
 * @Version 1.0
 */
public class LazyModel {
    private String msg;

    public LazyModel() {
        System.out.println("这是延迟加载 - LazyModel");
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
