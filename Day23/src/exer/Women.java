package exer;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;



/**
 * @author Lenovo
 */
@MyAnn("aaaa")
public class Women implements IEnglish{

    @MyAnn("women")
    public Women(){
        System.out.println("Women()");
    }

    public Women(String name){
        System.out.println("Women(String name)" + name);
    }

    private Women(int a,int b){

    }

    @MyAnn("info")
    public void info(){

    }

}
