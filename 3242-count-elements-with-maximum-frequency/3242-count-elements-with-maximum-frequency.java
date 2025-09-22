class Solution {
    public int maxFrequencyElements(int[] nums) {
        Map<Integer,Integer> mp=new HashMap<>();
        int maxi=0;
        for (Integer k:nums){
            int cnt=mp.getOrDefault(k,0)+1;
            mp.put(k,cnt);
            maxi=Math.max(maxi,cnt);
        }
        int result=0;
        for (int count:mp.values()){
            if (count==maxi) result+=maxi;
        }
        return result;
    }
}