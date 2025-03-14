package mkoner.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SynchronizedCollection {
    public static void main(String[] args) throws InterruptedException {
        List<Integer> list = Collections.synchronizedList(new ArrayList<>());
        //List<Integer> list = new ArrayList<>();
        Thread t1 = new Thread(()->{
            for(int i=0; i < 10000; i++){
                list.add(i);
            }
        });
        Thread t2 = new Thread(()->{
            for(int i=10000; i < 20000; i++){
                list.add(i);
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(list.size());
        for (int i: list){
            System.out.print(i + " ");
        }
    }
}

/*
In Java, synchronized collections are thread-safe versions of standard collections provided by
utility methods in the Collections class, such as:

Collections.synchronizedList(List<T> list)
Collections.synchronizedMap(Map<K, V> map)
Collections.synchronizedSet(Set<T> set)
These methods wrap the given collection and synchronize access to it, ensuring that only one
thread can modify it at a time.

Cons:
Low performance due to single lock(coarse grained) used for the entire resource
 */
