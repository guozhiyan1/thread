package com.bjpower.springboot;
//守护线程，中断线程的另一种方法
public class two {
    public static void main(String[] args) throws InterruptedException {
        MyThread t = new MyThread();
        t.start();
        Thread.sleep(1); // 暂停1毫秒
        t.running=false;
        //守护线程是指为其他线程服务的线程。在JVM中，所有非守护线程都执行完毕后，无论有没有守护线程，虚拟机都会自动退出;护线程不能持有任何需要关闭的资源，例如打开文件等，因为虚拟机退出时，守护线程没有任何机会来关闭文件，这会导致数据丢失。
        Thread t1=new Thread();
        t1.setDaemon(true);
        t1.start();
    }
    static class MyThread extends Thread {
        //线程间共享变量需要使用volatile关键字标记，确保每个线程都能读取到更新后的变量值。
        public volatile boolean running = true;
        public void run() {
            int n = 0;
            while (running) {
                n++;
                System.out.println(n + " hello!");
            }
            System.out.println("end!");
        }
    }
}
