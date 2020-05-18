package project.java;

import org.junit.Test;

public class CustomerListTest {
    CustomerList cl = new CustomerList(2);
    Customer c1 = new Customer("张三",'男',18,"010-25846598","15768439@qq.com");
    Customer c2 = new Customer("李四",'女',19,"010-15482912","15jl1d39@qq.com");

    @Test
    public void addCustomerTest(){
        System.out.println("cl.addCustomer(c1) = " + cl.addCustomer(c1));
        System.out.println("cl.addCustomer(c2) = " + cl.addCustomer(c2));
    }

    @Test
    public void replaceCustomerTest(){
        cl.addCustomer(c1);
        cl.addCustomer(c1);
        System.out.println("cl.replaceCustomer(0,c2) = " + cl.replaceCustomer(1, c2));
    }

    @Test
    public void deleteCustomerTest(){
        cl.addCustomer(c1);
        cl.addCustomer(c2);
        System.out.println("cl.deleteCustomer(0) = " + cl.deleteCustomer(0));
        System.out.println("cl.getCustomer(0).getName() = " + cl.getCustomer(0).getName());
    }
}
