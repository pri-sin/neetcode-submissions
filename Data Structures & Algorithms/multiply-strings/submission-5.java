class Solution {
    public String multiply(String num1, String num2) {
        if(num1.equals("0") || num2.equals("0")) return "0";
        int n1=num1.length();
        int n2=num2.length();
        int pos[]=new int[n1+n2];
        
        for(int i=n1-1;i>=0;i--){
            int n1c=num1.charAt(i)-'0';
            for(int j=n2-1;j>=0;j--){
                int n2c=num2.charAt(j)-'0';
                int mul=n1c*n2c;
                int p1=i+j;
                int p2=i+j+1;

                int sum=mul+pos[p2];
                pos[p1]+=sum/10;
                pos[p2]=sum%10;
            }
        }

        StringBuilder s=new StringBuilder();

        for(int p:pos){
            if(!(s.length()==0 && p==0)){
                s.append(p);
            }
        }

        return s.length()==0?"0":s.toString();

    }
}

/*class Solution {
    public String multiply(String num1, String num2) {
        if(num1.equals("0") || num2.equals("0")) return "0";
        
        if(num1.length()>num2.length()){
            String temp=num1;
            num1=num2;
            num2=temp;
        }
        int n1=num1.length();
        int n2=num2.length();
        StringBuilder s1=new StringBuilder();
        for(int i=n1-1;i>=0;i--){
            int carry=0;
            StringBuilder s2=new StringBuilder();
            int n1c=num1.charAt(i)-'0';
            for(int j=n2-1;j>=0;j--){
                int n2c=num2.charAt(j)-'0';
                int prod=n1c*n2c+carry;
                carry=prod/10;
                s2.insert(0, prod%10);
            }
            if(carry>0){
                s2.insert(0, carry);
            }
            
            s2.append("0".repeat(n1-i-1));
            s1=sum(s1,s2);
        }
        return s1.toString();
    }

    public StringBuilder sum(StringBuilder s1, StringBuilder s2){
        int carry=0;
        int i=s1.length()-1,j=s2.length()-1;
        StringBuilder s=new StringBuilder();
        
        while(i>=0 && j>=0){
            int s1c=s1.charAt(i)-'0';
            int s2c=s2.charAt(j)-'0';
            int sum=s1c+s2c+carry;
            carry=sum/10;
            s.insert(0,sum%10);
            i--;
            j--;
        }

        while(i>=0){
            int s1c=s1.charAt(i)-'0';
            int sum=s1c+carry;
            carry=sum/10;
            s.insert(0,sum%10);
            i--;
        }

        while(j>=0){
            int s2c=s2.charAt(j)-'0';
            int sum=s2c+carry;
            carry=sum/10;
            s.insert(0,sum%10);
            j--;
        }

        if(carry>0){
            s.insert(0, carry);
        }

        return s;
    }
}//got accepted but takes too much time*/

/*class Solution {
    public String multiply(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }

        int n1 = num1.length();
        int n2 = num2.length();
        if (n1 > n2) {
            String temp = num1;
            num1 = num2;
            num2 = temp;
            int tempN = n1;
            n1 = n2;
            n2 = tempN;
        }

        StringBuilder s1 = new StringBuilder();

        for (int i = n1 - 1; i >= 0; i--) {
            int carry = 0;
            StringBuilder s2 = new StringBuilder();
            
            // Pad trailing zeros first
            s2.append("0".repeat(n1 - 1 - i));

            int n1c = num1.charAt(i) - '0';
            for (int j = n2 - 1; j >= 0; j--) {
                int n2c = num2.charAt(j) - '0';
                int prod = n1c * n2c + carry;
                carry = prod / 10;
                s2.append(prod % 10);
            }
            if (carry > 0) {
                s2.append(carry);
            }

            s2.reverse();
            s1 = sum(s1, s2);
        }

        return s1.toString();
    }

    public StringBuilder sum(StringBuilder s1, StringBuilder s2) {
        int carry = 0;
        int i = s1.length() - 1, j = s2.length() - 1;
        StringBuilder s = new StringBuilder();

        while (i >= 0 || j >= 0 || carry > 0) {
            int sum = carry;
            if (i >= 0) sum += s1.charAt(i--) - '0';
            if (j >= 0) sum += s2.charAt(j--) - '0';

            carry = sum / 10;
            s.append(sum % 10);
        }

        return s.reverse();
    }
}//Better Approach, doesnt have insert overhead, just reverse done once
*/
