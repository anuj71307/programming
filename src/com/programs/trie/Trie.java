package com.programs.trie;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
     *
     * @param word
     */
    public void insert(String word) {
        if (word == null || word.length() == 0 || search(word)) return;
        TrieNode temp = root;
        int index;
        for (int i = 0; i < word.length(); i++) {
            index = word.charAt(i) - 'a';
            if (temp.children[index] == null) {
                temp.children[index] = new TrieNode();
            }
            temp.count++;
            temp = temp.children[index];
        }

        temp.isWord = true;

    }


    /**
     * search for a word inside
     *
     * @param word word to search
     * @return true of word exist, false otherwise
     */
    public boolean search(String word) {
        if (word == null || word.length() == 0) return false;
        TrieNode temp = root;
        int index;
        for (int i = 0; i < word.length(); i++) {
            index = word.charAt(i) - 'a';
            if (temp.children[index] == null) {
                return false;
            }
            temp = temp.children[index];
        }

        return (temp != null && temp.isWord);
    }

    public void remove(String word){
        remove(word, root, 0);
    }

    /**
     * remove a word from dictionary
     * https://www.geeksforgeeks.org/trie-delete/
     * @param word word to remove
     * @param root node to check
     * @param index index position of letter in word
     * @return
     */
    private boolean remove(String word, TrieNode root, int index) {
        if(root==null)  return false;
        if(index>=word.length()){
            root.isWord = false;
            return hasChild(root);
        }
        int pos = word.charAt(index)-'a';
        boolean res = remove(word, root.children[pos], index+1);
        if(res){
            root.children[pos] = null;
        }
        return  hasChild(root);
    }

    public boolean hasChild(TrieNode root) {
        for(int i = 0; i<root.children.length;i++){
            if(root.children[i]!=null) return true;
        }
        return false;
    }


    public void delete(String word) {
        if (word == null || word.length() == 0 || !search(word)) return;
        TrieNode crawl = root;
        int index = 0;
        for (int i = 0; i < word.length(); i++) {
            index = word.charAt(i) - 'a';
            TrieNode temp = crawl.children[index];
            if (temp.count == 1) {
                crawl.count--;
                crawl.children[index] = null;
                return;
            } else {
                crawl.count--;
                crawl = temp;
            }

        }

        crawl.isWord = false;
    }

    /**
     * https://leetcode.com/problems/add-and-search-word-data-structure-design/
     * Give a dictionary implement an API to find word in it l. Ex Boy, B.z, ..., Dot should be matched with any charger in between
     */
    boolean searchDfs(String word) {
        if (word == null || word.trim().length() == 0) return false;
        return search(root, word, 0);
    }

    private boolean search(TrieNode node, String word, int index) {
        if (node == null) return false;
        if (index >= word.length()) {
            return node.isWord;
        }
        if (word.charAt(index) == '.') {
            for (int i = 0; i < node.children.length; i++) {
                if (search(node.children[i], word, index + 1)) {
                    return true;
                }
            }
        } else {
            int child = word.charAt(index) - 'a';
            return search(node.children[child], word, index + 1);
        }

        return false;
    }


    public int prefixSearchCount(String prefix) {
        if (prefix == null || prefix.trim().length() == 0) return 0;
        TrieNode current = root;
        for (int i = 0; i < prefix.length(); i++) {
            int index = prefix.charAt(i) - 'a';
            TrieNode temp = current.children[index];
            if (temp == null) return 0;
            current = temp;
        }
        if (!current.isWord) return current.count;
        return current.count + 1;
    }

    /**
     * given a query string s and a set of all possible query strings,
     * return all strings in the set that have s as a prefix.
     * For example, given the query string de and the set of strings [dog, deer, deal], return [deer, deal].
     *
     * @param prefix
     */
    public List<String> prefixSearch(String prefix) {

        List<String> list = new ArrayList<>();
        prefixSearch(root, prefix, list, "", 0);
        return list;
    }

    private void prefixSearch(TrieNode root, String prefix, List<String> list, String word, int index) {
        if (root == null || prefix.isEmpty()) return;
        if (root.isWord && index >= prefix.length()) {

            list.add(word);
        }

        if (index < prefix.length()) {
            TrieNode node = root.children[prefix.charAt(index) - 'a'];
            prefixSearch(node, prefix, list, word + prefix.charAt(index), index + 1);
        } else {
            for (int i = 0; i < root.children.length; i++) {
                TrieNode node = root.children[i];
                char c = (char) (i + 97);
                prefixSearch(node, prefix, list, word + c, index);
            }
        }


    }


}

class TrieNode {
    TrieNode[] children;
    boolean isWord;
    int count;

    public TrieNode() {
        this.children = new TrieNode[26];
        count = 0;
    }
}