package homework.java;

public class Account {
    private int id;
    private double balance;
    private double annualInterestRate;

    public Account() {
    }

    public Account(int id, double balance, double annualInterestRate) {
        this.id = id;
        this.balance = balance;
        this.annualInterestRate = annualInterestRate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getAnnualInterestRate() {
        return annualInterestRate;
    }

    public void setAnnualInterestRate(double annualInterestRate) {
        this.annualInterestRate = annualInterestRate;
    }

    /**
     * 返回月利率
     * @return
     */
    public  double getMonthlyInterest(){
        return this.annualInterestRate/12;
    }

    /**
     * 取款
     * @param amount 取款金额
     */
    public  void withdraw(double amount){
        if (balance<amount){
            System.out.println("余额不足");
        }else {
            balance -= amount;
        }
    }

    /**
     * 存款
     * @param amount
     */
    public void deposit(double amount){
        balance += amount;
    }
}


class CheckAccount extends Account{
    private double overdraft;

    public CheckAccount() {
    }

    public CheckAccount(int id, double balance, double annualInterestRate, double overdraft) {
        super(id, balance, annualInterestRate);
        this.overdraft = overdraft;
    }

    public double getOverdraft() {
        return overdraft;
    }

    public void setOverdraft(double overdraft) {
        this.overdraft = overdraft;
    }

    @Override
    /**
     * 重写父类取款方法
     */
    public void withdraw(double amount) {
        if (getBalance()+overdraft<amount){
            //超过限额
            System.out.println("超过可透支限额！");
        } else if(getBalance()<amount){
            //超过余额，透支账户扣减
            overdraft += getBalance()-amount;
            setBalance(0);
        }else {
            //余额足够，调用父类方法
            super.withdraw(amount);
        }
    }
}