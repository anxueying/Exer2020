package homework.java;

/*
编写一个类实现银行账户的概念，包含的属性有“帐号”、“密码”、
“存款余额”、“利率”、“最小余额”，定义封装这些属性的方法。

账号要自动生成。

编写主类，使用银行账户类，输入、输出3个储户的上述信息。
考虑：哪些属性可以设计成static属性。
 */
class BankAccount {

    private static int id= 1000000;
    //底数 ，不能用一个，重新调用会重新赋值，即id=id++会一直是100000，如果是id=++id，则最后都变成1000003。必须有个非静态的值接收
    private int serialNumber;//赋值给账号
    private String password;
    private double balance;
    private static double rate = 0.005;
    private static double miniBalance = 100;

    public BankAccount(String password, double balance) {
        serialNumber = getNextId();
        this.password = password;
        this.balance = balance;
    }

    public static int getNextId(){
        return ++id;
    }

    public String toString(){
        return "帐号："+serialNumber+"，密码："+password+"，存款余额："+balance+"，利率："+rate+"，最小余额："+miniBalance;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        BankAccount.id = id;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public static double getRate() {
        return rate;
    }

    public static void setRate(double rate) {
        BankAccount.rate = rate;
    }

    public static double getMiniBalance() {
        return miniBalance;
    }

    public static void setMiniBalance(double miniBalance) {
        BankAccount.miniBalance = miniBalance;
    }
}

