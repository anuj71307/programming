package com.programs.sorting;

public class Sorting {

    /**
     * bubble sort
     * Time complexity O(n^2), space complexity O(1)
     * Best case time complexity - O(n)
     *
     * @param arr input array
     */
    private void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) return;
        for (int i = 0, lastIndex = arr.length; i < arr.length - 1; i++, lastIndex--) {
            boolean swapped = false;
            for (int j = 0; j < lastIndex - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    public static void main(String[] args) {
        Sorting sort = new Sorting();
        int arr2[] = new int[]{1, 1, 2, 3, 3, 4, 5, 6, 6};
        sort.insertionSort(arr2);
        for (int i : arr2) {
            System.out.print(i + " ");
        }

    }

    /**
     * https://leetcode.com/problems/kth-largest-element-in-an-array/
     *
     * @param arr
     * @param k
     * @return
     */
    private static int kthLargest(int[] arr, int k) {
        return kthLargest(arr, 0, arr.length - 1, k);
    }


    private static int kthLargest(int[] arr, int start, int end, int k) {

        int pivot = arr[end];
        int index = start;
        for (int i = start; i < end; i++) {
            if (arr[i] < pivot) {
                swap(arr, i, index);
                index++;
            }
        }
        swap(arr, index, end);
        if (arr.length - index == k) return arr[index];
        if (arr.length - index > k) {
            return kthLargest(arr, index + 1, end, k);
        }
        return kthLargest(arr, start, index - 1, k);
    }

    private static void swap(int[] arr, int i, int index) {
        int temp = arr[i];
        arr[i] = arr[index];
        arr[index] = temp;
    }

    /**
     * merge two array in place
     * time complexity O(m*n)
     * m and n is size of respective array
     *
     * @param first
     * @param second
     */
    public static void mergeArr(int first[], int second[]) {

        int firstSize = first.length;
        int secondSize = second.length;

        for (int i = secondSize - 1; i >= 0; i--) {


            int last = first[firstSize - 1];
            int j;
            for (j = firstSize - 2; j >= 0; j--) {
                if (first[j] > second[i]) {
                    first[j + 1] = second[j];
                } else {
                    break;
                }
            }

            if (j != firstSize - 2 || last > second[i]) {
                first[j + 1] = second[i];
                second[i] = last;
            }

        }
    }

    /**
     * selection sort in java
     * time complexity O(n^2)
     *
     * @param arr input array to sort
     * @return sorted array
     */
    private static int[] selectionSort(int[] arr) {

        if (arr == null || arr.length == 0) {
            return arr;
        }

        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int k = i + 1; k < arr.length; k++) {
                if (arr[k] < arr[min]) {
                    min = k;
                }
            }
            int temp = arr[min];
            arr[min] = arr[i];
            arr[i] = temp;
        }


        return arr;

    }

    /**
     * insertion sort
     *
     * @param arr arr to sort
     */
    public static void insertionSort(int[] arr) {
        if (arr == null || arr.length < 2) return;

        for (int i = 1; i < arr.length; i++) {
            int val = arr[i];
            int j = i - 1;
            if (arr[i - 1] > arr[i]) {
                while (j >= 0 && arr[j] > val) {
                    swap(arr, j, j + 1);
                    j--;
                }
                arr[++j] = val;
            }
        }

    }

    /**
     * merge sort implementation
     *
     * @param arr array to be sorted
     * @return sorted array
     */
    public static int[] mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return arr;
        }
        int length = arr.length;
        int mid = length / 2;
        int leftArr[] = new int[mid];
        int rightArr[] = new int[length - mid];
        for (int i = 0; i < mid; i++) {
            leftArr[i] = arr[i];
        }
        for (int i = mid; i < length; i++) {
            rightArr[i - mid] = arr[i];
        }
        mergeSort(leftArr);
        mergeSort(rightArr);
        return merge(leftArr, rightArr, arr);
    }

    /**
     * merge function for merge sort
     */
    private static int[] merge(int[] leftArr, int[] rightArr, int[] arr) {
        int i = 0, j = 0, k = 0;
        while (i < leftArr.length && j < rightArr.length) {
            if (leftArr[i] < rightArr[j]) {
                arr[k] = leftArr[i];
                i++;
                k++;
            } else {
                arr[k] = rightArr[j];
                j++;
                k++;
            }
        }
        while (i < leftArr.length) {
            arr[k] = leftArr[i];
            i++;
            k++;
        }
        while (j < rightArr.length) {
            arr[k] = rightArr[j];
            j++;
            k++;
        }

        return arr;
    }

    public static void quickSort(int[] arr, int start, int end) {
        if (start < end) {
            int index = partition(arr, start, end);
            quickSort(arr, start, index - 1);
            quickSort(arr, index + 1, end);
        }
    }

    private static int partition(int[] arr, int start, int end) {
        int pivot = arr[end];
        int index = start;
        for (int i = start; i < end; i++) {
            if (arr[i] < pivot) {
                int temp = arr[i];
                arr[i] = arr[index];
                arr[index] = temp;
                index++;
            }
        }
        arr[end] = arr[index];
        arr[index] = pivot;

        return index;
    }

}
