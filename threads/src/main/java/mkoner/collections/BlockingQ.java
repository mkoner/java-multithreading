package mkoner.collections;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BlockingQ {
    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();

        Thread producer = new Thread(new Producer(queue));
        Thread consumer1 = new Thread(new Consumer(queue));
        Thread consumer2 = new Thread(new Consumer(queue));

        producer.start();
        consumer1.start();
        consumer2.start();
    }
}

class Producer implements Runnable{
    private final BlockingQueue<Integer> queue;
    Producer(BlockingQueue<Integer> queue){
        this.queue = queue;
    }
    @Override
    public void run() {
        try {
            while(true){
                int num = new  Random().nextInt(0,100);
                queue.put(num);
                System.out.println(num + " produced by " + Thread.currentThread().getName());
                Thread.sleep(1000);
            }
        }
        catch (InterruptedException e){
            throw new RuntimeException(e);
        }
    }
}

class Consumer implements Runnable{
    private BlockingQueue<Integer> queue;
    Consumer(BlockingQueue<Integer> queue){
        this.queue = queue;
    }
    @Override
    public void run() {
        try {
            while (true){
                System.out.println(queue.take() + " Consumed by " + Thread.currentThread().getName());
            }
        }
        catch (InterruptedException e){
            throw new RuntimeException(e);
        }
    }
}
