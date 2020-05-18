/**
 * @author Lenovo
 */
public class ReviewExer5 {
    public static void main(String[] args) {
        printPic();
    }

    public static void  printPic(){
        for(int i=1;i<=3;i++){
            for (int j=1;j<=i;j++){
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
