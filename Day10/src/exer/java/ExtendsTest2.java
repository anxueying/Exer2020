package exer.java;

import java.util.concurrent.ForkJoinPool;

public class ExtendsTest2 {
    public static void listPrice(Computer c) {
        System.out.println("价格： " + c.getPrice());
        if (c instanceof Computer) {
            c.getDetails();
        }

        if (c instanceof PC) {
            PC pc = (PC) c;
            pc.powerOff();
        }

        if (c instanceof NotePad) {
            NotePad np = (NotePad) c;
            np.opening();
        }

    }

    public static void main(String[] args) {

        Computer[] computers = new Computer[3];
        computers[0] = new Computer("i5", "8G", "500M", 5000);//本态
        computers[1] = new PC("i7", "16G", "1T", "联想", 8000);//多态
        computers[2] = new NotePad("i9", "8G", "1T", 10000, "javaee");

        //排序价格，输出数组元素
        //这里是有-1的，3个元素，比较2次，i=0，i=1共两次，所以i<3-1;
        for (int i = 0; i < computers.length - 1; i++) {
            for (int j = 0; j < computers.length - i - 1; j++) {
                //这里都要用j呀 怎么还用i
                if (computers[j].getPrice() < computers[j + 1].getPrice()) {
                    Computer tempc = computers[j];
                    computers[j] = computers[j + 1];
                    computers[j + 1] = tempc;
                }
            }
        }

        //父类
        for (Computer c : computers) {
            //System.out.println(c.getDetails());
            listPrice(c);
            System.out.println("-------------");
        }


        NotePad np = new NotePad("i9", "8G", "1T", 13000, "javaee");
        Computer c = np;
        if (c instanceof PC){
            PC pc = (PC)c;  //父类转子类
            pc.powerOff();
        }else {
            System.out.println("转不了，永远为false");

        }

    }
}

class Computer {
    public Computer() {
    }

    public Computer(String CPU, String memory, String hdd) {
        this.CPU = CPU;
        this.memory = memory;
        this.hdd = hdd;
    }

    public String getCPU() {
        return CPU;
    }

    public void setCPU(String CPU) {
        this.CPU = CPU;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getHdd() {
        return hdd;
    }

    public void setHdd(String hdd) {
        this.hdd = hdd;
    }

    private String CPU;
    private String memory;
    private String hdd;
    private int price;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Computer(String CPU, String memory, String hdd, int price) {
        this.CPU = CPU;
        this.memory = memory;
        this.hdd = hdd;
        this.price = price;
    }

    public String getDetails() {
        return "CPU: " + getCPU() + ", 内存: " + getMemory() + ", 硬盘: " + getHdd() + "，价格：" + getPrice();
    }
}

class PC extends Computer {
    private String brand;


    public PC(String CPU, String memory, String hdd, String brand, int price) {
        super(CPU, memory, hdd, price);
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public PC() {
        //默认有一个隐式的super();
    }

    public PC(String brand) {
        this.brand = brand;
    }

    public PC(String CPU, String memory, String hdd, String brand) {
        super(CPU, memory, hdd);
        this.brand = brand;
    }

    public void startingUp() {
        System.out.println("开机：" + getBrand() + this.getCPU());
    }

    public void powerOff() {
        System.out.println("关机：" + getBrand() + this.getCPU());
    }

    public String getDetails() {
        return super.getDetails() + "，品牌：" + getBrand();
    }
}

class NotePad extends Computer {
    public NotePad() {
    }

    public NotePad(String CPU, String memory, String hdd, int price, String fileName) {
        super(CPU, memory, hdd, price);
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    private String fileName;

    public void opening() {
        System.out.println("打开文件" + getFileName());
    }

    public void closing() {
        System.out.println("关闭文件" + getFileName());
    }

    public String getDetails() {
        return super.getDetails() + "，文件名：" + getFileName();
    }
}
