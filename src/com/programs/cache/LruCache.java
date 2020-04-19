package com.programs.cache;

import com.programs.linkedlist.LinkedList;

import java.util.HashMap;

public class LruCache<K,V> {

    HashMap<K, LinkedList<KeyToValue<K,V>>> map;
    LinkedList<KeyToValue<K,V>> head;
    LinkedList<KeyToValue<K,V>> tail;
    int size = 3;

    public LruCache(int size) {
        map = new HashMap<>();
        this.size = size;
        head = null;
        tail = null;
    }

    public V get(K key){
        LinkedList<KeyToValue<K,V>> list = map.get(key);
        if(list!=null){
            remove(list);
            insertAtFront(list);
            return map.get(key).data.value;
        }

        return null;
    }

    public void put(K key, V value){
        LinkedList<KeyToValue<K,V>> list = null;
        if(map.containsKey(key)){
            list = map.get(key);
            list.data.value = value;
            remove(list);
        }
        else{
            if(map.size()==size){
                map.remove(tail.data.key);
                remove(tail);

            }
            list = new LinkedList(new KeyToValue(key,value));
        }
        insertAtFront(list);
        map.put(key, list);
    }

    private void insertAtFront(LinkedList<KeyToValue<K,V>> list) {
        if(head==null){
            head = list;
            tail = list;
            return;
        }
        list.next = head;
        head.prev = list;
        head = list;

    }

    private void remove(LinkedList<KeyToValue<K,V>> list) {

        if(head.data == list.data){
           updateHead(list);
        }
        else if(tail.data == list.data){
            updateTail(list);
        }
        else{
            updateMiddle(list);
        }
    }

    private void updateMiddle(LinkedList<KeyToValue<K,V>> list) {
        list.next.prev = list.prev;
        list.prev.next = list.next;
    }

    private void updateTail(LinkedList<KeyToValue<K,V>> list) {
        tail.prev.next = null;
        tail = tail.prev;
    }

    private void updateHead(LinkedList<KeyToValue<K,V>> list) {
        if(head.data == tail.data){
            head=null;
            tail = null;
        }
        else{
            head = head.next;
        }
    }

}

class KeyToValue<K,V>{
    K key;
    V value;

    public KeyToValue(K key, V value) {
        this.key = key;
        this.value = value;
    }
}
