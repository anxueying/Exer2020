package project;

import homework.CMUtility;

/*
– 主菜单显示及操作是否正确  test
– “添加客户”操作是否正确，给用户的提示是否明确合理；测试
- 当添加的客户总数超过10时，运行是否正确
– “修改客户”操作是否正确，给用户的提示是否明确合理；
– “删除客户”操作是否正确，给用户的提示是否明确合理；
– “客户列表”操作是否正确，表格是否规整
* */
public class CustomerView {

    CustomerList customers = new CustomerList(10);

    public static void main(String[] args) {
        CustomerView cv = new CustomerView();
        //循环判断
        boolean loopFlag = true;
        //主体
        do{
            cv.enterMainMenu();//打印menu
            char menuChoice = CMUtility.readMenuSelection();//用户选择
            switch (menuChoice){
                case '1':
                    //添加客户 done
                    cv.addNewCustomer();
                    break;
                case '2':
                    //修改客户
                    cv.modifyCustomer();
                    break;
                case '3':
                    //删除客户
                    cv.deleteCustomer();
                    break;
                case '4':
                    //客户列表
                    cv.listAllCustomers();
                    break;
                case '5':
                    //退出 done
                    System.out.print("是否确认退出(Y/N)：");
                    char ifexit = CMUtility.readConfirmSelection();
                    if(ifexit=='Y'){
                        loopFlag = false;
                        System.out.print("感谢使用，下次再见。");
                    }
                    break;
            }

        }while (loopFlag);

    }

    public void enterMainMenu(){
        // 显示主菜单，响应用户输入
        // 根据用户操作分别调用其他相应的成员方法（如addNewCustomer）
        // 以完成客户信息处理。
        System.out.println("-------客户信息管理软件------");
        System.out.println("\t\t1 添加客户\t\t");
        System.out.println("\t\t2 修改客户\t\t");
        System.out.println("\t\t3 删除客户\t\t");
        System.out.println("\t\t4 客户列表\t\t");
        System.out.println("\t\t5 退   出\t\t");
        System.out.print("\t\t请选择(1-5):\t\t");
    }

    private void addNewCustomer(){
        //添加客户，不可以有缺失
        Customer c = new Customer();
        if(customers.addCustomer(c)){
            System.out.println("-------添加客户------");
            System.out.print("姓名：");
            c.setName(CMUtility.readString(20));
            System.out.print("性别：");
            c.setGender(CMUtility.readChar());
            System.out.print("年龄：");
            c.setAge(CMUtility.readInt());
            System.out.print("电话：");
            c.setPhone(CMUtility.readString(11));
            System.out.print("邮箱：");
            c.setEmail(CMUtility.readString(30));
            System.out.println("-------添加完成------");
        }else{
            System.out.println("客户列表已满，无法继续添加");
        }
    }

    private void modifyCustomer(){
        //修改客户
        System.out.println("-------修改客户------");
        System.out.print("请选择待修改客户编号(-1退出)：");
        int index = CMUtility.readInt();//客户编号
        if(index==-1){
            System.out.println("退出修改，返回主菜单");
        }else {
            Customer c = customers.getCustomer(index);
            if (customers.replaceCustomer(index,c)) {
                System.out.print("直接回车表示不修改\n姓名(" + c.getName() + "):");//直接回车表示不修改
                c.setName(CMUtility.readString(20, null));

                System.out.print("性别(" + c.getGender() + "):");//直接回车表示不修改
                c.setGender(CMUtility.readChar('\u0000'));

                System.out.print("年龄(" + c.getAge() + "):");//直接回车表示不修改
                c.setAge(CMUtility.readInt(0));

                System.out.print("电话(" + c.getPhone() + "):");//直接回车表示不修改
                c.setPhone(CMUtility.readString(11, null));

                System.out.print("邮箱(" + c.getEmail() + "):");//直接回车表示不修改
                c.setEmail(CMUtility.readString(30, null));

                customers.replaceCustomer(index, c);
                System.out.print("-------修改完成------");
            }else {
                System.out.println("索引无效，无法替换");
            }
        }
    }

    private void deleteCustomer(){
        //删除客户
        System.out.println("-------删除客户------");
        System.out.print("请选择待删除客户编号(-1退出)：");
        int index = CMUtility.readInt();//客户编号
        if(index==-1){
            System.out.println("退出修改，返回主菜单");
        }else {
            System.out.print("确认是否删除(Y/N)：");
            char deleteChoice = CMUtility.readConfirmSelection();//删除确认
            if (deleteChoice=='Y'){
                if (customers.deleteCustomer(index)){
                    System.out.println("-------删除完成------");
                }else{
                    System.out.println("索引无效，无法删除");
                }
            }else {
                System.out.println("退出修改，返回主菜单");
            }
        }
    }

    private void listAllCustomers(){
        //客户列表
        System.out.println("-------客户列表------");
        System.out.println("编号\t\t姓名\t\t性别\t\t年龄\t\t电话\t\t邮箱");
        //用循环打印客户列表
        customers.getALlCustomers();
        System.out.println("-------客户列表完成------");

    }
}
