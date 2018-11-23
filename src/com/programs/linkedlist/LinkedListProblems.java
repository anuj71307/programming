package com.programs.linkedlist;


public class LinkedListProblems {

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

    public static void main(String[] args) {
        System.out.println("length of loop is ");
        LinkedList<Integer> linkedList1 = new LinkedList<>(1);
        LinkedList<Integer> linkedList2 = new LinkedList<>(0);
        linkedList1.setNext(linkedList2);
        LinkedList<Integer> linkedList3 = new LinkedList<>(0);
        linkedList2.setNext(linkedList3);

        LinkedList<Integer> node1 = new LinkedList<>(9);
        LinkedList<Integer> node2 = new LinkedList<>(0);
        node1.setNext(node2);
        LinkedList<Integer> node3 = new LinkedList<>(5);
        node2.setNext(node3);
        LinkedList<Integer> head = sumList(linkedList1, node1);

        System.out.println("Sum is " + getSum(head));
    }


    private static LinkedList<Integer> sumList(LinkedList<Integer> l1, LinkedList<Integer> l2) {
        LinkedList<Integer> head = null;

        int firstNum = getSum(l1);
        int secondNum = getSum(l2);

        int num = firstNum + secondNum;

        while (num > 0) {
            int rem = num % 10;
            if (head == null) {
                head = addNode(head, rem);
            } else {
                head = addNode(head, rem);
            }
            num = num/10;
        }

        return head;
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
