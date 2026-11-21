package domain;

import java.util.ArrayList;

public class Order {
     private int id;
    private OrderStatus status;
    private ArrayList<OrderItem> items;
    public Order(int id, OrderStatus status, ArrayList<OrderItem> items) {
        setId(id);
        setItems(items);
        setStatus(status);
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        if(id<=0){
            throw new IllegalArgumentException("el id no puede ser negativo");
        }
        this.id = id;
    }
    public OrderStatus getStatus() {
        return status;
    }
    public void setStatus(OrderStatus status) {
       if(status==null){
        throw new IllegalArgumentException("El estado del pedido no puede ser nulo.");
       }
        this.status = status;
    }
    public ArrayList<OrderItem> getItems() {
        return items;
    }
    public void setItems(ArrayList<OrderItem> items) {
        if(items==null){
            throw new IllegalArgumentException("La lista de ítems del pedido no puede ser nula.");
        }
        this.items = items;

    }
}
