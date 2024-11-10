package org.example;
import java.util.ArrayList;
import org.example.Semaphore;

public class MultiThreading extends Thread {
    Car ThisCar;
    public MultiThreading(Car ThisCar) {
        this.ThisCar = ThisCar;
    }


    @Override
    public void run() {

        try {
            Main.totalCars.incrementAndGet();
            // Simulate arrival time
            synchronized (Main.totalcarServed) {
                Main.totalcarServed.put(ThisCar.getGateNumber(), Main.totalcarServed.get(ThisCar.getGateNumber()) + 1);
            }            Thread.sleep(ThisCar.getArrivalTime() * 1000L);
            System.out.println("Car " + ThisCar.getCarNumber() + " from Gate " + ThisCar.getGateNumber() + " arrived at time " + ThisCar.getArrivalTime());
            Semaphore semGate = Main.GateSemaphore.get(ThisCar.getGateNumber());
            long waitStartTime = System.currentTimeMillis();
            semGate.acquire();
            boolean waited = false;
            if (Main.parkingSemaphore.availablePermits() <= 0) {
                waited = true;
                System.out.println("Car " + ThisCar.getCarNumber() + " from Gate " + ThisCar.getGateNumber() + " waiting for a spot.");
            }
            Main.parkingSemaphore.acquire();
            Main.inCdeC.acquire();
            Main.inCParkingSlots();
            if(waited) {
                long waitEndTime = System.currentTimeMillis();
                long waitingTimeInSeconds = (waitEndTime - waitStartTime) / 1000;
                System.out.println("Car " + ThisCar.getCarNumber() + " from Gate " + ThisCar.getGateNumber() + " parked after waiting for " +
                        waitingTimeInSeconds +" units of time. (Parking Status: " + Main.ParkingSlots + " spots occupied)");
            }
            else {
                System.out.println("Car " + ThisCar.getCarNumber() + " from Gate " + ThisCar.getGateNumber() + " parked. (Parking Status: " + Main.ParkingSlots + " spots occupied)");
            }
            Main.inCdeC.release();
            semGate.release();
            Thread.sleep(ThisCar.getParkingTime() * 1000L);
            Main.inCdeC.acquire();
            Main.deCParkingSlots();
            System.out.println("Car " + ThisCar.getCarNumber() + " from Gate " + ThisCar.getGateNumber() + " left after " + ThisCar.getParkingTime() + " units of time. (Parking Status: " + Main.ParkingSlots + " spots occupied)");
            Main.inCdeC.release();
            Main.parkingSemaphore.release();
        } catch (InterruptedException e) {
            System.err.println("Car " + ThisCar.getCarNumber() + " was interrupted during its operation.");
            Thread.currentThread().interrupt();
        }
    }
}
