package com.programs.trees;

public class RankTree {
    RankNode root = null;

    public RankTree(){ }

    public static void main(String args[]){
        RankTree tree = new RankTree();
        tree.track(20);
        tree.track(15);
        tree.track(10);
        tree.track(13);
        tree.track(5);

        tree.track(25);
        tree.track(23);
        tree.track(24);
        tree.track(16);

        System.out.print(tree.getRank(25));

    }

    int getRank(int data){
        if(root==null) return -1;
        if(root.data==data) {
            return root.count;
        }
        RankNode curr = root;
        int rank = 0;
        while(curr!=null){
            if(curr.data==data) {
                rank+=curr.count;
                return rank;
            }
            if(curr.data<data){
                if(curr.right==null) return -1;
                rank+=curr.count+1;
                curr = curr.right;
            }
            else{
                if(curr.left==null) return -1;
                else curr = curr.left;
            }
        }
        return -1;
    }

    void track(int data){
        insert(data);
    }

    private void insert(int data) {
        RankNode temp = new RankNode(data);
        if(root==null){
            root=temp;
            return;
        }
        RankNode curr = root;
        while(true){
            if(curr.data<data){
                if(curr.right==null){
                    curr.right=temp;
                    break;
                }
                else{
                    curr = curr.right;
                }
            }
            else{
                curr.count++;
                if(curr.left==null) {
                    curr.left=temp;
                    break;
                }
                else{
                    curr = curr.left;
                }
            }
        }
    }
}

class RankNode{
    int data;
    int count;
    RankNode left;
    RankNode right;
    public RankNode(int data){
        this.data = data;
    }
}
