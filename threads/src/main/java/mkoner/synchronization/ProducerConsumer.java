package mkoner.synchronization;

import java.util.ArrayList;
import java.util.List;

public class ProducerConsumer {
    public static void main(String[] args) {
        Worker worker = new Worker(3);
        Thread producer = new Thread(()->{
            try{
                worker.produce();
            }
            catch(InterruptedException e){
                System.out.println(e.getMessage());
            }
        });
        Thread consumer = new Thread(()->{
            try {
                worker.consume();
            }
            catch(InterruptedException e){
                System.out.println(e.getMessage());
            }
        });
        producer.start();
        consumer.start();
    }
}

class Worker{
    private int numberElements;
    private List<Integer> numbers;
    int count;

    public Worker(int numberElements) {
        this.numberElements = numberElements;
        this.numbers = new ArrayList<>();
        this.count = 0;
    }
    public synchronized void produce() throws InterruptedException {
        while (true) {
            if(numbers.size() == numberElements) {
                wait();
            }
            System.out.println(count + " produced");
            numbers.add(count++);
            notify();
        }
    }
    public synchronized void consume() throws InterruptedException {
        while (true) {
            if(numbers.isEmpty())
                wait();
            System.out.println(numbers.getLast() + " consumed");
            numbers.removeLast();
            notify();
        }
    }
}
