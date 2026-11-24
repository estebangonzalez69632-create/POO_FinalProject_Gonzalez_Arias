package data;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import domain.Order;
import domain.Restaurant;

public class CsvExporter {
    public void exportOrdersReport(Restaurant restaurant, String filePath) throws IOException {
        if (restaurant == null) {
            throw new IllegalArgumentException("El restaurante no puede ser nulo.");
        }

        FileWriter fileWriter = new FileWriter(filePath);
        PrintWriter writer = new PrintWriter(fileWriter);

        // 1. Escribir la fila de encabezados
        writer.println("Order_ID,Table_Number,Waiter_Name,Status,Items_Count,Total_Amount");

        // 2. Recorrer todos los pedidos con un for tradicional
        ArrayList<Order> orders = restaurant.getOrders();
        for (int i = 0; i < orders.size(); i++) {
            Order current = orders.get(i);

            int tableNumber = 0;
            if (current.getTable() != null) {
                tableNumber = current.getTable().getNumber();
            }

            String waiterName = "Sin asignar";
            if (current.getWaiter() != null) {
                waiterName = current.getWaiter().getFullName();
            }

            // 3. Escribir los datos de cada pedido separados por comas
            writer.println(
                current.getId() + "," +
                tableNumber + "," +
                waiterName + "," +
                current.getStatus() + "," +
                current.getItems().size() + "," +
                current.calculateTotal()
            );
        }

        writer.close();
        fileWriter.close();
    }
}
