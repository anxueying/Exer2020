package exer;

public class TestDate {
    public static void main(String[] args) {
        MyDate myDate1 = new MyDate(1,10,2014);
        MyDate myDate2 = new MyDate(1,10,2014);

        System.out.println("myDate1.equals(myDate2) = " + myDate1.equals(myDate2));
        System.out.println("myDate1 = " + myDate1);
        System.out.println("myDate2 = " + myDate2);

    }
}

class MyDate{
    private int day;
    private int month;
    private int year;

    public MyDate() {
    }

    public MyDate(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean equals(Object object){
        if (this == object){
            return true;
        }

        if (object instanceof  MyDate){
            MyDate myDate = (MyDate)object;
            if (myDate.day==this.day&&myDate.month==this.month&&myDate.year==this.year){
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }
    }

    public String toString(){
        return this.year+"年"+this.month+"月"+this.day+"日";
    }

}
