package homework.java;

public class Exercise7 {
    public static void main(String[] args) {
        Person[] persons = new Person[2];
        persons[0] = new Woman();
        persons[1] = new Man();

        for (Person person : persons) {
            person.pee();
        }
    }
}

abstract class Person{
    public abstract void pee();

}

class Woman extends Person{
    public void pee(){
        System.out.println("坐着尿");
    }
}

class Man extends Person{
    public void pee(){
        System.out.println("站着尿");
    }
}