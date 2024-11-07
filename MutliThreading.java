package org.example;

import java.util.concurrent.Semaphore;

public class MultiThreading extends Thread {
    public static Semaphore semaphore = new Semaphore(4);  // 4 parking spots
    Car ThisCar;

    public MultiThreading(Car ThisCar) {
        this.ThisCar = ThisCar;
    }

    @Override
    public void run() {
        try {
            // Wait for the car to arrive based on its arrival time
            Thread.sleep(ThisCar.getArrivalTime() * 1000L);
            System.out.println("Car " + ThisCar.getCarNumber() + " from Gate " + ThisCar.getGateNumber() + " arrived at time " + ThisCar.getArrivalTime());

            // Try to acquire a parking spot
            if (semaphore.tryAcquire() == false) {
                System.out.println("Car " + ThisCar.getCarNumber() + " from Gate " + ThisCar.getGateNumber() + " waiting for a spot.");
                // Keep trying to acquire a spot
                while (!semaphore.tryAcquire()) {
                    ThisCar.incrementWaitingTime();
                    Thread.sleep(1000);
                }
                Main.ParkingSlots += 1;
                System.out.println("Car " + ThisCar.getCarNumber() + " from Gate " + ThisCar.getGateNumber() + " parked after waiting for " + ThisCar.getWaitingTime() + " units of time. (Parking Status: " + Main.ParkingSlots + " spots occupied)");

            } else {
                Main.ParkingSlots += 1;
                // Car parked immediately if a spot is available
                System.out.println("Car " + ThisCar.getCarNumber() + " from Gate " + ThisCar.getGateNumber() + " parked. (Parking Status: " + Main.ParkingSlots + " spots occupied)");
            }

            // Simulate the parking time
            Thread.sleep(ThisCar.getParkingTime() * 1000L);

            // After parking time, release the spot
            semaphore.release();
            Main.ParkingSlots -= 1;
            System.out.println("Car " + ThisCar.getCarNumber() + " from Gate " + ThisCar.getGateNumber() + " left after " + ThisCar.getParkingTime() + " units of time. (Parking Status: " + Main.ParkingSlots + " spots occupied)");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
