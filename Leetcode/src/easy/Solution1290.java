package easy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Mrs.An Xueying
 * 2020/6/1 11:44
 */
public class Solution1290 {
    public int getDecimalValue(ListNode head) {
        int num = 0;
        List<Integer> list = new ArrayList<>();
        ListNode temp = head;
        while(temp!=null){
            list.add(temp.val);
            temp = temp.next;
        }
        for (int i = list.size()-1; i >=0; i--) {
            num += list.get(i)*Math.pow(2, list.size()-1-i);
        }
        return num;
    }
}




class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

