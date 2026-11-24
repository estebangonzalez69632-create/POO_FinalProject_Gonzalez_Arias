package domain;

import java.io.Serializable;

public abstract class MenuItem implements Serializable {
    private int id;
    private String name;
    private double basePrice;

    public MenuItem(int id, String name, double basePrice) {
        setId(id);
        setName(name);
        setBasePrice(basePrice);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("el id no puede ser negativo");
        }
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.length() == 0) {
            throw new IllegalArgumentException("el nombre debe estar nulo o vacio");
        }
        this.name = name;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        if (basePrice < 0) {
            throw new IllegalArgumentException("el precio base debe ser mayor a 0");
        }
        this.basePrice = basePrice;
    }

    // METODO ABSTRACTO
    public abstract double calculatePrice();

}
