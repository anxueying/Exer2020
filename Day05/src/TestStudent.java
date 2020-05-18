/**
 * @author Lenovo
 */
public class TestStudent {
    public static void main(String[] args) {
        Student s1 = new Student();
        s1.name = "Shelly";
        s1.gender = "female";
        s1.age = 18;
        s1.id = 1;
        s1.score = 100;
        System.out.println(s1.say());

    }
}

class Student{
    //变量-属性
    String name;
    String gender;
    int age;
    int id;
    double score;
    //方法-行为
    public String say(){
        return name+","+gender+","+age+","+id+","+score;
    }
}