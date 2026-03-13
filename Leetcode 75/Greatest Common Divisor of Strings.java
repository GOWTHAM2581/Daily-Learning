//1071. Greatest Common Divisor of Strings

class Solution {
    public String gcdOfStrings(String str1, String str2) {
        String gcd = (str1.length()<str2.length()) ? str1 : str2;

        while(gcd.length()>0){
             if(isDivisable(str1,gcd) && isDivisable(str2,gcd)){
                return gcd;
             }
             gcd = gcd.substring(0,gcd.length()-1);
        }

        return "";
    }

     public static Boolean isDivisable(String word, String div){
        
         int l = div.length() , L =word.length();

         if(L%l!=0){
            return false;
         }
         int j = 0;

         for(int i =0;i<word.length();i++){
            if(word.charAt(i)!=div.charAt(j)){
                return false;
            }
            j=(j+1)%l;
         }
         return true;

        }
}