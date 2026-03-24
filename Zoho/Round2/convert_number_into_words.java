import java.util.*;

class Main {
    
    static String[]  ones ={
            "","one","two","three","four","five","six","seven",
            "eight","nine"
        };
        
        static String[] tens ={
            "","","Twenty","Thirty","Fourty","Fifty","Sixty","Seventy","Eighty","Ninety"
        };
        
        static String[] teens = {
            "ten","eleven","twelve","fourteen","fifteen","sixteen","Seventenn","eightteen","nineteen"
        };
        
    
    public static void main(String[] args) {
        
        Scanner sc  = new Scanner(System.in);
        int n = sc.nextInt();
        
        String Result  = Convert(n);
        
        System.out.println(Result);
        
    }
    
    public static String Convert(int n){
        
        if (n==0){
            return "zero";
        }
        
        String result = "";
        
        //hundred
        if(n>=100){
            result += ones[n/100] + " Hundred";
            
            n = n%100;
            
            if(n>0){
                result+=" and ";
            }
        }
        
        if(n>=10 && n<=19){
            result+=" "+teens[n-10];
        }
        
        else if(n>=20){
            result+=tens[n/10];
            
            if(n%10!=0){
                result+=" "+ones[n%10];
            }
        }
        
        else if(n>0){
            result+=ones[n];
        }
        
        return result;
    }
}