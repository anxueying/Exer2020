package project.java;
import project.java.CMUtility;

public class CustomerView {
    CustomerList cl = new CustomerList(10);

    /**
     * 主菜单
     * loopFlag控制循环
     * do..while
     *
     */
    public void enterMainMenu(){
        boolean loopFlag = true;
        do{
            System.out.println("\n-----------------客户信息管理软件-----------------\n");
            System.out.println("                   1 添 加 客 户");
            System.out.println("                   2 修 改 客 户");
            System.out.println("                   3 删 除 客 户");
            System.out.println("                   4 客 户 列 表");
            System.out.println("                   5 退       出");
            System.out.println("");
            System.out.print("                   请选择(1-5)：");

            char key = CMUtility.readMenuSelection();
            switch (key){
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
                    System.out.print("是否确定退出（Y/N）：");
                    char yn= CMUtility.readConfirmSelection();
                    if (yn=='Y'){
                        loopFlag = false;
                        System.out.print("感谢使用，下次再见");
                    }
                    break;
            }
        }while (loopFlag);
    }

    /**
     * 添加客户
     * 1. 键盘获得值
     * 2. 封装进对象
     * 3. 调用CustomerList方法，返回boolean
     * 4. 根据boolean结果，返回结果是否成功
     * //5. 优化：在添加信息之前判断
     */
    private void addNewCustomer(){
        System.out.println("\n---------------------添加客户---------------------\n");

        System.out.print("姓名:");
        String name = CMUtility.readString(25);

        System.out.print("性别:");
        char gender = CMUtility.readChar();

        System.out.print("年龄:");
        int age = CMUtility.readInt();

        System.out.print("电话:");
        String phone = CMUtility.readString(30);

        System.out.print("邮箱:");
        String email = CMUtility.readString(30);

        // 将散列数据封装进 Customer 对象
        Customer cust = new Customer(name, gender, age, phone, email);
        boolean flag = cl.addCustomer(cust);
        if (flag) {
            System.out.println("---------------------添加完成---------------------");
        } else {
            System.out.println("---------------------数组已满，无法添加---------------------");
        }
    }

    /**
     * 修改客户
     * 1. 判断num是否为-1
     * 2. 调用方法(无限循环，直到正确的num值）
     * 3. 如回车，返回该位置对象的值，全部数据封装进新对象
     * 4. 调用CustomerList中方法修改客户
     * 5. 根据返回flag输出是否修改成功
     */
    private void modifyCustomer(){
        System.out.println("\n---------------------修改客户---------------------\n");
        Customer cust = null;
        int num = 0;
        for (;;) {
            System.out.print("请选择待修改客户编号(-1退出)：");
            num = CMUtility.readInt();// num：客户编号

            if (num == -1) {
                return; // 用于结束当前方法
            }

            // 根据编号查询客户是否存在
            cust = cl.getCustomer(num - 1);

            if (cust == null) {
                System.out.println("无法找到指定客户！");
            } else {
                break;
            }
        }
        // 修改操作
        System.out.print("姓名(" + cust.getName() + "):");
        String name = CMUtility.readString(25, cust.getName());

        System.out.print("性别(" + cust.getGender() + "):");
        char gender = CMUtility.readChar(cust.getGender());

        System.out.print("年龄(" + cust.getAge() + "):");
        int age = CMUtility.readInt(cust.getAge());

        System.out.print("电话(" + cust.getPhone() + "):");
        String phone = CMUtility.readString(30, cust.getPhone());

        System.out.print("邮箱(" + cust.getEmail() + "):");
        String email = CMUtility.readString(30, cust.getEmail());

        Customer customer = new Customer(name, gender, age, phone, email);

        // 调用 CustomerList 中的修改方法
        boolean flag = cl.replaceCustomer(num - 1, customer);

        if (flag) {
            System.out.println("---------------------修改完成---------------------");
        } else {
            System.out.println("---------------------修改失败---------------------");
        }
    }

    /**
     * 删除客户
     * 1. 判断num是否为-1
     * 2. 调用方法(无限循环，直到正确的num值）
     * 3. 确认是否删除
     * 4. 调用CustomerList中方法删除客户
     * 5. 根据返回flag输出是否修改成功
     */
    private void deleteCustomer(){
        System.out.println("---------------------删除客户---------------------");

        int num = 0;
        for (;;) {
            System.out.print("请选择待删除客户编号(-1退出)：");
            num = CMUtility.readInt();

            if (num == -1) {
                return;
            }

            Customer cust = cl.getCustomer(num - 1);

            if (cust == null) {
                System.out.println("无法找到指定客户！");
            } else {
                break;
            }
        }

        // 进行删除操作
        System.out.print("确认是否删除(Y/N)：");
        char yn = CMUtility.readConfirmSelection();

        if (yn == 'N') {
            return;
        }

        // 通过 CustomerList 中提供的 deleteCustomer 方法进行删除
        boolean flag = cl.deleteCustomer(num - 1);

        if (flag) {
            System.out.println("---------------------删除完成---------------------");
        } else {
            System.out.println("---------------------删除失败---------------------");
        }
    }

    /**
     * 客户列表
     * 1. 为空输出：客户列表为空
     * 2. 非空：输出表头、getAllCustomers、表尾
     * 3. 1和2之后均回主菜单
     */
    private  void listAllCustomers(){
        System.out.println("---------------------------客户列表---------------------------");

        Customer[] customers = cl.getAllCustomers();

        if (customers.length == 0) {
            System.out.println("没有客户记录！");
        } else {
            System.out.println("编号\t姓名\t性别\t年龄\t电话\t\t邮箱");
        }

        for (int i = 0; i < customers.length; i++) {
            // System.out.println((i+1) + "\t" + customers[i].getName() + "\t" +
            // customers[i].getGender() );
            System.out.println((i + 1) + "\t" + customers[i].getDetails());
        }

        System.out.println("-------------------------客户列表完成-------------------------");
    }

    public static void main(String[] args) {
        CustomerView cv = new CustomerView();
        cv.enterMainMenu();
    }
}
