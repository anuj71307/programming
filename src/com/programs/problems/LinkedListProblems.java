package com.programs.problems;


import com.programs.linkedlist.LinkedList;
import com.programs.stack.Stack;

public class LinkedListProblems<T> {

    public static void main(String[] args) {

        LinkedListProblems lp = new LinkedListProblems();
        ListNode node = new ListNode(1);
        node.next = new ListNode(3);
        node.next.next = new ListNode(8);
        node.next.next.next = new ListNode(1);
        node.next.next.next.next = new ListNode(4);
        ListNode arr = lp.insertionSortList(node );
        while(arr!=null){
            System.out.print(arr.val+" ");
            arr= arr.next;
        }
    }

    // 1  3 4 2 1
    public ListNode insertionSortList(ListNode node) {
        if(node==null || node.next==null) return node;
        ListNode head = node;// 1
        ListNode next = node.next; //2
        head.next = null;
        while(next!=null){
             ListNode temp = next.next;
             next.next = null;
             head = insert(head, next);
             next = temp;
        }
        return head;

    }

    private ListNode insert(ListNode head, ListNode next) {
        if (next.val < head.val) {
            next.next = head;
            return next;
        }
        if (head.next == null) {
            head.next = next;
            return head;
        }
        ListNode temp = head;
        while (temp.next!=null && temp.next.val < next.val) {
            temp = temp.next;
        }
        next.next = temp.next;
        temp.next = next;
        return head;
    }

    /**
     * https://leetcode.com/problems/split-linked-list-in-parts/
     * for a given linkedlist we get the size lets say given linked list has 10 node and if we have to split it in 4
     * groups. then our  minimum size for each splited list is 2 which is (10/4) and remaining (10%4) ie 2 .
     * the remaining node we need add 1(cause different in size should not be more than 1 ) in each of our splited nodes starting from 0th.
     * @param root
     * @param k
     * @return
     */
    public ListNode[] splitListToParts(ListNode root, int k) {

        int size  = getSize(root);
        ListNode[] result = new ListNode[k];
        if(k==1) {
            result[0] = root;
            return result;
        }
        if(size == 0) return result;
        // check what should be minimum size of each list
        int count = size/k;
        int rem = size%k; // get remainder so we keep adding one extra node to each of splited node list as long as there is some remaining node
        int cc;
        ListNode temp ;
        int i = 0;
        while(k>0) {
            temp = root;
            cc = count+(rem>0?1:0); // if there are more nodes left we need to add one extra to current list
            rem-=1;
            while (cc > 1 && temp!=null) {
                temp = temp.next;
                cc--;
            }
            result[i++] = root;
            root = temp != null ? temp.next : null;
            if(temp!=null){
                temp.next=null;
            }
            k--; // decrement the node split size
        }

        return result;
    }

    int getSize(ListNode root){
        int size =0;
        ListNode temp = root;
        while(temp!=null){
            size++;
            temp = temp.next;
        }
        return size;
    }

    /**
     * https://leetcode.com/problems/reorder-list/
     * @param head
     */
    public void reorderList(ListNode head) {
        if(head==null || head.next==null || head.next.next==null){
            return;
        }
        Stack<ListNode> st = new Stack();
        ListNode temp = head;
        while(temp!=null){
            st.push(temp);
            temp=temp.next;
        }
        ListNode node = head;//2
        while(node!=st.peek() && node!=null){
            ListNode n = st.pop();//3
            st.peek().next=null;
            ListNode next = node.next;//2
            node.next=n;
            n.next=next;
            node=next;
        }
        if(node!=null) {
            node.next = null;
        }

    }

    /**
     * https://leetcode.com/problems/remove-zero-sum-consecutive-nodes-from-linked-list/
     *
     * @param head
     * @return
     */
    public ListNode removeZeroSumSublists(ListNode head) {
        if (head == null) return head;
        ListNode temp = new ListNode(1);
        ListNode prev = temp;

        ListNode node = head;
        int sum = 0;
        boolean found;
        while (node != null) {
            sum = 0;
            ListNode t = node;
            found = false;
            while (t != null) {
                sum += t.val;
                if (sum == 0) {
                    found = true;
                    break;
                }
                t = t.next;
            }

            if (found) {
                node = t.next;
            } else {
                ListNode next = node.next;
                node.next = null;
                prev.next = node;
                prev = prev.next;
                node = next;
            }
        }


        return temp.next;
    }

    /**
     * Time complexity Worst case : O(n)
     * check below solution for easy understanding in O(n^2)
     *
     * @param head
     * @return
     */
    public Node flattend(Node head) {

        flater(head);
        return head;
    }

