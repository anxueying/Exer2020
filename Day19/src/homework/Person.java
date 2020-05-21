package homework;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Mrs.An Xueying
 * 2020/5/19 21:24
 */
public class Person implements Serializable {

    private static final long serialVersionUID = 6062262228401649629L;
    private String name;
    private int age;
    private double weight = 50;
    /**
     *  static修饰的变量不会被序列化，仍为 中国
     */
    private static String nation = "中国";
    /**
     *     transient修饰的变量不会被序列化
     */
    private transient String password;


    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) {return false;}
        Person person = (Person) o;
        return age == person.age &&
                Double.compare(person.weight, weight) == 0 &&
                name.equals(person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, weight);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", weight=" + weight +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Person(String name, int age, double weight) {
        this.name = name;
        this.age = age;
        this.weight = weight;
    }

    public Person() {
    }
}
