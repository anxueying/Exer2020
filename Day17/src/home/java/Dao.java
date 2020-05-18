package home.java;

import org.junit.Test;

import java.util.*;

/**
 * 中定义一个Map 成员变量，Map 的键为 String 类型，值为 T 类型。
 * @author 安雪莹
 * 实验2
 */

class Dao<T>{
    private Map<String,T> hashMap = new HashMap<>();

    /**
     * 保存 T 类型的对象到 Map 成员变量中
     * @param id
     * @param entity
     */
    public void save(String id,T entity){
        hashMap.put(id,entity);
    }

    /**
     * 从 map 中获取 id 对应的对象
     * @param id
     * @return
     */
    public T get(String id){
        return hashMap.get(id);
    }

    /**
     * 替换 map 中key为id的内容,改为 entity 对象
     * @param id
     * @param entity
     */
    public void update(String id,T entity){
        for (Map.Entry<String, T> entry : hashMap.entrySet()) {
            if (entry.getKey().equals(id)){
                entry.setValue(entity);
            }
        }
    }

    /**
     * 返回 map 中存放的所有 T 对象
     * @return
     */
    public List<T> list(){
        List<T> l = new ArrayList<>();
        for (T t : hashMap.values()) {
            l.add(t);
        }
        return l;
    }

    /**
     * 删除指定 id 对象
     * @param id
     */
    public void delete(String id){
        hashMap.remove(id);
    }
}


/**
 * 该类包含：private成员变量（int类型） id，age；（String 类型）name。
 */
class User{
    private int id;
    private int age;
    private String name;


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User() {
    }

    public User(int id, int age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }
}

