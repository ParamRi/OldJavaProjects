/*
Programmer: Param A Ri
Instructor: Mrs. Jackson
Class: CSCI-1302
Date: 1/13/14
*/

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
/*
   This program combines two arrays into one array.
*/
public class ArrayAppend
{
   static ArrayList<Integer> A = new ArrayList<Integer>(Arrays.asList(3,4,19,6));
   static ArrayList<Integer> B = new ArrayList<Integer>(Arrays.asList(34,4,16,89,12));
   public static void main(String[]args)
   {
      System.out.print("Array #1: ");
      for(int i =0; i < A.size(); i++)
      {
         int num = A.get(i);
         System.out.print(num +", ");
      }
      System.out.println();
      System.out.print("Array #2: ");
      for(int i =0; i < B.size(); i++)
      {
         int num = B.get(i);
         System.out.print(num +", ");
      }
      
      ArrayList<Integer> C = append(A, B);
      
      System.out.println();
      System.out.print("Appended Array: ");
      for(int i =0; i < C.size(); i++)
      {
         int num = C.get(i);
         System.out.print(num +", ");
      }
   }
   
   public static ArrayList<Integer> append(ArrayList<Integer> a, ArrayList<Integer> b)
   {
      for( int i = 0; i < b.size(); i++)
      {
         a.add(b.get(i));
      }
      return a;
   }

}