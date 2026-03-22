package pers.mk.interview.algorithm.base;

import pers.mk.interview.model.BinaryTree;

public class Tree {
    public static void main(String[] args) {
        method1();
    }



    private static void method1(){
        BinaryTree tree = new BinaryTree();
        tree.insert(1);
        tree.insert(1);
        tree.insert(3);
        tree.insert(4);
        tree.insert(5);
        tree.insert(2);

        tree.preOrder();
        tree.inOrder();
        tree.postOrder();

        System.out.println(tree.getHeight());

        System.out.println(tree.getSize());

        System.out.println(tree.findMin().getVal());

        System.out.println(tree.findMax().getVal());

        System.out.println(tree.getRoot().getVal());
        tree.delete(1);

        tree.inOrder();
    }
}
