package domain;
import java.util.ArrayList;
public class Restaurant {
    private String name;
    private ArrayList<Table> tables;
    private ArrayList<Order> orders;
    private ArrayList<Employee> employees;
    private ArrayList<MenuItem> menu;
    //CONSTRUCTOR
    public Restaurant(String name, ArrayList<Table> tables, ArrayList<Order> orders, ArrayList<Employee> employees,
            ArrayList<MenuItem> menu) {
        setName(name);
        setTables(tables);
        setOrders(orders);
        setEmployees(employees);
        setMenu(menu);
    }
    // SETERS AND GETTERS
    public String getName() {
        return name;
    }
    public void setName(String name) {
        if (name==null || name.length()==0){
            throw new IllegalArgumentException("el nombre del restaurante mo puede estar vacio o ser nulo");
        }
        this.name = name;
    }
    public ArrayList<Table> getTables() {
        return tables;
    }
    
    public void setTables(ArrayList<Table> tables) {
        if (tables==null){
             throw new IllegalArgumentException("la lista de mesas no puede ser nula");
        }
        this.tables = tables;
    }
    public ArrayList<Order> getOrders() {
        return orders;
    }
    public void setOrders(ArrayList<Order> orders) {
        if(orders==null){
            throw new IllegalArgumentException("la lista de pedidos no puede ser nula");
        }
        this.orders = orders;
    }
    public ArrayList<Employee> getEmployees() {
        return employees;
    }
    public void setEmployees(ArrayList<Employee> employees) {
        if(employees==null){
            throw new IllegalArgumentException("la lista de empleados no puede ser nula");
        }
        this.employees = employees;
    }
    public ArrayList<MenuItem> getMenu() {
        return menu;
    }
    public void setMenu(ArrayList<MenuItem> menu) {
        if (menu== null){
            throw new IllegalArgumentException("el menu no puede ser nula");
        }
        this.menu = menu;
    }

    //CRUD EMPLEADOS

    public void addEmployee(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("No se puede agregar un empleado nulo.");
        }
        this.employees.add(employee);
    }

    public Employee getEmployeeById(int id) {
        for (int i = 0; i < this.employees.size(); i++) {
            if (this.employees.get(i).getId() == id) {
                return this.employees.get(i);
            }
        }
        return null;
    }

    public void updateEmployee(int id, String newName) {
        Employee emp = getEmployeeById(id);
        if (emp != null) {
            emp.setFullName(newName);
        } else {
            throw new IllegalArgumentException("Empleado no encontrado.");
        }
    }

    public void removeEmployee(int id) {
        for (int i = 0; i < this.employees.size(); i++) {
            if (this.employees.get(i).getId() == id) {
                this.employees.remove(i);
                return;
            }
        }
        throw new IllegalArgumentException("Empleado no encontrado para eliminar.");
    }

    //CRUD DE MESAS

    public void addTable(Table table) {
        if (table == null) {
            throw new IllegalArgumentException("No se puede agregar una mesa nula.");
        }
        this.tables.add(table);
    }

    public Table getTableByNumber(int number) {
        for (int i = 0; i < this.tables.size(); i++) {
            if (this.tables.get(i).getNumber() == number) {
                return this.tables.get(i);
            }
        }
        return null;
    }

    public void updateTable(int number, int newCapacity, TableStatus newStatus) {
        Table table = getTableByNumber(number);
        if (table != null) {
            table.setCapacity(newCapacity);
            table.setStatus(newStatus);
        } else {
            throw new IllegalArgumentException("Mesa no encontrada.");
        }
    }

    public void removeTable(int number) {
        for (int i = 0; i < this.tables.size(); i++) {
            if (this.tables.get(i).getNumber() == number) {
                this.tables.remove(i);
                return;
            }
        }
        throw new IllegalArgumentException("Mesa no encontrada para eliminar.");
    }

    //CRUD DE MENU

    public void addMenuItem(MenuItem item) {
        if (item == null) {
            throw new IllegalArgumentException("No se puede agregar un ítem nulo al menú.");
        }
        this.menu.add(item);
    }

    public MenuItem getMenuItemById(int id) {
        for (int i = 0; i < this.menu.size(); i++) {
            if (this.menu.get(i).getId() == id) {
                return this.menu.get(i);
            }
        }
        return null;
    }

    public void updateMenuItem(int id, String newName, double newPrice) {
        MenuItem item = getMenuItemById(id);
        if (item != null) {
            item.setName(newName);
            item.setBasePrice(newPrice);
        } else {
            throw new IllegalArgumentException("Ítem del menú no encontrado.");
        }
    }

    public void removeMenuItem(int id) {
        for (int i = 0; i < this.menu.size(); i++) {
            if (this.menu.get(i).getId() == id) {
                this.menu.remove(i);
                return;
            }
        }
        throw new IllegalArgumentException("Ítem del menú no encontrado para eliminar.");
    }

    //CRUD PEDIDOS
    public void addOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("No se puede registrar un pedido nulo.");
        }
        this.orders.add(order);
    }

    public Order getOrderById(int id) {
        for (int i = 0; i < this.orders.size(); i++) {
            if (this.orders.get(i).getId() == id) {
                return this.orders.get(i);
            }
        }
        return null;
    }

    public void updateOrderStatus(int id, OrderStatus newStatus) {
        Order order = getOrderById(id);
        if (order != null) {
            order.setStatus(newStatus);
        } else {
            throw new IllegalArgumentException("Pedido no encontrado.");
        }
    }

    public void removeOrder(int id) {
        for (int i = 0; i < this.orders.size(); i++) {
            if (this.orders.get(i).getId() == id) {
                this.orders.remove(i);
                return;
            }
        }
        throw new IllegalArgumentException("Pedido no encontrado para eliminar.");
    }
}






