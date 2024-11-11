package mkoner.executorService;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorServiceDemo {
    public static void main(String[] args) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.schedule(()->{
            System.out.println("Run after 3 secs");
        }, 3, TimeUnit.SECONDS);
        scheduler.scheduleAtFixedRate(()->{
            System.out.println("Fixed rate task");
        }, 2, 5, TimeUnit.SECONDS);
        scheduler.scheduleWithFixedDelay(()->{
            System.out.println("Fixed delay");
        }, 3, 3, TimeUnit.SECONDS);
    }
}
