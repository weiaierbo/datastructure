package com.xk.tree.threadedbinary;

/** 线索二叉树
 * @author xk
 * @create 2021.07.18 15:04
 */
public class ThreadedBinaryDemo {
    public static void main(String[] args) {
        HeroNode root = new HeroNode(1,"tom");
        HeroNode node2 = new HeroNode(3,"jack");
        HeroNode node3 = new HeroNode(6,"smith");
        HeroNode node4 = new HeroNode(8,"mary");
        HeroNode node5 = new HeroNode(10,"king");
        HeroNode node6 = new HeroNode(14,"dim");

        root.setLeft(node2);
        root.setRight(node3);

        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree(root);
        threadedBinaryTree.threadedNodes(root);
        HeroNode left = node5.getLeft();
        System.out.println(node5);
        System.out.println(left);
        System.out.println(node5.getRight());

        threadedBinaryTree.threadedList();
    }
}



class ThreadedBinaryTree{

    private HeroNode root;

    //为了实现线索化，需要创建指向当前节点的前驱节点的指针
    private HeroNode pre;

    public ThreadedBinaryTree() {
    }

    public ThreadedBinaryTree(HeroNode root) {
        this.root = root;
    }

    public HeroNode getRoot() {
        return root;
    }

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    /**
     * 前序遍历
     */
    public void preOrder(){
        if(root != null){
            root.preOrder();
        }
    }

    /**
     * 中序遍历
     */
    public void infixOrder(){
        if(root != null){
            root.infixOrder();
        }
    }

    /**
     * 后续遍历
     */
    public void postOrder(){
        if(root != null){
            root.postOrder();
        }
    }

    /**
     * 前序遍历查找
     * @param id
     * @return
     */
    public HeroNode findPreOrder(int id){
        if(root != null){
            return root.findPreOrder(id);
        }
        return null;
    }

    /**
     * 中序遍历查找
     * @param id
     * @return
     */
    public HeroNode findInfixOrder(int id){
        if(root != null){
            return root.findInfixOrder(id);
        }
        return null;
    }

    /**
     * 后序遍历查找
     * @param id
     * @return
     */
    public HeroNode findPostOrder(int id){
        if(root != null){
            return root.findPostOrder(id);
        }
        return null;
    }

    /**
     * 遍历线索化二叉树的节点
     */
    public void threadedList(){
        HeroNode node = root;
        while(node != null){
            //循环找到leftType=1的结点，第一个就是
            //
            while(node.getLeftType() == 0){
                node = node.getLeft();
            }

            System.out.println(node);

            //如果当前节点的右指针指向后继节点，直接输出
            while(node.getRightType()==1){
                node = node.getRight();
                System.out.println(node);
            }

            node = node.getRight();
        }
    }

    /**
     * 中序线索化二叉树
     * @param node
     */
    public void threadedNodes(HeroNode node){
        if(node == null){
            return;
        }
        //先线索化左子树
        threadedNodes(node.getLeft());
        //线索化当前节点
        if(node.getLeft()== null){
            node.setLeft(pre);
            node.setLeftType(1);
        }
        if(pre!=null && pre.getRight()==null){
            pre.setRight(node);
            pre.setRightType(1);
        }
        //处理完后，将pre指向当前节点
        pre = node;
        //线索化右子树
        threadedNodes((node.getRight()));
    }
}

class HeroNode{

    private int id;
    private String name;
    private HeroNode left;
    private HeroNode right;

    // 左指针类型，约定0代表左子树，1代表指向前驱节点
    private int leftType;
    // 右指针类型，约定0代表左子树，1代表指向前驱节点
    private int rightType;

    public HeroNode(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", leftType=" + leftType +
                ", rightType=" + rightType +
                '}';
    }

    /**
     * 前序遍历   根-》左-》右
     */
    public void preOrder(){
        System.out.println(this);
        if(this.left != null){
            this.left.preOrder();
        }
        if(this.right != null){
            this.right.preOrder();
        }

    }

    /**
     * 中序遍历  左-》根-》右
     */
    public void infixOrder(){

        if(this.left != null){
            this.left.infixOrder();
        }
        System.out.println(this);
        if(this.right != null){
            this.right.infixOrder();
        }

    }

    /**
     * 后序遍历 左->右->根
     */
    public void postOrder(){

        if(this.left != null){
            this.left.postOrder();
        }
        if(this.right != null){
            this.right.postOrder();
        }
        System.out.println(this);
    }

    /**
     * 前序遍历查找
     * @param id
     * @return
     */
    public HeroNode findPreOrder(int id){
        if(this.id == id){
            return this;
        }
        HeroNode heroNode = null;
        if(this.left != null){
            heroNode = this.left.findPreOrder(id);
        }
        if(heroNode != null){
            return heroNode;
        }
        if(this.right != null){
            heroNode = this.right.findPreOrder(id);
        }
        return heroNode;
    }

    /**
     * 中序遍历查找
     * @param id
     * @return
     */
    public HeroNode findInfixOrder(int id){
        HeroNode heroNode = null;
        if(this.left != null){
            heroNode = this.left.findInfixOrder(id);
        }
        if(heroNode != null){
            return heroNode;
        }
        if(this.id == id){
            return this;
        }
        if(this.right != null){
            heroNode = this.right.findInfixOrder(id);
        }
        return heroNode;
    }

    /**
     * 后序遍历查找
     * @param id
     * @return
     */
    public HeroNode findPostOrder(int id){
        HeroNode heroNode = null;
        if(this.left != null){
            heroNode = this.left.findPostOrder(id);
        }
        if(heroNode != null){
            return heroNode;
        }
        if(this.right != null){
            heroNode = this.right.findPostOrder(id);
        }
        if(heroNode != null){
            return heroNode;
        }

        if(this.id == id){
            return this;
        }
        return heroNode;
    }
}
