package com.bjpower.springboot;
//线程开始，中断
public class one {
    public static void main(String[] args) throws InterruptedException {
        Thread t=new MyThread();
        t.start();
        //中断t线程，方法1：通过判断状态
        t.interrupt();
        //t执行完再执行main线程
        t.join();
        System.out.println("end");
    }
    static class MyThread extends Thread{
        @Override
        public void run(){
            int n = 0;
            while (! isInterrupted()) {
                n ++;
                System.out.println(n + " hello!");
            }
        }
    }
}
