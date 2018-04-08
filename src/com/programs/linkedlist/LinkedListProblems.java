package com.programs.linkedlist;


import java.util.Scanner;

public class LinkedListProblems {

    /**
     * source: https://www.geeksforgeeks.org/delete-nodes-list-greater-x/
     * delete all the next element where value is greater than given value
     */
    private static <T> LinkedList deleteElemGreater(LinkedList head,Integer data ){
        LinkedList node = head;
        if(node== null){
            return null;
        }
        while(node.getNext()!=null){
            Integer nodeData = (Integer) node.getNext().getData();
            if(nodeData > data){
                node.setNext(node.getNext().getNext());
                continue;
            }
            else{
                node = node.getNext();
            }
        }

        //change head if head's value if greater
        if((Integer)head.getData() > data){
            head = head.getNext();
        }

        return head;

    }

    /**
     * print all element of a linkedlist
     * @param head head of a linked list
     */
    private static void printAllElem(LinkedList head){
        if(head == null){
            return;
        }
        while(head!=null){
            System.out.print(head.getData()+ " --> ");
            head = head.getNext();
        }
        System.out.println();
    }


    public static void main(String[] args) {

        LinkedList<Integer> head =  new LinkedList<>(7);

        System.out.println("Print number of  elemenet for the list");
        Scanner scanner = new Scanner(System.in);
        int k = scanner.nextInt();
        LinkedList<Integer> prev = head;
        for(int i = 0; i<k;i++){
            System.out.println("-- Enter next node -- ");
            int j = scanner.nextInt();
            LinkedList<Integer> node = new LinkedList<>(j);
            prev.setNext(node);
            prev = node;
        }
        System.out.println("Print before deleting");
        printAllElem(head);
        System.out.println("Insert item to delete");
        int m = scanner.nextInt();
        scanner.close();
        head = deleteElemGreater(null, m);
        System.out.println("Print after deleting");
        printAllElem(head);
    }
}
