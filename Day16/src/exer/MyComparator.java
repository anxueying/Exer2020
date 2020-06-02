package exer;

import java.util.Comparator;

public class MyComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        if (o1 instanceof Person&&o2 instanceof Person){
            Person p1 = (Person)o1;
            Person p2 = (Person)o2;

            if (p1.getAge().equals(p2.getAge())){
                return p1.getName().compareTo(p2.getName());
            }else {
                return p1.getAge() - p2.getAge();
            }
        }

        return 0;
    }
}
