class Solution {
    public int smallestAbsent(int[] nums) {
        Set<Integer> s=new HashSet<Integer>();

        int sum=0;

        for (int num:nums){
            s.add(num);
            sum+=num;
        }

        double avg=(double)sum/nums.length;

        int cand=(int) Math.floor(avg)+1;

        if (cand<=0) cand=1;

        while (true){
            if (!s.contains(cand)) return cand;
            cand++;
        }
    }
}