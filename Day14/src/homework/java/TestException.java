package homework.java;

public class TestException {
    public static void main(String[] args) {
        int num1 = 0;
        int num2 = 0;
        try {
            num1 = Integer.parseInt(args[0]);
            num2 = Integer.parseInt(args[1]);

            try {
                System.out.println(num1 / num2);
            } catch (ArithmeticException a) {
                System.out.println("除数为0");
            }

        } catch (ArrayIndexOutOfBoundsException a) {
            System.out.println("参数不足");
        } catch (NumberFormatException n) {
            System.out.println("参数类型有误");
        }
    }
}
