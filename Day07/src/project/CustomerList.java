package project;

public class CustomerList {
/*
    public static void main(String[] args) {
        //测试
        Customer shelly = new Customer("shelly", 'f', 10, "18088889999", "shelly@qq.com");
        Customer rock = new Customer("rock", 'f', 10, "18088889999", "shelly@qq.com");
        Customer lay = new Customer("lay", 'f', 10, "18088889999", "shelly@qq.com");
        Customer shaking = new Customer("shaking", 'f', 10, "18088889999", "shelly@qq.com");
        Customer lina = new Customer("lina", 'f', 10, "18088889999", "shelly@qq.com");
        Customer lucy = new Customer("lucy", 'f', 10, "18088889999", "shelly@qq.com");//溢出测试
        CustomerList clTest = new CustomerList(5);
        clTest.addCustomer(shelly);
        clTest.addCustomer(rock);
        clTest.addCustomer(lay);
        clTest.addCustomer(shaking);
        clTest.addCustomer(lina);
        clTest.addCustomer(lucy);
        //clTest.getALlCustomers();//未显示lucy
        //replce
        //clTest.replaceCustomer(0,lucy);
        //clTest.getALlCustomers();//shelly替换成lucy
        //delete
        //clTest.deleteCustomer(4);
        //clTest.getALlCustomers();//少于5个对象时，返回所有对象不能报错
        clTest.getCustomer(1);
        System.out.println(clTest.getTotal());

    }

 */



    private Customer[] customers; //保存客户对象的数组
    private int total = 0;//已保存客户对象的数量

    public Customer[] getCustomers(int i) {
        return customers;
    }

    public void setCustomers(Customer[] customers) {
        this.customers = customers;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public CustomerList(int totalCustomer) {
        //制定customers数组的最大空间
        this.customers = new Customer[totalCustomer];//数组长度为totalCustomer，类型为Customer
        this.total = totalCustomer;
    }

    public boolean addCustomer(Customer customer) {
        //将参数customer添加组中最后一个客户对象记录之后
        for (int i = 0; i < this.customers.length; i++) {
            if (this.customers[i] == null) {
                //为空则添加
                this.customers[i] = customer;
                return true;//添加成功
            } else {
                //不为空,继续循环
                continue;
            }
        }
        return false;//数组已满，无法添加
    }

    public boolean replaceCustomer(int index, Customer cust) {
        //替换数组中由index指定的对象
        if (index < this.customers.length && index >= 0) {
            this.customers[index] = cust;
            return true;//替换成功
        } else {
            return false;//索引无效，无法替换
        }
    }

    public boolean deleteCustomer(int index) {
        //删除参数index指定索引位置的客户对象记录
        if (index < this.customers.length && index >= 0) {
            this.customers[index] = null;
            return true;//删除成功
        } else {
            return false;//索引无效，无法删除
        }
    }

    public Customer[] getALlCustomers() {
        //返回数组中记录的所有客户对象
        //customers中有空对象时，不能报错
        Customer[] allCustomers = new Customer[customers.length];
        for (int i = 0; i < customers.length; i++) {
            allCustomers[i] = customers[i];
            if (customers[i]!=null){
                System.out.print(i+"\t\t"+customers[i].getName() + "\t\t" + customers[i].getAge() + "\t\t" + customers[i].getPhone() + "\t\t" +customers[i].getGender() + "\t\t" +customers[i].getEmail() + "\n");
            }else {
                continue;
            }
        }
        return allCustomers;
    }

    public Customer getCustomer(int index) {
        //返回参数index指定索引位置的客户对象记录
        Customer c = new Customer();
        if (index>customers.length-1){
            System.out.println("索引值错误，无法取值");
        }else if (customers[index]!=null){
            c.setName(customers[index].getName());
            c.setGender(customers[index].getGender());
            c.setAge(customers[index].getAge());
            c.setPhone(customers[index].getPhone());
            c.setEmail(customers[index].getEmail());
            System.out.print("name:" + c.getName() + "\t" + "age:" + c.getAge() + "\t" + "phone:" + c.getPhone() + "\t" + "gender:" + c.getGender() + "\t" + "email:" + c.getEmail() + "\n");
        }else{
            System.out.println("该客户无内容");
        }
        return c;
    }

}
