package homework;

public class ExerTest1 {
    public static void main(String[] args) {
        //char[] alphabet = new char[36]; 另一种写法
        char[] alphabet; //声明
        alphabet = new char[36];  //初始化

        //赋值
        char a = 'A';
        char b = '0';

        for (int i = 0;i<alphabet.length;i++){
            if(i<26){
                alphabet[i] = a;
                a++;
            }else {
                alphabet[i] = b;
                b++;
            }
            System.out.print(alphabet[i]);//输出
        }
    }
}
