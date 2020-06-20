package com.programs.trie.autocomplete;

import java.util.ArrayList;
import java.util.List;

public class AutoCompleteDictionary {
    TrieNode root = null;

    public AutoCompleteDictionary() {
        root = new TrieNode();
    }

    public void insertAll(String[] words) {
        if (words == null || words.length == 0) return;
        for (String word : words) {
            if (!word.isEmpty()) {
                insert(word);
            }
        }
    }

    private void insert(String word) {
        TrieNode curr = root;
        for (int index = 0; index < word.length(); index++) {
            int pos = word.charAt(index) - 'a';
            if (curr.children[pos] == null) {
                curr.children[pos] = new TrieNode();
            }
            curr = curr.children[pos];
        }
        curr.isWord = true;
    }

    public boolean search(String word) {
        if (word == null || word.isEmpty()) return true;
        TrieNode curr = root;
        for (int index = 0; index < word.length() && curr != null; index++) {
            int pos = word.charAt(index) - 'a';
            curr = curr.children[pos];
        }
        return curr != null && curr.isWord;
    }

    public List<String> autoComplete(String prefix) {
        List<String> list = new ArrayList<>();
        if (prefix == null || prefix.isEmpty()) return list;
        populateList(prefix, list);
        return list;
    }

    private void populateList(String prefix, List<String> list) {
        TrieNode curr = root;
        for(int index=0;index<prefix.length() && curr!=null;index++){
            int pos = prefix.charAt(index)-'a';
            curr = curr.children[pos];
            if(curr==null) break;
        }
        if(curr==null){
            return;
        }
        completeWords(curr, prefix, list);
    }

    private void completeWords(TrieNode node, String prefix,List<String> words ){
        if(node==null) return;
        if(node.isWord){
            words.add(prefix);
        }
        for(int i = 0; i< node.children.length;i++){
            TrieNode temp = node.children[i];
            if(temp==null) continue;
            char c =(char)(i+'a');
            completeWords(temp, prefix+c, words);
        }
    }

    public static void main(String[] args) {
        AutoCompleteDictionary acd = new AutoCompleteDictionary();
        String[] words = {"grape", "banana", "mango", "hello", "anuj", "anupam", "monkey", "amul", "amulmasti"};
        acd.insertAll(words);
        System.out.print(acd.autoComplete("a"));
    }
}

class TrieNode {
    TrieNode[] children;
    boolean isWord;

    public TrieNode() {
        children = new TrieNode[26];
    }
}
