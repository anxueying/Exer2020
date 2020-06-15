package easy;

/**
 * @author Mrs.An Xueying
 * 2020/6/15 23:43
 */
public class Solution14 {
    public static String longestCommonPrefix(String[] strs) {
        if (strs==null||strs.length==0){return "";}
        String pre = strs[0];
        for (int i = 1; i < strs.length; i++) {
            pre = getIndex(strs[i], pre);
            if (pre.length()==0){
                break;
            }
        }

        return pre;
    }

    public static String getIndex(String str1,String str2){

        int length = Math.min(str1.length() , str2.length() );
        int index = 0;
        while(index<length&&str1.charAt(index)==str2.charAt(index)){
            index++;
        }

        return str1.substring(0, index);
    }

    public static void main(String[] args) {
        String s = longestCommonPrefix(new String[]{"cc","c"});
        System.out.println(s);
    }
}
