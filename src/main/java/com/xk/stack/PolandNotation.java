package com.xk.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 逆波兰表达式
 * @author xk
 * @create 2021.07.08 9:07
 */
public class PolandNotation {

    public static void main(String[] args) {
        /*String expression = "3 4 + 5 * 6 -";
        List<String> list = getList(expression);
        int res = calculate(list);
        System.out.println("计算结果："+expression+"="+res);*/

        String expression = "10+((2+31)*4)-5";
        List<String> infixList = toInfixExpression(expression);
        System.out.println(infixList);
        List<String> suffixExpression = parseSuffixExpression(infixList);
        System.out.println("后缀表达式:"+suffixExpression);
        int res = calculate(suffixExpression);
        System.out.println("计算结果："+expression+"="+res);
    }

    /**
     * 将中缀表达式转为list
     * @param s
     * @return
     */
    public static List<String> toInfixExpression(String s){
        List<String> list = new ArrayList<String>();
        int i=0;//这是一个指针，用于遍历
        String str;//对多位数进行拼接
        char c;//接收每次读取的字符
        do{
            //是非数字，直接加入list
            if((c=s.charAt(i)) < 48 || c>57){
                list.add(""+c);
                i++;
            } else {
                str = "";//先置空
                while(i<s.length() && (c=s.charAt(i)) >=48 && c<=57){
                    str+=c;
                    i++;
                }
                list.add(str);
            }

        }while (i<s.length());

        return list;
    }

    /**
     * 中缀转后缀
     * @param ls
     * @return
     */
    public static List<String> parseSuffixExpression(List<String> ls){

        //符号栈
        Stack<String> s1 = new Stack<String>();
        // 存储中间结果
        List<String> s2 = new ArrayList<String>();

        for(String item:ls){
            // 如果是数字，进入时
            if(item.matches("\\d+")){
                s2.add(item);
            } else if(item.equals("(")){
                s1.push(item);
            }else if(item.equals(")")){
                //如果是右括号，依次弹出s1的符号，并压入s2,直到遇到左括号
                while(s1.size()>0 && !s1.peek().equals("(")){
                    s2.add(s1.pop());
                }
                s1.pop();//小括号出栈
            }else{
                //比较运算符的优先级
                // 当item的优先级小于等于s1栈顶运算符，将s1栈顶运算符弹出加入s2,
                // 直到遇到比item小的运算符直接将item入s1
                while (s1.size()!=0 && Operation.getValue(s1.peek()) >=Operation.getValue(item)){
                    s2.add(s1.pop());
                }
                // item进入符号栈
                s1.push(item);
            }
        }

        // 将剩下的符号压入s2
        while(s1.size() !=0){
            s2.add(s1.pop());
        }
        return s2;
    }


    /**
     * 计算后缀表达式
     * @param list 表达式元素组成的list
     * @return
     */
    public static int calculate(List<String> list){
        Stack<String> stack = new Stack<String>();
        for(String item:list){
            if(item.matches("\\d+")){
                stack.push(item);
            }else{
                // pop两个数
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if(item.equals("+")){
                    res = num1+num2;
                } else if(item.equals("-")){
                    res = num1-num2;
                } else if(item.equals("*")){
                    res = num1*num2;
                } else if(item.equals("/")){
                    res = num1/num2;
                } else {
                    throw new IllegalArgumentException("表达式不合法");
                }
                stack.push(""+res);
            }
        }
        //最后栈中的数据就是计算结果
        return Integer.parseInt(stack.pop());
    }

    /**
     * 表达式内容分割提取到list
     * @param expression
     * @return
     */
    public static List<String> getList(String expression){
        List<String> list = new ArrayList<String>();
        String[] split = expression.split(" ");
        for(String item:split){
            list.add(item);
        }
        return list;
    }

}

/**
 * 运算符
 */
class Operation{
    private static int ADD=1;
    private static int SUB=1;
    private static int MUL=2;
    private static int DIV=2;

    /**
     * 返回运算符优先级
     * @param operation
     * @return
     */
    public static int getValue(String operation){
        int result=0;
        switch (operation){
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("不存在该运算符");
                break;
        }
        return result;
    }
}