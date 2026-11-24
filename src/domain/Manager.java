package domain;

public class Manager extends Employee {

    public Manager(int id, String fullName) {
        super(id, fullName);
    }

    public String generateReport(Restaurant restaurant) {
        if (restaurant == null) {
            throw new IllegalArgumentException("El restaurante no puede ser nulo.");
        }
        return "Reporte de " + restaurant.getName() + " / Mesas: " + restaurant.getTables().size() +
                " / Pedidos totales: " + restaurant.getOrders().size() + " / Ventas del día: $"
                + restaurant.calculateDailySales();
    }

    @Override
    public String getRole() {
        return "Manager";
    }
}
