package project.java;

public class CustomerList {
    private Customer[] customers;
    private int total = 0;

    public Customer[] getCustomers() {
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
        customers = new Customer[totalCustomer];//忘记创建对象
    }

    /**
     * 添加用户
     * @param customer
     * @return
     */
    public boolean addCustomer(Customer customer){
        if (total>= customers.length){
            return false;
        }else {
            customers[total++] = customer;
            return true;
        }
    }

    /**
     * 修改用户
     * @param index
     * @param cust
     * @return
     */
    public boolean replaceCustomer(int index,Customer cust){
        if (index<0||index>=total){
            return false;
        }else{
            customers[index] = cust;
            return true;
        }
    }

    /**
     * 删除用户
     * @param index
     * @return
     */
    public boolean deleteCustomer(int index){
        if (index<0||index>=total){
            return false;
        }

        for (int i=index;i<total-1;i++){
            customers[i] = customers[i+1];
        }

        customers[--total] = null;
        return true;
    }

    public Customer[] getAllCustomers(){
        Customer[] clist = new Customer[total];
        for (int i = 0;i<total;i++){
            clist[i] = customers[i];
        }
        return clist;
    }

    public Customer getCustomer(int index){
        if (index<0||index>=total){
            return null;
        }else {
            return customers[index];
        }
    }

}
