package UI;

import java.util.ArrayList;
import java.util.Scanner;

import data.CsvExporter;
import data.PersistenceManager;
import domain.Beverage;
import domain.Dish;
import domain.Employee;
import domain.Manager;
import domain.MenuItem;
import domain.Order;
import domain.OrderItem;
import domain.OrderStatus;
import domain.Restaurant;
import domain.Table;
import domain.Waiter;

public class main {
    static Restaurant restaurant;
    static PersistenceManager persistence = new PersistenceManager();
    static CsvExporter csvExporter = new CsvExporter();
    static Scanner scanner = new Scanner(System.in);
    static final String DATA_FILE = "restaurant_data.dat";
    static final String CSV_FILE = "sales_report.csv";

    public static void main(String[] args) {
        cargarDatos();
        int op = -1;
        while (op != 0) {
            System.out.println("\n=== " + restaurant.getName() + " ===");
            System.out.println("1. Mesas  \n2.Empleados  \n3.Menu  \n4.Pedidos  \n5.Reportes/CSV  \n0.Guardar y Salir");
            try {
                op = leerInt("Elija una opcion: ");
                switch (op) {
                    case 1:
                        menuMesas();
                        break;
                    case 2:
                        menuEmpleados();
                        break;
                    case 3:
                        menuProductos();
                        break;
                    case 4:
                        menuPedidos();
                        break;
                    case 5:
                        menuReportes();
                        break;
                    case 0:
                        guardarDatos();
                        System.out.println("Saliendo...");
                        break;
                    default:
                        System.out.println("Opcion no valida.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    static void menuMesas() {
        int op = leerInt("\n--- MESAS ---\n1. Agregar\n2. Listar\n3. Actualizar\n4. Eliminar\n5. Recomendar\nOpcion: ");
        if (op == 1) {
            restaurant.addTable(new Table(leerInt("Numero: "), leerInt("Capacidad: ")));
            System.out.println("Mesa agregada.");
        } else if (op == 2) {
            ArrayList<Table> list = restaurant.getTables();
            for (int i = 0; i < list.size(); i++) {
                Table t = list.get(i);
                System.out.println("Mesa #" + t.getNumber() + " | Cap: " + t.getCapacity() + " | " + t.getStatus());
            }
        } else if (op == 3) {
            int num = leerInt("Numero de mesa: ");
            Table t = restaurant.getTableByNumber(num);
            if (t != null) {
                restaurant.updateTable(num, leerInt("Nueva capacidad: "), t.getStatus());
                System.out.println("Mesa actualizada.");
            } else
                System.out.println("Mesa no encontrada.");
        } else if (op == 4) {
            restaurant.removeTable(leerInt("Numero de mesa a eliminar: "));
            System.out.println("Mesa eliminada.");
        } else if (op == 5) {
            Table rec = restaurant.recommendTable(leerInt("Comensales: "));
            if (rec != null)
                System.out.println("Recomendada: Mesa #" + rec.getNumber() + " (Cap: " + rec.getCapacity() + ")");
            else
                System.out.println("No hay mesas disponibles.");
        }
    }

    static void menuEmpleados() {
        int op = leerInt(
                "\n--- EMPLEADOS ---\n1.Agregar Mesero\n2.Agregar Gerente\n3.Listar\n4.Actualizar\n5.Eliminar\nOpcion: ");
        if (op == 1) {
            restaurant.addEmployee(new Waiter(leerInt("ID: "), leerTexto("Nombre: "), new ArrayList<Table>()));
            System.out.println("Mesero registrado.");
        } else if (op == 2) {
            restaurant.addEmployee(new Manager(leerInt("ID: "), leerTexto("Nombre: ")));
            System.out.println("Gerente registrado.");
        } else if (op == 3) {
            ArrayList<Employee> list = restaurant.getEmployees();
            for (int i = 0; i < list.size(); i++) {
                Employee e = list.get(i);
                System.out.println("ID: " + e.getId() + " | " + e.getFullName() + " | " + e.getRole());
            }
        } else if (op == 4) {
            restaurant.updateEmployee(leerInt("ID: "), leerTexto("Nuevo nombre: "));
            System.out.println("Empleado actualizado.");
        } else if (op == 5) {
            restaurant.removeEmployee(leerInt("ID a eliminar: "));
            System.out.println("Empleado eliminado.");
        }
    }

    static void menuProductos() {
        int op = leerInt(
                "\n--- MENU ---\n1.Agregar Plato\n2.Agregar Bebida\n3.Ver Menu\n4.Actualizar\n5.Eliminar\nOpcion: ");
        if (op == 1) {
            restaurant.addMenuItem(new Dish(leerInt("ID: "), leerTexto("Nombre: "), leerDouble("Precio base: "),
                    leerInt("Minutos prep: ")));
            System.out.println("Plato agregado.");
        } else if (op == 2) {
            restaurant.addMenuItem(new Beverage(leerInt("ID: "), leerTexto("Nombre: "), leerDouble("Precio base: "),
                    leerInt("Tamano ml: ")));
            System.out.println("Bebida agregada.");
        } else if (op == 3) {
            ArrayList<MenuItem> list = restaurant.getMenu();
            for (int i = 0; i < list.size(); i++) {
                MenuItem m = list.get(i);
                System.out.println("ID: " + m.getId() + " | " + m.getName() + " | Final: $" + m.calculatePrice());
            }
        } else if (op == 4) {
            restaurant.updateMenuItem(leerInt("ID: "), leerTexto("Nuevo nombre: "), leerDouble("Nuevo precio: "));
            System.out.println("Producto actualizado.");
        } else if (op == 5) {
            restaurant.removeMenuItem(leerInt("ID a eliminar: "));
            System.out.println("Producto eliminado.");
        }
    }

    static void menuPedidos() {
        int op = leerInt(
                "\n--- PEDIDOS ---\n1.Crear\n2.Agregar Item\n3.Servir\n4.Pagar\n5.Cancelar\n6.Ver Activos\nOpcion: ");
        if (op == 1) {
            int id = leerInt("ID Pedido: ");
            Table t = restaurant.getTableByNumber(leerInt("Numero de mesa: "));
            Employee emp = restaurant.getEmployeeById(leerInt("ID Mesero: "));
            if (t != null && emp instanceof Waiter) {
                restaurant.registerOrder(new Order(id, t, (Waiter) emp));
                System.out.println("Pedido creado y mesa ocupada.");
            } else
                System.out.println("Mesa o mesero invalido.");
        } else if (op == 2) {
            Order o = restaurant.getOrderById(leerInt("ID Pedido: "));
            MenuItem m = restaurant.getMenuItemById(leerInt("ID Producto: "));
            if (o != null && m != null) {
                o.addItem(new OrderItem(leerInt("Cantidad: "), m));
                System.out.println("Agregado. Total actual: $" + o.calculateTotal());
            } else
                System.out.println("Pedido o producto no encontrado.");
        } else if (op == 3) {
            restaurant.updateOrderStatus(leerInt("ID Pedido: "), OrderStatus.SERVED);
            System.out.println("Pedido marcado como SERVIDO.");
        } else if (op == 4) {
            restaurant.updateOrderStatus(leerInt("ID Pedido: "), OrderStatus.PAID);
            System.out.println("Pedido PAGADO y mesa liberada.");
        } else if (op == 5) {
            restaurant.cancelOrder(leerInt("ID Pedido: "));
            System.out.println("Pedido CANCELADO y mesa liberada.");
        } else if (op == 6) {
            ArrayList<Order> list = restaurant.findActiveOrders();
            for (int i = 0; i < list.size(); i++) {
                Order o = list.get(i);
                System.out.println("Pedido #" + o.getId() + " | Mesa #" + o.getTable().getNumber() + " | "
                        + o.getStatus() + " | $" + o.calculateTotal());
            }
        }
    }

    static void menuReportes() throws Exception {
        int op = leerInt(
                "\n--- REPORTES ---\n1.Ventas del dia\n2.Reporte Gerente\n3.Exportar CSV\n4.Guardar\nOpcion: ");
        if (op == 1) {
            System.out.println("Ventas pagadas del dia: $" + restaurant.calculateDailySales());
        } else if (op == 2) {
            Employee emp = restaurant.getEmployeeById(leerInt("ID Gerente: "));
            if (emp instanceof Manager)
                System.out.println(((Manager) emp).generateReport(restaurant));
            else
                System.out.println("Gerente no encontrado.");
        } else if (op == 3) {
            csvExporter.exportOrdersReport(restaurant, CSV_FILE);
            System.out.println("Exportado a " + CSV_FILE);
        } else if (op == 4) {
            guardarDatos();
        }
    }

    // --- METODOS DE AYUDA (Persistencia y lectura de consola) ---
    static void cargarDatos() {
        try {
            restaurant = persistence.loadRestaurant(DATA_FILE);
            System.out.println("Datos cargados desde " + DATA_FILE);
        } catch (Exception e) {
            restaurant = new Restaurant("Mi Restaurante", new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),
                    new ArrayList<>());
            System.out.println("Iniciando restaurante nuevo...");
        }
    }

    static void guardarDatos() {
        try {
            persistence.saveRestaurant(restaurant, DATA_FILE);
            System.out.println("Datos guardados en " + DATA_FILE);
        } catch (Exception e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }

    static String leerTexto(String msg) {
        System.out.print(msg);
        return scanner.nextLine();
    }

    static int leerInt(String msg) {
        System.out.print(msg);
        return Integer.parseInt(scanner.nextLine());
    }

    static double leerDouble(String msg) {
        System.out.print(msg);
        return Double.parseDouble(scanner.nextLine());
    }
}
