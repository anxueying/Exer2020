public class Test06 {
    public static void main(String[] args) {
        MathTools mathTools = new MathTools();
        System.out.println(mathTools.add(3,4));
        System.out.println(mathTools.subtract(3,4));
        System.out.println(mathTools.mutiply(3,4));
        System.out.println(mathTools.divide(3,4));
        System.out.println(mathTools.remainder(3,4));
        System.out.println(mathTools.max(3,4));
        System.out.println(mathTools.min(3,4));
        System.out.println(mathTools.equals1(3,4));
        System.out.println(mathTools.isEven(3));
        System.out.println(mathTools.isPrimeNumber(3));
        System.out.println(mathTools.round(3.6));

    }
}

class MathTools{
    public int add(int a,int b){
        return a+b;
    }

    public int subtract(int a,int b){
        return a-b;
    }

    public int mutiply(int a,int b){
        return a*b;
    }

    public int divide(int a,int b){
        return a/b;
    }

    public int remainder(int a,int b){
        return a%b;
    }

    public int max(int a,int b){
        if(a>b){
            return a;
        }else {
            return b;
        }
    }

    public int min(int a,int b){
        if(a>b){
            return b;
        }else {
            return a;
        }
    }

    public boolean equals1(int a,int b) { //怕覆盖底层代码，改了名字
        if(a==b){
            return true;
        }else {
            return false;
        }
    }

    public boolean isEven(int a){
        if(a%2==0){
            return true;
        }else{
            return false;
        }
    }

    public boolean isPrimeNumber(int a){
            boolean flag = true;
            for(int j=2;j<a;j++){
                if(a%j==0){
                    flag = false;
                }
            }
            return flag;
    }

    public int round(double d){
        if((d*10)%10>4){
            return (int)d+1;
        }else{
            return  (int)d;
        }
    }
}