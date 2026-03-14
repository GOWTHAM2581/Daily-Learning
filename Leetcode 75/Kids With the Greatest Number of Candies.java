//1431. Kids With the Greatest Number of Candies

class Solution {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        
        int max = Max(candies);

        List<Boolean> result = new ArrayList<>();

        for(int i=0;i<candies.length;i++){
            if (candies[i]+extraCandies >= max){
                result.add(true);
            }else{
                result.add(false);
            }
        }

        return result;
    }

    public static int Max(int[] candies){
        int max = 0;

        for (int i=0;i<candies.length;i++){
            if (max<candies[i]){
                max = candies[i];
            }
        }

        return max;
    }
}