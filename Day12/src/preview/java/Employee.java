package preview.java;

import java.util.Scanner;

public abstract class Employee {
    private String name;
    private int number;
    private MyDate birthday;

    public Employee(String name, int number, MyDate birthday) {
        this.name = name;
        this.number = number;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public MyDate getBirthday() {
        return birthday;
    }

    public abstract int earnings();

    public String toString(){
        return this.name+"\t"+this.number+"\t"+this.birthday.toDateString();
    }

}

class MyDate{
    private int day;
    private int month;
    private int year;

    public MyDate(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public String toDateString(){
        return this.year+"年"+this.month+"月"+this.day+"日";
    }
}

class SalariedEmployee extends Employee{
    private int monthlySalary;

    public SalariedEmployee(String name, int number, MyDate birthday, int monthlySalary) {
        super(name, number, birthday);
        this.monthlySalary = monthlySalary;
    }

    public int earnings(){
        return monthlySalary;
    }

    public String toString(){
        return "月薪制"+"\t"+super.toString();
    }
}

class HourlyEmploye extends Employee{
    private int wage;
    private int hour;

    public HourlyEmploye(String name, int number, MyDate birthday, int wage, int hour) {
        super(name, number, birthday);
        this.wage = wage;
        this.hour = hour;
    }

    public int earnings(){
        return wage*hour;
    }

    public String toString(){
        return "时薪制"+"\t"+super.toString();
    }
}

/*
创建Employee变量数组并初始化，该数组存放各类雇员对象的引用。
利用循环结构遍历数组元素，输出各个对象的类型,name,number,birthday,以及该对象生日。
 */
class PayrollSystem{
    public static void main(String[] args) {
        Employee[] employees = new Employee[2];
        employees[0] = new SalariedEmployee("aaa",1001,new MyDate(12,12,1990),10000);
        employees[1]  = new HourlyEmploye("bbb",2001,new MyDate(10,10,1989),100,10);

         /*
    当键盘输入本月月份值时，如果本月是某个Employee对象的生日，还要输出增加工资信息。
     */
        for (;;){
            Scanner scan = new Scanner(System.in);
            System.out.print("请输入月份(退出-1)：");
            int month = scan.nextInt();

            if (month==-1){
                System.out.print("是否确认退出（-1确认）：");
                int exitChoice = scan.nextInt();
                if (exitChoice==-1){
                    break;
                }else {
                    System.out.println("__返回主菜单__");
                }
            }

            if (month>12||month<0){
                System.out.println("输入月份有误！");
            }

            for (Employee employee : employees) {
                if (employee.getBirthday().getMonth()==month){
                    System.out.println(employee+"\t"+employee.getBirthday().getMonth()+"\t生日奖励：100，\t总工资："+(employee.earnings()+100));
                }else {
                    System.out.println(employee+"\t"+employee.getBirthday().getMonth()+"\t总工资："+employee.earnings());
                }
            }
        }

    }

}