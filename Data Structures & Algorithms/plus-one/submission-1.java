class Solution {
    public int[] plusOne(int[] digits) {
        
        int carry=1;
        List<Integer> res=new ArrayList<>();
        for(int i=digits.length-1;i>=0;i--){
            int x=digits[i]+carry;
            res.add(0, x%10);
            carry=x/10;
        }

        if(carry!=0){
            res.add(0, carry);
        }
        
        return res.stream().mapToInt(Integer::intValue).toArray();
    }
}
