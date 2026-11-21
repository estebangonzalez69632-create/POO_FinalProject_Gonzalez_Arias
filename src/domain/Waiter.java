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
}
