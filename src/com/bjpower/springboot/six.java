package com.bjpower.springboot;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//synchronized是Java语言层面提供的语法，所以我们不需要考虑异常，而ReentrantLock是Java代码实现的锁，我们就必须先获取锁，然后在finally中正确释放锁。
public class six {
    public static void main(String[] args) throws InterruptedException {

        Thread t1=new AddThread();
        t1.start();
        t1.join();

    }
    public static class AddThread extends Thread{
        public int count=0;
        public final Lock lock=new ReentrantLock();
        public void run(){
            lock.lock();
            try {
                count += 1;
            } finally {
                lock.unlock();
                System.out.println(count);
            }

            try {
                //在尝试获取锁的时候，最多等待1秒。如果1秒后仍未获取到锁，tryLock()返回false，程序就可以做一些额外处理，而不是无限等待下去
                //使用ReentrantLock比直接使用synchronized更安全，线程在tryLock()失败的时候不会导致死锁
                if (lock.tryLock(1, TimeUnit.SECONDS)){
                    try {

                    } finally {
                        lock.unlock();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    //使用ReentrantLock比直接使用synchronized更安全，线程在tryLock()失败的时候不会导致死锁。
}
