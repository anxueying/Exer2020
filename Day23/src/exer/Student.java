package exer;

public class Student extends Person{

    private String snamePrivate;
    private int sagePrivate;

    public String sidPublic;
    public int snumberPublic;


    public void testPublic(){
        System.out.println("test Public");
    }

    private void testPrivate(){
        System.out.println("test Private");
    }
}
