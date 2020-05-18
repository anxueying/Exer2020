package project.team.domain;

/**
 * @author Mrs.An Xueying
 * 2020/5/18 21:09
 */
public class Pc implements Equipment{
    private String model;
    private String display;

    public Pc() {
    }

    public Pc(String model, String display) {
        this.model = model;
        this.display = display;
    }

    @Override
    public String getDescription() {
        return model+"("+display+")";
    }
}
