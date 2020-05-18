package homework.java;

public class TrimTest {

    public static void main(String[] args) {
        System.out.println(MyTrim("  abc   ac  "));
    }

    public static String MyTrim(String str){
        String temp1 = "";
        for (int i = 0; i < str.length(); i++) {
            if(!str.substring(i,i+1).equals(" ")){
                temp1 = str.substring(i);
                break;
            }
        }

        String temp2 = "";
        for (int i = temp1.length(); i >0; i--) {
            if(!temp1.substring(i-1,i).equals(" ")){
                temp2 = temp1.substring(0,i);
                break;
            }
        }

        return temp2;
    }
}
