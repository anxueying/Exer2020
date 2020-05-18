public class Test01 {
    public static void main(String[] args) {
        Employee em1 = new Employee();
        em1.id = 1;
        em1.name = "张三";
        em1.age = 23;
        em1.salary = 10000;
        System.out.println("员工"+em1.id+"的编号："+em1.id+"，姓名："+em1.name+"，年龄："+em1.age+"，薪资："+em1.salary);

        Employee em2 = new Employee();
        em2.id = 2;
        em2.name = "李四";
        em2.age = 22;
        em2.salary = 11000;
        System.out.println("员工"+em2.id+"的编号："+em2.id+"，姓名："+em2.name+"，年龄："+em2.age+"，薪资："+em2.salary);
    }
}

class Employee{
    //属性
    int id; //编号
    String name; //姓名
    int age; //年龄
    double salary; //薪资
}
