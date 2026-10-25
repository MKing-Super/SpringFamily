package pers.mk.interview.jzcw.algorithm;

import pers.mk.interview.model.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

// 101. 对称二叉树
public class Quiz10 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1, new TreeNode(2, new TreeNode(3), new TreeNode(4)), new TreeNode(2, new TreeNode(4), new TreeNode(3)));
        // 递归   深度优先搜索     dfs
//        System.out.println(m1(root));

        // 迭代   广度优先搜索     bfs
        System.out.println(m2(root));
    }


    private static boolean m1(TreeNode root) {
        return dfs(root.left,root.right);
    }

    private static boolean dfs(TreeNode L,TreeNode R){
        if (L == null && R == null){
            return true;
        }
        if (L == null || R == null){
            return false;
        }

        boolean b1 = L.val == R.val;
        boolean b2 = dfs(L.left, R.right);
        boolean b3 = dfs(L.right, R.left);
        return b1 && b2 && b3;
    }




    private static boolean m2(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root.left);
        queue.offer(root.right);
        boolean pass = true;
        while (!queue.isEmpty()){
            TreeNode L = queue.poll();
            TreeNode R = queue.poll();
            if (L == null && R ==null){
                continue;
            }
            if (L == null || R == null){
                pass = false;
                break;
            }
            if (L.val != R.val){
                pass = false;
                break;
            }

            TreeNode ll = L.left;
            TreeNode rr = R.right;

            TreeNode lr = L.right;
            TreeNode rl = R.left;

            queue.offer(ll);
            queue.offer(rr);
            queue.offer(lr);
            queue.offer(rl);
        }
        return pass;
    }

}
