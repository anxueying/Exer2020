package project.team.domain;

/**
 * @author Mrs.An Xueying
 * 2020/5/18 21:20
 */
public class Architect extends Designer{
    private int stock;

    @Override
    public String toString() {
        return getDetails()+"\t架构师\t" +getStatus()+"\t"+ getBonus()+"\t"+stock+"\t"+getEquipment().getDescription();
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Architect(int id, String name, int age, double salary, Equipment equipment, double bonus, int stock) {
        super(id, name, age, salary, equipment, bonus);
        this.stock = stock;
    }

    public Architect() {
    }
}
