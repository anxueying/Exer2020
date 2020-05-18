package exer;



import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.ForkJoinPool;

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
public class Commodity {

    private int id;
    private String name;
    private String description;


    @Override
    public String toString() {
        return "Commodity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) {return false;}
        Commodity commodity = (Commodity) o;
        return id == commodity.id &&
                name.equals(commodity.name) &&
                description.equals(commodity.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description);
    }

    public Commodity(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Commodity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}


class CommodityManage implements Dao<Commodity>{

    ArrayList<Commodity> commodities = new ArrayList<>();

    @Override
    public void add(Commodity commodity) {
        commodities.add(commodity);
    }

    @Override
    public void modify(int id, Commodity commodity) {
        for (int i = 0; i < commodities.size(); i++) {
            if(commodities.get(i).getId()==id){
                 commodities.set(i,commodity);
            }
        }
    }

    @Override
    public Commodity get(int id) {
        for (int i = 0; i < commodities.size(); i++) {
            try {
                if(commodities.get(i).getId()==id){
                    return commodities.get(i);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void delete(int id) {
        for (int i = 0; i < commodities.size(); i++) {
            if(commodities.get(i).getId()==id){
                commodities.remove(i);
            }
        }
    }

    @Override
    public ArrayList<Commodity> getAllList() {
        return commodities;
    }

    @Override
    public int size() {
        return commodities.size();
    }
}

class Test{
    public static void main(String[] args) {
        CommodityManage cm = new CommodityManage();
        cm.add(new Commodity(1, "内衣", "内裤"));
        cm.add(new Commodity(2, "外衣", "内裤"));
        cm.modify(1, new Commodity(1, "毛衣", "针织"));
        System.out.println(cm.get(1));
        cm.delete(1);
        System.out.println(cm.size());
        System.out.println(cm.getAllList());
    }
}