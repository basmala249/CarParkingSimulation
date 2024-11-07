package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    static public int ParkingSlots = 0;
    public static void main(String[] args) {
        String filePath = "C:\\Users\\Lenovo\\Downloads\\AssigmentOS2\\src\\main\\java\\org\\example\\file.txt"; // Replace with the actual file path
        ArrayList<Car> upComingCars = new ArrayList<Car>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                Car car = new Car();
                for (int i = 0; i < tokens.length; i++) {
                    String[] parts = tokens[i].trim().split(" ");
                    if (parts.length > 1) {
                        // Only parse if the second part is a valid integer
                        try {
                            int val = Integer.parseInt(parts[1].trim());
                            switchFunction(car, i, val);
                        } catch (NumberFormatException e) {
                            // Handle the case where the second part is not a valid integer
                            System.out.println("Invalid number format in line: " + line);
                        }
                    }
                }
                upComingCars.add(car);
            }
            for (Car car : upComingCars) {
                MultiThreading thready = new MultiThreading(car);
                thready.start();
            }

        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
    }

    public static void switchFunction(Car car, int i, int val) {
        switch (i) {
            case 0:
                car.setGateNumber(val);
                break;
            case 1:
                car.setCarNumber(val);
                break;
            case 2:
                car.setArrivalTime(val);
                break;
            case 3:
                car.setParkingTime(val);
                break;
        }
    }
}
