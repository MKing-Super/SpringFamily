package pers.mk.api.model;

/**
 * @describe: TODO
 * @Author MK
 * @PackageName openhome
 * @Package pers.mk.api.model
 * @Date 2023/5/8 10:53
 * @Version 1.0
 */
public class Son1 extends Father{
    private String msg;

    public Son1(Object source, String msg) {
        super(source);
        this.msg = msg;
    }

    public Son1() {
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
