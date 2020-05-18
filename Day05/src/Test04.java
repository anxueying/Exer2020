public class Test04 {
    public static void main(String[] args) {
        MyDate1 birthday = new MyDate1();
        birthday.year = 1990;
        birthday.month =12;
        birthday.day =22;
        System.out.println("出生日期："+birthday.year+"年"+birthday.month+"月"+birthday.day+"日");

        System.out.println("是否为闰年：" + birthday.isLeapYear(birthday.year));

        birthday.set(2000,11,10);
        System.out.println("出生日期："+birthday.year+"年"+birthday.month+"月"+birthday.day+"日");

        birthday.plus(10,2,6);
        System.out.println("出生日期："+birthday.year+"年"+birthday.month+"月"+birthday.day+"日");

    }
}

class MyDate1 {//与test02命名冲突，加了1
    int year;
    int month;
    int day;
    //方法
    public boolean isLeapYear(int year){
        //是否为闰年
        if((year%4==0&&year%100!=0)||(year%400==0)){
            return true;
        }else{
            return false;
        }
    }

    public void set(int y,int m,int d){
        //修改年月日
        year = y;
        month = m;
        day = d;
    }

    public void plus(int years,int months,int days) {
        //加上years年，months月，days后的日期
        if (months + month > 12) {
            year += years + (months + month) / 12;
            month = (months + month) % 12;
        } else {
            month += months;
            year += years;
        }
        day += days;//暂时没想明白，应该是按不同月份判断
    }

}