package com.xk.search;

/**
 * @author xk
 * @create 2021.07.17 9:51
 */
public class HashTabDemo {

    public static void main(String[] args) {

    }


}
class Emp{
    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

class EmpLinkedList{

    public Emp head;

    public void add(Emp emp){
        if(head == null){
            head = emp;
            return;
        }

        Emp curEmp = head;
        while(true){
            if(curEmp.next == null){
                break;
            }
            curEmp = curEmp.next;
        }
        curEmp.next = emp;
    }

    public void list(){
        if(head == null){
            System.out.println("空");
            return;
        }
        Emp curTemp = head;
        while(true){
            System.out.println("id:"+curTemp.id+" ,name:"+curTemp.name);
            curTemp = curTemp.next;
            // 是最后节点
            if(curTemp == null){
                break;
            }
        }
    }
}
