class Solution {
    public String convertToTitle(int columnNumber) {
        int r = columnNumber;
        int n= r;
        StringBuilder sb=new StringBuilder();
        while(n!=0){
            n--;
            r=n%26;
            sb.append((char)(r+'A'));
            n/=26;
        }

        return sb.reverse().toString();
    }
}