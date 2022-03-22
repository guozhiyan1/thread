package com.bjpower.springboot;
//线程同步
public class three {
    public static void main(String[] args) throws InterruptedException {
        Thread add = new AddThread();
        Thread dec = new DecThread();
        add.start();
        dec.start();
        add.join();
        dec.join();
        System.out.println(Counter.count);
    }
    static class Counter {
        public static Object lock=new Object();
        public static int count = 0;
    }

    static class AddThread extends Thread {
        public void run() {
            for (int i=0; i<10000; i++) {
                synchronized (Counter.lock){Counter.count += 1; }}
        }
    }

    static class DecThread extends Thread {
        public void run() {
            for (int i = 0; i < 10000; i++) {
                synchronized (Counter.lock){Counter.count -= 1;}
            }
        }
    }
}
