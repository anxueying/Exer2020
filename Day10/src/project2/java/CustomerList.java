package project2.java;

public class CustomerList {
    private Customer[] customers;
    private int total = 0;

    public CustomerList(int totalCustomer) {
        this.customers = new Customer[totalCustomer];
    }

    public int getTotal() {
        return total;
    }

    /**
     * 添加客户对象
     * @param customer
     * @return
     */
    public boolean addCustomer(Customer customer){
        if (total>=customers.length){
            return false;
        }

        customers[total++] = customer;
        return true;
    }

    public boolean  replaceCustomer(int index, Customer cust){
        if(getCustomer(index)==null){
            return false;
        }
        customers[index] = cust;
        return true;
    }

    public boolean deleteCustomer(int index){
        if(getCustomer(index)==null){
            return false;
        }
        for (int i = index; i < total-1; i++) {
            customers[i] = customers[i+1];
        }

        customers[--total] = null;
        return true;
    }

    public Customer[] getAllCustomers(){
        if (total==0){
            return null;
        }
        Customer[] customers = new Customer[total];
        for (int i = 0; i < total; i++) {
            customers[i] = this.customers[i];
        }
        return customers;
    }

    public Customer getCustomer(int index){
        if (index>=customers.length||index<0){
            return null;
        }
        return customers[index];
    }

}
