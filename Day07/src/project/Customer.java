package project;

public class Customer {

    /*
    public static void main(String[]args){
        Customer customerTest = new Customer("shelly",'f',10,"18088889999","shelly@qq.com");
        //customerTest.setAge(10);
        System.out.println("age:"+customerTest.getAge());

        //customerTest.setName("shelly");
        System.out.println("name:"+customerTest.getName());

        //customerTest.setPhone("18088889999");
        System.out.println("phone:"+customerTest.getPhone());

        //customerTest.setGender('f');
        System.out.println("gender:"+customerTest.getGender());

        //customerTest.setEmail("shelly@qq.com");
        System.out.println("email:"+customerTest.getEmail());

    }

     */



    private String name;
    private char gender;
    private int age;
    private String phone;
    private String email;

    public Customer(){

    }

    public Customer(String name,char gender,int age,String phone,String email){
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.phone = phone;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
