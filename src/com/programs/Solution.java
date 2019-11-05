package com.programs;

import java.util.Calendar;
import java.util.HashMap;

public class Solution {

    static HashMap<String, Integer> dayMap = new HashMap<>();
    static HashMap<String, Integer> monthMap = new HashMap<>();

    static {
        dayMap.put("SUNDAY", Calendar.SUNDAY);
        dayMap.put("MONDAY", Calendar.MONDAY);
        dayMap.put("TUESDAY", Calendar.TUESDAY);
        dayMap.put("WEDNESDAY", Calendar.WEDNESDAY);
        dayMap.put("THURSDAY", Calendar.THURSDAY);
        dayMap.put("FRIDAY", Calendar.FRIDAY);
        dayMap.put("SATURDAY", Calendar.SATURDAY);


        monthMap.put("JANUARY", Calendar.JANUARY);
        monthMap.put("FEBRUARY", Calendar.FEBRUARY);
        monthMap.put("MARCH", Calendar.MARCH);
        monthMap.put("APRIL", Calendar.APRIL);
        monthMap.put("MAY", Calendar.MAY);
        monthMap.put("JUNE", Calendar.JUNE);
        monthMap.put("JULY", Calendar.JULY);
        monthMap.put("AUGUST", Calendar.AUGUST);
        monthMap.put("SEPTEMBER", Calendar.SEPTEMBER);
        monthMap.put("OCTOBER", Calendar.OCTOBER);
        monthMap.put("NOVEMBER", Calendar.NOVEMBER);
        monthMap.put("DECEMBER", Calendar.DECEMBER);
    }

    public static void main(String[] args) {


        int res = solution(2014, "April", "September", "Wednesday");
        System.out.println(res);
    }


    /**
     * Que: Ramesh who lives in Bangalore wants to go to island for a vacation. The problem is there is a flight which goes only on Monday from bangalore to that island
     * and there is a return flight from island to bangalore which runs only on sunday. Ramesh wants to stay as long as he can on that island.
     * If given a month Ramesh goes on first monday of that month from banaglore and take a return fligh on last sunday of given month.
     * Given a start trip month ie the month in which Ramesh goes to island ie he will go on first monday as that would be first flight
     * and a trip end month Ramesh has to take last sunday of that month to come back. Our requirement is to calculate how many weeks Ramesh can
     * stay there.
     * We will also be given year and day of week which falls on 1st jan of that year
     *
     * @param Y Year of trip
     * @param A start Month
     * @param B end month will always be greater than start month
     * @param W day of week on 1st january of that year
     * @return
     */
    public static int solution(int Y, String A, String B, String W) {
        // write your code in Java SE 8
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, Y);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DATE, 1);
        cal.set(Calendar.DAY_OF_WEEK, getDay(W));

        int startMonth = getMonth(A);
        int endMonth = getMonth(B);
        int numberofMon = 0;
        int numberOfSun = 0;
        while (cal.get(Calendar.MONTH) <= endMonth) {
            cal.add(Calendar.DAY_OF_MONTH, 1);
            if (cal.get(Calendar.MONTH) >= startMonth && cal.get(Calendar.MONTH) <= endMonth) {

                if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
                    numberofMon++;
                }
                if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                    if (numberofMon > 0) {
                        numberOfSun++;
                    }

                }
            }
        }


        return Math.min(numberofMon, numberOfSun);
    }


    private static int getMonth(String month) {
        return monthMap.get(month.toUpperCase());
    }

    private static int getDay(String day) {
        return dayMap.get(day.toUpperCase());
    }
}
