package data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import domain.Restaurant;

public class PersistenceManager {

    // Guarda todo el objeto Restaurant en un archivo binario (.dat)
    public void saveRestaurant(Restaurant restaurant, String filePath) throws IOException {
        if (restaurant == null) {
            throw new IllegalArgumentException("El restaurante no puede ser nulo.");
        }

        FileOutputStream fileOut = new FileOutputStream(filePath);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);

        out.writeObject(restaurant);

        out.close();
        fileOut.close();
    }

    // Carga el objeto Restaurant desde el archivo binario (.dat)
    public Restaurant loadRestaurant(String filePath) throws IOException, ClassNotFoundException {
        FileInputStream fileIn = new FileInputStream(filePath);
        ObjectInputStream in = new ObjectInputStream(fileIn);

        Restaurant restaurant = (Restaurant) in.readObject();

        in.close();
        fileIn.close();

        return restaurant;
    }
}