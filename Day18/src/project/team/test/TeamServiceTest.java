package project.team.test;

import org.junit.Test;
import project.team.domain.*;
import project.team.service.TeamException;
import project.team.service.TeamService;

/**
 * @author Mrs.An Xueying
 * 2020/5/19 1:00
 */
public class TeamServiceTest {
    @Test
    public void testAddMember(){
        TeamService ts = new TeamService();
//        try {
//            ts.addMember(new Employee(1, "z", 10, 10));
//        } catch (TeamException e) {
//            e.printStackTrace();
//        }

        try {
            ts.addMember(new Programmer(1, "z", 10, 10, new Pc("1","1")));
            ts.addMember(new Programmer(2, "z", 10, 10, new Pc("1","1")));
            ts.addMember(new Designer(3, "z", 10, 10, new Pc("1","1"),1));
            ts.addMember(new Designer(4, "z", 10, 10, new Pc("1","1"),1));
            ts.addMember(new Designer(5, "z", 10, 10, new Pc("1","1"),1));
        } catch (TeamException e) {
            e.printStackTrace();
        }

        for (Programmer programmer : ts.getTeam()) {
            System.out.println(programmer);
        }
    }
}
