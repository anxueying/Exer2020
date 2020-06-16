package com.atguigu.java;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;

/**
 * @author Mrs.An Xueying
 * 2020/6/13 14:21
 */
public class HDFSTest {
    FileSystem fs;
    Configuration conf;
    /**
     * 在执行任意一个单元测试前执行该方法
     * @throws IOException
     * @throws InterruptedException
     */
    @Before
    public void createObject() throws IOException, InterruptedException {
        /**
         * 1. 获取文件系统对象
         * get(
         * final URI uri,  //core-site.xml  HDFS中NameNode的地址  集群操作路径  hdfs://hadoop102:9820
         * final Configuration conf, //配置文件的对象 可以不配置，用默认 该配置只是针对本次操作有效，而不是改变集群配置
         * final String user //操作集群的用户 atguigu
         * )
         */
        conf = new Configuration();
        //副本数 从hdfs-default中找参数和值
        conf.set("dfs.replication", "1");
        fs = FileSystem.get(URI.create("hdfs://hadoop102:9820"), conf, "atguigu");
    }

    /**
     * 创建文件系统的对象
     */
    @Test
    public void test() throws IOException {

        /**
         * 2. 具体操作
         * 复制文件
         * boolean delSrc : 是否删除原文件
         * boolean overwrite : 如果目标地址已经存在和上传对象同名文件是否覆盖，true则覆盖，false则抛异常
         * Path src : 原文件  (本地）
         * Path dst : 目标地址 （HDFS)
         */
        fs.copyFromLocalFile(false,false,new Path("D:\\developer_tools\\200421JavaSE\\Items.txt"),new Path("/"));
    }

    /**
     * 在执行任意一个单元测试后再执行该方法
     */
    @After
    public void close(){
        /**
         * 3. 关资源
         */
        if(fs!=null){
            try {
                fs.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 下载文件
     */
    @Test
    public void download() throws IOException {
        /**
         * boolean delSrc, 是否删除源文件
         * Path src, 源文件
         * Path dst, 目标地址
         * boolean useRawLocalFileSystem  是否使用crc校验
         */
        fs.copyToLocalFile(false,new Path("/Items.txt"),new Path("D:\\developer_tools\\HadoopTest\\HDFS\\Items.txt"),true);
    }

    /**
     * 删除文件夹
     */
    @Test
    public void delete() throws IOException {
        /**
         * Path f, 路径
         * boolean recursive 目录必须true 否则抛异常 ；文件无所谓都可
         */
        boolean delete = fs.delete(new Path("/test"), true);
        System.out.println("是否成功删除："+delete);
    }

    /**
     * 改名
     */
    @Test
    public void rename() throws IOException {
        /**
         * path src 源文件
         * path dst 目标文件（修改名后的文件）或地址
         */
        fs.rename(new Path("/Items.txt"),new Path("/MyQuestions.txt"));
    }

    /**
     * 移动文件
     */
    @Test
    public void remove() throws IOException {
        /**
         * path src 源文件
         * path dst 目标文件（修改名后的文件）或地址
         */
        fs.rename(new Path("/MyQuestions.txt"),new Path("/sanguo/"));
    }

    /**
     * 查看文件详情
     */
    @Test
    public void listFile() throws IOException {
        /**
         * 获取迭代器
         * pathString  文件或目录
         * recursive 是否递归
         */
        RemoteIterator<LocatedFileStatus> localFile = fs.listFiles(new Path("/sanguo/"), true);
        //获取具体的数据
        while (localFile.hasNext()){

            //获取一个数据
            LocatedFileStatus file = localFile.next();
            //所有者
            String owner = file.getOwner();
            //所属组
            String group = file.getGroup();
            //副本数
            short replication = file.getReplication();
            //文件名
            String name = file.getPath().getName();
            //获取块信息
            BlockLocation[] blockLocations = file.getBlockLocations();
            for (BlockLocation block : blockLocations) {
                //输出块信息
                String[] blockHosts = block.getHosts();
                for (String blockHost : blockHosts) {
                    System.out.println(blockHost);
                }
            }
            System.out.println("======="+name+"========");
        }
    }


    /**
     * 判断是文件还是文件夹
     */
    @Test
    public void ifDirs() throws IOException {
        FileStatus[] fileStatuses = fs.listStatus(new Path("/sanguo/"));
        for (FileStatus file : fileStatuses) {
            String name = file.getPath().getName();
            boolean b1 = file.isFile();
            System.out.println(name+"是否为文件："+b1);
            boolean b2 = file.isDirectory();
            System.out.println(name+"是否为目录："+b2);
        }
    }

    /**
     * 上传和下载的本质就是IO流
     */
    @Test
    public void ioTest() throws IOException {
        //输入流：从本地读文件
        FileInputStream fis = new FileInputStream("D:\\developer_tools\\200421JavaSE\\Items.txt");
        //输出流：上传至HDFS
        FSDataOutputStream os = fs.create(new Path("/Items.txt"));
        //一边读一边写:文件对拷
        IOUtils.copyBytes(fis, os, conf);
        //关流
        IOUtils.closeStream(os);
        IOUtils.closeStream(fis);
    }
}
