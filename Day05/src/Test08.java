public class Test08 {
    public static void main(String[] args) {
        DateCommonsTools dctools = new DateCommonsTools();
        System.out.println(dctools.getMonthName(3));
        System.out.println(dctools.getMonthName(3));
        System.out.println(dctools.getTotalDaysOfMonth(2013,12));
        System.out.println(dctools.getTotalDaysOfYear(2020));
        System.out.println(dctools.isLeapYear(2020));
    }
}

class DateCommonsTools{
    public String getWeekName(int week){
        //根据星期值，返回对应的英文单词
        String engweek = "";
        switch (week){
            case 7:
                engweek = "Sunday";
                break;
            case 6:
                engweek = "Saturday";
                break;
            case 5:
                engweek = "Friday";
                break;
            case 4:
                engweek = "Thursday";
                break;
            case 3:
                engweek = "Wednesday";
                break;
            case 2:
                engweek = "Tuesday";
                break;
            case 1:
                engweek = "Monday";
                break;
            default:
                break;
        }
        return engweek;
    }

    public String getMonthName(int month){
        //根据月份值，返回对应的英文单词
        String engmonth = "";
        switch (month){
            case 12:
                engmonth = "December";
                break;
            case 11:
                engmonth = "November";
                break;
            case 10:
                engmonth = "October";
                break;
            case 9:
                engmonth = "September";
                break;
            case 8:
                engmonth = "August";
                break;
            case 7:
                engmonth = "July";
                break;
            case 6:
                engmonth = "June";
                break;
            case 5:
                engmonth = "May";
                break;
            case 4:
                engmonth = "April";
                break;
            case 3:
                engmonth = "March";
                break;
            case 2:
                engmonth = "February";
                break;
            case 1:
                engmonth = "January";
                break;
            default:
                break;
        }
        return  engmonth;
    }

    public int getTotalDaysOfMonth(int year,int month){
        //返回某年某月的总天数
        int sum = 0;
        switch (month){
            case 12:
                sum += 31;
            case 11:
                sum += 30;
            case 10:
                sum += 31;
            case 9:
                sum += 30;
            case 8:
                sum += 31;
            case 7:
                sum += 31;
            case 6:
                sum += 30;
            case 5:
                sum += 31;
            case 4:
                sum += 30;
            case 3:
                sum += 31;
            case 2:
                if(isLeapYear(year)){
                    sum += 29;
                }else {
                    sum +=  28;
                }
            case 1:
                sum += 31;
            default:
                break;
        }
        return sum;
    }

    public int getTotalDaysOfYear(int year){
        //获取某年的总天数
        if(isLeapYear(year)){
            return 366;
        }else {
            return 365;
        }
    }

    public boolean isLeapYear(int year){
        //判断某年是否是闰年
        if((year%4==0&&year%100!=0)||(year%400==0)){
            return true;
        }else{
            return false;
        }
    }
}
