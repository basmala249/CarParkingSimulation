package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Semaphore;

public class Main {
    static public int ParkingSlots = 0;
    public static Semaphore parkingSemaphore = new Semaphore(4);  // Semaphore for parking slots
    public static HashMap<Integer, Semaphore> GateSemaphore = new HashMap<>();  // Semaphore for gates

    public static void Insert() {
        Semaphore sem1 = new Semaphore(1);
        Semaphore sem2 = new Semaphore(1);
        Semaphore sem3 = new Semaphore(1);
        GateSemaphore.put(1, sem1);
        GateSemaphore.put(2, sem2);
        GateSemaphore.put(3, sem3);
    }
    public static void inCParkingSlots() {
        ParkingSlots++;
    }
    public static void deCParkingSlots() {
        ParkingSlots--;
    }
    public static void main(String[] args) {
        Insert();
        String filePath = "C:\\Users\\Lenovo\\Downloads\\AssigmentOS2\\src\\main\\java\\org\\example\\file.txt"; // Replace with the actual file path
        ArrayList <Car> upComingCars = new ArrayList<>();
        upComingCars = fileRead(filePath);
        for (Car car : upComingCars) {
                MultiThreading thready = new MultiThreading(car);
                thready.start();
        }
    }
    public static ArrayList<Car> fileRead(String filePath){
        ArrayList<Car> upComingCars = new ArrayList<Car>();


        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                Car car = new Car();
                for (int i = 0; i < tokens.length; i++) {
                    String[] parts = tokens[i].trim().split(" ");
                    if (parts.length > 1) {
                        try {
                            int val = Integer.parseInt(parts[1].trim());
                            switchFunction(car, i, val);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid number format in line: " + line);
                        }
                    }
                }
                upComingCars.add(car);
            }
        } catch (IOException e) {
                System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
        return upComingCars;
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
