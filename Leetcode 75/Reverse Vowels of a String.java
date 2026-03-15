//345. Reverse Vowels of a String

class Solution {
    public String reverseVowels(String s) {
        char[] words = s.toCharArray();

        int l = 0 , r = words.length - 1;

       

        while (l < r){
          if (!vowelFinder(words[l])){
            l+=1;
          }else if(!vowelFinder(words[r])){
            r-=1;
          }else{
            char temp = words[l];
            words[l]=words[r];
            words[r]=temp;

            l+=1;
            r-=1;
          }
        }

        return new String(words);
    }

    public static boolean vowelFinder(char s){

     char[] vowels = {'a','e','i','o','u','A','E','I','O','U'};

     for(int i=0;i<vowels.length;i++){
        if(vowels[i]==s){
            return true;
        }
     }

     return false;
     

    }
}