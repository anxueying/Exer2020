

public class TestRoom {
    public static void main(String[] args) {
        Room r1 = new Room();
        System.out.println(r1.getDetails());

        Room r2 = new Room(702);
        System.out.println(r2.getDetails());

        Room r3 = new Room(701,45.67);
        System.out.println(r3.getDetails());

        Room r4 = new Room(703,60.23,"中南海");
        System.out.println(r4.getDetails());
    }
}

class Room{
    private int id;
    private double area;
    private String address;

    public Room(){}

    public Room(int id){
        this.id = id;
    }

    public Room(int id,double area){
        this(id,area,null);
    }

    public Room(int id,double area,String address){
        this(id);
        this.area = area;
        this.address = address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public double getArea() {
        return area;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getDetails(){
        return "房号："+getId()+"，面积："+getArea()+"，地址："+getAddress();
    }
}