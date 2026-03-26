import java.util.*;

class Main {
    static int count = 0;
    
    public static void main(String[] args) {
    
            int[] arr = {3,9,7,4,6,8};
            
            int X = 3;
            for(int size = 2;size<=X;size++){
                int[] temp = new int[size];
                generateCombination(arr,temp,0,0,size,X);
            }
            
            System.out.println("The number of Possibility "+count);
        }
        
    public static void generateCombination(int[] arr,int[] temp,int start,int index,int size,int X){
            
            if(index == size){
                checkAndCount(temp,X);
                return;
            }
            
            for(int i=start;i<arr.length;i++){
                temp[index]=arr[i];
                generateCombination(arr,temp,i+1,index+1,size,X);
            }
        }
        
         public static void checkAndCount(int[] temp,int X){
            
            int sum = 0;
            
            for(int i=0;i<temp.length;i++){
                sum+=temp[i];
            }
            
            if(sum%X==0){
                count++;
            }
        }
}