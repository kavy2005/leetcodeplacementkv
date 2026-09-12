class Solution {
    public int rob(int[] nums) {
        int n=nums.length;
        int []dp=new int[n];
        Arrays.fill(dp,-1);
        return f(n-1, nums, dp);
    }
    private int f(int i, int[]nums,int []dp){
        if(i==0){
            return nums[0];
        }
        if(i<0){
            return 0;
        }
        if(dp[i]!=-1)
            return dp[i];

         int notTake=f((i-1),nums,dp);
         int take=nums[i]+f((i-2),nums,dp);
         dp[i]=Math.max(take,notTake);
         return dp[i];   
    }
}