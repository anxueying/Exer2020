package project.team.test;

import org.junit.Test;
import project.team.domain.Employee;
import project.team.service.NameListService;
import project.team.service.TeamException;

/**
 * @author Mrs.An Xueying
 * 2020/5/18 23:53
 */
public class NameListServiceTest {
    @Test
    public void testGetAllEmployees(){
        NameListService nls = new NameListService();
        Employee[] employees = nls.getAllEmployees();
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    @Test
    public void testGetEmployee(){
        NameListService nls = new NameListService();
        try {
            System.out.println(nls.getEmployee(0));
        } catch (TeamException e) {
            e.printStackTrace();
        }
    }
}
