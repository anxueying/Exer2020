package project.java;

import static project.java.Data.*;

/**
 * 管理公司员工
 * @author Lenovo
 */
public class NameListService {
    private final Employee[] employees;

    public NameListService() {
        //Employee  :  10, id, name, age, salary
        //Programmer:  11, id, name, age, salary
        //Designer  :  12, id, name, age, salary, bonus
        //Architect :  13, id, name, age, salary, bonus, stock
        employees = new Employee[EMPLOYEES.length];

        for (int i = 0; i < EMPLOYEES.length; i++) {
            //员工类型
            String employeeType = EMPLOYEES[i][0];


            //提取设备
            //PC      :21, model, display
            //NoteBook:22, model, price
            //Printer :23, type, name
            Equipment equipment;

            if (EQIPMENTS[i].length == 0) {
                equipment = new PC();
            } else if ("22".equals(EQIPMENTS[i][0])) {
                equipment = new NotePad(EQIPMENTS[i][1], Double.parseDouble(EQIPMENTS[i][2]));
            } else if ("23".equals(EQIPMENTS[i][0])) {
                equipment = new Printer(EQIPMENTS[i][1], EQIPMENTS[i][2]);
            } else {
                equipment = new PC(EQIPMENTS[i][1], EQIPMENTS[i][2]);
            }


            //提取员工信息
            int id = Integer.parseInt(EMPLOYEES[i][1]);
            String name = EMPLOYEES[i][2];
            int age = Integer.parseInt(EMPLOYEES[i][3]);
            double salary = Double.parseDouble(EMPLOYEES[i][4]);
            double bonus;
            int stock;

            //创建对象，加入数组存储
            switch (employeeType) {
                case "10":
                    employees[i] = new Employee(id, name, age, salary);
                    break;
                case "11":
                    employees[i] = new Programmer(id, name, age, salary, equipment);
                    Programmer programmer = (Programmer) employees[i];
                    programmer.setStatus(Status.FREE);
                    break;
                case "12":
                    bonus = Double.parseDouble(EMPLOYEES[i][5]);
                    employees[i] = new Designer(id, name, age, salary,equipment,bonus);
                    Designer designer = (Designer) employees[i];
                    designer.setStatus(Status.FREE);
                    break;
                case "13":
                    bonus = Double.parseDouble(EMPLOYEES[i][5]);
                    stock = Integer.parseInt(EMPLOYEES[i][6]);
                    employees[i] = new Architect(id, name, age, salary, equipment,bonus, stock);
                    Architect architect = (Architect) employees[i];
                    architect.setStatus(Status.FREE);
                    break;
                default:
                    break;
            }
        }
    }


    /**
     * 获取指定ID的员工对象
     *
     * @param id 员工id
     * @return 员工对象
     * @throws TeamException 找不到指定的员工
     */
    public Employee getEmployee(int id) throws TeamException {
        if (id<0||id>employees.length){
            throw new TeamException("找不到指定的员工");
        }

        Employee employee = new Employee();

        for (Employee value : this.employees) {
            if (value.getId() == id) {
                employee = value;
                break;
            }
        }

        return employee;
    }

    public Employee[] getAllEmployees() {
        return employees;
    }

}


class Test{
    public static void main(String[] args) throws TeamException {
        NameListService nlService = new NameListService();
        Employee employee = nlService.getEmployee(1);
        System.out.println(employee);
    }
}