package pers.mk.opspace.java.copy;

/**
 * @Description: TODO
 * @Author: kun.ma
 * @Date: 2023/1/31 15:11
 */
public class Clone implements Cloneable{

    private Integer id;
    private String name;
    private CloneChild cloneChild;

    public Clone() {
    }

    public Clone(Integer id, String name, CloneChild cloneChild) {
        this.id = id;
        this.name = name;
        this.cloneChild = cloneChild;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CloneChild getCloneChild() {
        return cloneChild;
    }

    public void setCloneChild(CloneChild cloneChild) {
        this.cloneChild = cloneChild;
    }


    @Override
    public Object clone() {
        try {
            Clone clone = (Clone) super.clone();
            clone.cloneChild = (CloneChild) this.cloneChild.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
