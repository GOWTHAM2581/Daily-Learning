import java.util.*;

class Main {
    public static void main(String[] args) {
        int[] a = {9,9,9};
        int[] b = {1,1};
        
        int n = a.length;
        int m = b.length;
        
        int max  = (n>m ? n : m);
        
        int[] result = new int[max+1];
        
        int i = n -1;
        int j = m -1;
        int k = max;
        int carry = 0;
        
        while(i>=0 || j>=0 || carry>0){
            
            int sum = carry;
            
            if(i>=0){
                sum+=a[i--];
            }
            if(j>=0){
                sum+=b[j--];
            }
            
            result[max--]=sum%10;
            
            carry = sum / 10;
        }
        
        for(int l=0;l<result.length;l++){
            System.out.print(result[l]+" ");
        }
    }
}