package pers.mk.opspace.java.copy;

/**
 * @Description: 深拷贝
 * @Author: kun.ma
 * @Date: 2023/1/31 15:10
 */
public class DeepCopy {

    public static void main(String[] args) {
        Clone clone = new Clone(123,"水电费",new CloneChild("轻微"));
        System.out.println(clone.hashCode());
        System.out.println(clone.getCloneChild().hashCode());
        System.out.println("-------------------------------");
        Clone clone1 = (Clone)clone.clone();
        System.out.println(clone1.hashCode());
        System.out.println(clone1.getCloneChild().hashCode());
    }


}
