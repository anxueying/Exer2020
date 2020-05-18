package Homework.java;

public class Person {
    private String name;
    private int lifeValue;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLifeValue() {
        return lifeValue;
    }

    /**
     * 首先判断，如果 lifeValue为负数,就抛出NoLifeValueException，
     * 异常信息为：生命值不能为负数：xx；
     * @param lifeValue 给成员lifeValue赋值。
     */
    public void setLifeValue(int lifeValue) {
        if (lifeValue<0){
            throw new NoLifeValueException("生命值不能为负数："+lifeValue);
        }else {
            this.lifeValue = lifeValue;
        }
    }

    public Person(String name, int lifeValue) {
        setName(name);
        setLifeValue(lifeValue);
    }

    public Person() {
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", lifeValue=" + lifeValue +
                '}';
    }
}
