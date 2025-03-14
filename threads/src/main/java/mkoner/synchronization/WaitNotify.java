package mkoner.synchronization;

public class WaitNotify {
    public static void main(String[] args) {
        Printer printer = new Printer(10);

        Thread t1 = new Thread(()->{
            try {
                printer.printOdd();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread t2 = new Thread(()->{
            try {
                printer.printEven();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        t1.start();
        t2.start();
    }
}

class Printer{
    int max;
    int count;

    public Printer(int max){
        this.max = max;
        count = 0;
    }

    public synchronized void printOdd() throws InterruptedException {
        while(count < max){
            if(count%2 == 0)
                wait();
            System.out.println(count + Thread.currentThread().getName());
            count++;
            notify();
        }

    }
    public synchronized void printEven() throws InterruptedException {
        while(count < max){
            if(count%2 == 1)
                wait();
            System.out.println(count + Thread.currentThread().getName());
            count++;
            notify();
        }

    }
}

/*
wait(): it makes the current executing thread go to waiting state
thus releasing the lock on the synchronized object.
Must be called in the context of synchronize(block or method)

notify(): wakes up one of the waiting thread on the same obj lock(if many are waiting
one of them is chosen arbitrary). It does not release the lock immediately it releases
it only after exiting the synchronized block or method

notifyAll(): wakes up all the threads waiting on the current obj
*/
