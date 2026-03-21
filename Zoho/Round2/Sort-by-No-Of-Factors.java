import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Array Size");
        int n = sc.nextInt();
        System.out.println("Enter Array Elements");
        int[] arr= new int[n];
        for(int i=0;i<n;i++){
            arr[i]=sc.nextInt();
        }
        
        int[] factor = new int[n];
        
        
        for(int i=0;i<n;i++){
            factor[i]=factorCount(arr[i]);
        }
        
        for(int i=0;i<n-1;i++){
            for(int j=0;j<n-i-1;j++){
                if(factor[j]<factor[j+1]){
                    int temp = factor[j];
                    factor[j]=factor[j+1];
                    factor[j+1]=temp;
                    
                    
                    int temp2 = arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp2;
                }
            }
        }
        
        for(int i=0;i<n;i++){
            System.out.print(arr[i]+" ");
        }
    }
    
    public static int factorCount(int n){
        int count= 0;
        
        for(int i=1;i*i<=n;i++){
            if(n%i==0){
                count++;
                if(i!=n/i){
                    count++;
                }
            }
        }
        
        return count;
    }
}