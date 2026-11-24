package domain;

import java.io.Serializable;

public class Table implements Serializable {
    private int number;
    private int capacity;
    private TableStatus status;
    private Order activeOrder;

    public Table(int number, int capacity, TableStatus status) {
        setNumber(number);
        setCapacity(capacity);
        setStatus(status);
        this.activeOrder = null;
    }

    public Table(int number, int capacity) {
        this(number, capacity, TableStatus.FREE);
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        if (number <= 0) {
            throw new IllegalArgumentException("el numero de mesa debe ser positivo");
        }
        this.number = number;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("la capacidad debe ser mayor o igual a 1");
        }
        this.capacity = capacity;
    }

    public TableStatus getStatus() {
        return status;
    }

    public void setStatus(TableStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("el estado no puede ser nulo");
        }
        this.status = status;
    }

    public Order getActiveOrder() {
        return activeOrder;
    }

    public void setActiveOrder(Order activeOrder) {
        this.activeOrder = activeOrder;
    }

    public void occupy() {
        if (this.status == TableStatus.OCCUPIED) {
            throw new IllegalStateException("La mesa ya se encuentra ocupada.");
        }
        this.status = TableStatus.OCCUPIED;
    }

    public void free() {
        this.status = TableStatus.FREE;
        this.activeOrder = null;
    }
}
