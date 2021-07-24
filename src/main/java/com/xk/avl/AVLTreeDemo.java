package com.xk.avl;

/**
 * @author xk
 * @create 2021.07.24 20:48
 */
public class AVLTreeDemo {
    public static void main(String[] args) {
        /*int[] arr={4,3,6,5,7,8};*///适合左旋
        int[] arr={10,12,8,9,7,6};//适合右旋
        AVLTree avlTree = new AVLTree();
        for(int i=0;i<arr.length;i++){
            avlTree.add(new Node(arr[i]));
        }
        avlTree.infixOrder();
        System.out.println();
        System.out.println(avlTree.getRoot().height());
        System.out.println(avlTree.getRoot().leftHeight());
        System.out.println(avlTree.getRoot().rightHeight());
    }
}
class AVLTree{
    private Node root;

    /**
     * 查找指定节点
     * @param value
     * @return
     */
    public Node search(int value){
        if(root != null){
            return root.search(value);
        }
        return null;
    }

    /**
     * 查找父节点
     * @param value
     * @return
     */
    public Node searchParent(int value){
        //增强判断
        if(root == null || root.value==value){
            return null;
        }
        return root.searchParent(value);
    }

    /**
     * 删除node为根节点的二叉排序树的最小结点
     * @param node
     * @return
     */
    public int deleteRightTreeMin(Node node){
        Node target = node;
        while(target.left !=null){
            target = target.left;
        }
        //此时target指向了最小结点
        delNode(target.value);
        return target.value;
    }

    public void delNode(int value){
        if(root == null){
            return;
        } else {
            Node targetNode = search(value);
            if(targetNode == null){
                return;
            }
            //如果当前数只有一个结点（根节点)
            if(root.left == null && root.right == null){
                root=null;
                return;
            }

            //查找父节点
            Node parent = searchParent(value);
            //叶子结点
            if(targetNode.left == null && targetNode.right == null){
                //判断targetNode是parent的左子结点还是右子结点
                if(parent.left!=null && parent.left.value==value){
                    parent.left=null;
                }else if(parent.right!=null && parent.right.value == value){
                    parent.right=null;
                }
            } else if(targetNode.left!=null &&targetNode.right!=null){//删除有两棵子树的结点
                //从右子树寻找最小的结点
                int min = deleteRightTreeMin(targetNode.right);
                targetNode.value = min;
            } else {//删除只有一棵子树的结点
                //如果要删除的结点有左子结点
                if(targetNode.left!=null){
                    //说明targetNode是根节点,等价于targetNode==root
                    if(parent == null){
                        root = targetNode.left;
                    }else {
                        //targetNode是parent的左子结点
                        if(parent.left.value == value){
                            parent.left = targetNode.left;
                        } else {
                            parent.right = targetNode.left;
                        }
                    }
                } else {
                    //要删除的是根结点
                    if(parent == null){
                        root = targetNode.right;
                    }else{
                        //要删除的结点有右子结点
                        //targetNode是parent的左子结点
                        if(parent.left !=null && parent.left.value == value){
                            parent.left = targetNode.right;
                        } else {
                            parent.right = targetNode.right;
                        }
                    }
                }
            }
        }
    }
    /**
     * 添加结点
     * @param node
     */
    public void add(Node node){
        if(root == null){
            root = node;
        } else {
            root.add(node);
        }
    }

    /**
     * 中序遍历
     * 输出的结果是有序的
     */
    public void infixOrder(){
        if(root != null){
            root.infixOrder();
        } else {
            System.out.println("无数据");
        }
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }
}

class Node{

    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }
    public int rightHeight(){
        if(right == null){
            return 0;
        }
        return right.height();
    }
    public int leftHeight(){
        if(left == null){
            return 0;
        }
        return left.height();
    }
    //返回已该结点为根结点的树的高度
    public int height(){
        return Math.max(left==null?0: left.height(), right==null?0: right.height())+1;
    }

    //左旋
    private void leftRotate(){
        Node newNode = new Node(value);
        //把新的节点的左子树设置成当前结点的左子树
        newNode.left = left;
        //把新节点的右子树设置成当前结点右子树的左子树
        newNode.right = right.left;
        //把当前结点的值设置成右子结点的值
        value = right.value;
        //把当前结点的右子树设置成当前结点右子树的右子树
        right = right.right;
        //把新结点作为当前结点的左子树
        left = newNode;
    }

    /**
     * 右旋
     */
    private void rightRotate(){
        Node newNode = new Node(value);
        newNode.right = right;
        newNode.left = left.right;
        value = left.value;
        left = left.left;
        right = newNode;
    }

    /**
     * 查找结点
     * @param value
     * @return
     */
    public Node search(int value){
        if(value == this.value){
            return this;
        }
        //左子树查找
        if(value < this.value){
            //左子树为空
            if(this.left == null){
                return null;
            }
            return this.left.search(value);
        }else{
            if(this.right == null){
                return null;
            }
            return this.right.search(value);
        }
    }

    public Node searchParent(int value){

        if((this.left != null && this.left.value==value) || (this.right!= null && this.right.value== value)){
            return this;
        } else {
            if(value <this.value && this.left != null){
                return this.left.searchParent(value);
            } else if(value>=this.value && this.right !=null){
                return this.right.searchParent(value);
            } else {
                return null;
            }
        }

    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }


    public void add(Node node){
        if(node == null){
            return;
        }
        if(node.value < this.value){
            if(this.left == null){
                this.left = node;
            } else {
                this.left.add(node);
            }
        } else {
            if(this.right == null){
                this.right = node;
            } else {
                this.right.add(node);
            }
        }
        if(rightHeight()-leftHeight()>1){
            //如果右子树的左子树高度大于右子树的右子树高度
            if(right!=null && right.leftHeight()>right.rightHeight()){
                //先对右子树右旋
                right.rightRotate();
                //再左旋
                leftRotate();
            }else{
                leftRotate();
            }
            return;
        }
        if(leftHeight()-rightHeight()>1){
            //如果左子树的右子树高度大于左子树的左子树高度
            if(left != null && left.rightHeight()>left.leftHeight()){
                //先左旋
                left.leftRotate();
                //再右旋
                rightRotate();
            }else{
                rightRotate();
            }

        }
    }

    /**
     * 中序遍历
     */
    public void infixOrder(){
        if(this.left !=null){
            this.left.infixOrder();
        }
        System.out.println(this.value);
        if(this.right != null){
            this.right.infixOrder();
        }
    }
}