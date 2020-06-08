package hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Mrs.An Xueying
 * 2020/6/7 23:00
 * 给定两个单词（beginWord 和 endWord）和一个字典 wordList，找出所有从 beginWord 到 endWord 的最短转换序列。转换需遵循如下规则：
 * 每次转换只能改变一个字母。
 * 转换过程中的中间单词必须是字典中的单词。
 */
public class Solution126 {

    /**
     * 所有单词具有相同的长度。
     * 所有单词只由小写字母组成。
     * @param beginWord 非空的，且二者不相同。
     * @param endWord 非空的，且二者不相同。
     * @param wordList 字典中不存在重复的单词。
     * @return 如果不存在这样的转换序列，返回一个空列表。
     */
    public static List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        //单词长度 hit  3   目标单词 cog

        // 有哪几位不同  0 1 2  数组dif（记录不同的索引,1为不同，0为相同）  转换路径ret
        int[] dif = stringDif(beginWord, endWord);
        List<List<String>> ret = new ArrayList<>();
        // 索引0  h->c   看字典中是否有cit

        // 索引1  i->o   看字典中是否有hot   √  移除数组中的 hit  cog   hot   //ret  "hit","hot"
        //--------------------------------------------------------------
            // wordList  "dot","dog","lot","log"
            //如有，删除dif中该索引，取得现在的单词hot，进行下一轮
            // 索引0  h->c   看字典中是否有cot
            // 索引2  t->g   看字典中是否有hog
            //都无，找数组中和自己有一位不同的

                //dot  取得现在的单词dot，进行下一轮  cog  //ret  "hit","hot","dot"
                // "dog","lot","log"
                // 索引0  d->c   看字典中是否有dot
                // 索引2  t->g   看字典中是否有dog  √   //ret  "hit","hot","dot","dog"
                //dog  只有一个字母不一样，不用比了，返回ret
                //--------------------------------------------------------------
                //lot  取得现在的单词lot，进行下一轮  cog //ret  "hit","hot","lot"
                // "dot","dog","log"
                // 索引0  l->c   看字典中是否有cot
                // 索引2  t->g   看字典中是否有log √  //ret  "hit","hot","dot","log"
                //dog  只有一个字母不一样，不用比了，返回ret
        //--------------------------------------------------------------
        // 索引2  t->g   看字典中是否有hig
        return ret;
    }

    public static int[] stringDif(String str1,String str2){
        int[] difNums = new int[str1.length()];
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        for (int i = 0; i < chars1.length; i++) {
            if (chars1[i]!=chars2[i]){
                difNums[i] = 1;
            }else {
                difNums[i] = 0;
            }
        }
        return difNums;
    }

    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = Arrays.asList("hot","dot","dog","lot","log","cog");
        List<List<String>> result = findLadders(beginWord, endWord, wordList);
        for (List<String> stringList : result) {
            for (String s : stringList) {
                System.out.println(s);
            }
        }
    }
}
