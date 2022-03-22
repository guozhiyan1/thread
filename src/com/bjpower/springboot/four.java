package com.bjpower.springboot;
//死锁；两个线程各自持有不同的锁，然后各自试图获取对方手里的锁，造成了双方无限等待下去，这就是死锁;死锁一旦形成，就只能强制结束进程
//那么我们应该如何避免死锁呢？答案是：线程获取锁的顺序要一致
public class four {
    public static void main(String[] args) throws InterruptedException {
        Thread add =new AddThread();
        Thread des =new DesThread();
        add.start();
        des.start();
        add.join();
        des.join();
        System.out.println(Counter.count);
    }
    public static class Counter{
        public static int count=0;
        public static Object lock1=new Object();
        public static Object lock2=new Object();
    }
    public static class AddThread extends Thread{
        @Override
        public void run() {
            synchronized (Counter.lock1) {
                for (int i = 0; i < 1000; i++) {
                    synchronized (Counter.lock2) {
                        Counter.count = Counter.count + 1;
                    }

                }
            }
        }
    }
    public static class DesThread extends Thread{
        @Override
        public void run() {
            synchronized (Counter.lock2) {
                for (int i = 0; i < 1000; i++) {
                    synchronized (Counter.lock1) {
                        Counter.count = Counter.count - 1;
                    }
                }
            }
        }

    }
}
