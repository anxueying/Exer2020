package com.atguigu.wordcount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import java.io.IOException;

/**
 * 在本地提交任务到集群上
 * 1. 配置一些内容
 * 2， 打包 ： 打包前：job.setJarByClass(CountDriver.class);
 */
public class CountDriver3 {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        //配置集群信息
        Configuration conf = new Configuration();
        conf.set("fs.deaultFS","hdfs://hadoop102:9820");
        conf.set("mapreduce.framework.name", "yarn");
        conf.set("mapreduce.app-submission.cross-platform", "true");
        conf.set("yarn.resourcemanager.hostname", "hadoop103");


        // 1. 获取job对象
        Job job = Job.getInstance(new Configuration());
        //2. 关联jar
        //打包前：
        //打包后：
        job.setJar("D:\\developer_tools\\HadoopTest\\Hadoop\\target\\Hadoop-1.0-SNAPSHOT.jar");
        //3. 关联mapper和reducer
        job.setMapperClass(CountMapper.class);
        job.setReducerClass(CountReducer.class);
        //4. 设置mapper的输出类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        //5. 设置最终（reducer）输出的key和value的类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        //6. 设置输入输出路径
        FileInputFormat.setInputPaths(job,new Path(args[0]));

        FileOutputFormat.setOutputPath(job,new Path(args[1]));
        // 7. 提交job任务  参数true ：打印进度
        boolean b = job.waitForCompletion(true);
        //虚拟机退出的状态：0正常退出 1不正常退出
        System.exit(b?0:1);
    }
}