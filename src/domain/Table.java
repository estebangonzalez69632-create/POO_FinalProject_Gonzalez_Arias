package domain;

public class Table {
 private int number;
    private int capacity;
    private TableStatus status;
    public Table(int number, int capacity, TableStatus status) {
        setNumber(number);
        setCapacity(capacity);
        setStatus(status);
    }
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        if(number<=0){
            throw new IllegalArgumentException("el numero de mesa debe ser positivo");
        }
        this.number = number;
    }
    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int capacity) {
        if(capacity<=0){
            throw new IllegalArgumentException("la capacidad debe ser mayor o igual a 1");
        }
        this.capacity = capacity;
    }
    public TableStatus getStatus() {
        return status;
    }
    public void setStatus(TableStatus status) {
        if(status==null){
            throw new IllegalArgumentException("el estado no puede ser nulo");
        }
        this.status = status;
    }   
}
