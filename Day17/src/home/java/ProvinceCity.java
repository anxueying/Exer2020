package home.java;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @author axy
 * day16 map课后练习 第3题
 */
public class ProvinceCity {
    public static void main(String[] args) {
        HashMap<String, HashSet<String>> hashMap = new HashMap<>();
        hashMap.put("浙江省",new HashSet<>());
        hashMap.get("浙江省").addAll(Arrays.asList("绍兴市","温州市","湖州市","嘉兴市","台州市","金华市","舟山市","衢州市","丽水市"));

        hashMap.put("海南省",new HashSet<>());
        hashMap.get("海南省").add("海口市");
        hashMap.get("海南省").add("三亚市");

        hashMap.put("北京市",new HashSet<>());
        hashMap.get("北京市").add("北京市");

        for (Map.Entry<String, HashSet<String>> entry : hashMap.entrySet()) {
            System.out.println(entry.getKey());
            for (String city : entry.getValue()) {
                System.out.println("\t"+city);
            }
        }
    }
}
