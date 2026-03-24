// 3. Form a number system with only 3 and 4. Find the nth number of the number 
// system.
// Eg.) The numbers are: 3, 4, 33, 34, 43, 44, 333, 334, 343, 344, 433, 434, 443, 444,
// 3333, 3334, 3343, 3344, 3433, 3434, 3443, 3444 …

import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        
        for(int i=0;i<=n;i++){
            printNumber(i);
            System.out.print(" ");
        }
    }
    
    public static void printNumber(int n){
        int index = 0;
        int num = n+1;
        
        int[] binary = new int[32];
        
        while(num>0){
            binary[index++]=num%2;
            num=num/2;
        }
        
        // remove the msb(most significant bit) from the binary rray
        for(int i=index-2;i>=0;i--){
            if(binary[i]==0){
                System.out.print("3");
            }else{
                System.out.print("4");
            }
        }
    
    }
}