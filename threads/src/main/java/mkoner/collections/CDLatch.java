package mkoner.collections;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CDLatch {
    public static void main(String[] args) throws InterruptedException {
        int numOfTask = 10;
        CountDownLatch latch = new CountDownLatch(numOfTask);
        try (ExecutorService service = Executors.newCachedThreadPool()) {
            for(int i=0; i < numOfTask; i++){
                service.submit(new Task(i, latch));
            }
        }
        latch.await();
        System.out.println("All tasks completed");
    }
}

class Task implements Runnable{
    private int taskId;
    private CountDownLatch latch;
    public Task(int taskId, CountDownLatch latch){
        this.latch = latch;
        this.taskId = taskId;
    }
    @Override
    public void run(){
        try {
            System.out.println("Task: " + taskId + " started by: " + Thread.currentThread().getName());
            Thread.sleep(3000);
            System.out.println("Task: " + taskId + " completed by: " + Thread.currentThread().getName());
            latch.countDown();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
/*
CountDownLatch is a synchronization aid in Java that allows one or more threads to wait until a
set of operations being performed by other threads completes. It is part of the
java.util.concurrent package.
- It starts with a count (an integer) that represents the number of tasks that must complete before
 the waiting thread(s) can proceed.
- Threads call countDown() to decrease the count.
- Other threads call await(), which blocks them until the count reaches zero.
 */
