class Solution {
    public int rob(int[] nums) {
        int n=nums.length;
        if(n==1)
            return nums[0];
        int case1=robrange(nums,0,n-2);
        int case2=robrange(nums,1,n-1);
        return Math.max(case1,case2);    
        
    }
    private int robrange(int []nums, int start, int end){
        int[]dp=new int[nums.length];
        Arrays.fill(dp,-1);
        return f(end,nums,start,dp);
    }
    private int f(int i, int []nums, int start, int[]dp){
        if(i<start)
            return 0;
        if(i==start)
            return nums[start];
        if(dp[i]!=-1){
            return dp[i];
        }       
        int take=nums[i]+f(i-2,nums,start,dp);
        int nottake=f(i-1,nums,start,dp);
        dp[i]=Math.max(take,nottake);
        return dp[i];
    }
}