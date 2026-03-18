//  Given two sorted arrays, merge them such that the elements are not repeated
// Eg 1: Input:
//         Array 1: 2,4,5,6,7,9,10,13
//         Array 2: 2,3,4,5,6,7,8,9,11,15
//        Output:
//        Merged array: 2,3,4,5,6,7,8,9,10,11,13,15


import java.util.*;

public class Main {
    public static void main(String[] args) {
      int[] a = {1,2,3,4,5};
      int[] b = {2,4,5,6,8,12};
      
      List<Integer> res = mergeArray(a,b);
      
      System.out.print(res);
    }
    
    public static List<Integer> mergeArray(int[] a,int[] b){
       int i = 0,j=0;
       
       List<Integer> res = new ArrayList<>();
       
       while(i<a.length && j < b.length){
         if(a[i]<b[j]){
           addIfNotDuplicate(res,a[i]);
           i++;
         }else if(b[j]<a[i]){
           addIfNotDuplicate(res,b[j]);
           j++;
         }else{ //if both are equal
           addIfNotDuplicate(res,a[i]);
           i++;
           j++;
         }
       }
       
       while(i<a.length){
         addIfNotDuplicate(res,a[i]);
           i++;
       }
       
       while(j<b.length){
         addIfNotDuplicate(res,b[j]);
           j++;
       }
       
       return res;
    }

    public static void addIfNotDuplicate(List<Integer> res,int val){
      if(res.isEmpty() || res.get(res.size()-1)!=val){
        res.add(val);
      }
    }
}