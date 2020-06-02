package exer;

public class Person implements Comparable{
    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age &&
                name.equals(person.name);
    }

    @Override
    //若两个对象的内容一样，生成的 hashCode 值也一样
    public int hashCode() {
        return name.hashCode() + age.hashCode() * 31;
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof Person){
            Person person = (Person)o;

            if (this.age.equals(person.age)){
                return this.name.compareTo(person.name);
            }else {
                return this.age - person.age;
            }
        }
        return 0;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
