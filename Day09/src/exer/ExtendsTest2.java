package exer;

public class ExtendsTest2 {
    public static void main(String[] args) {

        PC leno = new PC("i7","16G","1T","联想");

        //父类
        System.out.println(leno.getDetails());

        //子类
        leno.startingUp();
        leno.powerOff();

        NotePad np = new NotePad();
        np.opening();
        np.closing();

    }
}

class Computer{
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

    public String  getDetails(){
        return "CPU: "+getCPU() +", 内存: "+ getMemory()+", 硬盘: "+getHdd();
    }
}

class PC extends Computer{
    private String brand;


    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public  PC(){
        //默认有一个隐式的super();
    }
    public PC(String brand) {
        this.brand = brand;
    }

    public PC(String CPU, String memory, String hdd, String brand) {
        super(CPU, memory, hdd);
        this.brand = brand;
    }

    public void startingUp(){
        System.out.println("开机："+getBrand()+this.getCPU());
    }

    public void powerOff(){
        System.out.println("关机："+getBrand()+this.getCPU());
    }

    public String getDetails(){
        return super.getDetails()+"，品牌："+getBrand();
    }
}

class NotePad extends  Computer{
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    private String fileName;

    public  void opening(){
        System.out.println("打开文件"+getFileName());
    }

    public void closing(){
        System.out.println("关闭文件"+getFileName());
    }
}
