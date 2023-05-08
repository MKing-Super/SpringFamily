package pers.mk.api.model;

/**
 * @describe: TODO
 * @Author MK
 * @PackageName openhome
 * @Package pers.mk.api.model
 * @Date 2023/5/8 10:52
 * @Version 1.0
 */
public abstract class Father {
    private Object source;

    public Father() {
    }

    public Object getSource() {
        return source;
    }

    public void setSource(Object source) {
        this.source = source;
    }

    public Father(Object source) {
        this.source = source;
    }
}
