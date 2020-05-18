public class TestPerson {
    public static void main(String[] args) {
        Person wuxie = new Person("无邪",20);
        Person pangzi = new Person("胖子",31,"家里蹲");
        Person sanshu = new Person("吴三省",46,"小学","盗墓");

        wuxie.personinfo();
        pangzi.personinfo();
        sanshu.personinfo();
    }
}

class Person{
    private int age;
    private String name;
    private String school;
    private String major;

    public Person(String name,int age){
        this.age = age;
        this.name = name;
    }

    public Person(String name,int age,String school){
        this(name,age);
        this.school = school;
    }

    public Person(String name,int age,String school,String major){
        this(name,age,school);
        this.major = major;
    }

    public void setAge(int i) {
        if (i>0&&i<130){
            age=i;
        }
    }

    public int getAge(){
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getMajor() {
        return major;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getSchool() {
        return school;
    }

    public void personinfo(){
        System.out.println(this.getName()+"的年龄："+this.getAge()+"，专业是："+this.getMajor()+"，学校："+this.getSchool());
    }
}