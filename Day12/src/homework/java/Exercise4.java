package homework.java;

public class Exercise4 {
    public static void main(String[] args) {
        Worker worker1 = new Worker();
        Worker worker2 = new Worker();
        Apple a1 = new Apple(5,"青色");
        Apple a2 = new Apple(3,"红色");
        worker1.pickApple(new CompareBig(),a1,a2);
        worker1.pickApple(new CompareColor(),a1,a2);
    }
}

class Apple{
    private double size;
    private String color;

    public Apple() {
    }

    public Apple(double size, String color) {
        this.size = size;
        this.color = color;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String toString(){
        return "大小："+size+"，颜色："+color;
    }
}

interface CompareAble{
    default void compare(Apple... a){
        Apple temp = a[0];
        for (int i = 1; i < a.length; i++) {
            if (a[i].getSize()>temp.getSize()){
                temp = a[i];
            }
        }
        System.out.println(temp);
    }
}

class CompareBig implements CompareAble{

}


class CompareColor implements CompareAble{
    public void compare(Apple... a){
        for (Apple apple : a) {
            if (apple.getColor().equals("红色")){
                System.out.println(apple);
            }
        }
    }
}

class Worker{
    public void pickApple(CompareAble c,Apple a1,Apple a2){

        if (c instanceof CompareColor){
            System.out.println("挑红的：");
            CompareColor compareColor = (CompareColor)c;
            compareColor.compare(a1,a2);
        }else {
            System.out.println("默认挑大的：");
            c.compare(a1,a2);
        }

    }
}