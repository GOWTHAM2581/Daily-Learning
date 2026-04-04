// . Spiral printing.
// O/P
// 4444444
// 4333334
// 4322234
// 4321234
// 4322234
// 4333334
// 4444444


import java.util.*;

class Pattern{
    public static void main(String[] args){
        int n = 9;
        
        int center = n/2;
        
        for(int i =0;i<n;i++){
            for(int j=0;j<n;j++){
                
                int top = i;
                int left = j;
                int right = n - j - 1;
                int bottom = n - i - 1;
                
                int min = top;
                
                if(left<min){
                    min = left;
                }
                if(right<min){
                    min = right;
                }
                if(bottom<min){
                    min = bottom;
                }
                
                int value  = center - min +1;
                
                System.out.print(value+" ");
            }
            System.out.println();
        }
    }
}