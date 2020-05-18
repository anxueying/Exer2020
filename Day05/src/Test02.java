public class Test02 {
    public static void main(String[] args) {
        MyDate birthday = new MyDate();
        birthday.year = 1990;
        birthday.month = 12;
        birthday.day = 22;
        System.out.println("出生日期："+birthday.year+"年"+birthday.month+"月"+birthday.day+"日");

        MyDate atSGG = new MyDate();
        atSGG.year = 2020;
        atSGG.month = 4;
        atSGG.day = 21;
        System.out.println("尚硅谷开学："+atSGG.year+"年"+atSGG.month+"月"+atSGG.day+"日");

        MyDate leaveSGG = new MyDate();
        leaveSGG.year = 2020;
        leaveSGG.month = 9;
        leaveSGG.day = 21;
        System.out.println("尚硅谷毕业："+leaveSGG.year+"年"+leaveSGG.month+"月"+leaveSGG.day+"日");
    }
}

class MyDate {
    int year;
    int month;
    int day;
}