package project.java;

/**
 * 异常类
 */
public class TeamException extends Exception{
    //private String message;
    public TeamException() {
    }

    public TeamException(String message) {
        //this.message = message;
        super(message);
    }

    /*public String getMessage(){
        return this.message;
    }*/
}
