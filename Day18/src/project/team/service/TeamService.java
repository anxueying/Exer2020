package project.team.service;

import project.team.domain.Architect;
import project.team.domain.Designer;
import project.team.domain.Employee;
import project.team.domain.Programmer;



/**
 * @author Mrs.An Xueying
 * 2020/5/19 0:13
 */
public class TeamService {
    private int counter = 1;
    private final int MAX_MEMBER = 5;
    private final int MAX_ARCHITECT = 1;
    private final int MAX_DESIGNER = 2;
    private final int MAX_PROGRAMMER = 3;

    private Programmer[] team = new Programmer[MAX_MEMBER];
    private int total = 0;

    public Programmer[] getTeam(){
        Programmer[] pros = new Programmer[total];
        for (int i = 0; i < total; i++) {
            pros[i] = team[i];
        }
        return pros;
    }

    public void addMember(Employee e) throws  TeamException{
        //成员已满，无法添加
        if (total>=MAX_MEMBER){
            throw new TeamException("成员已满，无法添加");
        }

        //该成员不是开发人员，无法添加
        if (!(e instanceof Programmer)){
            throw new TeamException("该成员不是开发人员，无法添加");
        }

        //该员已是团队成员
        //该员正在休假，无法添加
        Status status = ((Programmer) e).getStatus();
        switch (status){
            case BUSY:
                throw new TeamException("该员已是团队成员");
            case VOCATION:
                throw new TeamException("该员正在休假，无法添加");
            default:
                break;
        }

        //该员工已是团队成员
        for (Programmer programmer : getTeam()) {
            if (programmer.getId() == e.getId()){
                throw new TeamException("该员工已是团队成员");
            }
        }

        //团队中只能有一名架构师
        //团队中只能有两名设计师
        //团队中只能有三名程序员
        int arc = 0, des = 0, pro = 0;
        for (Programmer programmer : getTeam()) {
            if (programmer instanceof Architect){
                arc++;

            }else if(programmer instanceof Designer){
                des++;

            }else {
                pro++;

            }
        }

        if (e instanceof Architect){
            if (arc>=MAX_ARCHITECT){
                throw new TeamException("团队中只能有一名架构师");
            }else{
                team[total++] = (Architect)e;
                ((Architect) e).setMemberId(counter++);
                ((Architect) e).setStatus(Status.BUSY);
            }
        }else if (e instanceof Designer){
            if (des>=MAX_DESIGNER){
                throw new TeamException("团队中只能有两名设计师");
            }else {
                team[total++] = (Designer)e;
                ((Designer) e).setMemberId(counter++);
                ((Designer) e).setStatus(Status.BUSY);
            }
        }else {
            if (pro>=MAX_PROGRAMMER){
                throw new TeamException("团队中只能有三名程序员");
            }else {
                team[total++] = (Programmer) e;
                ((Programmer) e).setMemberId(counter++);
                ((Programmer) e).setStatus(Status.BUSY);
            }
        }

    }

    public void removeMember(int memberId) throws TeamException{
        int i = 0;
        for (; i < total; i++) {
            if(team[i].getMemberId()==memberId){
                team[i].setStatus(Status.FREE);
                break;
            }
        }

        for (int j = i; j < total-1; j++) {
            team[j] = team[j+1];
        }

        team[--total] = null;
    }
}
