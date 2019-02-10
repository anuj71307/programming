package com.programs.cache;

import java.util.*;

public class LruCache {
    private int capacity;
    private HashSet<Integer > hashMap;
    Deque<Integer> queue;




    public LruCache(int capacity) {
        this.capacity = capacity;
        this.hashMap = new HashSet<>(capacity);
        queue = new LinkedList<>();
    }



    public void updateQ(int x){
        if(!hashMap.contains(x)){
            if(queue.size()==capacity){
                int k = queue.removeLast();
                hashMap.remove(k);
            }
        }
        else{
            queue.remove(x);

        }

        queue.addFirst(x);
        hashMap.add(x);

    }

    public void display(){
        Iterator<Integer> itr = queue.iterator();
        while(itr.hasNext()){
            System.out.print(itr.next()+" ");
        }
        System.out.println();
    }


    public static void main(String args[]){
        LruCache cache = new LruCache(3);
        cache.updateQ(1);
        cache.updateQ(3);
        cache.updateQ(4);
        cache.display();
        cache.updateQ(3);
        cache.display();
        cache.updateQ(6);
        cache.updateQ(8);
        cache.display();

    }


}



