package domain;

public class Beverage extends MenuItem {

     private int sizeMl;

     public Beverage(int id, String name, double basePrice, int sizeMl) {
          super(id, name, basePrice);
          setSizeMl(sizeMl);
     }

     public void setSizeMl(int sizeMl) {
          if (sizeMl <= 0) {
               throw new IllegalArgumentException("el tamaño de la bebida en ml debe ser mayor a 0");
          }
          this.sizeMl = sizeMl;
     }

     public int getSizeMl() {
          return sizeMl;
     }

     @Override
     public double calculatePrice() {
          return getBasePrice();
     }

}
