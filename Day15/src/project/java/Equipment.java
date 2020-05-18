package project.java;

import java.lang.reflect.Modifier;

public interface Equipment {
    /**
     * 获得设备描述信息
     * @return
     */
    public String getDescription();

}


class PC implements Equipment{
    private String model;
    private String display;

    public PC() {
    }

    /**
     * 构造器
     * @param model 机器型号
     * @param display 显示器名称
     */

    public PC(String model, String display) {
        this.model = model;
        this.display = display;
    }

    public String getDescription(){
        return model+"\t"+display;
    }
}

class NotePad implements Equipment{
    private String model;
    private double price;

    public NotePad(String model, double price) {
        this.model = model;
        this.price = price;
    }

    public String getDescription(){
        return model+"\t"+price;
    }

}

class Printer implements Equipment{
    private String type;
    private String name;

    public Printer(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getDescription(){
        return type+"\t"+name;
    }
}