package com.xk.linkedlist;

import java.util.Stack;

/**单链表
 * @author xk
 * @create 2021.07.05 9:08
 */
public class SingleLinkedListDemo {

    public static void main(String[] args) {

        SingleLinkedList linkedList = new SingleLinkedList();
        HeroNode heroNode = new HeroNode(1,"宋江","xx1");
        HeroNode heroNode2 = new HeroNode(3,"林冲","zz1");
        HeroNode heroNode1 = new HeroNode(2,"李逵","yy1");
        HeroNode heroNode3 = new HeroNode(2,"李逵2","yy2");

        linkedList.addData(heroNode);
        linkedList.addData(heroNode1);
        linkedList.addData(heroNode2);
        linkedList.showList();

        /*linkedList.addByOrder(heroNode);
        linkedList.addByOrder(heroNode2);
        linkedList.addByOrder(heroNode1);
        linkedList.addByOrder(heroNode3);

        linkedList.showList();
        System.out.println("修改后列表---");
        HeroNode heroNode4 = new HeroNode(4,"李逵3","yy3");
        linkedList.update(heroNode4);
        linkedList.showList();
*/
       /* linkedList.delete(1);
        linkedList.delete(3);
        linkedList.delete(2);
        System.out.println("删除后列表---");
        linkedList.showList();*/

        /*System.out.println(SingleLinkedList.getLength(linkedList.head));

        HeroNode lastNode = SingleLinkedList.findLastNode(linkedList.head, 2);
        System.out.println(lastNode.toString());*/

        /*System.out.println("转换后..");
        SingleLinkedList.reverseList(linkedList.head);
        linkedList.showList();*/
        System.out.println("反转输出");
        SingleLinkedList.reversePrint(linkedList.head);


    }
}

class SingleLinkedList{
    public HeroNode head = new HeroNode(0,null,null);

    /**
     * 添加元素
     * @param heroNode
     * @return
     */
    public boolean addData(HeroNode heroNode){
        HeroNode p = head;
        while(p.next != null){
            p = p.next;
        }
        p.next = heroNode;
        heroNode.next = null;
        return true;
    }

    public boolean addByOrder(HeroNode heroNode){
        HeroNode p = head;
        //是否允许插入的标识
        boolean flag = true;
        //找到相应的p结点，该结点位于待插入结点的前一个位置
        while(true){
            if(p.next == null){
                break;
            }
            if(p.next.no == heroNode.no){
                System.out.println("排名已存在!");
                flag = false;
                break;
            }else if(p.next.no > heroNode.no){
                break;
            }
            p = p.next;
        }
        if(!flag){
            return false;
        }
        heroNode.next = p.next;
        p.next = heroNode;

        return true;
    }

    /**
     * 更新
     * @param heroNode
     */
    public void update(HeroNode heroNode){
        if(isEmpty()){
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = head.next;
        // 是否找到的标志
        boolean flag = false;
        while (true){
            if(temp == null){
                break;
            }
            if(temp.no == heroNode.no){
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if(!flag){
            System.out.println("没有找到要更改的数据");
        } else {
            temp.name = heroNode.name;
            temp.nickName = heroNode.nickName;
        }
    }

    public void delete(int no){
        // 找到temp，指向待删除结点的前一个位置
        HeroNode temp = head;
        if(isEmpty()){
            System.out.println("链表为空");
            return;
        }
        boolean flag = false;
        while(true){
            if(temp.next == null){
                break;
            }
            if(temp.next.no == no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(flag){
            temp.next = temp.next.next;
        } else {
            System.out.println("没有找到要删除的数据");
        }
    }

    /**
     * 判断链表是否为空
     * @return
     */
    public boolean isEmpty(){
        if(head.next == null) {
            return true;
        }
        return false;
    }

    public void showList(){
        HeroNode p = head;
        if(p.next == null){
            System.out.println("链表为空");
            return;
        }
        while(p.next != null){
            p = p.next;
            System.out.println(p.toString());
        }
    }

    public void findIndex(){
        if(isEmpty()){
            return;
        }
        HeroNode temp = head.next;

    }

    /**
     * 找到倒数第n个位置的数据
     * @param head
     * @param index
     * @return
     */
    public static HeroNode findLastNode(HeroNode head,int index){
        if(head.next == null) {
            return null;
        }
        if(index <= 0){
            System.out.println("下标小于0");
            return null;
        }
        int length = getLength(head);
        if(index>length){
            System.out.println("下标大于链表长度");
            return null;
        }

        HeroNode cur = head.next;

        for(int i=0;i<length-index;i++){
            cur = cur.next;
        }
        return cur;
    }

    /**
     * 反转链表
     * @param heroNode
     */
    public static void reverseList(HeroNode heroNode){
        if(heroNode.next == null || heroNode.next.next == null){
            return;
        }
        // 指向当前节点
        HeroNode cur = heroNode.next;
        // 指向当前节点的下一个节点
        HeroNode next = null;

        HeroNode reverseHead = new HeroNode(0,"","");
        while (cur!= null){
            next = cur.next;
            cur.next = reverseHead.next;
            reverseHead.next = cur;
            cur = next;
        }

        heroNode.next = reverseHead.next;
    }

    /**
     * 从尾到头打印数据
     * 采用栈结构实现
     * @param heroNode
     */
    public static void reversePrint(HeroNode heroNode){

        if(heroNode.next == null){
            return;
        }
        Stack<HeroNode> stack = new Stack<HeroNode>();
        HeroNode temp = heroNode.next;
        while(temp != null){
            stack.add(temp);
            temp = temp.next;
        }

        while(stack.size()>0){
            HeroNode node = stack.pop();
            System.out.println(node.toString());
        }
    }

    public static int getLength(HeroNode head){
        if(head.next == null){
            return 0;
        }
        int length = 0;
        HeroNode cur = head.next;
        while (cur != null){
            length++;
            cur = cur.next;
        }
        return length;
    }


}

class HeroNode{

    public int no;
    public String name;
    public String nickName;
    public HeroNode next;


    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '}';
    }
}