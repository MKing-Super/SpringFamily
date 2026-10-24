package pers.mk.interview.jzcw.algorithm;

import com.alibaba.fastjson.JSON;
import pers.mk.interview.model.TreeNode;

import java.util.ArrayList;
import java.util.List;

// 94. 二叉树的中序遍历
// 二叉树的中序遍历属于   深度优先搜索（DFS）
// 前序遍历​    根 → 左 → 右
// 中序遍历     左 → 根 → 右
// 后序遍历     左 → 右 → 根
public class Quiz08 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1, null, new TreeNode(2, new TreeNode(3), null));

        // 不是中序遍历！！！
//        System.out.println(JSON.toJSONString(mk(root)));


        System.out.println(JSON.toJSONString(m1(root)));
    }


    private static List<Integer> mk(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        mkLayer(root,list);
        return list;
    }
    private static void mkLayer(TreeNode node,List<Integer> list){
        if (node == null){
            return;
        }else {
            list.add(node.val);
        }
        mkLayer(node.left,list);
        mkLayer(node.right,list);
    }


    private static List<Integer> m1(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        m1Layer(root,list);
        return list;
    }
    private static void m1Layer(TreeNode node,List<Integer> list){
        if (node == null){
            return;
        }
        m1Layer(node.left,list);
        list.add(node.val);
        m1Layer(node.right,list);
    }

}
