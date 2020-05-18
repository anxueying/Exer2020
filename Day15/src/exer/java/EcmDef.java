package exer.java;

public class EcmDef {
    public static void main(String[] args) {
        //System.out.println("开始运行");
        try {
            int num1 =  Integer.parseInt(args[0]);
            //System.out.println(num1);
            int num2 = Integer.parseInt(args[1]);
            try {
                int num3 = intDiv(num1,num2);
                System.out.println(num3);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }catch (NumberFormatException n){
            throw new NumberFormatException("必须输入整数类型");
        }catch (ArrayIndexOutOfBoundsException a){
            throw new ArrayIndexOutOfBoundsException("输入参数过少");
            //System.out.println(a.getMessage());
        }
       //System.out.println("结束运行");
    }


    public static int intDiv(int i,int j) throws Exception{
        if (i<0||j<0){
            throw new Exception("不能输入负数！");
        }else {
            try {
                //System.out.println("子类try");
                return i/j;
            }catch (ArithmeticException a){
                throw new ArithmeticException("除数不能为0！");
            }
        }
    }
}
