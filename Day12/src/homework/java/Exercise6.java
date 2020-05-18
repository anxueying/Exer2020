package homework.java;

public class Exercise6 {
    public static void main(String[] args) {
        C c = new C();
        c.showA();
        c.showB();
        c.showC();
    }

}

abstract class A1{
    private int numa = 10;

    public int getNuma() {
        return numa;
    }

    public void setNuma(int numa) {
        this.numa = numa;
    }

    public  abstract void showA();
}

abstract class B1 extends A1{
    private int numb = 20;

    public int getNumb() {
        return numb;
    }

    public void setNumb(int numb) {
        this.numb = numb;
    }

    public abstract  void  showB();
}

class C extends B1{
    private int numc =30;

    public int getNumc() {
        return numc;
    }

    public void setNumc(int numc) {
        this.numc = numc;
    }

    public void showA(){
        System.out.println("A类中numa："+getNuma());
    }

    public void showB(){
        System.out.println("B类中numb："+getNumb());
    }

    public void showC(){
        System.out.println("C类中numc："+getNumc());
    }
}