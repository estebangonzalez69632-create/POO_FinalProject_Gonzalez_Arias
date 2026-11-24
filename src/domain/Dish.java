package domain;

public class Dish extends MenuItem {
     private int preparationTimeMin;

     public Dish(int id, String name, double basePrice, int preparationTimeMin) {
          super(id, name, basePrice);
          setPreparationTimeMin(preparationTimeMin);
     }

     public int getPreparationTimeMin() {
          return preparationTimeMin;
     }

     public void setPreparationTimeMin(int preparationTimeMin) {
          if (preparationTimeMin <= 0) {
               throw new IllegalArgumentException("el tiempo de preparacion debe ser mayor a 0");
          }
          this.preparationTimeMin = preparationTimeMin;
     }

     @Override
     public double calculatePrice() {
          return getBasePrice() * 1.08;
     }

}
