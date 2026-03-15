//151. Reverse Words in a String

class Solution {
    public String reverseWords(String s) {
        String[] words = s.trim().split("\\s+");

        int l =0 , r=words.length -1;

        while (l<r){
            String temp = words[l];
            words[l]=words[r];
            words[r]=temp;
            l+=1;
            r-=1;
        }

        return String.join(" ",words);

        // for(int i=words.length()-1;i>=0;i--){
        //     rev+=word.charAt(i);
        //  }
        // return String.join(" ",words);
    }

    // public static String reverse(String word){
        
    //     String rev ="";

    //     for(int i=word.length()-1;i>=0;i--){
    //         rev+=word.charAt(i);
    //     }

    //     return rev;
    // }
}