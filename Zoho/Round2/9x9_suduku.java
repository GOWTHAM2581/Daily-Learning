import java.util.*;

class Main {
    public static void main(String[] args) {
                int[][] board = {
            {5,3,4,6,7,8,9,1,2},
            {6,7,2,1,9,5,3,4,8},
            {1,9,8,3,4,2,5,6,7},

            {8,5,9,7,6,1,4,2,3},
            {4,2,6,8,5,3,7,9,1},
            {7,1,3,9,2,4,8,5,6},

            {9,6,1,5,3,7,2,8,4},
            {2,8,7,4,1,9,6,3,5},
            {3,4,5,2,8,6,1,7,9}
        };

        if(isValidSuduko(board)){
            System.out.println("This is valid suduko");
        }else{
            System.out.println("This is Invalid suduko");
        }
    }
    
    public static boolean isValidSuduko(int[][] board){
        
        // 1. Check for row
        
        for(int i=0;i<9;i++){
            int[] freq = new int[10];
            for(int j=0;j<9;j++){
    
                int data  = board[i][j];
                if(data<0 || data>9 || freq[data]==1){
                    return false;
                }
                
                freq[data]=1;
            }
        }
        
        // 2. Check for column
        
        for(int j=0;j<9;j++){
            int[] freq = new int[10];
            for(int i=0;i<9;i++){
                
                int data  = board[i][j];
                if(data<0 || data>9 || freq[data]==1){
                    return false;
                }
                
                freq[data]=1;
            }
        }
        
         // 3. Check for inner pattern
         
         for(int row = 0;row<9;row+=3){
             
             for(int column=0;column<9;column+=3){
                 int[] freq = new int[10];
                 for(int i=0;i<3;i++){
                     for(int j=0;j<3;j++){
                         int data = board[row+i][column+j];
                         if(freq[data]==1 || data < 0 || data > 9){
                             return false;
                         }
                         freq[data] = 1;
                     }
                     
                     
                 }
             }
         }
         return true;
    }
}