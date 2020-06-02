package exer;

public class Order {
    private int orderId;
    private String OrderName;

    public Order(int orderId, String orderName) {
        this.orderId = orderId;
        OrderName = orderName;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderName() {
        return OrderName;
    }

    public void setOrderName(String orderName) {
        OrderName = orderName;
    }

    public boolean equals(Object obj){
        if (this == obj){
            return true;
        }

        if (obj instanceof Order){
            Order order = (Order)obj;
            if (this.orderId == order.orderId&&this.OrderName.equals(order.OrderName)){
                return true;
            }else {
                return false;
            }
        }else {
            return super.equals(obj);
        }
    }
}
