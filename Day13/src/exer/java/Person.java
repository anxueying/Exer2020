package exer.java;

import java.lang.annotation.Target;

public class Person {
    private String name;
    private char gender;

    public Person(String name, char gender) {
        this.name = name;
        this.gender = gender;
    }

    @Override
    @MyTiger("Person")
    public String toString(){
        return name+gender;
    }
}


