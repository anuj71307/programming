package com.programs.problems;


import com.programs.linkedlist.LinkedList;

public class LinkedListProblems<T> {


    /**
     * source: https://www.geeksforgeeks.org/delete-nodes-list-greater-x/
     * delete all the next element where value is greater than given value
     */
    private static <T> LinkedList deleteElemGreater(LinkedList head, Integer data) {
        LinkedList node = head;
        if (node == null) {
            return null;
        }
        while (node.getNext() != null) {
            Integer nodeData = (Integer) node.getNext().getData();
            if (nodeData > data) {
                node.setNext(node.getNext().getNext());
                continue;
            } else {
                node = node.getNext();
            }
        }

        //change head if head's value if greater
        if ((Integer) head.getData() > data) {
            head = head.getNext();
        }

        return head;

    }

    /**
     * print all element of a linkedlist
     *
     * @param head head of a linked list
     */
    private static void printAllElem(LinkedList head) {
        if (head == null) {
            return;
        }
        while (head != null) {
            System.out.print(head.getData() + " --> ");
            head = head.getNext();
        }
        System.out.println();
    }

    /**
     * print all unique elements of a linkedlist
     * https://www.geeksforgeeks.org/find-unique-elements-linked-list/
     *
     * @param head
     */
    private static void findUniqueElementInLinkedList(LinkedList head) {

    }

    private static int detectAndCountLoop(LinkedList<Integer> linkedList) {
        int length = 0;
        if (linkedList == null) {
            return length;
        }
        LinkedList<Integer> fast_pointer = linkedList;
        LinkedList<Integer> slow_pointer = linkedList;
        while (fast_pointer != null && fast_pointer.getNext() != null && slow_pointer != null) {
            fast_pointer = fast_pointer.getNext().getNext();
            slow_pointer = slow_pointer.getNext();
            if (fast_pointer == slow_pointer) {

                LinkedList<Integer> countPointer = slow_pointer.getNext();
                length++;
                while (countPointer != slow_pointer) {
                    length++;
                    countPointer = countPointer.getNext();
                }
                break;
            }
        }

        return length;
    }

    /*
      //temporary class
      static class Node {
          public int val;
          public Node prev;
          public Node next;
          public Node child;

          public Node(int v){
              val = v;
          }
          public Node(int _val,Node _prev,Node _next,Node _child) {
              val = _val;
              prev = _prev;
              next = _next;
              child = _child;
          }
      }

      //https://leetcode.com/problems/flatten-a-multilevel-doubly-linked-list/

      static public Node flatten(Node head) {
          if (head == null) return null;
          Node temp = head;
          while (temp != null) {
              if (temp.child != null) {
                  Node next = temp.next;

                  temp.next = temp.child;
                  temp.child=null;
                  flatten(temp.next);//7
                  Node n = temp.next;
                  n.prev = temp;
                  while (n.next != null) {
                      n = n.next;
                  }
                  n.next = next;
                  if(next!=null)
                  next.prev = n;
              }
              temp = temp.next;
          }

          return head;

      }

       */
    public static void main(String[] args) {

        LinkedList<Integer> head = new LinkedList<>(1);
        LinkedList<Integer> next = new LinkedList<>(2);
        head.setNext(next);
        LinkedList temp = head;
        while (temp != null) {
            System.out.print(temp.getData() + " ");
            temp = temp.getNext();
        }
        deleteNode(head);
        System.out.println();
        temp = head;
        while (temp != null) {
            System.out.print(temp.getData() + " ");
            temp = temp.getNext();
        }
    }


    /**
     * https://practice.geeksforgeeks.org/problems/delete-without-head-pointer/1
     * delete a given node form linkedlist which may not be head
     * Assumption: Last will ever be given as input
     */
    static void deleteNode(LinkedList<Integer> node) {
        node.setData(node.getNext().getData());
        node.setNext(node.getNext().getNext());
    }

    /**
     * sum of two linked list
     *
     * @param l1
     * @param l2
     * @return
     */
    private static LinkedList<Integer> sumList(LinkedList<Integer> l1, LinkedList<Integer> l2) {
        LinkedList<Integer> head = null;

        int firstNum = getSum(l1);
        int secondNum = getSum(l2);

        int num = firstNum + secondNum;

        while (num > 0) {
            int rem = num % 10;
            head = addNode(head, rem);
            num = num / 10;
        }

        return head;
    }

    // 1--2
    public LinkedList<T> reverse(LinkedList<T> node) {
        if (node == null || node.getNext() == null) return node;
        return reverse(null, node, node.getNext());
    }

    public LinkedList<T> reverse(LinkedList<T> prev, LinkedList<T> curr, LinkedList<T> nxt) {
        curr.setNext(prev);
        return nxt != null ? reverse(curr, nxt, nxt.getNext()) : curr;
    }

    private static LinkedList<Integer> addNode(LinkedList<Integer> head, int num) {

        if (head == null) {
            head = new LinkedList<>(num);
        } else {
            LinkedList<Integer> temp = new LinkedList<>(num);
            temp.setNext(head);
            head = temp;
        }

        return head;
    }

    private static int getSum(LinkedList<Integer> l1) {
        if (l1 == null) return -1;
        LinkedList<Integer> temp = l1;
        int num = 0;
        while (temp != null) {
            if (num == 0) {
                num = num + temp.getData();
            } else {
                num = (num * 10) + temp.getData();
            }
            temp = temp.getNext();
        }

        return num;
    }
}
