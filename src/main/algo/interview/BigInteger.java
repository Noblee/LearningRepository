package interview;

public class BigInteger {

    private String value;


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public BigInteger(String value) {
        this.value = value;
    }

    public BigInteger minus(BigInteger target) {
        if (this.value.charAt(0) == '-' && target.getValue().charAt(0) != '-') {
            return new BigInteger("-" + doAdd(this.value.substring(1), target.getValue()));
        } else if (this.value.charAt(0) != '-' && target.getValue().charAt(0) == '-') {
            return new BigInteger(doAdd(this.value, target.getValue().substring(1)));
        } else if (this.value.charAt(0) == '-' && target.getValue().charAt(0) == '-') {
            return new BigInteger(doMinus(target.getValue().substring(1), this.value.substring(1)));
        } else {
            return new BigInteger(doMinus(this.value, target.value));
        }
    }

    private String doMinus(String s1, String s2) {
        boolean swap = false;
        if (!compare(s1, s2)) {
            String temp = s1;
            s1 = s2;
            s2 = temp;
            swap = true;
        }
        s1 = new StringBuilder(s1).reverse().toString();
        s2 = new StringBuilder(s2).reverse().toString();
        int flag = 0;
        StringBuilder ans = new StringBuilder();
        int i = 0;
        for (; i < Math.min(s1.length(), s2.length()); i++) {
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);
            if (c1 - c2 - flag < 0) {
                ans.append((char) (c1 - c2 - flag + 10 + '0'));
                flag = 1;
            } else {
                ans.append((char) (c1 - c2 - flag + '0'));
                flag = 0;
            }
        }
        for (; i < s1.length(); i++) {
            char c1 = s1.charAt(i);
            if (c1 - flag - '0' < 0) {
                ans.append((char) (c1 - flag + 10));
                flag = 1;
            } else {
                ans.append((char) (c1 - flag));
                flag = 0;
            }
        }

        String res = ans.reverse().toString();
        if (swap) {
            res = "-" + res;
        }
        return res;
    }

    boolean compare(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return s1.length() > s2.length();
        } else {
            for (int i = 0; i < s1.length(); i++) {
                if (s1.charAt(i) != s2.charAt(i)) {
                    return s1.charAt(i) > s2.charAt(i);
                }
            }
        }
        return true;
    }

    private String doAdd(String s1, String s2) {
        s1 = new StringBuilder(s1).reverse().toString();
        s2 = new StringBuilder(s2).reverse().toString();
        int flag = 0;
        StringBuilder ans = new StringBuilder();
        int i = 0;
        for (; i < Math.min(s1.length(), s2.length()); i++) {
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);
            if (c1 + c2 + flag - 2*'0'>= 10) {
                ans.append((char) (c1 + c2 + flag - 10 - '0'));
                flag = 1;
            } else {
                ans.append((char) (c1 + c2 + flag - '0'));
                flag = 0;
            }
        }
        for (; i < s1.length(); i++) {
            char c1 = s1.charAt(i);
            if (c1 + flag - '0' >= 10) {
                ans.append((char) (c1 + flag - 10));
                flag = 1;
            } else {
                ans.append((char) (c1 + flag));
                flag = 0;
            }
        }
        for (; i < s2.length(); i++) {
            char c2 = s2.charAt(i);
            if (c2 + flag - '0' >= 10) {
                ans.append((char) (c2 + flag - 10));
                flag = 1;
            } else {
                ans.append((char) (c2 + flag));
                flag = 0;
            }
        }
        return ans.reverse().toString();
    }

    public static void main(String[] args) {
        BigInteger b1 = new BigInteger("123456");
        BigInteger b2 = new BigInteger("5223456");
        System.out.println(b1.minus(b2).getValue());
        b1 = new BigInteger("5223456");
        b2 = new BigInteger("123456");
        System.out.println(b1.minus(b2).getValue());
        b1 = new BigInteger("-123456");
        b2 = new BigInteger("-5223456");
        System.out.println(b1.minus(b2).getValue());
        b1 = new BigInteger("123456");
        b2 = new BigInteger("-5223456");
        System.out.println(b1.minus(b2).getValue());
        b1 = new BigInteger("-123456");
        b2 = new BigInteger("5223456");
        System.out.println(b1.minus(b2).getValue());
    }
}
