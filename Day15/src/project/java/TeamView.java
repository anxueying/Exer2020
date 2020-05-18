package project.java;

import project.java.TSUtility;

import javax.naming.Name;

/**
 * 主控页面
 */
public class TeamView {
    private NameListService listSvc = new NameListService();
    private TeamService teamSvc = new TeamService();

    /**
     * 主界面显示及控制方法
     */
    public void enterMainMenu() {
        boolean loopFlag = true;
        System.out.println("-------------------------------------开发团队调度软件--------------------------------------");
        listAllEmployees();
        do {
            System.out.print("1-团队列表  2-添加团队成员  3-删除团队成员 4-退出   请选择(1-4)： ");
            char choice = TSUtility.readMenuSelection();
            switch (choice) {
                case '1':
                    listTeamEmployees();
                    break;
                case '2':
                    addMember();
                    break;
                case '3':
                    deleteMember();
                    break;
                case '4':
                    System.out.print("确认是否退出(Y/N)：");
                    char yn = TSUtility.readConfirmSelection();
                    if (yn == 'Y') {
                        System.out.println("感谢使用，下次再见！");
                        loopFlag=false;
                    }
                    break;
            }
        }while (loopFlag);
    }

    /**
     * 以表格形式列出公司所有成员
     */
    private void listAllEmployees() {
        System.out.println("ID     姓名      年龄    工资      职位      状态      奖金      股票    领用设备");
        //公司成员
        for (Employee employee : listSvc.getAllEmployees()) {
            System.out.println(employee);
        }
        System.out.println("---------------------------------------------------------------------------------------------------");
    }

    /**
     * 以表格形式列出团队所有成员
     */
    private void listTeamEmployees() {
        System.out.println("--------------------团队成员列表---------------------");
        System.out.println("TDI/ID  姓名    年龄    工资    职位    奖金    股票");
        //团队成员
        Programmer[] teamMembers = teamSvc.getTeam();
        for (int i = 0; i < teamMembers.length; i++) {
            int tid = teamMembers[i].getMemberId();
            int id = teamMembers[i].getId();
            String name = teamMembers[i].getName();
            int age = teamMembers[i].getAge();
            double salary = teamMembers[i].getSalary();

            if (teamMembers[i] instanceof Architect) {
                Architect architect = (Architect) teamMembers[i];
                double bonus = architect.getBonus();
                int stock = architect.getStock();
                System.out.println(tid + "/" + id + "\t" + name + "\t" + age + "\t" + salary + "\t架构师\t" + bonus + "\t" + stock);
            } else if (teamMembers[i] instanceof Designer) {
                Designer designer = (Designer) teamMembers[i];
                double bonus = designer.getBonus();
                System.out.println(tid + "/" + id + "\t" + name + "\t" + age + "\t" + salary + "\t设计师\t" + bonus);
            } else {
                System.out.println(tid + "/" + id + "\t" + name + "\t" + age + "\t" + salary + "\t程序员");
            }
        }
        System.out.println("-----------------------------------------------------");
    }

    /**
     * 添加成员
     */
    private void addMember() {
        System.out.println("---------------------添加成员---------------------");

        for(;;){
            System.out.print("\n请输入要添加的员工ID（-1退出）：");
            int id = TSUtility.readInt();
            if (id==-1){
                return;
            }

            try {
                Employee employee = listSvc.getEmployee(id);
                teamSvc.addMember(employee);
                //成功
                System.out.print("添加成功");
                TSUtility.readReturn();
                return;//重新显示主界面
            } catch (TeamException t) {
                //失败
                System.out.print("添加失败，原因："+t.getMessage());
            }
        }
    }

    /**
     * 删除成员
     */
    private void deleteMember() {
        System.out.println("---------------------删除成员---------------------");
        for (;;){
            System.out.print("\n请输入要删除员工的TID（-1退出）：");
            int tid = TSUtility.readInt();
            if (tid==-1){
                return;
            }
            System.out.print("确认是否删除(Y/N)：");
            char yn = TSUtility.readConfirmSelection();
            if (yn == 'Y') {
                try {
                    teamSvc.removeMember(tid);
                    System.out.print("删除成功");
                    TSUtility.readReturn();
                    return;//重新显示主界面
                }catch (TeamException t){
                    System.out.print("删除失败，原因："+t.getMessage());
                }
            }
        }
    }

    public static void main(String[] args) {
        TeamView tv = new TeamView();
        tv.enterMainMenu();
    }
}
