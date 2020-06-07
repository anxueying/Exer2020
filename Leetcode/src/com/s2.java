package com;

import com.sun.deploy.security.SandboxSecurity;

import java.util.*;

/**
 * @author Mrs.An Xueying
 * 2020/6/7 10:46
 */
public class s2 {
    public int[] getStrongest(int[] arr, int k) {
        Arrays.sort(arr);
        int m = arr[(arr.length - 1) / 2];
        Integer[] array = new Integer[arr.length];
        for (int i = 0; i < arr.length; i++) {
            array[i] = arr[i];
        }
        Arrays.sort(array, (a, b) -> Math.abs(a - m) == Math.abs(b - m) ? b - a : Math.abs(b - m) - Math.abs(a - m));
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = array[i];
        }
        return result;
    }
}
