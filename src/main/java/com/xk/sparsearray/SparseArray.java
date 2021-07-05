package com.xk.sparsearray;

/**
 * 稀疏数组的实现
 * @author xk
 * @create 2021.07.04 17:50
 */
public class SparseArray {
    public static void main(String[] args) {
        int array[][] = new int[11][11];
        array[1][2]=1;
        array[2][3]=2;
        System.out.println("原数组如下:");
        for(int[] row:array){
            for(int data:row){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }
        //获取值不为0的个数
        int sum = 0;

        for(int[] row:array){
            for(int data:row){
                if(data != 0){
                    sum++;
                }
            }
        }
        System.out.println("个数:"+sum);

        int sparseArray[][] = new int[sum+1][3];
        sparseArray[0][0] = 11;
        sparseArray[0][1] = 11;
        sparseArray[0][2] = sum;
        int count=0;
        for(int i=0;i<11;i++){
            for(int j=0;j<11;j++){
                if(array[i][j]!=0){
                    count++;
                    sparseArray[count][0]=i;
                    sparseArray[count][1] = j;
                    sparseArray[count][2]=array[i][j];
                }
            }
        }
        System.out.println("稀疏数组如下:");
        for(int i=0;i<sparseArray.length;i++){
            System.out.printf("%d\t%d\t%d\n",sparseArray[i][0],sparseArray[i][1],sparseArray[i][2]);
        }
        System.out.println();

        int chessArr[][] = new int[sparseArray[0][0]][sparseArray[0][1]];

        for(int i=1;i<sparseArray.length;i++){
            chessArr[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }
        System.out.println("恢复后的数组如下:");
        for(int[] row:chessArr){
            for(int data:row){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }
    }
}
