package domain;

public class OrderItem {
    private int quantity;
    private MenuItem menuItem;
    public OrderItem(int quantity, MenuItem menuItem) {
        setMenuItem(menuItem);
        setQuantity(quantity);
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        if(quantity<=0){
            throw new IllegalArgumentException("la cantidad debe ser mayor a 0");
        }
        this.quantity = quantity;
    }
    public MenuItem getMenuItem() {
        return menuItem;
    }
    public void setMenuItem(MenuItem menuItem) {
       if (menuItem==null){
        throw new IllegalArgumentException("el item del menu no puede ser nulo");
       }
        this.menuItem = menuItem;
    }
}
