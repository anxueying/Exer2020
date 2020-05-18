package project.team.domain;

/**
 * @author Mrs.An Xueying
 * 2020/5/18 21:10
 */
public class Printer implements Equipment{
    private String type;
    private String name;

    public Printer() {
    }

    public Printer(String type, String name) {
        this.type = type;
        this.name = name;
    }

    @Override
    public String getDescription() {
        return type+"("+name+")";
    }
}
