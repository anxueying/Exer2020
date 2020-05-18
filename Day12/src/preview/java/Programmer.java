package preview.java;
/*
包含姓名 name、年龄 age、设备 Equipment√
(包含名称 name 与 颜色 color。其中设备有子类 PC 与 NotePad。),√
重写 Programmer 的 equals() 与 toString方法√
其中 equals() 比较两个程序员的姓名与年龄。√
编写测试类创建对象与 Equipment 关联。
调用 equals() 与 toString() 方法
注：构造器自己设定。
PC 与 NotePad 写出类的声明即可。√
 */
public class Programmer {
    private String name;
    private int age;
    private Equipment equipment;

    public Programmer() {
    }

    public Programmer(String name, int age, Equipment equipment) {
        this.name = name;
        this.age = age;
        this.equipment = equipment;
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

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    //重写 Programmer 的 equals() 与 toString方法
    //其中 equals() 比较两个程序员的姓名与年龄。
    public boolean equals(Object object){
        if (this == object){
            return true;
        }

        if (object instanceof Programmer){
            Programmer programmer = (Programmer)object;
            if (programmer.age==this.age&&this.name.equals(programmer.name)){
                return true;
            }
        }
        return false;
    }

    public String toString(){
        return this.name+"\t"+this.age+"\t"+this.equipment.toString();
    }
}

class Equipment{
    private String name;
    private String color;

    public Equipment() {
    }

    public Equipment(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public String toString(){
        return this.name+"\t"+this.color;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}

class  PC extends Equipment{

}

class NotePad extends Equipment{

}