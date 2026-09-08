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
/*
class Solution {
    public int[] plusOne(int[] digits) {
        int n = digits.length;
        for(int i=n-1;i>=0;i--){
            if(digits[i]<9){
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }
        int arr[] = new int[n+1];
        arr[0] = 1;
        return arr;
    }
}
*/