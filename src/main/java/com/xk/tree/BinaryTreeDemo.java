package com.xk.tree;

/**二叉树
 * @author xk
 * @create 2021.07.17 11:14
 */
public class BinaryTreeDemo {

    public static void main(String[] args) {

        /**
         *
         *    1
         *   2 3
         *  4 5
         */
        BinaryTree binaryTree = new BinaryTree();
        HeroNode heroNode1 = new HeroNode(1,"xx");
        HeroNode heroNode2 = new HeroNode(2,"yy");
        HeroNode heroNode3 = new HeroNode(3,"zz");
        HeroNode heroNode4 = new HeroNode(4,"cc");
        HeroNode heroNode5 = new HeroNode(5,"vv");
        binaryTree.setRoot(heroNode1);

        heroNode1.setLeft(heroNode2);
        heroNode1.setRight(heroNode3);

        heroNode2.setLeft(heroNode4);
        heroNode2.setRight(heroNode5);

        /*binaryTree.preOrder();
        System.out.println("-------------------------");
        binaryTree.infixOrder();
        System.out.println("-------------------------");
        binaryTree.postOrder();*/
        System.out.println(binaryTree.findPreOrder(2));
        System.out.println(binaryTree.findInfixOrder(2));
        System.out.println(binaryTree.findPostOrder(2));

    }

}

class BinaryTree{

    private HeroNode root;

    public BinaryTree() {
    }

    public BinaryTree(HeroNode root) {
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
}

class HeroNode{

    private int id;
    private String name;
    private HeroNode left;
    private HeroNode right;

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

    @Override
    public String toString() {
        return "HeroNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
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
