package org.example;

public class Semaphore {
    protected int count = 0;
    protected Semaphore(){}
    protected Semaphore(int count){
        this.count = count;
    }
    public synchronized void acquire() throws InterruptedException {
        count--;
        if(count < 0){
            try {
                wait();
            }catch (InterruptedException e){

            }
        }
    }
    public synchronized void release() throws InterruptedException {
        count++;
        if(count <= 0){
            notify();
        }
    }
    public synchronized int availablePermits() {
        return count;
    }

}
