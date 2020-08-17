package com.automationframework.testcases;
import java.lang.*;
import java.util.concurrent.ThreadPoolExecutor;

public class leetcode {
    public boolean detectCapitalUse(String word) {
        char a;
        int count = 0;
        for (int i = 0; i < word.length(); i++) {
            a = word.charAt(i);
            if (Character.isUpperCase(a)) {
                count++;
            }
        }
        if ((count == 0) | (Character.isUpperCase(word.charAt(0))) | (count == word.length()))
            return true;
        else
            return false;
    }


   static public boolean isPalindrome(String s) {

        if (s.isEmpty()){
            return true;
        }
        s = s.toLowerCase();
        System.out.println(s);
        int i = 0;
        int j = s.length() - 1;
        while (i <= j) {
            if(!Character.isLetterOrDigit(s.charAt(i))){
                i++;
            }
            else if (! Character.isLetterOrDigit(s.charAt(j))){
                j--;
            }
            else{
                System.out.println("Comparing:"+ s.charAt(i)+ " and " +s.charAt(j));
                if (s.charAt(i) != s.charAt(j)) {
                    return false;
                }
                i++;
                j--;
            }


        }
        return  true;
    }

    static public boolean isPowerOfFour(int num) {
     if(num >1){
         while (num%4==0){
             num = num/4;
         }
     }
     return num==1;
    }



    public static void main (String args[]){
    /*    boolean b = isPalindrome("A man, a plan, a canal: Panama");
        System.out.println(b);*/

        boolean b =  isPowerOfFour(256);
        System.out.println(b);
    }


    }


