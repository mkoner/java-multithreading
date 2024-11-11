package mkoner.executorService;

import java.util.concurrent.*;

public class CallableAndFuture {
    public static void main(String[] args) {
        try (ExecutorService service = Executors.newFixedThreadPool(2)) {
            for (int i = 0; i < 3; i++) {
                Future<Integer> result = service.submit(new Job());
                try {
                    System.out.println(result.get());
                } catch (InterruptedException | ExecutionException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}

class Job implements Callable<Integer>{
    public Integer call() throws InterruptedException {
        Thread.sleep(9000);
        return 13;
    }
}
