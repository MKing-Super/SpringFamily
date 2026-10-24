package pers.mk.interview.jzcw.algorithm;

import com.alibaba.fastjson.JSON;
import pers.mk.interview.model.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// 102. 二叉树的层序遍历
// 层序遍历 就是    广度优先搜索（BFS）
public class Quiz09 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(7)));

        // 结果对，但不是层序遍历，而是中序遍历！！！！
//        System.out.println(JSON.toJSONString(mk(root)));

        // 使用队列（Queue）实现  层序遍历
        System.out.println(JSON.toJSONString(m1(root)));
    }


    private static List<List<Integer>> mk(TreeNode root) {
        ArrayList<List<Integer>> list = new ArrayList<>();
        mkLayer(root,list,0);
        return list;
    }
    private static void mkLayer(TreeNode root,List<List<Integer>> list,int layerIndex){
        if (root == null){
            return;
        }
        if (layerIndex + 1 > list.size()){
            list.add(new ArrayList<Integer>());
        }
        mkLayer(root.left,list,layerIndex + 1);
        list.get(layerIndex).add(root.val);
        mkLayer(root.right,list,layerIndex + 1);
    }



    private static List<List<Integer>> m1(TreeNode root){
        ArrayList<List<Integer>> ret = new ArrayList<>();
        if (root == null){
            return ret;
        }

        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            ArrayList<Integer> level = new ArrayList<>();
            int curLevelSize = queue.size();
            for (int i = 0 ; i < curLevelSize ; i++){
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null){
                    queue.offer(node.left);
                }
                if (node.right != null){
                    queue.offer(node.right);
                }
            }
            ret.add(level);
        }
        return ret;
    }

}
