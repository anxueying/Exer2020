package project.team.domain;

/**
 * @author Mrs.An Xueying
 * 2020/5/18 21:09
 */
public class NoteBook implements Equipment{
    private String model;
    private double price;

    public NoteBook() {
    }

    public NoteBook(String model, double price) {
        this.model = model;
        this.price = price;
    }

    @Override
    public String getDescription() {
        return model+"("+price+")";
    }
}
