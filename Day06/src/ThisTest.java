public class ThisTest {
    public static void main(String[] args) {
        Girl HanMeimei = new Girl();
        Boy LiMing = new Boy();

        HanMeimei.setName("韩梅梅");
        LiMing.setName("李明");
        LiMing.setAge(19);

        LiMing.marry(HanMeimei);//男的说

        HanMeimei.marry(LiMing);//女的说

    }
}

class Girl{
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void marry(Boy boy){
        System.out.println("想嫁给"+boy.getName());
        boy.shout(this);
    }
}

class Boy{
    private String name;
    private int age;

    public void setName(String name) {
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setAge(int age) {
        if (age>0&&age<130){
            this.age = age;
        }
    }

    public int getAge() {
        return age;
    }

    public void marry(Girl girl){
        System.out.println("想和"+girl.getName()+"结婚,想想就行了");
    }

    public void shout(Girl girl){
        System.out.println(girl.getName()+"，我愿意！");
    }
}
