package com.programs.cache;

import java.util.HashMap;

public class LruCache {
    private int capacity;
    private HashMap<Integer,Node> hashMap;
    Node head;
    Node end;

    public LruCache(int capacity) {
        this.capacity = capacity;
        this.hashMap = new HashMap<>(capacity);
    }

    public int get(int key){

        if (hashMap.containsKey(key)) {
            return hashMap.get(key).value;
        }

        return -1;
    }

    public void set(int key, int value){

       if(hashMap.containsKey(key)){
           Node temp = hashMap.get(key);
           remove(temp);
           temp.value = value;
           setHead(temp);
       }
       else{
           Node temp = new Node(key,value);
           if(hashMap.size()==capacity){
               remove(end);
           }
           setHead(temp);
           hashMap.put(key, temp);
       }
    }

    private void remove(Node temp) {
        if(temp.prev!=null) {
            temp.prev.next = temp.next;
        }
        else {
            temp.next = head;
            head = temp;
        }
        if(temp.next!=null) {
            temp.next.prev = temp.prev;
        }else{

        }
    }

    private void setHead(Node temp) {
    }

}




 class Node {
     int key;
     int value;
     Node next;
     Node prev;

     public Node(int key, int value) {
         this.key = key;
         this.value = value;
     }
 }
