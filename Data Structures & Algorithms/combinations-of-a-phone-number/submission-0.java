class Solution {
    private static final String[] PHONE_MAP = {
        "",     "",     "abc",  "def", 
        "ghi",  "jkl",  "mno", 
        "pqrs", "tuv",  "wxyz"
    };
    public List<String> letterCombinations(String digits) {
        List<String> result=new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        if (digits == null || digits.isEmpty()) {
            return result;
        }
            
        getLetterCombinations(digits, 0, sb, result);
        return result;
    }

    public void getLetterCombinations(String digits, int i, StringBuilder sb, List<String> result){
        if(sb.length()==digits.length()){
             result.add(sb.toString());
             return;
        }

        String phoneCode=PHONE_MAP[digits.charAt(i)-'0'];

        for(char c:phoneCode.toCharArray()){
            sb.append(c);
            getLetterCombinations(digits, i+1, sb, result);
            sb.deleteCharAt(sb.length()-1);
        }
    }
}
