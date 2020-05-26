package exer;

/**
 * @author Mrs.An Xueying
 * 2020/5/25 15:02
 */
public class Person {
    private String namePrivate;
    private int agePrivate;

    public String idPublic;
    public int numberPublic;


    public void info(){
        System.out.println("namePrivate==" + namePrivate);
    }

    public void demoPublic(String name,int age){
        this.idPublic = name;
        this.numberPublic = age;
        System.out.println("demo Public===" + name + "===" +age);
    }

    private void demoPrivate(){
        System.out.println("demoPrivate");
    }
}
