package easy;

/**
 * @author Mrs.An Xueying
 * 2020/6/12 23:40
 */
public class Interview06 {
    public int[] reversePrint(ListNode head) {
        if(head==null){return new int[0];}
        ListNode node = head;
        int count = 0;
        while (node!=null){
            count++;
            node = node.next;
        }
        int[] result = new int[count];
        node = head;
        for (int i = count-1; i >= 0; i--) {
            result[i] = node.val;
            node = node.next;
        }

        return result;
    }
}