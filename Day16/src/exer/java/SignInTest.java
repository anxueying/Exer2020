package exer.java;

/*
4、编写一个登录的方法，参数自行设计，登录成功返回true,登录失败返回false
     如果用户名或密码错误，报异常，设置异常信息“用户名或密码错误”
     这里，暂时用户名是admin,密码是123456
 要求：
（1）登录方法声明抛出异常
（2）在main方法中调用登录的方法，并使用try..catch..finally处理异常，
	在catch中使用异常的打印方式显示异常消息
 */
public class SignInTest {
    public static void main(String[] args)  {
        SignIn signIn = new SignIn();
        try {
            signIn.sighUp("admin","123456");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}


class SignIn{
    private String username = "admin";
    private String password = "123456";

    public boolean sighUp(String username, String password) throws Exception {
        if (username.equals(this.username) && password.equals(this.password)){
            System.out.println("登录成功");
            return true;
        }else if (!username.equals(this.username)){
            throw new Exception("用户名错误");
        }else if(!password.equals(this.password)){
            throw new Exception("密码错误");
        } else {
            System.out.println("登录失败");
            return false;
        }
    }

    public SignIn() {
    }

    public SignIn(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}