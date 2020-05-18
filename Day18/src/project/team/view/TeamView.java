package project.team.view;

import project.team.service.NameListService;
import project.team.service.TeamService;

/**
 * @author Mrs.An Xueying
 * 2020/5/19 1:11
 */
public class TeamView {
    private NameListService listSvc = new NameListService();
    private TeamService teamSvc = new TeamService();

    public void enterMainMenu(){

    }

    public void listAllEmployees(){

    }

    public void listTeamEmployees(){

    }

    public void addMember(){

    }

    public void deleteMember(){

    }

    public static void main(String[] args) {
        TeamView tv = new TeamView();
        tv.enterMainMenu();
    }
}
