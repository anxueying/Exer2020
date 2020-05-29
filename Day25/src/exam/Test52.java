package exam;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Mrs.An Xueying
 * 2020/5/28 10:14
 * 2.有一个字符串，其中包含中文字符、英文字符和数字字符，
 * 请统计和打印出各个字符的字数。
 * 举例说明： String content = “中中国55kkfff”;
 * 统计出：中：2, 国：1,  5：2,  k：2,  f：32
 */
public class Test52 {
    public static void main(String[] args) {
        String content = "中中国55kkfff";
        HashMap<Character, Integer> hashMap = countNum(content);
        Set<Map.Entry<Character, Integer>> entrySet = hashMap.entrySet();
        for (Map.Entry<Character, Integer> entry : entrySet) {
            System.out.println(entry.getKey() + ":" +entry.getValue());
        }
    }

    public static HashMap<Character,Integer> countNum(String content){
        char[] chars = content.toCharArray();
        HashMap<Character,Integer> hashMap = new HashMap<>();
        for (char c : chars) {
            if (hashMap.get(c)==null){
                hashMap.put(c,1);
            }else {
                hashMap.put(c, hashMap.get(c)+1);
            }
        }
        return hashMap;
    }
}
