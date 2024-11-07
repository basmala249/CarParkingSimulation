package org.example;

public class Car {
    private int GateNumber;
    private int CarNumber;
    private int ArrivalTime;
    private long waitingTime;  // Corrected variable name
    private int parkingTime;

    // Default constructor
    public Car() {
        this.waitingTime = 0;
    }

    // Parameterized constructor
    public Car(int gateNumber, int carNumber, int arrivalTime, int parkingTime) {
        this.GateNumber = gateNumber;
        this.CarNumber = carNumber;
        this.ArrivalTime = arrivalTime;
        this.waitingTime = 0;  // Initial waiting time is 0
        this.parkingTime = parkingTime;
    }

    // Setters
    public void setGateNumber(int GateNumber) {
        this.GateNumber = GateNumber;
    }

    public void setCarNumber(int CarNumber) {
        this.CarNumber = CarNumber;
    }

    public void setArrivalTime(int ArrivalTime) {
        this.ArrivalTime = ArrivalTime;
    }

    public void setParkingTime(int parkingTime) {
        this.parkingTime = parkingTime;
    }

    // Getters
    public int getGateNumber() {
        return GateNumber;
    }

    public int getCarNumber() {
        return CarNumber;
    }

    public int getArrivalTime() {
        return ArrivalTime;
    }

    public long getWaitingTime() {
        return waitingTime;
    }

    public int getParkingTime() {
        return parkingTime;
    }

    // Increment waiting time
    public void incrementWaitingTime() {
        this.waitingTime++;
    }
}
