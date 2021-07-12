package com.xk.recursion;

/**迷宫问题
 * @author xk
 * @create 2021.07.10 16:51
 */
public class MiGong {

    public static void main(String[] args) {
        //初始化地址
        int[][] map = new int[8][7];
        //使用1表示墙
        // 初始化上下的墙
        for(int i=0;i<7;i++){
            map[0][i]=1;
            map[7][i]=1;
        }
        // 左右的墙
        for(int i=0;i<8;i++){
            map[i][0]=1;
            map[i][6]=1;
        }
        map[3][1]=1;
        map[3][2]=1;
        //输出地图
        for(int i=0;i<8;i++){
            for(int j=0;j<7;j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
        setWay(map,1,1);
        System.out.println();
        //输出地图
        for(int i=0;i<8;i++){
            for(int j=0;j<7;j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }

    /**
     * 使用递归回溯给小球找路
     * i,j表示从地图的哪个位置开始寻找
     * 如果能到map[6][5]，说明通路找到
     * 约定，0表示没走过，为1表示墙，为2表示可以走，为3表示已走过单走不通
     * 走迷宫，按下右上走的顺序尝试
     * @param map
     * @param i
     * @param j
     * @return
     */
    public static boolean setWay(int[][] map,int i,int j){

        if(map[6][5]==2){
            return true;
        } else {
            if(map[i][j] == 0){

                map[i][j]=2;//假定可以走
                if(setWay(map,i+1,j)){
                    return true;
                } else if(setWay(map,i,j+1)){
                    return true;
                } else if(setWay(map,i-1,j)){
                    return true;
                } else if (setWay(map,i-1,j-1)){
                    return true;
                } else {
                    map[i][j]=3;
                    return false;
                }

            } else {
                return false;
            }
        }
    }
}
