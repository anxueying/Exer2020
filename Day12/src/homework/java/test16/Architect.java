package homework.java.test16;

public class Architect extends Designer{
    private int shareholding;

    public Architect() {
    }

    public Architect(int id, String name, int age, double salary,  double bonus, int shareholding) {
        super(id, name, age, salary,bonus);
        this.shareholding = shareholding;
    }

    public Architect(int shareholding) {
        this.shareholding = shareholding;
    }

    public int getShareholding() {
        return shareholding;
    }

    public void setShareholding(int shareholding) {
        this.shareholding = shareholding;
    }

    @Override
    public String getInfo() {
        return super.getInfo()+"，持有股票数量："+getShareholding();
    }
}
