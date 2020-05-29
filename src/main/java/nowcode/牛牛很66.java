package nowcode;

public class 牛牛很66 {
    public static void main(String[] args) {

        int i = 0;
        int j = 0;
        long z = 0;
        for ( ;i < Integer.MAX_VALUE; i++) {
            for (j = 0; j < Integer.MAX_VALUE; j++) {
                z++;
            }
        }
        System.out.println(i+j+z);
//        Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt();
//        interview.BigInteger x = new interview.BigInteger("9");
//        interview.BigInteger y = new interview.BigInteger("1");
//        interview.BigInteger z = new interview.BigInteger("0");
//        interview.BigInteger temp1 = new interview.BigInteger("9");
//        interview.BigInteger temp2 = new interview.BigInteger("10");
//        interview.BigInteger temp3 = new interview.BigInteger("0");
//        interview.BigInteger temp4 = interview.BigInteger.valueOf(1);
//        for (int i = 0; i < n; i++) {
//            temp4=temp4.multiply(temp2);
//        }
//
//
//        for (int i = 2; i <= n; i++) {
//            z = y.add(z.multiply(temp2));
//            y = x.add(temp3);
//            x = x.multiply(temp1);
//        }
//        System.out.println("\""+temp4.subtract(z).toString()+"\"");
    }
}
