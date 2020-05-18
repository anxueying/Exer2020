package exer.java;

public class EmployeeSalary {
    public static void main(String[] args) {
        Normal normalEmployee = new Normal(300,22);
        normalEmployee.getDetails();

        Manager managerEmployee = new Manager(1000,18);
        managerEmployee.getDetails();
    }
}

class Employee{
    private double salary;
    private int workDay;
    private double level;

    public Employee() {
    }

    public Employee(double salary, int workDay) {
        this.salary = salary;
        this.workDay = workDay;
    }

    public Employee(double salary, int workDay, double level) {
        this.salary = salary;
        this.workDay = workDay;
        this.level = level;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getWorkDay() {
        return workDay;
    }

    public void setWorkDay(int workDay) {
        this.workDay = workDay;
    }

    public double getLevel() {
        return level;
    }

    public void setLevel(double level) {
        this.level = level;
    }

    public void getDetails(){
        System.out.println("工资为："+salary*workDay*level);
    }
}

class Normal extends Employee{
    public Normal() {
        setLevel(1);
    }

    public Normal(double salary, int workDay) {
        super(salary, workDay);
        setLevel(1);
    }

    public void getDetails(){
        System.out.println("普通员工工资为："+getSalary()*getWorkDay()*getLevel());
    }
}

class Manager extends Employee{
    public Manager() {
        setLevel(1.2);
    }

    public Manager(double salary, int workDay) {
        super(salary, workDay);
        setLevel(1.2);
    }

    public void getDetails(){
        System.out.println("部门经理工资为："+getSalary()*getWorkDay()*getLevel());
    }
}
