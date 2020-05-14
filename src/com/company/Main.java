package com.company;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Lucky.LuckyThread();
        Thread t2 = new Lucky.LuckyThread();
        Thread t3 = new Lucky.LuckyThread();

        t1.setName("t1");
        t2.setName("t2");
        t3.setName("t3");

        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        System.out.println("Total: " + Lucky.LuckyThread.getCount());
    }
}

class StateObject {
    private int x = 0;
    private int count = 0;
    public int getX(){
        return x;
    }

    public int getCount(){
        return count;
    }

    public void incrementX() {
        x++;
    }
    public void incrementCount(){
        count++;
    }
}

class Lucky {
    static StateObject so = new StateObject();

    static class LuckyThread extends Thread {
        @Override
        public void run() {
            while (true) {
                synchronized (so) {
                    if(so.getX() >= 999999) {
                        break;
                    }
                    so.incrementX();
                    if ((so.getX() % 10) + (so.getX() / 10) % 10 + (so.getX() / 100) % 10 == (so.getX() / 1000)
                            % 10 + (so.getX() / 10000) % 10 + (so.getX() / 100000) % 10) {
                        System.out.println(so.getX() + " " + this.getName());
                        so.incrementCount();
                    }
                }
            }
        }

        public static int getCount(){
            return so.getCount();
        }
    }
}