package exer.java;

public class OrderTest {
    public static void main(String[] args) {
        Order order1 = new Order(10,"11");
        Order order2 = new Order(10,"11");
        Order order3 = new Order(10,"12");
        System.out.println("order1.equals(order2) = " + order1.equals(order2));
        System.out.println("order1.equals(order3) = " + order1.equals(order3));
    }
}
