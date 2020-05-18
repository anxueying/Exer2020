package homework.java;

public class Homework4 {
    public static void main(String[] args) {
         String str = "abkkcadkabkebfkabkskab";
        int abCount = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.indexOf("ab",i)!=-1){
                abCount++;
                i=str.indexOf("ab",i);
            }
        }
        System.out.println("abCount = " + abCount);
    }
}
