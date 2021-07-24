package com.xk.binarysorttree;

/**
 * @author xk
 * @create 2021.07.24 17:07
 */
public class BinarySortTreeDemo {

    public static void main(String[] args) {
        int[] arr={7,7};//,10,12,5,1,9,0};
        BinarySortTree binarySortTree = new BinarySortTree();
        for(int i:arr){
            binarySortTree.add(new Node(i));
        }
        binarySortTree.infixOrder();
        System.out.println();
        binarySortTree.delNode(7);
        binarySortTree.infixOrder();
    }
}

class BinarySortTree{
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
}

class Node{

    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
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