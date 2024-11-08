package org.example;

import java.util.concurrent.Semaphore;

public class MultiThreading extends Thread {
    Car ThisCar;

    public MultiThreading(Car ThisCar) {
        this.ThisCar = ThisCar;
    }

    @Override
    public void run() {
        try {
            // Simulate arrival time
            Thread.sleep(ThisCar.getArrivalTime() * 1000L);
            System.out.println("Car " + ThisCar.getCarNumber() + " from Gate " + ThisCar.getGateNumber() + " arrived at time " + ThisCar.getArrivalTime());
            Semaphore semGate = Main.GateSemaphore.get(ThisCar.getGateNumber());
            semGate.acquire();
            System.out.println("Avalialbe : " + Main.parkingSemaphore.availablePermits());
            if (Main.parkingSemaphore.availablePermits() <= 0) {
                System.out.println("Car " + ThisCar.getCarNumber() + " from Gate " + ThisCar.getGateNumber() + " waiting for a spot.");
                Main.parkingSemaphore.acquire();
                semGate.release();
                Main.inCParkingSlots();
                System.out.println("Car " + ThisCar.getCarNumber() + " from Gate " + ThisCar.getGateNumber() + " parked after waiting for " + ThisCar.getWaitingTime() +" units of time. (Parking Status: " + Main.ParkingSlots + " spots occupied)");
            }
            else {
                Main.parkingSemaphore.acquire();
                semGate.release();
                Main.inCParkingSlots();
                System.out.println("Car " + ThisCar.getCarNumber() + " from Gate " + ThisCar.getGateNumber() + " parked. (Parking Status: " + Main.ParkingSlots + " spots occupied)");
            }
            Thread.sleep(ThisCar.getParkingTime() * 1000L);
            Main.parkingSemaphore.release();
            Main.deCParkingSlots();
            System.out.println("Car " + ThisCar.getCarNumber() + " from Gate " + ThisCar.getGateNumber() + " left after " + ThisCar.getParkingTime() + " units of time. (Parking Status: " + Main.ParkingSlots + " spots occupied)");
        } catch (InterruptedException e) {
            System.err.println("Car " + ThisCar.getCarNumber() + " was interrupted during its operation.");
            Thread.currentThread().interrupt();
        }
    }
}
