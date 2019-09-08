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

        LinkedListProblems lp = new LinkedListProblems();
        //lp.sortList();


    }

    /**
     * https://leetcode.com/problems/sort-list/
     *
     * @param head
     * @return
     */
    public LinkedList<Integer> sortList(LinkedList<Integer> head) {
        if (head == null || head.next == null) return head;

        LinkedList<Integer> left = getMid(head, halflength(head));
        LinkedList<Integer> right = left.next != null ? left.next : null;
        left.next = null;
        head = sortList(head);
        right = sortList(right);
        return merge(head, right);
    }

    /**
     * https://leetcode.com/problems/merge-k-sorted-lists/
     * @param lists
     * @return
     */
    public LinkedList<Integer> mergeKLists(LinkedList<Integer>[] lists) {

        if(lists==null || lists.length==0) return null;
        if(lists.length==1) return lists[0];

        int length = lists.length;
        int l1 = length/2;
        int l2 = length-l1;
        LinkedList<Integer>[] left = new LinkedList[l1];
        LinkedList<Integer>[] right = new LinkedList[l2];
        System.arraycopy(lists,0,left,0,l1);
        System.arraycopy(lists,l1,right,0,l2);
        LinkedList<Integer> k = mergeKLists(left);
        LinkedList<Integer> p = mergeKLists(right);
        LinkedList<Integer> head = merge(k,p);
        return head;
    }

    /**
     * MERGE TWO SORTED LINKED-LIST
     * @param first
     * @param second
     * @return
     */
    LinkedList<Integer> merge(LinkedList<Integer> first, LinkedList<Integer> second) {

        if (first == null) return second;
        if (second == null) return first;

        LinkedList<Integer> temp = new LinkedList(1);
        LinkedList<Integer> prev = temp;

        while (first != null && second != null) {

            if (first.data < second.data) {
                prev.next = first;
                first = first.next;
            } else {
                prev.next = second;
                second = second.next;
            }
            prev = prev.next;

        }

        if (first != null) {
            prev.next = first;
        } else {
            prev.next = second;
        }

        return temp.next;

    }


    LinkedList<Integer> getMid(LinkedList<Integer> head, int mid) {

        while (head != null && mid > 1) {
            mid--;
            head = head.next;
        }

        return head;
    }


    /**
     * return half length
     * @param head
     * @return
     */
    int halflength(LinkedList<Integer> head) {
        int len = 0;
        while (head != null) {
            head = head.next;
            len++;
        }

        return len / 2;
    }

    /**
     * https://leetcode.com/problems/add-two-numbers/
     *
     * @param l1
     * @param l2
     * @return
     */
    public static LinkedList<Integer> addTwoNumbers(LinkedList<Integer> l1, LinkedList<Integer> l2) {

        if (l1 == null) return l2;
        if (l2 == null) return l1;
        int carry = 0;
        LinkedList<Integer> head = null;
        LinkedList<Integer> prev = null;
        LinkedList<Integer> temp;
        while (l1 != null && l2 != null) {
            int val = l1.getData() + l2.getData() + carry;
            int rem = val % 10;
            carry = val / 10;
            temp = new LinkedList(rem);
            if (head == null) {
                head = temp;
                prev = head;
            } else {
                prev.next = temp;
                prev = temp;
            }
            l1 = l1.next;
            l2 = l2.next;
        }

        while (l1 != null) {
            int val = l1.getData() + carry;
            int rem = val % 10;
            carry = val / 10;
            temp = new LinkedList(rem);
            if (head == null) {
                head = temp;
                prev = head;
            } else {
                prev.next = temp;
                prev = temp;
            }
            l1 = l1.next;
        }
        while (l2 != null) {
            int val = l2.data + carry;
            int rem = val % 10;
            carry = val / 10;
            temp = new LinkedList<>(rem);
            if (head == null) {
                head = temp;
                prev = head;
            } else {
                prev.next = temp;
                prev = temp;
            }
            l2 = l2.next;
        }

        if (carry != 0) {

            temp = new LinkedList<>(carry);
            prev.next = temp;
        }

        return head;
    }


    //variable used to point to start node and move one by one
    static LinkedList<Integer> left;

    /**
     * https://www.geeksforgeeks.org/function-to-check-if-a-singly-linked-list-is-palindrome/
     * check if a linkedlist is palindrome or not
     *
     * @param node
     * @return
     */
    static boolean isPalindrome(LinkedList<Integer> node) {

        left = node;
        return isPalindromeUtil(node);
    }


    /**
     * check if a linkedlist is palindrome or not
     * @param right
     * @return
     */
    static boolean isPalindromeUtil(LinkedList<Integer> right) {
        if (right == null) return true;
        boolean result = isPalindromeUtil(right.next);
        if (!result) return false;
        boolean res = (left.data == right.data);
        if (!res) return false;
        left = left.next;
        return true;
    }

    /**
     * https://www.geeksforgeeks.org/reverse-a-list-in-groups-of-given-size/
     * reverse linkedlist in groups
     *
     * @param head
     * @param size
     * @return
     */
    public static LinkedList<Integer> reverseInGroup(LinkedList<Integer> head, int size) {

        if (head == null) return null;
        LinkedList temp = head;
        LinkedList tail = head;
        int j = 0;
        LinkedList prev = null;

        while (temp != null && j < size) {
            LinkedList next = temp.next;//2
            temp.next = prev;
            prev = temp;
            temp = next; //2
            j++;

        }
        tail.next = reverseInGroup(temp, size);
        return prev;
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

    /**
     * Reverse linked list recursively
     * @param node
     * @return
     */
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
