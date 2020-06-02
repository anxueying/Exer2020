package exer;

public class ApiTest {
    public static void main(String[] args) {
        Integer num1 = new Integer(args[0]);
        Integer num2 = Integer.parseInt(args[1]);

        int n1 = num1.intValue();
        int n2 = num2.intValue();

        System.out.println("n1 + n2 = " + (n1+n2));

        int n3 = num1;
        int n4 = num2;

        System.out.println("n3*n4 = " + (n3 * n4));

        String str1 = "a";
        String str2 = "b";
        String str3 = "a" + "b";

        str3 = str1+ str2;

        //String str4 = str1+ str2;
        //System.out.println(str3 == str4);


    }
}
