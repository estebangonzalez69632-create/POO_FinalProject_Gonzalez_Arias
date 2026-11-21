package domain;
import java.util.ArrayList;
public class Restaurant {
    private String name;
    private ArrayList<Table> tables;
    private ArrayList<Order> orders;
    private ArrayList<Employee> employees;
    private ArrayList<MenuItem> menu;

    public Restaurant(String name, ArrayList<Table> tables, ArrayList<Order> orders, ArrayList<Employee> employees,
            ArrayList<MenuItem> menu) {
        this.name = name;
        this.tables = tables;
        this.orders = orders;
        this.employees = employees;
        this.menu = menu;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public ArrayList<Table> getTables() {
        return tables;
    }
    
    public void setTables(ArrayList<Table> tables) {
        this.tables = tables;
    }
    public ArrayList<Order> getOrders() {
        return orders;
    }
    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }
    public ArrayList<Employee> getEmployees() {
        return employees;
    }
    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }
    public ArrayList<MenuItem> getMenu() {
        return menu;
    }
    public void setMenu(ArrayList<MenuItem> menu) {
        this.menu = menu;
    }

}  

