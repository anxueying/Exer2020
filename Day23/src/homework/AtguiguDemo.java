package homework;

import java.io.Serializable;

/**
 * @author Mrs.An Xueying
 * 2020/5/28 1:14
 */
public class AtguiguDemo implements Serializable,Comparable {

    private static final long serialVersionUID = 3418305214929003223L;
    private static String school = "尚硅谷";
    private String className;

    public AtguiguDemo() {
    }

    public AtguiguDemo(String className) {
        this.className = className;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public String toString() {
        return "AtguiguDemo{" +
                "className='" + className + '\'' +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof AtguiguDemo){
            AtguiguDemo demo = (AtguiguDemo)o;
            return this.className.compareTo(demo.getClassName());
        }
        return 0;
    }
}


