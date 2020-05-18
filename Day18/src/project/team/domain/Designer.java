package project.team.domain;


/**
 * @author Mrs.An Xueying
 * 2020/5/18 21:16
 */
public class Designer extends Programmer {
    private double bonus;

    @Override
    public String toString() {
        return getDetails()+ "\t设计师\t" +getStatus()+"\t"+ bonus+"\t\t\t"+getEquipment().getDescription();
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public Designer() {
    }

    public Designer(int id, String name, int age, double salary, Equipment equipment, double bonus) {
        super(id, name, age, salary, equipment);
        this.bonus = bonus;
    }
}
