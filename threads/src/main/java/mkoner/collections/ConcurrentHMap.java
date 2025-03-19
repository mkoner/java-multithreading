package mkoner.collections;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/*

We have multiple users making requests to an API, and we want to track the number of requests
per user concurrently without synchronization issues.
 */
public class ConcurrentHMap {
    public static void main(String[] args) {
        RequestCounter counter = new RequestCounter();

        try (ExecutorService service = Executors.newFixedThreadPool(4)){
            Runnable task = () -> {
                for(int i=0; i<100; i++){
                    counter.recordRequest("Momo");
                    counter.recordRequest("Amira");
                    counter.recordRequest("Ibra");
                }
            };
            for(int i = 0; i < 5; i++){
                service.submit(task);
                service.submit(task);
            }
        }
        counter.printRequests();

    }
}

class RequestCounter{
    private ConcurrentMap<String, Integer> counter = new ConcurrentHashMap<>();

    public void recordRequest(String user){
        /*
        counter.put(user, counter.getOrDefault(user, 0) + 1);
        put is not atomic will cause race conditions
         */
        //counter.merge(user, 1, (oldValue, newValue) -> oldValue + newValue);
        counter.merge(user, 1, Integer::sum);
    }
    public void printRequests(){
        System.out.println(counter);
    }
}
