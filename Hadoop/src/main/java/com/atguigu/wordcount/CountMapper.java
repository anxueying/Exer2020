package com.atguigu.wordcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author Mrs.An Xueying
 * 2020/6/16 9:29
 * 1. 自定义的类需要继承Mapper，Mapper的四个泛型如下：
 * Mapper<KEYIN, VALUEIN, KEYOUT, VALUEOUT>代表数据类型，是两对，分别为：
 *     ①  输入：KEYIN, VALUEIN
 *          KEYIN：数据的偏移量，一行一行读数据，用来记录数据读到哪里了
 *          VALUEIN：实际读取的具体的一行数据
 *     ②  输出：KEYOUT, VALUEOUT
 *          KEYOUT：单词
 *          VALUEOUT：单词出现的数量（1）
 */
public class CountMapper extends Mapper<LongWritable, Text,Text, IntWritable> {
    /**
     * (KEYOUT, VALUEOUT)
     */
    private Text outkey = new Text();
    private IntWritable outValue = new IntWritable(1);
    /**
     *      * Called once for each key/value pair in the input split. Most applications
     *      * should override this, but the default is the identity function.
     * 该方法用来处理具体的业务逻辑
     * @param key 输入数据的KEYIN，数据的偏移量
     * @param value 输入数据的VALUEIN，实际读取的具体的一行数据
     * @param context 上下文 在这里用来将数据写出去
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    @SuppressWarnings("unchecked")
    protected void map(LongWritable key, Text value,
                       Context context) throws IOException, InterruptedException {
        //1. 先将读进来的一行数据转换成String便于操作
        String line = value.toString();
        //2. 切割数据（按照空格切）
        String[] words = line.split(" ");
        //3. 遍历所有的单词并进行封装（K,V)
        for (String word : words) {
            //写数据
            outkey.set(word);
            context.write(outkey,outValue);
        }
    }

}
