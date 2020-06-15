package com;

import java.util.*;

/**
 * @author Mrs.An Xueying
 * 2020/6/14 10:30
 */
public class s5436 {

    public static int[] runningSum(int[] nums) {
        int[] result = new int[nums.length];
        //每次加数的个数
        for (int i = 1; i <= nums.length; i++) {
            int sum = 0;
            for (int j = 0; j < i; j++) {
                sum += nums[j];
            }
            result[i - 1] = sum;
        }
        return result;

    }

    public static int findLeastNumOfUniqueInts(int[] arr, int k) {
        HashMap<Integer, Integer> countNum = new HashMap<>();
        for (int a = 0; a < arr.length; a++) {
            if (countNum.containsKey(arr[a])) {
                countNum.put(arr[a], countNum.get(arr[a]) + 1);
            } else {
                countNum.put(arr[a], 1);
            }
        }


        System.out.println(countNum);

        Set<Integer> valueSet = new TreeSet<>();
        int valueSum = 0;
        for (Integer value : countNum.values()) {
            valueSum += value;
            valueSet.add(value);
        }

        if (valueSum < k) {
            return 0;
        }
        Set<Map.Entry<Integer, Integer>> entries = countNum.entrySet();
        Iterator<Integer> iterator = valueSet.iterator();

        //先移除出现次数最少的
        List<Integer> keyMoveList = new ArrayList<>();
        while (k > 0 && iterator.hasNext()) {

            Integer next = iterator.next();
            System.out.println(k + "---" + next);
            for (Map.Entry<Integer, Integer> entry : entries) {
                if (entry.getValue().equals(next)) {
                    if (k < next) {
                        break;
                    }
                    keyMoveList.add(entry.getKey());
                    k -= next;
                }
            }
        }

        System.out.println(keyMoveList);


        for (int i = 0; i < keyMoveList.size(); i++) {
            Integer key = keyMoveList.get(i);
            countNum.put(key, 0);
        }
        System.out.println(countNum);

        int result = 0;
        for (Map.Entry<Integer, Integer> entry : countNum.entrySet()) {
            if (entry.getValue() != 0) {
                result++;
            }
        }


        return result;
    }


    /**
     * @param bloomDay 花园中有 n 朵花 第 i 朵花会在 bloomDay[i] 时盛开，恰好 可以用于 一束 花中。
     * @param m        需要制作 m 束花
     * @param k        每束只需要 k朵。
     * @return 返回从花园中摘 m 束花需要等待的最少的天数 如果不能摘到 m 束花则返回 -1 。
     */
    public static int minDays(int[] bloomDay, int m, int k) {

        int inf = (int) 1e9 + 1;
        int ans = inf;
        int n = bloomDay.length;

        int l = 0;
        int r = inf;
        while (l < r) {
            int mid = (l + r) / 2;
            if (check(bloomDay, m, k, mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l == inf ? -1 : l;
    }

    public static boolean check(int[] b, int m, int k, int t) {
        int cnt = 0;
        int total = 0;
        for (int i = 0; i < b.length; i++) {
            if (b[i] > t) {
                cnt = 0;
                continue;
            }
            cnt++;
            if (cnt == k) {
                total++;
                cnt = 0;
            }
        }
        return total >= m;
    }

    /**
     * 大神
     *
     * @param nums
     * @return
     */
    public int[] runningSumDS(int[] nums) {
        int[] ps = new int[nums.length];
        ps[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            ps[i] = ps[i - 1] + nums[i];
        }
        return ps;
    }


    /**
     * 大神
     *
     * @param arr
     * @param k
     * @return
     */
    public int findLeastNumOfUniqueIntsDS(int[] arr, int k) {
        Map<Integer, Integer> cnt = new HashMap(arr.length);
        for (int x : arr) {
            cnt.put(x, cnt.getOrDefault(x, 0) + 1);
        }

        List<Map.Entry<Integer, Integer>> list = new ArrayList(cnt.entrySet());
        list.sort((a, b) -> a.getValue().compareTo(b.getValue()));

        int ans = list.size();
        for (int i = 0; i < list.size(); i++) {
            Map.Entry<Integer, Integer> ele = list.get(i);
            if (ele.getValue() > k) {
                break;
            }
            ans--;
            k -= ele.getValue();
        }
        return ans;
    }

    public static void main(String[] args) {
        //[1,2,2,2,2]
        //4

        int runningSum = minDays(new int[]{1, 10, 3, 10, 2}, 3, 1);

        System.out.println(runningSum);

    }


}


/**
 * Your TreeAncestor object will be instantiated and called as such:
 * TreeAncestor obj = new TreeAncestor(n, parent);
 * int param_1 = obj.getKthAncestor(node,k);
 */
class TreeAncestor {
    private int[][] jump;


    public TreeAncestor(int n, int[] parent) {
        jump = new int[20][n];
        jump[0] = parent;
        for (int i = 0; i + 1 < 20; i++) {
            for (int j = 0; j < n; j++) {
                jump[i + 1][j] = jump[i][j] == -1 ? -1 : jump[i][jump[i][j]];
            }
        }
    }

    public static int floorLog(int x) {
        return 31 - Integer.numberOfLeadingZeros(x);
    }


    public int find(int node, int k) {
        if (k == 0 || node == -1) {
            return node;
        }
        int log = floorLog(k);
        return find(jump[log][node], k - (1 << log));
    }

    public int getKthAncestor(int node, int k) {
        return find(node, k);
    }
}
