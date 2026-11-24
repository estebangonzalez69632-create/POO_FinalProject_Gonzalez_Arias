package domain;

import java.io.Serializable;

public abstract class Employee implements Serializable {
    private int id;
    private String fullName;

    public Employee(int id, String fullName) {
        setId(id);
        setFullName(fullName);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("el id debe ser mayor a 0");
        }
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        if (fullName == null || fullName.length() == 0) {
            throw new IllegalArgumentException("El nombre no puede estar vacio o nulo");
        }
        this.fullName = fullName;
    }

    // METODO ABSTRACTO
    public abstract String getRole();

}
