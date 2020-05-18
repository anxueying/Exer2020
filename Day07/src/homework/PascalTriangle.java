package homework;

public class PascalTriangle {
    public static void main(String[] args) {
        //创建二维数组，长度为10
        int[][] Pascal = new int[10][];

        //直接对第1、2行做赋值
        Pascal[0] = new int[]{1};
        Pascal[1]=  new int[]{1,1};

        //从第三行开始，创建并用规律赋值
        for(int i=2;i<Pascal.length;i++){
            //创建一维数组
            Pascal[i] = new int[i+1];
            //赋值  也可用首位末位为1，从第三行起开始加法的思路做
            for(int j=0;j<Pascal[i].length;j++){
                if(j==0){
                    Pascal[i][j] = Pascal[i-1][j];
                }else if(j==Pascal[i].length-1){
                    Pascal[i][j] = Pascal[i-1][j-1];
                }else {
                    Pascal[i][j] = Pascal[i-1][j-1] + Pascal[i-1][j];
                }
            }
        }
        //打印
        for (int[] a : Pascal){
            for (int b : a){
                System.out.print(b+"\t");
            }
            System.out.println();
        }
    }
}
