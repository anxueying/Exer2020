public class BankAccount {
    public static void main(String[] args) {
        Customer customer = new Customer("Jane", "Smith");
        Account account = new Account(1000, 2000, 0.0123);

        BankAccount bankacc = new BankAccount();
        //银行账户和客户关联
        //customer.setAccount(account);
        bankacc.setCustomerAcc(customer,account);

        //客户的账户(因为需求往往是给一个客户，然后来操作其账户。而不是操作客户，或者给一个账户直接操作。）在这里 jsAcc 和 account是一个账户
        //Account jsAcc = customer.getAccount();
        Account jsAcc = bankacc.getCustomerAcc(customer);

        //客户的账户操作
        jsAcc.deposit(100);
        jsAcc.withdraw(960);
        jsAcc.withdraw(2000);
    }

    //客户与账户关联
    public void setCustomerAcc(Customer customer,Account account){
        customer.setAccount(account);
    }

    //获得客户的账户
    public Account getCustomerAcc(Customer customer){
        return customer.getAccount();
    }
}

class Account {
    private int id;
    private double balance;
    private double annualInterestRate;

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

    public void withdraw(double amount) {
        //提款方法
        if (amount <= balance) {
            balance -= amount;
            System.out.println("成功取出：" + amount);
        } else {
            System.out.println("余额不足，取款失败");
        }
    }

    public void deposit(double amount) {
        //存款方法
        balance += amount;
        System.out.println("成功存入：" + amount);
    }
}

class Customer {
    private String firstName;
    private String lastName;
    private Account account;

    public Customer() {
    }

    public Customer(String f, String l) {
        this.firstName = f;
        this.lastName = l;
    }

    public Customer(String f, String l,Account account) {
        this.firstName = f;
        this.lastName = l;
        this.account = account;
    }

    public String getFirstName() {
        return firstName;
    }


    public String getLastName() {
        return lastName;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

}