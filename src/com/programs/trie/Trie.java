package com.programs.trie;

import java.util.LinkedList;

/**
 * Trie data structure using array
 */
public class Trie {

 TrieNode root;

    public Trie() {
        root = new TrieNode();
        root.count = 0;
    }

    /**
     * insert a word in trie structure
     * @param word
     */
    public void insert(String word){
        if(word==null || word.length()==0 || search(word)) return;
        TrieNode temp = root;
        int index;
        for(int i =0; i< word.length();i++){
            index = word.charAt(i)-'a';
            if(temp.children[index]==null){
                temp.children[index] = new TrieNode();
            }
            temp.count++;
            temp = temp.children[index];
        }

        temp.isWord = true;

    }


    /**
     * search for a word inside
     * @param word word to search
     * @return true of word exist, false otherwise
     */
    public boolean search(String word){
        if(word==null || word.length()==0) return false;
        TrieNode temp = root;
        int index;
        for(int i =0; i< word.length();i++){
            index = word.charAt(i)-'a';
            if(temp.children[index]==null){
                return  false;
            }
            temp = temp.children[index];
        }

        return  (temp!=null && temp.isWord);
    }


    public void delete(String word){
        if(word==null || word.length()==0 || !search(word)) return;
        TrieNode crawl = root;
        int index = 0;
        for(int i =0; i< word.length();i++) {
            index = word.charAt(i) - 'a';
            TrieNode temp  = crawl.children[index];
            if(temp.count==1){
                crawl.count--;
                crawl.children[index] = null;
                return;
            }
            else{
                crawl.count--;
                crawl = temp;
            }

        }

        crawl.isWord = false;
    }

    /**
     * Give a dictionary implement an API to find word in it l. Ex Boy, B.z, ..., Dot should be matched with any charger in between
     */
    boolean searchDfs(String word){
       if(word==null || word.trim().length()==0) return false;
       return search(root, word, 0);
    }

    private boolean search(TrieNode node, String word, int index) {
        if(node==null) return  false;
        if(index>=word.length()){
            return node.isWord;
        }
        if(word.charAt(index)=='.'){
            for(int i = 0; i< node.children.length;i++){
                if(search(node.children[i], word, index+1)){
                    return  true;
                }
            }
        }
        else{
            int child = word.charAt(index)-'a';
            return search(node.children[child], word, index+1);
        }

        return  false;
    }


}

class TrieNode{
    TrieNode [] children;
    boolean isWord;
    int count ;

    public TrieNode() {
        this.children = new TrieNode[26];
        count = 0;
    }
}