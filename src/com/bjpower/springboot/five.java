package com.bjpower.springboot;

import java.util.LinkedList;
import java.util.Queue;

//多线程协调运行的原则就是：当条件不满足时，线程进入等待状态；当条件满足时，线程被唤醒，继续执行任务
public class five {
    Queue<String> queue = new LinkedList<>();

    public synchronized void addTask(String s) {
        this.queue.add(s);
        this.notify(); // 唤醒在this锁等待的线程以调用notify()或notifyAll()唤醒其他等待线程
    }

    public synchronized String getTask() throws InterruptedException {
        while (queue.isEmpty()) {
            //wait()方法调用时，会释放线程获得的锁;wait()方法返回后，线程又会重新试图获得锁
            // 释放this锁:
            this.wait();
            // 重新获取this锁
        }
        return queue.remove();
    }
}
