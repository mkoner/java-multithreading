package mkoner.executorService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPoolExec {
    public static void main(String[] args) {
        try(ExecutorService service = Executors.newFixedThreadPool(5)){
            for(int i=0; i<16; i++){
                service.execute(new Task(i));
            }
        }
    }
}

class Task1 implements Runnable {
    int id;
    public Task1(int id) {
        this.id = id;
    }
    public void run() {
        System.out.println(Thread.currentThread().getName() + ": Executing task " + id);
    }
}
