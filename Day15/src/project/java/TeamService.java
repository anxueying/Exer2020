package project.java;


import java.util.Arrays;

/**
 * 管理开发团队成员
 */
public class TeamService {
    private static int counter = 1;//开发团队新增成员 自增1作为团队唯一ID  memberId
    private static final int MAX_MEMBER = 5;//开发团队最大成员数
    private Programmer[] team = new Programmer[MAX_MEMBER];//保存当前团队中的各成员对象
    private int total = 0;//记录团队成员的实际人数

    private int programmerNum = 0;
    private int designerNum = 0;
    private int architectNum = 0;

    /**
     * 返回当前团队的所有对象
     *
     * @return 包含所有成员对象的数组，数组大小与成员人数一致
     */
    public Programmer[] getTeam() {
        Programmer[] allProgrammer = new Programmer[total];
        for (int i = 0; i < total; i++) {
            allProgrammer[i] = team[i];
        }
        return allProgrammer;
    }

    /**
     * 向团队中添加成员
     * * 最多一名架构师
     * * 最多两名设计师
     * * 最多三名程序员
     *
     * @param e 成员
     * @throws TeamException 异常说明
     *                       * 成员已满，无法添加  d
     *                       * 该成员不是开发人员，无法添加 d
     *                       * 该员已是团队成员 d
     *                       * 该员正在休假，无法添加
     *                       * 该员工已是团队成员 d
     *                       * 团队中只能有一名架构师 d
     *                       * 团队中只能有两名设计师 d
     *                       * 团队中只能有三名程序员 d
     */
    public void addMember(Employee e) throws TeamException {
        for (Programmer programmer : getTeam()) {
            if (programmer.equals(e)) {
                throw new TeamException("该员工已是团队成员");
            }
        }

        if (total > MAX_MEMBER) {
            throw new TeamException("成员已满，无法添加");
        }


        if (e instanceof Architect) {
            if (architectNum >= 1) {
                throw new TeamException("团队中只能有一名架构师");
            }

            Architect architect = (Architect) e;

            if (architect.getStatus().equals(Status.VOCATION)) {
                throw new TeamException("该员正在休假，无法添加");
            }
            team[total++] = architect;
            architect.setMemberId(counter++);//设置memberId
            architect.setStatus(Status.BUSY);
            architectNum++;
        } else if (e instanceof Designer) {
            if (designerNum >= 2) {
                throw new TeamException("团队中只能有两名设计师");
            }
            Designer designer = (Designer) e;

            if (designer.getStatus().equals(Status.VOCATION)) {
                throw new TeamException("该员正在休假，无法添加");
            }

            team[total++] = designer;
            designer.setMemberId(counter++);//设置memberId
            designer.setStatus(Status.BUSY);
            designerNum++;
        } else if (e instanceof Programmer) {
            if (programmerNum >= 3) {
                throw new TeamException("团队中只能有三名程序员");
            }

            Programmer programmer = (Programmer) e;
            if (programmer.getStatus().equals(Status.VOCATION)) {
                throw new TeamException("该员正在休假，无法添加");
            }

            team[total++] = programmer;
            programmer.setMemberId(counter++);//设置memberId
            programmer.setStatus(Status.BUSY);
            programmerNum++;
        } else {
            throw new TeamException("该成员不是开发人员，无法添加");
        }
    }

    public void removeMember(int memberId) throws TeamException {

        if (memberId < 1||total==0) {
            throw new TeamException("成员不存在");
        }

        int index = 0;

        for (int i = 0; i < total; i++) {
            if (team[i].getMemberId()== memberId) {
                index = i;
            }
        }

        if (team[index] instanceof Architect){
            //System.out.println("architectNum="+architectNum);
            architectNum--;
        }else if(team[index] instanceof Designer){
            //System.out.println("designerNum = " + designerNum);
            designerNum--;
        }else {
            //System.out.println("programmerNum = " + programmerNum);
            programmerNum--;
        }

        for (int i = index; i < total - 1; i++) {
            team[i] = team[i + 1];
        }
        team[--total] = null;

    }

    public static void main(String[] args) throws TeamException {
        TeamService ts = new TeamService();
        NameListService nls = new NameListService();
        Employee[] employees = nls.getAllEmployees();
        /*for (Employee employee : employees) {
            System.out.println(employee);
        }*/
        ts.addMember(employees[1]);
        ts.addMember(employees[2]);
        ts.addMember(employees[3]);
        ts.addMember(employees[4]);
        ts.addMember(employees[9]);

        ts.removeMember(1);
        for (Programmer programmer : ts.getTeam()) {
            System.out.print(programmer);
            System.out.println(programmer.getMemberId());
        }
        ts.addMember(employees[1]);





   /*     ts.removeMember(1);

        for (Programmer programmer : ts.getTeam()) {
            System.out.print(programmer);
            System.out.println(programmer.getMemberId());
        }*/

    }
}
