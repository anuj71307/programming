package com.programs.problems;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/find-median-from-data-stream/
 * Leetcode 295
 * Find median in stream.
 * Median is defined as middle point in sorted array
 */
public class MedianStream {

    PriorityQueue<Integer> minHeap;
    PriorityQueue<Integer> maxHeap;

    public MedianStream() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>((a, b) -> b- a);
    }

    public void addNum(int num) {

        minHeap.offer(num);
        maxHeap.offer(minHeap.poll());
        while (minHeap.size() < maxHeap.size()) {
            minHeap.offer(maxHeap.poll());
        }
    }

    public double findMedian() {
        if (minHeap.size() > maxHeap.size()) return minHeap.peek();
        return (minHeap.peek() + maxHeap.peek()) * 0.5;
    }
}
