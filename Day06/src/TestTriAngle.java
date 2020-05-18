public class TestTriAngle {
    public static void main(String[] args) {
        TriAngle tri1 = new TriAngle(4,3);
    }
}

class TriAngle{
    private int base;
    private int height;

    public TriAngle(int base,int height){
        this.base = base;
        this.height = height;
        area();
    }

    public void setBase(int base) {
        this.base = base;
    }

    public int getBase() {
        return base;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public void area(){
        System.out.println("三角形面积为："+this.base*this.height/2);
    }
}