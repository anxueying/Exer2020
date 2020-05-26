package homework;

import java.io.File;

/**
 * @author Mrs.An Xueying
 * 2020/5/22 15:53
 */
public class ThreadTest4 {
    public static void main(String[] args) {
        File file = new File("D:\\developer_tools\\200421JavaSE\\Day20\\src\\exer");


        new Thread(new Runnable(){

            @Override
            public void run() {
                if (file.exists()&&file.list().length!=0){
                    for (File listFile : file.listFiles()) {
                        System.out.println(listFile.getName()+"\t文件大小："+listFile.length());
                    }
                }
            }
        }).start();


        new Thread(new Runnable(){

            @Override
            public void run() {
                int fileNum = 0;
                long fileSize = 0L;
                if (file.exists()&&file.list().length!=0){
                    for (File listFile : file.listFiles()) {
                        fileNum++;
                        fileSize += listFile.length();
                    }
                }
                System.out.println("文件个数："+fileNum);
                System.out.println("文件大小："+fileSize);
            }
        }).start();


    }
}
