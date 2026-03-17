// Online Java Compiler
// Use this editor to write, compile and run your Java code online
import java.util.*;
class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("ENter String1");
        String s1 = sc.nextLine();
        System.out.println("ENter String2");
        String s2 = sc.nextLine();
        
        System.out.println(subStringFinder(s1,s2));
        
        
    }
    
    public static int subStringFinder(String s1,String s2){
        int m = s1.length();
        int n = s2.length();
        
        for(int i = 0;i<=m-n;i++){
            int j=0;
            
            while(j<n && s1.charAt(i+j)==s2.charAt(j)){
                j++;
            }
            
            if(n==j){
                return i; 
            }
        }
        
        return -1;
        
    }
}