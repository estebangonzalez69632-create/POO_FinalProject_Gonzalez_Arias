package domain;

import java.util.ArrayList;

public class Waiter extends Employee {
     private ArrayList<Table> assignedTables;

     public Waiter(int id, String fullName, ArrayList<Table> assignedTables) {
          super(id, fullName);
          setAssignedTables(assignedTables);
     }

     public ArrayList<Table> getAssignedTables() {
          return assignedTables;
     }

     public void setAssignedTables(ArrayList<Table> assignedTables) {
          if (assignedTables==null){
              throw new IllegalArgumentException("La lista de mesas asignadas no puede ser nula"); 
          }
          this.assignedTables = assignedTables;
     } 
     public void takeOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("El pedido no puede ser nulo.");
        }
        if (order.getStatus() == OrderStatus.CANCELLED || order.getStatus() == OrderStatus.PAID) {
            throw new IllegalStateException("No se puede tomar un pedido cerrado o cancelado.");
        }
    }
     @Override
    public String getRole() {
        return "Waiter";
    }
}
