package com.programs.map;

import java.util.Objects;

public class THashMap<K,V> {
    int size;
    Node<K,V> table[];

    public THashMap(int capacity) {
        table= (Node<K,V>[])new Node[capacity];
    }

    public void put(K key, V val){
        if(key==null) putNullKey(key, val);
        else {
            int pos = getPos(key);
            insertAtPos(pos, key, val);
        }
    }

    private int getPos(K key){
        return  hashing(key)%table.length;
    }

    public V get(K key){
        V val = null;
        int pos = getPos(key);
        Node<K,V> node = table[pos];
        while(node!=null){
            if(hashing(node.key)==hashing(key) && (key==node.key || (key!=null && key.equals(node.key)))){
                return node.val;
            }
            node = node.next;
        }

        return val;
    }

    public boolean containsKey(K key) {
        if (size == 0) return false;
        int pos = getPos(key);
        Node<K, V> node = table[pos];
        while (node != null) {
            if (node.key == key) return true;
            node = node.next;
        }
        return false;
    }

    private void insertAtPos(int pos, K key, V val) {
        Node<K,V> node = table[pos];
        if(node==null) {
            table[pos] = new Node(key,val);
            size++;
            return;
        }

        Node<K,V> prev = node;
        while(node!=null&& hashing(key)!=hashing(node.key)){
            prev = node;
            node = node.next;
        }
        if(node==null){
            prev.next= new Node(key,val);
            size++;
            return;
        }
        node.setVal(val);
    }

    private void putNullKey(K key, V val) {
        Node<K,V> node = table[0];
        if(node==null) {
            table[0] = new Node(key,val);
            size++;
            return;
        }
        Node<K,V> prev = node;
        while(node!=null&& node.key!=null){
            prev = node;
            node = node.next;
        }
        if(node==null){
            prev.next= new Node(key,val);
            size++;
            return;
        }
        node.setVal(val);
    }

    public boolean isEmpty(){
        return size==0;
    }

    public int size() {
        return size;
    }

    int hashing(K key){
        if (key == null) return 0;
        int h = key.hashCode();
        if(h<0) return h >>>16;
        return h;
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (Node<K, V> node : table) {

            while (node != null) {
                builder.append(node.toString() + ", ");
                node = node.next;
            }
        }
        builder.append("]");

        return builder.toString();
    }

}


class Node<K,V>{
    K key;
    V val;
    Node<K,V> next;

    public Node(K key, V val) {
        this.key = key;
        this.val = val;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getVal() {
        return val;
    }

    public void setVal(V val) {
        this.val = val;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;
        Node<?, ?> node = (Node<?, ?>) o;
        return Objects.equals(key, node.key) &&
                Objects.equals(val, node.val);
    }

    @Override
    public int hashCode() {

        return Objects.hash(key, val);
    }

    @Override
    public String toString() {
        return key + " = " + val;
    }
}
