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
 * @author Mrs.An Xueying
 * 2020/6/16 9:29
 * 驱动类
 * 1， 作为程序的入口
 * 2. 进行相关的一些关联
 * 3. 参数的设置
 */
public class CountDriver {

    /**
     * 1. 获取job对象
     * 2. 关联jar
     * 3. 关联mapper和reducer
     * 4. 设置mapper的输出类型
     * 5. 设置最终（reducer）输出的key和value的类型
     * 6. 设置输入输出路径
     * 7. 提交job任务
     * @param args
     */

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
        FileInputFormat.setInputPaths(job,new Path("D:\\input"));
        //此目录必须不存在
        FileOutputFormat.setOutputPath(job,new Path("D:\\output"));
        // 7. 提交job任务  参数true ：打印进度
        boolean isSuccess = job.waitForCompletion(true);
        //虚拟机退出的状态：0正常退出 1不正常退出
        //System.exit(isSuccess?0:1);
    }
}



