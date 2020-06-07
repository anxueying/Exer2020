package com;

import java.util.ArrayDeque;

/**
 * @author Mrs.An Xueying
 * 2020/6/7 12:15
 */
public class s3 {
}

class BrowserHistory {
    private ArrayDeque<String> back = new ArrayDeque<>(), forward = new ArrayDeque<>();
    private String current;

    public BrowserHistory(String homepage) {
        current = homepage;
    }

    public void visit(String url) {
        back.add(current);
        current = url;
        forward.clear();
    }

    public String back(int steps) {
        for (int i = 0; i < steps; i++) {
            if (back.isEmpty()) {
                break;
            }
            forward.addFirst(current);
            current = back.removeLast();
        }
        return current;
    }

    public String forward(int steps) {
        for (int i = 0; i < steps; i++) {
            if (forward.isEmpty()) {
                break;
            }
            back.add(current);
            current = forward.removeFirst();
        }
        return current;
    }
}