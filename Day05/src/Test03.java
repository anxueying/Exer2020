public class Test03 {
    public static void main(String[] args) {
        Citizen shelly = new Citizen();
        shelly.name = "Shelly";
        shelly.birthday.year = 1990;
        shelly.birthday.month =12;
        shelly.birthday.day =22;
        shelly.id = "19901222";
        System.out.println("姓名："+shelly.name+"，出生日期："+shelly.birthday.year+"年"+shelly.birthday.month+"月"+shelly.birthday.day+"日，身份证号码："+shelly.id);

        Citizen cat = new Citizen();
        cat.name = "小小";
        cat.birthday.year = 2019;
        cat.birthday.month =12;
        cat.birthday.day =12;
        cat.id = "20191212";
        System.out.println("姓名："+cat.name+"，出生日期："+cat.birthday.year+"年"+cat.birthday.month+"月"+cat.birthday.day+"日，身份证号码："+cat.id);
    }
}

class Citizen{
    String name;
    MyDate birthday = new MyDate();
    String id;
}