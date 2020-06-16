package com.atguigu.wordcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author Mrs.An Xueying
 * 2020/6/16 9:29
 * 1. 自定义的类需要继承Reducer，Reducer的四个泛型如下：
 * Reducer<KEYIN, VALUEIN, KEYOUT, VALUEOUT>代表数据类型，是两对，分别为：
 *     ①  输入：KEYIN, VALUEIN
 *          KEYIN：mapper中输出的key的类型
 *          VALUEIN：mapper中输出的value的类型
 *     ②  输出：KEYOUT, VALUEOUT
 *          KEYOUT：单词
 *          VALUEOUT：单词出现的数量（1）
 */
public class CountReducer extends Reducer<Text, IntWritable, Text,IntWritable> {
    /**
     * KEYOUT  不用封装了，直接用key即可
     * VALUEOUT
     */
    private IntWritable outValue = new IntWritable();
    /**
     * 该方法就是具体操作业务逻辑的方法
     * （一组一组的读数据，key相同则为一组，因此一个key，对应一组value）
     * @param key 单词
     * @param values 相同单词的一组value
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        //累加value值
        int sum = 0;
        //遍历所有的value
        for (IntWritable value : values) {
            //value.get()：将IntWritable转成基本数据类型int
            sum += value.get();
            //封装（K,V）
            outValue.set(sum);
            //将数据写出去
            context.write(key,outValue);
        }
    }
}
