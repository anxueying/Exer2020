package project.team.view;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import project.team.domain.Employee;
import project.team.domain.Programmer;
import project.team.service.NameListService;
import project.team.service.TeamException;
import project.team.service.TeamService;

/**
 * @author Mrs.An Xueying
 * 2020/5/19 1:11
 */
public class TeamView {
    private NameListService listSvc = new NameListService();
    private TeamService teamSvc = new TeamService();
    char yes = 'Y';

    public void enterMainMenu(){
        boolean loopFlag = true;
        char c = '0';
        do{
            if (c!='1'){listAllEmployees();}
            System.out.println("---------------------------------------------------------------------------------------------------\n");
            System.out.print("1-团队列表  2-添加团队成员  3-删除团队成员 4-退出   请选择(1-4)：");
            c = TSUtility.readMenuSelection();
            switch (c){
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
                    System.out.println("确认退出（Y/N）：");
                    char yn = TSUtility.readConfirmSelection();
                    if (yn==yes){
                        loopFlag=false;
                    }
                    break;
                default:
                    break;
            }
        }while (loopFlag);

    }

    public void listAllEmployees(){
        System.out.println("\n-------------------------------------开发团队调度软件--------------------------------------\n");
        System.out.println("ID\t姓名\t年龄\t工资\t职位\t状态\t奖金\t股票\t领用设备\n");
        for (Employee employee : listSvc.getAllEmployees()) {
            System.out.println(employee);
        }
    }

    public void listTeamEmployees(){
        System.out.println("--------------------团队成员列表---------------------");
        System.out.println("TDI/ID\t姓名\t年龄\t工资\t职位\t奖金\t股票");
        for (Programmer programmer : teamSvc.getTeam()) {
            System.out.println(programmer.getMemberId()+programmer.toString());
        }
    }

    public void addMember(){
        System.out.println("---------------------添加成员---------------------\n");
        System.out.print("请输入要添加的员工ID：");
        int id = TSUtility.readInt();
        try {
            teamSvc.addMember(listSvc.getEmployee(id));
        } catch (TeamException e) {
            System.out.println(e.getMessage());
        }

        TSUtility.readReturn();
    }

    public void deleteMember(){
        System.out.println("---------------------删除成员---------------------\n");
        System.out.print("请输入要删除员工的TID：");
        int tid = TSUtility.readInt();
        System.out.print("确认是否删除(Y/N)：");
        char yn = TSUtility.readConfirmSelection();

        if (yn==yes){
            try {
                teamSvc.removeMember(tid);
                System.out.println("删除成功\n");
            } catch (TeamException e) {
                System.out.println(e.getMessage());
            }
            TSUtility.readReturn();
        }else {
            return;
        }
    }

    public static void main(String[] args) {
        TeamView tv = new TeamView();
        tv.enterMainMenu();
    }
}
