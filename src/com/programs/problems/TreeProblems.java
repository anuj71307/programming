package com.programs.problems;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class TreeProblems {
    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String str = null;
        int square = 0;
        int rect = 0;
        int poly = 0;
        while((str = reader.readLine())!=null){
            System.out.println("str : " + str);
            String arr[] = str.split(" ");
            System.out.println(arr.length);
            System.out.println(Arrays.toString(arr));


            if(Integer.parseInt(arr[0].trim())<0 || Integer.parseInt(arr[1].trim()) < 0 || Integer.parseInt(arr[2].trim()) < 0 || Integer.parseInt(arr[3].trim()) < 0){

                poly++;
            }

            else if(arr[0].equals(arr[1])&& arr[1].equals(arr[2]) && arr[2].equals(arr[3])){
                square++;
            }

            else if(arr[0].equals(arr[2]) && arr[1].equals(arr[3])){
                rect++;
            }
            else{
                poly++;
            }

        }

        System.out.println(square + " " + rect + " " + poly);

    }




}

