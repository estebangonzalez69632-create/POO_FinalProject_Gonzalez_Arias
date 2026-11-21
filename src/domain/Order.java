package domain;

import java.io.Serializable;
import java.util.ArrayList;

public class Order implements Serializable {
     private int id;
    private OrderStatus status;
    private ArrayList<OrderItem> items;
    private Table table;   // <--- AGREGADO (Relación con Table en UML)
    private Waiter waiter;


    public Order(int id, OrderStatus status, ArrayList<OrderItem> items) {
       setId(id);
        setTable(table);
        setWaiter(waiter);
        this.status = OrderStatus.OPEN;
        this.items = new ArrayList<>();
        this.table.occupy();
        this.table.setActiveOrder(this);
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


    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        if (table == null) {
            throw new IllegalArgumentException("La mesa asociada al pedido no puede ser nula.");
        }
        this.table = table;
    }

    public Waiter getWaiter() {
        return waiter;
    }

    public void setWaiter(Waiter waiter) {
        if (waiter == null) {
            throw new IllegalArgumentException("El mesero asignado no puede ser nulo.");
        }
        this.waiter = waiter;
    }

    public void addItem(OrderItem item) {
        if (item == null) {
            throw new IllegalArgumentException("No se puede agregar un detalle nulo al pedido.");
        }
        if (this.status == OrderStatus.PAID || this.status == OrderStatus.CANCELLED) {
            throw new IllegalStateException("No se pueden agregar ítems a un pedido cerrado o cancelado.");
        }
        this.items.add(item);
    }

    public double calculateTotal() {
        double total = 0.0;
        for (int i = 0; i < this.items.size(); i++) {
            total += this.items.get(i).getSubtotal();
        }
        return total;
    }
}
