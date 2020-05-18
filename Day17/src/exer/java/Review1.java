package exer.java;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;

/*创建Car类，包含name，price属性，构造器等方法，创建测试类，
在main方法中创建Set接口的实现类，
添加5个以上的Car对象，打印集合元素，实现过滤重复数据功能；
 */
public class Review1 {
    public static void main(String[] args) {
        HashSet cars = new HashSet();
        cars.add(new Car("奥拓",10000));
        cars.add(new Car("宝马",1099000));
        cars.add(new Car("奥拓",10500));
        cars.add(new Car("奥拓",10000));
        cars.add(new Car("马自达",708000));
        cars.add(new Car("奔驰",999000));

        Iterator it = cars.iterator();
        while (it.hasNext()){
            System.out.println(it.next());
        }
    }
}

class Car{
    private String name;
    private double price;

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Double.compare(car.price, price) == 0 &&
                name.equals(car.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Car(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Car() {
    }
}
