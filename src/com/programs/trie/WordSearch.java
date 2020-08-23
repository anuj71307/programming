package com.programs.trie;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/word-search-ii/
 * Leetcode 212
 * Insert all the word into tri and make dictionary
 * and now do dfs of matrix from each position and check if word made existin dictionary or not.
 * To do 2nd part we can do bit optimization.
 */
public class WordSearch {
    TrieNode root = new TrieNode();

    public static void main(String args[]) {
        char[][] board = {
                {'a'}
                /*{'o','s','n'},
                {'t','h','q'},
                {'o','e','n'}*/
        };
        String worfs[] = {"a"};
        System.out.print(new WordSearch().findWords(board, worfs));
    }

    public List<String> findWords(char[][] board, String[] words) {
        buildDictionary(words);
        return search(board);
    }

    private List<String> search(char[][] board) {
        List<String> result = new ArrayList<>();
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                dfs(board, row, col, root, result);
            }
        }
        return result;
    }

    private void dfs(char[][] board, int row, int col, TrieNode node, List<String> result) {
        if (node.word != null) {
            result.add(node.word);
            node.word = null;
        }
        if (row < 0 || col < 0 || row >= board.length || col >= board[row].length) return;
        if (board[row][col] == '#') return;
        char temp = board[row][col];
        int index = temp - 'a';

        if (node.children[index] == null) return;
        board[row][col] = '#';


        dfs(board, row + 1, col, node.children[index], result);
        dfs(board, row - 1, col, node.children[index], result);
        dfs(board, row, col + 1, node.children[index], result);
        dfs(board, row, col - 1, node.children[index], result);
        board[row][col] = temp;

    }

    private void buildDictionary(String[] words) {
        for (String word : words) {
            insert(word);
        }
    }

    private void insert(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (node.children[index] == null) {
                node.children[index] = new TrieNode();
            }
            node = node.children[index];
        }

        node.word = word;
    }


    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word;
    }
}

