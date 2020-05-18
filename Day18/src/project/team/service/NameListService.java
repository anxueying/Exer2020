package project.team.service;

import project.team.domain.*;

import static project.team.service.Data.*;

/**
 * @author Mrs.An Xueying
 * 2020/5/18 23:25
 */
public class NameListService {
    private Employee[] employees = new Employee[EMPLOYEES.length];

    public NameListService() {
        for (int i = 0; i < employees.length; i++) {
            int employeeType =Integer.parseInt(EMPLOYEES[i][0]);

            int id = Integer.parseInt(EMPLOYEES[i][1]);
            String name = EMPLOYEES[i][2];
            int age = Integer.parseInt(EMPLOYEES[i][3]);
            double salary = Double.parseDouble(EMPLOYEES[i][4]);
            double bonus;
            int stock;

            switch (employeeType){
                case EMPLOYEE:
                    employees[i] = new Employee(id, name, age, salary);
                    break;
                case PROGRAMMER:
                    employees[i] = new Programmer(id, name, age, salary, getEquipment(i));
                    break;
                case DESIGNER:
                    bonus = Double.parseDouble(EMPLOYEES[i][5]);
                    employees[i] = new Designer(id, name, age, salary, getEquipment(i), bonus);
                    break;
                case ARCHITECT:
                    bonus = Double.parseDouble(EMPLOYEES[i][5]);
                    stock = Integer.parseInt(EMPLOYEES[i][6]);
                    employees[i] = new Architect(id, name, age, salary, getEquipment(i), bonus, stock);
                    break;
                default:
                    break;
            }
        }
    }

    public Employee[] getAllEmployees(){
        return employees;
    }

    public Employee getEmployee(int id) throws TeamException{

        for (Employee employee : employees) {
            if (employee.getId() == id) {
                return employee;
            }
        }
        throw new TeamException("找不到指定的员工");
    }

    private Equipment getEquipment(int i){
        Equipment equipment;
        int equipmentType = Integer.parseInt(EQUIPMENTS[i][0]);
        switch (equipmentType){
            case PC:
                equipment = new Pc(EQUIPMENTS[i][1],EQUIPMENTS[i][2]);
                break;
            case NOTEBOOK:
                equipment = new NoteBook(EQUIPMENTS[i][1],Double.parseDouble(EQUIPMENTS[i][2]));
                break;
            case PRINTER:
                equipment = new Printer(EQUIPMENTS[i][1],EQUIPMENTS[i][2]);
                break;
            default:
                equipment = null;
                break;
        }
        return equipment;
    }
}
