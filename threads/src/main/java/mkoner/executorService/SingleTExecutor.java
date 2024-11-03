package mkoner.executorService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleTExecutor {
    public static void main(String[] args) {
        try (ExecutorService service = Executors.newSingleThreadExecutor()) {
            for (int i = 0; i < 10; i++) {
                service.execute(new Task(i));
            }
        }
    }
}

class Task implements Runnable {
    int id;
    public Task(int id) {
        this.id = id;
    }
    public void run() {
        System.out.println(id + " Executed by " + Thread.currentThread().getName());
    }
}

/*
Creating and managing multiple threads can become tedious.
 ExecutorService provides a framework for creating and managing thread in a flexible
 and efficient manner.
 - creates a certain number of threads that forms a pool of threads
 - you submit tasks to the executor service, the service adds the task to the queue of
 tasks
 - Available threads fetch the tasks from the queue and executes them
 */
