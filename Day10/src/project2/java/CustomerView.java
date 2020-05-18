package project2.java;

import project2.java.CMUtility;

public class CustomerView {
    CustomerList cl = new CustomerList(10);

    public void enterMainMenu() {
        boolean loopFlag = true;
        do {
            System.out.println("\n-----------------客户信息管理软件-----------------");
            System.out.println("                  1 添 加 客 户");
            System.out.println("                  2 修 改 客 户");
            System.out.println("                  3 删 除 客 户");
            System.out.println("                  4 客 户 列 表");
            System.out.println("                  5 退      出");
            System.out.print("\n                  请选择(1-5)：_");
            char key = CMUtility.readMenuSelection();
            switch (key) {
                case '1':
                    addNewCustomer();
                    break;
                case '2':
                    modifyCustomer();
                    break;
                case '3':
                    deleteCustomer();
                    break;
                case '4':
                    listAllCustomers();
                    break;
                case '5':
                    System.out.print("是否确认退出（Y/N）：");
                    char yn = CMUtility.readConfirmSelection();
                    if (yn == 'Y') {
                        loopFlag = false;
                        System.out.println("感谢使用，下次再见");
                    }
                    break;
            }
        } while (loopFlag);

    }

    private void addNewCustomer() {

//        if (!cl.addCustomer(null)) {
//            System.out.println("用户已满，无法添加");
//        }

        System.out.println("---------------------添加客户---------------------");
        System.out.print("姓名：");
        String name = CMUtility.readString(10);
        System.out.print("性别：");
        char gender = CMUtility.readChar();
        System.out.print("年龄：");
        int age = CMUtility.readInt();
        System.out.print("电话：");
        String phone = CMUtility.readString(30);
        System.out.print("邮箱：");
        String email = CMUtility.readString(30);

        Customer customer = new Customer(name, gender, age, phone, email);

        if (cl.addCustomer(customer)) {
            System.out.println("---------------------添加完成---------------------");
        } else {
            System.out.println("---------------------添加失败---------------------");
        }

    }

    private void modifyCustomer() {
        System.out.println("---------------------修改客户---------------------");
        System.out.print("请选择待修改客户编号(-1退出)：");
        int num = CMUtility.readInt();

        if (num == -1) {
            return;
        }

        if (cl.getCustomer(num - 1) == null) {
            System.out.println("该用户不存在");
            return;
        }

        System.out.print("姓名：");
        String name = CMUtility.readString(10, cl.getCustomer(num - 1).getName());
        System.out.print("性别：");
        char gender = CMUtility.readChar(cl.getCustomer(num - 1).getGender());
        System.out.print("年龄：");
        int age = CMUtility.readInt(cl.getCustomer(num - 1).getAge());
        System.out.print("电话：");
        String phone = CMUtility.readString(30, cl.getCustomer(num - 1).getPhone());
        System.out.print("邮箱：");
        String email = CMUtility.readString(30, cl.getCustomer(num - 1).getEmail());

        Customer customer = new Customer(name, gender, age, phone, email);
        if (cl.replaceCustomer(num - 1, customer)) {
            System.out.println("---------------------修改完成---------------------");
        } else {
            System.out.println("---------------------修改失败---------------------");
        }


    }

    private void deleteCustomer() {
        System.out.println("---------------------删除客户---------------------");
        System.out.print("请选择待删除客户编号(-1退出)：");
        int num = CMUtility.readInt();

        if (num == -1) {
            return;
        }

        if (cl.getCustomer(num - 1) == null) {
            System.out.println("该用户不存在");
            return;
        }

        System.out.print("确认是否删除(Y/N)：");
        char yn = CMUtility.readConfirmSelection();

        if (yn == 'N') {
            return;
        }

        if (cl.deleteCustomer(num - 1)) {
            System.out.println("---------------------删除完成---------------------");
        } else {
            System.out.println("---------------------删除失败---------------------");
        }
    }

    private void listAllCustomers() {
        if (cl.getTotal() == 0) {
            System.out.println("用户列表为空");
        }

        Customer[] customers = cl.getAllCustomers();
        System.out.println("---------------------------客户列表---------------------------");
        System.out.println("编号\t姓名\t性别\t年龄\t电话\t邮箱");
        for (int i = 0; i < customers.length; i++) {
            System.out.print(i + 1 + "\t");
            customers[i].getDetail();
            System.out.println();
        }
        System.out.println("---------------------------客户列表完成---------------------------");
    }

    public static void main(String[] args) {
        CustomerView cv = new CustomerView();
        cv.enterMainMenu();
    }

}
