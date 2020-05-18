package homework.java;

public class Exercise8 {
    public static void main(String[] args) {
        Person1[] persons = new Person1[3];
        persons[0] = new Chinese();
        persons[1] = new American();
        persons[2] = new Indian();

        for (Person1 person : persons) {
            person.eat();
        }
    }
}


abstract class Person1{
    public abstract void eat();

}

class Chinese extends Person1{
    public void eat(){
        System.out.println("用筷子吃饭");
    }
}

class American extends Person1{
    public void eat(){
        System.out.println("用刀叉吃饭");
    }
}

class Indian extends Person1{
    public void eat(){
        System.out.println("用手抓饭");
    }
}