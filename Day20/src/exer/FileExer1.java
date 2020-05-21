package exer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Mrs.An Xueying
 * 2020/5/20 15:01
 */
public class FileExer1 {
    public static void main(String[] args) {
        File dir1 = new File("dir1");
        if (!dir1.exists()) {
            System.out.println("dir1.mkdir() = " + dir1.mkdir());
        }

        File dir2 = new File(dir1, "dir2");
        if (!dir2.exists()) {
            System.out.println("dir2.mkdir() = " + dir2.mkdir());
        }

        File dir3 = new File(dir1, "dir3/dir31");
        if (!dir3.exists()) {
            System.out.println("dir3.mkdirs() = " + dir3.mkdirs());
        }

        File dir4 = new File(dir1, "dir3/dir32");
        if (!dir4.exists()) {
            System.out.println("dir4.mkdirs() = " + dir4.mkdirs());
        }

        File file = new File(dir3, "test.txt");
        if (!file.exists()) {
            FileWriter fw = null;
            try {
                System.out.println("dir3.createNewFile() = " + file.createNewFile());
                fw = new FileWriter(file);
                fw.write("我是最厉害的");
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if (fw!=null){
                    try {
                        fw.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        System.out.println("文件大小： " + getDicSize(dir1) +"字节");
        System.out.println("删除是否成功： "+deleteAllFiles(dir1));
    }

    public static boolean deleteAllFiles(File file) {
        if (!file.exists()) {
            return true;
        }

        if (file.isDirectory()) {
            File[] files = file.listFiles();

            for (File file1 : files) {
                deleteAllFiles(file1);
            }

        }
        return file.delete();
    }

    /**
     * 计算目录的大小
     * @param file
     * @return
     */
    public static long getDicSize(File file){
        if (!file.exists()) {
            return -1L;
        }
        long size = 0L;
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files.length!=0){
                for (File file1 : files) {
                    size += getDicSize(file1);
                }
            }
        }else {
            size += file.length();
        }
        return size;
    }
}
