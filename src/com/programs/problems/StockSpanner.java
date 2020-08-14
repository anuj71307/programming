package com.programs.problems;

import java.util.Stack;

/**
 * https://leetcode.com/problems/online-stock-span/
 * Leetcode 901
 * Online Stock Span
 */
public class StockSpanner {


    public static void main(String args[]) {
        StockSpanner sp = new StockSpanner();
        System.out.println(sp.next(100));
        System.out.println(sp.next(80));
        System.out.println(sp.next(60));
        System.out.println(sp.next(70));
        System.out.println(sp.next(60));
        System.out.println(sp.next(75));
        System.out.println(sp.next(45));
    }

    Stack<Stock> stack;

    public StockSpanner() {

        stack = new Stack<>();
    }

    /**
     * Use of stack to keep data in decreasing order ie 100, 80, 70
     * @param price
     * @return
     */
    public int next(int price) {
        Stock st = new Stock(price, 1);

        int total = st.totalMin;
        while (!stack.isEmpty()) {
            Stock stock = stack.peek();
            if (stock.price > price) {
                break;
            }
            total += stock.totalMin;
            stack.pop();
        }
        st.totalMin = total;
        stack.push(st);
        return total;
    }
}

class Stock {
    int price;
    int totalMin;

    public Stock(int price, int totalMin) {
        this.price = price;
        this.totalMin = totalMin;
    }
}
