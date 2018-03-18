package com.programs.sorting;

import java.util.Scanner;

public class Sorting {

	/**
	 * bubble sort
	 * time complexity O(n^2), space complexity O(1)
	 * @param arr input array 
	 * @return sorted array
	 */
	static int[] bubbleSort(int arr[]) {
		int unSortedLength = arr.length-1;
		boolean isSorted = false;
		while(!isSorted) {
			isSorted = true;
			for(int i = 0; i<unSortedLength;i++) {
				if(arr[i]>arr[i+1]) {
					int temp = arr[i];
					arr[i]= arr[i+1];
					arr[i+1] = temp;
					isSorted = false;
				}
				
			}
			unSortedLength--;
		}
		
		return arr;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Enter size of array");
		Scanner scan = new Scanner(System.in);
		int k = scan.nextInt();
		int arr[] = new int[k];
		
		for(int i = 0;i<k;i++) {
			arr[i] = scan.nextInt();
		}
		scan.close();
		System.out.println("Array before sorted");
		for(int j = 0;j<arr.length;j++) {
			System.out.print(arr[j]+" ");
		}
		System.out.println();
		bubbleSort(arr);
		System.out.println("Array after sorted");
		for(int j = 0;j<arr.length;j++) {
			System.out.print(arr[j]+" ");
		}

	}

}
