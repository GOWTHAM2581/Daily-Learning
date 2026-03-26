import java.util.*;

class Main{
    public static void main(String[] args){
        System.out.println("Enter a Number you wnated to convert into Excel column");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        

        char[] result = new char[100]; 
        int index =0;
        while(n>0){
             n = n - 1;
            int  rem = n %26;
             result[index++]=(char) ('A'+ rem);
             n=n/26;
             
        }
        
        for(int i = index-1;i>=0;i--){
            System.out.print(result[i]);
        }
    
}}