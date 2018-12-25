package com.programs.trie;

/**
 * Trie data structure using array
 */
public class Trie {

 TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    /**
     * insert a word in trie structure
     * @param word
     */
    public void insert(String word){
        if(word==null || word.length()==0) return;
        TrieNode temp = root;
        int index =0;
        for(int i =0; i< word.length();i++){
            index = word.charAt(i)-'a';
            if(temp.children[index]==null){
                temp.children[index] = new TrieNode();
            }
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


}

class TrieNode{
    TrieNode [] children;
    boolean isWord;

    public TrieNode() {
        this.children = new TrieNode[26];
    }
}