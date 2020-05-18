/**
 * @author Lenovo
 */
public class ReviewExer3 {
    public static void main(String[] args) {
        signIn(123,456);
    }

    public static void signIn(int user,int passwd){
        if(user==123&&passwd==456){
            System.out.println("登录成功");
        }else{
            System.out.println("登录失败");
        }
    }
}
