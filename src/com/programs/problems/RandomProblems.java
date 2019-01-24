package com.programs.problems;

import java.util.*;

/**
 * class which will call appropriate method and classes
 * for different problems
 *
 * @author anujjha
 */
public class RandomProblems {


    public int diffPossible(final List<Integer> a, int b) {
        HashSet<Integer> hash = new HashSet<>();

        // 1 3 5 2 
        for(Integer k : a){
            Integer diff = Math.abs(k-b);
            if(hash.contains(diff)) return 1;
            hash.add(k);
        }

        return  0;
    }

    static int migratoryBirds(int n, int[] ar) {
        // Complete this function
        int type = 0;
        int arr[] = new int[5];
        for (int k : ar) {
            arr[--k]++;
        }
        for (int i = 0; i < 5; i++) {
            if (arr[i] > type) {
                type = i + 1;
            }
        }
        return type;
    }

    public static void main(String[] args) {

        int num = 152;
       // System.out.println(num==cubeSum(num));
      // int[] arr = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3};

        Integer[] arr={ 1000, 1000, 1000, 1000, 1001, 1002, 1003, 1003, 1004, 1010};
        for(int i : arr){
            System.out.print(i+" ");
        }
        System.out.println();
        System.out.println("After");
        ArrayList<Integer> list = new ArrayList<>();
        Collections.addAll(list, arr);
        int len = removeDuplicates(list);
        for(Integer k: list){
            System.out.print(k+" ");
        }
        System.out.println();


    }

    public static int removeDuplicates(ArrayList<Integer> a) {
        if(a==null|| a.size()==0) return 0;
        int length = a.size();//3
        int i =0;
        int j =0;
        for( i =0;i<a.size();){
            if(i+1<a.size()&& a.get(i).intValue()==a.get(i+1).intValue()){
                Integer k = a.get(i);

                a.set(j,k);
                j++;
                a.set(j,k);
                j++;

                while(i+1<a.size()&& a.get(i).intValue()==a.get(i+1).intValue()){
                    i++;
                }
                i++;
            }

            else{
                Integer k = a.get(i);

                a.set(j,k);
                j++;
                i++;

            }
        }

        return j;
    }

    public static int removeDuplicates(int[] a) {
        if(a==null|| a.length==0) return 0;
        int length = a.length;//3
        int i =0;
        int j =0;//2
        for( i =0;i<a.length;){
          if(i+1<a.length&& a[i]==a[i+1]){
                a[j] =a[i];
                j++;
                a[j]=a[i];
                j++;
                while(i+1<a.length && a[i+1]==a[i]){
                    i++;
                }
                i++;
            }
            else{
                a[j] = a[i];
                j++;
                i++;
            }
        }

        return j;
    }

    /**
     * find sum of cube of all digits in a num
     * @param num input num
     * @return sum of cubeof all num
     */
    public static int cubeSum(int num){

        if(num==0) return 0;
        int sum = 0;
        while(num>0){
            int rem = num%10;
            sum+= rem*rem*rem;
            num=num/10;
        }
        return  sum;
    }

    public static int compareVersion(String a, String b) {
        String str1[] = a.split("\\.");
        String str2[] = b.split("\\.");
        int i = 0;
        while(i<str1.length && i < str2.length){
            double one = Double.parseDouble(str1[i]);
            double two = Double.parseDouble(str2[i]);
            if(one>two){
                return 1;
            }
            else if(one<two){
                return -1;
            }
            i++;

        }

        if(i==str1.length && i == str2.length){
            return 0;
        }

        if(i<str1.length){
            return 1;
        }
        return -1;

    }

}
