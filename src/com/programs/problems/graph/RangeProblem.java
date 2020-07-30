package com.programs.problems.graph;

import java.util.LinkedList;

/**
 * Class keep and range and then check if a value exist or not
 */
public class RangeProblem {

    LinkedList<Range> list = new LinkedList<>();

    public static void main(String args[]) {
        RangeProblem rp = new RangeProblem();
        rp.insertRange(10, 18);
        rp.insertRange(25, 32);
        rp.insertRange(87, 90);
        rp.insertRange(50, 55);
        rp.insertRange(40, 42);

        System.out.println(rp.isInRange(24));
        System.out.println(rp.isInRange(25));
        System.out.println(rp.isInRange(41));
        System.out.println(rp.isInRange(9));

    }

    /**
     * @param start
     * @param end
     */
    public void insertRange(int start, int end) {
        Range range = new Range(start, end);
        if (list.isEmpty()) {
            list.add(range);
            return;
        }
        int i = 0;
        for (i = 0; i < list.size(); i++) {
            Range temp = list.get(i);
            if (temp.start > start) {
                list.add(i, range);
                return;
            }
        }
        list.add(i, range);
    }


    public boolean isInRange(int value) {

        int i = 0;
        int j = list.size() - 1;
        while (i <= j) {
            int mid = (i + j) / 2;
            Range temp = list.get(mid);
            if (value >= temp.start && value <= temp.end) {
                return true;
            }
            if (temp.start > value) {
                j = mid - 1;
            } else {
                i = mid + 1;
            }
        }
        return false;
    }

}


class Range {
    int start;
    int end;

    public Range(int start, int end) {
        this.start = start;
        this.end = end;
    }
}