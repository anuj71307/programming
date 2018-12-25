package com.programs.trie;

public class TrieProblems {

    public  static  void main(String[] args){

        Trie trie = new Trie();
        trie.insert("try");
        trie.insert("tried");
        trie.insert("crack");
        trie.insert("code");
        trie.insert("interview");
        trie.insert("freedom");
        trie.insert("fry");
        trie.insert("amazon");
        System.out.println(trie.search("baby"));
        System.out.println(trie.search("trie"));

    }
}
