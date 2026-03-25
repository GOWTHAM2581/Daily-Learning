// Level two:
// 1) Print the given pattern:
// Input:
// N= 3, M=3
// Output:
// X X X
// X 0 X
// X X X
// Input:
// N=4 M=5
// Output:
// X X X X
// X 0 0 X
// X 0 0 X
// X 0 0 X
// X X X X
// Input:
// N=6 M=7
// X X X X X X
// X 0 0 0 0 X
// X 0 X X 0 X
// X 0 X X 0 X
// X 0 X X 0 X
// X 0 0 0 0 X
// X X X X X X

import java.util.*;

class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter the no of column");
        int n = sc.nextInt();
        
        System.out.println("Enter the no of row");
        int m=sc.nextInt();
        
        for(int i = 0;i<n;i++){
            for(int j=0;j<m;j++){
                
                //outer border
                if(i==0 || j == 0 || i==n-1 || j == m-1){
                    System.out.print("X");
                }
                
                else if(i>=2 && j >= 2 && i <= n-3 && j<=m-3){
                    if(i==2 || j==2 || i==n-3 || j==m-3){
                        System.out.print("X");
                    }else{
                        System.out.print("0");
                    }
                }
                
                else{
                    System.out.print("0");
                }
            }
            System.out.println();
        }
        
    }
}