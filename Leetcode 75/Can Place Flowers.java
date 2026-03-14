// 605. Can Place Flowers

class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int i = 0 , prev = 0,k=flowerbed.length;

        while (i<k-1 && n>0){
            if (prev == 0 && flowerbed[i]==0 && flowerbed[i+1]==0){
                flowerbed[i]=1;
                n-=1;
            }
            prev=flowerbed[i];
            i++;
        }

        if (prev == 0 && flowerbed[i] == 0){
            n-=1;
            flowerbed[i]=1;
        }

        return (n<=0) ? true : false;

        
    }
}