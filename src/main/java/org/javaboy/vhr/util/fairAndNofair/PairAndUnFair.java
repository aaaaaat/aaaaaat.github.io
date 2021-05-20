package org.javaboy.vhr.util.fairAndNofair;

import jdk.nashorn.internal.ir.CallNode;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 演示公平锁，分别展示公平和不公平的情况
 * 非公平锁会让现在持有锁的线程优先再次获取得到锁
 *
 * @author xyf
 * @version 1.0
 * @date 2021/3/5 10:38 上午
 */
public class PairAndUnFair {
    public static void main(String[] args) {
        PrintQueue printQueue = new PrintQueue();

        Thread[] threads = new Thread[10];

        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(new Job(printQueue), "thread" + i);
        }

        for (int i = 0; i < 10; i++) {

            threads[i].start();
            try{
                Thread.sleep(100);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
        /**
         *公平输出
         * Thread 0: Going to print a job
         * Thread 0: PrintQueue: Printing a Job during 5 seconds
         * Thread 1: Going to print a job
         * Thread 2: Going to print a job
         * Thread 3: Going to print a job
         * Thread 4: Going to print a job
         * Thread 5: Going to print a job
         * Thread 6: Going to print a job
         * Thread 7: Going to print a job
         * Thread 8: Going to print a job
         * Thread 9: Going to print a job
         * 线程1-9执行了"Going to print a job"，等待释放第一个Lock锁
         */

        /**
         * 非公平输出
         * Thread 0: Going to print a job
         * Thread 0: PrintQueue: Printing a Job during 6 seconds
         * Thread 1: Going to print a job
         * Thread 2: Going to print a job
         * Thread 3: Going to print a job
         * Thread 4: Going to print a job
         * Thread 5: Going to print a job
         * Thread 6: Going to print a job
         * Thread 7: Going to print a job
         * Thread 8: Going to print a job
         * Thread 9: Going to print a job
         * Thread 0: PrintQueue: Printing a Job during 8 seconds
         * Thread 0: The document has been printed
         * Thread 1: PrintQueue: Printing a Job during 9 seconds
         * Thread 1: PrintQueue: Printing a Job during 8 seconds
         * 非公平情况下，存在抢锁“插队”的现象，
         * 比如Thread 0 在释放锁后又能优先获取到锁，
         * 虽然此时在等待队列中已经有 Thread 1 ~ Thread 9 在排队了。
         */
    }

    private static class PrintQueue {
        //我们可以通过改变new ReentrantLock(false)中的参数来设置公平/非公平锁
        private final Lock queueLock=new ReentrantLock(true);
        public void printJob(Object document){
            //--------------------
            queueLock.lock();
            try{

                Long duration = (long)(Math.random() * 10000);
                System.out.printf("%s: PrintQueue: Printing a Job during %d seconds\n",
                        Thread.currentThread().getName(),(duration/1000));
                Thread.sleep(duration);

            }catch (InterruptedException e){
                e.printStackTrace();
            }finally {
                queueLock.unlock();
            }
            //--------------------
            queueLock.lock();
            try{

                Long duration = (long)(Math.random() * 10000);
                System.out.printf("%s: PrintQueue: Printing a Job during %d seconds\n",
                        Thread.currentThread().getName(),(duration/1000));
                Thread.sleep(duration);
            } catch(InterruptedException e){
                e.printStackTrace();
            }finally {
                queueLock.unlock();
            }

        }

    }
    static class Job implements Runnable{

        private PrintQueue printQueue;

        public Job(PrintQueue printQueue){
            this.printQueue=printQueue;
        }

        @Override
        public void run() {
            System.out.printf("%s: Going to print a job\n",Thread.currentThread().getName());
            printQueue.printJob(new Object());
            System.out.printf("%s: The document has been printed\n",Thread.currentThread().getName());
         }
    }
}
