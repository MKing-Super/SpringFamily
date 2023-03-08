package pers.mk.tools.converter.model;

import java.io.Serializable;

/**
 * @Author MK
 * @PackageName tools
 * @Package pers.mk.tools.converter.model
 * @Date 2023/3/8 11:01
 * @Version 1.0
 */
public class SnakeNode implements Serializable {
    private static final long serialVersionUID = -5213407666626906415L;

    private Integer x;

    private Integer y;

    private SnakeNode next;

    public SnakeNode(Integer x, Integer y, SnakeNode next) {
        this.x = x;
        this.y = y;
        this.next = next;
    }

    public SnakeNode() {
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public SnakeNode getNext() {
        return next;
    }

    public void setNext(SnakeNode next) {
        this.next = next;
    }
}
