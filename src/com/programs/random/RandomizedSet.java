package com.programs.random;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * https://leetcode.com/problems/insert-delete-getrandom-o1/
 * Leetcode 380
 * To insert and delete in O(1) we can use hash set but this wont help us with getting random.
 * Get random works fine with randomly accessible datas-tructure like array or list where we can fetch item by index position
 * Therefore we make use of both these data set.
 * We use array list to keep all the element but in arraylist if we remove element then it can be O(N), therefore
 * instead of removing we swap the element with last element in array and remove last element.
 * Removal of last element will be O(1) since there is no shifting required.
 * But how do we know index of element being removed??,  for this we use hashmap. Whenever we insert an item we insert
 * and add it in map as key and its position as its value
 * Now while removing we get its position and get element at last index and swap it. then remove last element
 * and also update the map.
 */
public class RandomizedSet {

    ArrayList<Integer> list;
    HashMap<Integer, Integer> map;

    /**
     * Initialize your data structure here.
     */
    public RandomizedSet() {
        list = new ArrayList<>();
        map = new HashMap<>();
    }

    /**
     * Inserts a value to the set. Returns true if the set did not already contain the specified element.
     */
    public boolean insert(int val) {
        if (map.containsKey(val)) return false;
        list.add(val);
        map.put(val, list.size() - 1);
        return true;
    }

    /**
     * Removes a value from the set. Returns true if the set contained the specified element.
     */
    public boolean remove(int val) {
        int pos = map.getOrDefault(val, -1);
        if (pos == -1) return false;
        int last = list.get(list.size() - 1);
        list.set(pos, last);
        list.remove(list.size() - 1);
        map.put(last, pos);
        map.remove(val);
        return true;
    }

    /**
     * Get a random element from the set.
     */
    public int getRandom() {
        int rand = new Random().nextInt(list.size());
        return list.get(rand);
    }

    public static void main(String args[]) {
        RandomizedSet rs = new RandomizedSet();
        System.out.println(rs.insert(2));
        System.out.println(rs.insert(81));
        System.out.println(rs.insert(91));
        System.out.println(rs.insert(1002));
        System.out.println(rs.insert(236));
        System.out.println(rs.insert(5672));
        System.out.println(rs.insert(81));
        System.out.println(rs.getRandom());
        System.out.println(rs.getRandom());
        System.out.println(rs.getRandom());
        System.out.println(rs.remove(236));
        System.out.println(rs.remove(81));
        System.out.println(rs.getRandom());
        System.out.println(rs.getRandom());
        System.out.println(rs.getRandom());
        System.out.println(rs.remove(3));
    }
}
