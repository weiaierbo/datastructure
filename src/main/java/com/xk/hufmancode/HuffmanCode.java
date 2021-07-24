package com.xk.hufmancode;

import java.util.*;

/**
 * @author xk
 * @create 2021.07.24 10:33
 */
public class HuffmanCode {

    public static void main(String[] args) {
        String content = "i like like like java do you like a java";
        byte[] bytes = content.getBytes();
        System.out.println(bytes.length);

        List<Node> nodes = getNodes(bytes);
        System.out.println(nodes);
        Node root = createHuffman(nodes);
        root.preOrder();
        //getCodes(root,"",stringBuilder);
        getCodes(root);
        System.out.println(huffmanCodeMap);
        byte[] huffmanZip = zip(bytes,huffmanCodeMap);
        //byte[] huffmanZip = huffmanZip(bytes);
        byte[] decode = decode(huffmanCodeMap, huffmanZip);
        System.out.println(new String(decode));
    }

    private static byte[] decode(Map<Byte,String> huffmanCodes,byte[] huffmanBytes){
        StringBuilder builder = new StringBuilder();
        for(int i=0;i<huffmanBytes.length;i++){
            //判断是不是最后一个字节
            boolean flag = (i==huffmanBytes.length-1);
            builder.append(byteToBitString(!flag,huffmanBytes[i]));
        }
        // 存储编码-》值的映射
        Map<String,Byte> map = new HashMap<>();
        for(Map.Entry<Byte,String> entry:huffmanCodes.entrySet()){
            map.put(entry.getValue(), entry.getKey());
        }
        List<Byte> list = new ArrayList<>();

        for(int i=0;i<builder.length();){
            int count=1;
            boolean flag = true;
            Byte b = null;
            while(flag){
                String key = builder.substring(i, i + count);
                b = map.get(key);
                //没匹配到
                if(b==null){
                    count++;
                }else{
                    flag = false;
                }
            }
            list.add(b);
            i+=count;
        }

        byte[] bytes = new byte[list.size()];
        for(int i=0;i<list.size();i++){
            bytes[i] = list.get(i);
        }
        return bytes;
    }
    /**
     * 将一个字节转换为字符串
     * @param flag 标志是否需要补高位，为true则需要补
     * @param b
     * @return
     */
    private static String byteToBitString(boolean flag,byte b){
        int temp = b;
        if(flag){
            temp|=256;
        }
        String str = Integer.toBinaryString(temp);
        if(flag){
            return str.substring(str.length()-8);
        } else {
            return str;
        }
    }

    private static byte[] huffmanZip(byte[] bytes){
        List<Node> nodes = getNodes(bytes);
        System.out.println(nodes);
        Node root = createHuffman(nodes);
        //getCodes(root,"",stringBuilder);
        getCodes(root);
        System.out.println(huffmanCodeMap);
        byte[] zip = zip(bytes, huffmanCodeMap);
        return zip;
    }

    private static byte[] zip(byte[] bytes,Map<Byte,String> huffmanMap){
        StringBuilder builder = new StringBuilder();
        for(byte b:bytes){
            builder.append(huffmanMap.get(b));
        }
        System.out.println(builder.toString());

        int len = (builder.length()+7)/8;
        byte[] by = new byte[len];
        int index=0;
        for(int i=0;i<builder.length();i+=8){
            String strByte;
            if(i+8>builder.length()){
                strByte = builder.substring(i);
            } else {
                strByte = builder.substring(i,i+8);
            }

            by[index] = (byte) Integer.parseInt(strByte,2);
            index++;
        }
        return by;
    }

    static Map<Byte,String> getCodes(Node root){
        if(root == null){
            return null;
        }
        getCodes(root.left,"0",stringBuilder);
        getCodes(root.right,"1",stringBuilder);
        return huffmanCodeMap;
    }

    static Map<Byte,String> huffmanCodeMap = new HashMap<>();
    static StringBuilder stringBuilder = new StringBuilder();
    private static void getCodes(Node node,String code,StringBuilder stringBuilder){
        StringBuilder stringBuilder1 = new StringBuilder(stringBuilder);
        stringBuilder1.append(code);
        if(node != null){
            //非叶子结点
            if(node.data == null){
                getCodes(node.left,"0",stringBuilder1);
                getCodes(node.right,"1",stringBuilder1);
            } else {
                huffmanCodeMap.put(node.data,stringBuilder1.toString());
            }
        }
    }

    /**
     * 构造node结点
     * @param bytes
     * @return
     */
    private static List<Node> getNodes(byte[] bytes){
        List<Node> nodes = new ArrayList<>();
        //统计每个字符出现的次数
        Map<Byte,Integer> map = new HashMap<>();
        for(Byte b:bytes){
            Integer count = map.get(b);
            if(count == null){
                map.put(b,1);
            } else {
                map.put(b,count+1);
            }
        }
        //把每个键值对转换为Node对象
        for(Map.Entry<Byte,Integer> entry:map.entrySet()){
            Node node = new Node(entry.getKey(), entry.getValue());
            nodes.add(node);
        }
        return nodes;
    }

    private static Node createHuffman(List<Node> nodes){
        while(nodes.size()>1){
            //排序从小到大
            Collections.sort(nodes);
            //获取最小的结点
            Node leftNode = nodes.get(0);
            //获取第二小的结点
            Node rightNode = nodes.get(1);
            //构造父节点
            Node parent  = new Node(null, leftNode.weight+rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parent);
        }
        //返回根节点
        return nodes.get(0);
    }
}

class Node implements Comparable<Node>{
    Byte data;
    int weight;
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        return this.weight-o.weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    public void preOrder(){
        System.out.println(this);

        if(this.left != null){
            this.left.preOrder();
        }

        if(this.right != null){
            this.right.preOrder();
        }
    }
}
