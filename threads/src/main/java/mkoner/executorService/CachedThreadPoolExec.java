package mkoner.executorService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedThreadPoolExec {
    public static void main(String[] args) {
        try(ExecutorService service = Executors.newCachedThreadPool()){
            for (int i = 0; i < 100; i++) {
                service.execute(new Task2(i));
            }
        }
    }
}

class Task2 implements Runnable {
    int id;
    public Task2(int id) {
        this.id = id;
    }
    public void run() {
        System.out.println("Task id " + id + " is being executed by " + Thread.currentThread().getName());
    }
}

/*
The task q accepts only one task, if a task is added to q,
- if there is an idle thread the task is assigned to it, if no thread is available a
new one is created to execute the task
- if a thread completes its tasks, it checks if a new task is in the q and then executes
 it, if not it remains idle for 60s max before being killed.

 */
