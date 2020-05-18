package homework.java.test16;

public class Programmer extends Employee{
    private String codeLanguage= "java";

    public Programmer() {

    }

    public Programmer(int id, String name, int age, double salary) {
        super(id, name, age, salary);
    }

    public String getCodeLanguage() {
        return codeLanguage;
    }

    public void setCodeLanguage(String codeLanguage) {
        this.codeLanguage = codeLanguage;
    }

    @Override
    public String getInfo() {
        return super.getInfo()+"，编程语言："+getCodeLanguage();
    }
}
