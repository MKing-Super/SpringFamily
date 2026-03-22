package pers.mk.interview.model;

public class BinaryTree {
    private TreeNode root;

    public BinaryTree() {
        this.root = null;
    }

    public BinaryTree(int val) {
        this.root = new TreeNode(val);
    }

    // 1. 插入节点
    public void insert(int val){
        root = insertRecursive(root,val);
    }

    private TreeNode insertRecursive(TreeNode node,int val){
        if (node == null){
            return new TreeNode(val);
        }

        if (val < node.val){
            node.left = insertRecursive(node.left,val);
        }else if (val > node.val){
            node.right = insertRecursive(node.right,val);
        }
        return node;
    }

    // 2. 查找节点
    public boolean search(int val){
        return searchRecursive(root,val);
    }

    private boolean searchRecursive(TreeNode node,int val){
        if (node == null){
            return false;
        }
        if (node.val == val){
            return true;
        }
        return searchRecursive(node.left,val) || searchRecursive(node.right,val);
    }

    // 3. 遍历算法
    // 前序遍历：根 -> 左 -> 右
    public void preOrder(){
        preOrderRecursive(root);
        System.out.println( );
    }

    private void preOrderRecursive(TreeNode node){
        if (node != null){
            System.out.print(node.val + " ");
            preOrderRecursive(node.left);
            preOrderRecursive(node.right);
        }
    }

    // 中序遍历：左 -> 根 -> 右
    public void inOrder(){
        inOrderRecursive(root);
        System.out.println();
    }

    private void inOrderRecursive(TreeNode node){
        if (node != null){
            inOrderRecursive(node.left);
            System.out.print(node.val + " ");
            inOrderRecursive(node.right);
        }
    }

    // 后序遍历：左 -> 右 -> 根
    public void postOrder() {
        postOrderRecursive(root);
        System.out.println();
    }

    private void postOrderRecursive(TreeNode node) {
        if (node != null) {
            postOrderRecursive(node.left);
            postOrderRecursive(node.right);
            System.out.print(node.val + " ");
        }
    }

    // 4. 获取树的高度
    public int getHeight() {
        return getHeightRecursive(root);
    }

    private int getHeightRecursive(TreeNode node){
        if (node == null){
            return 0;
        }
        int leftHeight = getHeightRecursive(node.left);
        int rightHeight = getHeightRecursive(node.right);
        return Math.max(leftHeight,rightHeight) + 1;
    }

    // 5. 获取节点数量
    public int getSize() {
        return getSizeRecursive(root);
    }

    private int getSizeRecursive(TreeNode node){
        if (node == null){
            return 0;
        }
        return getSizeRecursive(node.left) + getSizeRecursive(node.right) + 1;
    }


    // 6. 检查是否是空树
    public boolean isEmpty() {
        return root == null;
    }

    // 7. 获取根节点
    public TreeNode getRoot() {
        return root;
    }

    // 8. 查找最小节点
    public TreeNode findMin() {
        return findMinRecrsive(root);
    }

    private TreeNode findMinRecrsive(TreeNode node){
        if (node == null){
            return null;
        }
        if (node.left == null){
            return node;
        }
        return findMinRecrsive(node);
    }

    // 9. 查找最大节点
    public TreeNode findMax(){
        return findMaxRecursive(root);
    }

    private TreeNode findMaxRecursive(TreeNode node){
        if (node == null){
            return null;
        }
        if (node.right == null){
            return node;
        }
        return findMaxRecursive(node.right);
    }

    // 10. 删除节点
    public void delete(int val) {
        root = deleteRecursive(root, val);
    }

    private TreeNode deleteRecursive(TreeNode node,int val){
        if (node == null){
            return null;
        }

        if (val < node.val){
            node.left = deleteRecursive(node.left,val);
        }else if (val > node.val){
            node.right = deleteRecursive(node.right,val);
        }else {
            // 找到要删除的节点
            // 情况1：没有子节点
            if (node.left == null && node.right == null) {
                return null;
            }
            // 情况2：只有一个子节点
            else if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }
            // 情况3：有两个子节点
            else {
                // 找到右子树的最小节点
                TreeNode minNode = findMinRecrsive(node.right);
                node.val = minNode.val;
                node.right = deleteRecursive(node.right, minNode.val);
            }
        }
        return node;
    }

}