    public Node flater(Node head) {

        Node temp = head;
        while (temp != null) {
            if (temp.child != null) {
                Node nxt = temp.next;
                Node child = temp.child;
                Node tail = flater(temp.child);
                temp.child = null;
                temp.next = child;
                child.prev = temp;
                tail.next = nxt;
                if (nxt != null) {
                    nxt.prev = tail;
                }
                if (nxt == null) {
                    return tail;
                }

            }


            if (temp.next == null) return temp;
            temp = temp.next;
        }
        return null;
    }

    /**
     * https://leetcode.com/problems/flatten-a-multilevel-doubly-linked-list/
     * Idea is to recursively flatten each node.
     * We start with head and move forward untill traversal is complete. While traversing we check if any node has child or not
     * If it does then we recursively flatten child. After a child is flatten we need to change pointers for node, its' child and node's next node's
     * pointers. We have to be careful when we return from flattening of child since returned node would be head of child list.
     * We will first change pointer's (next and prev) of node and it's child.
     * Then we will change pointer's of tail of child list and node's next
     * We also need to set every child as null.
     * Time complexity worst case O(n^2).
     *
     * @param head
     * @return
     */
    public Node flatten(Node head) {

        if (head == null) return head;
        Node temp = head;
        while (temp != null) {
            if (temp.child != null) {
                Node n = flatten(temp.child);
                Node nxt = temp.next;
                temp.next = n;
                n.prev = temp;
                while (n.next != null) {
                    n = n.next;
                }
                n.next = nxt;
                if (nxt != null) {
                    nxt.prev = n;
                }
                temp.child = null;
                temp = nxt;
            } else {
                temp = temp.next;
            }


        }


        return head;
    }


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

    /**
     * https://leetcode.com/problems/reverse-nodes-in-k-group/
     *
     * @param node
     * @param k
     * @return
     */
    public LinkedList<T> reverseKGroup(LinkedList<T> node, int k) {
        return reverseKGroup(node, k, size(node));
    }

    /**
     * https://leetcode.com/problems/reverse-nodes-in-k-group/
     *
     * @param node
     * @param k
     * @param size
     * @return
     */
    public LinkedList<T> reverseKGroup(LinkedList<T> node, int k, int size) {
        if (node == null || node.next == null || k < 2 || size < k) return node;

        int i = 1;
        LinkedList<T> head = node;
        node = node.next;
        head.next = null;
        LinkedList<T> tail = head;

        LinkedList<T> temp = tail;
        while (node != null && i < k) {
            temp = node.next;
            node.next = head;
            head = node;
            node = temp;
            i++;
        }

        tail.next = reverseKGroup(node, k, size - k);

        return head;
    }


    public int size(LinkedList<T> node) {
        int k = 0;
        while (node != null) {
            k++;
            node = node.next;
        }
        return k;
    }

    /**
     * https://leetcode.com/problems/sort-list/
     *
     * @param head
     * @return
     */
    public LinkedList<Integer> mergeSortLinkedList(LinkedList<Integer> head) {
        if (head == null || head.next == null) return head;

        LinkedList<Integer> left = getMid(head);
        LinkedList<Integer> right = left.next;
        left.next = null;
        head = mergeSortLinkedList(head);
        right = mergeSortLinkedList(right);
        return merge(head, right);
    }

    /**
     * https://leetcode.com/problems/merge-k-sorted-lists/
     *
     * @param lists
     * @return
     */
    public LinkedList<Integer> mergeKLists(LinkedList<Integer>[] lists) {

        if (lists == null || lists.length == 0) return null;
        if (lists.length == 1) return lists[0];

        int length = lists.length;
        int l1 = length / 2;
        int l2 = length - l1;
        LinkedList<Integer>[] left = new LinkedList[l1];
        LinkedList<Integer>[] right = new LinkedList[l2];
        System.arraycopy(lists, 0, left, 0, l1);
        System.arraycopy(lists, l1, right, 0, l2);
        LinkedList<Integer> k = mergeKLists(left);
        LinkedList<Integer> p = mergeKLists(right);
        LinkedList<Integer> head = merge(k, p);
        return head;
    }

    /**
     * MERGE TWO SORTED LINKED-LIST
     *
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


    LinkedList<Integer> getMid(LinkedList<Integer> head) {

        LinkedList<Integer> slow = head;
        LinkedList<Integer> fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
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
     *
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
     *
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

    // Definition for a Node.
    class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;

        public Node() {
        }

        public Node(int _val, Node _prev, Node _next, Node _child) {
            val = _val;
            prev = _prev;
            next = _next;
            child = _child;
        }
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}




