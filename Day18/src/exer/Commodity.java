package exer;



import java.util.ArrayList;
import java.util.Objects;

/**
 * @author 安雪莹
 * 2、有一个DAO接口，运用ArrayList用于实现数据的操作管理(80分)
 * （1）有一个添加数据的方法
 * （2）有一个修改的方法，根据ID，修改数据
 * （3）有一个根据ID返回具体对象的方法
 * （4）有一个删除的方法，根据ID，删除数据
 * （5）有一个返回所有数据对象的方法
 * （6）有一个返回数据总数量的方法
 *
 *
 */
interface Dao<T>{

    public void add(T t);

    public void modify(int id,T t);

    public T get(int id);

    public void delete(int id);

    public ArrayList<T> getAllList();

    public int size();
}

/**
 * @author Lenovo
 * 现在有一个商品类，该类有id,name,price,description等属性，
 *  * 编写商品管理类实现DAO接口。并测试
 */
public class Commodity implements Dao<Commodity>{

    private int id;
    private String name;
    private String description;

    private ArrayList<Commodity> arrayList = new ArrayList<>();

    @Override
    public void add(Commodity t){
        arrayList.add(t);
    }

    @Override
    public void modify(int id, Commodity t){
        arrayList.set(id,t);
    }


    @Override
    public Commodity get(int id){
        return arrayList.get(id);
    }

    @Override
    public void delete(int id){
        arrayList.remove(id);
    }

    @Override
    public ArrayList<Commodity> getAllList(){
        ArrayList<Commodity> temp = new ArrayList<>(arrayList.size());
        for (int i = 0; i < arrayList.size(); i++) {
            temp.add(arrayList.get(i));
        }
        return temp;
    }

    @Override
    public int size(){
        return arrayList.size();
    }

    public Commodity(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Commodity() {
    }

    @Override
    public String toString() {
        return "Commodity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", arrayList=" + arrayList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) {return false;}
        Commodity commodity = (Commodity) o;
        return id == commodity.id &&
                name.equals(commodity.name) &&
                description.equals(commodity.description) &&
                arrayList.equals(commodity.arrayList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, arrayList);
    }
}

class Test{
    public static void main(String[] args) {
        /**
         *     public void add(T t);
         *
         *     public void modify(int id,T t);
         *
         *     public T get(int id);
         *
         *     public void delete(int id);
         *
         *     public ArrayList<T> getAllList();
         *
         *     public int size();
         */

        Commodity commodity = new Commodity();
        commodity.add(new Commodity(1,"a","a"));
        commodity.add(new Commodity(2,"a","b"));
        commodity.add(new Commodity(3,"a","c"));

        System.out.println(commodity.getAllList());

        commodity.modify(1,new Commodity(1,"a","d"));
    }
}