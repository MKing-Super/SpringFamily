package pers.mk.opspace.java.copy;

/**
 * @Description: TODO
 * @Author: kun.ma
 * @Date: 2023/1/31 15:12
 */
public class CloneChild implements Cloneable{

    private String son;

    public CloneChild() {
    }

    public CloneChild(String son) {
        this.son = son;
    }

    public String getSon() {
        return son;
    }

    public void setSon(String son) {
        this.son = son;
    }


    @Override
    public CloneChild clone() {
        try {
            return (CloneChild) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
