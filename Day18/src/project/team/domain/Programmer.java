package project.team.domain;

import project.team.service.Status;

/**
 * @author Mrs.An Xueying
 * 2020/5/18 21:04
 */
public class Programmer extends Employee{
    private int memberId;
    private Status status = Status.FREE;
    private Equipment equipment;


    @Override
    public String toString() {
        return getDetails()+"\t程序员\t"+status+"\t\t\t\t\t\t"+equipment.getDescription();
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public Programmer(int id, String name, int age, double salary, Equipment equipment) {
        super(id, name, age, salary);
        this.equipment = equipment;
    }

    public Programmer() {
    }
}
