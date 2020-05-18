/**
 * @author Lenovo
 */
public class ReviewExer1 {
    public static void main(String[] args) {
        User shelly = new User();
        shelly.ID = 7;
        shelly.userName = "shelly";
        shelly.password = "shelly";

        ReviewExer1 re = new ReviewExer1();

        User user1 = re.sighing(shelly.userName,shelly.password);
        if(user1==null){
            System.out.println("登录失败");
        }else {
            System.out.println(user1.getDetails());
        }


        User shelly1 = new User();
        shelly1.ID = 8;
        shelly1.userName = "shelly1";
        shelly1.password = "shelly1";
        User user2 = re.sighing(shelly1.userName,shelly1.password);
        if(user2==null){
            System.out.println("登录失败");
        }else {
            System.out.println(user1.getDetails());
        }
    }

    public User sighing(String username,String password){
        if(username.equals("shelly") && password.equals("shelly")){
            //return new User(); 返回个新对象
            //模拟查询后测试数据
            User user = new User();
            user.ID = 9527;
            user.userName = "admin";
            user.password = "123456";
            return user;
        }else{
            return null;
        }
    }
}

class User{
    int ID;
    String userName;
    String password;
    public String getDetails(){
        return ID+userName+password;
    }
}
