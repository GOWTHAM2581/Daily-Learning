// A man his driving car from home to office with X petrol. There are N number of 
// petrol bunks in the city with only few capacities and each petrol is located in 
// different places For one km one liter will consume. So he fill up petrol in his 
// petrol tank in each petrol bunks. Output the remaining petrol if he has or tell him
// that he cannot travel if he is out of petrol.
// Input:
// Petrol in car: 2 Liters
// Petrol bunks: A B C
// Distance from petrol each petrol bunks: 1, 5, 3
// Capacities of each petrol bunk: 6, 4, 2
// Output:
// Remaining petrol in car is 5 liters


import java.util.*;

class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Available petrol in car");
        int petrol = sc.nextInt();
        
        System.out.println("Enter the number of petrol bunk");
        int petrolBunk = sc.nextInt();
        
        int[] petrolDistance = new int[petrolBunk];
        for(int i = 0;i<petrolBunk;i++){
            System.out.println("Enter the Bunk "+i+1+" Distance");
            petrolDistance[i]=sc.nextInt();
        }
        
        int[] petrolBunkCapacity = new int[petrolBunk];
        for(int i = 0;i<petrolBunk;i++){
            System.out.println("Enter the Bunk "+(i+1)+" Capacity");
            petrolBunkCapacity[i]=sc.nextInt();
        }
        
        findPetrolAvailability(petrol,petrolBunk,petrolDistance,petrolBunkCapacity);
        
        
    }
    
    public static void findPetrolAvailability(int petrol,int petrolBunk,int[] petrolDistance,int[] petrolBunkCapacity){
        
        
        for(int i=0;i<petrolBunk;i++){
            petrol = petrol - petrolDistance[i];
            
            if(petrol<=0){
                System.out.println("No petrol Available");
                return;
            }
            
            petrol += petrolBunkCapacity[i];
            
        }
        
        System.out.println("Available Petrol are "+petrol);
    }
}