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
 * 在集群中运行MR任务：
 * 1. 路径问题 - 数据读取和输出的路径
 * 2，打jar包（maven）
 * 3. 思考 打jar包的时候需不需要将依赖的jar包打进包里
 *      -  junit  不要
 *      -  log4j  不要  集群里有
 *      -  hadoop-client 不要 集群里有
 */
public class CountDriver2 {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        // 1. 获取job对象
        Job job = Job.getInstance(new Configuration());
        //2. 关联jar
        job.setJarByClass(CountDriver.class);
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

