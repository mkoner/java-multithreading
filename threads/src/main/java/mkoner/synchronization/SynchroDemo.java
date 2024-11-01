package mkoner.synchronization;

public class SynchroDemo {
    private static int counter = 0;

    public static void main(String[] args) {
        Thread t1 = new Thread(()->{
            for(int i=0; i<10000; i++){
//                counter++;
                increment();
            }
        });
        Thread t2 = new Thread(()->{
            for(int i=0; i<10000; i++){
//                counter++;
                increment();
            }
        });
        t1.start();
        t2.start();
       try{
           t1.join();
           t2.join();
       }catch (InterruptedException e){
           e.printStackTrace();
       }
       System.out.println(counter);
    }
    private static synchronized void increment(){
        counter++;
    }
}

/*
counter++; is a non atomic operations (it 1st involves reading the value of count
, increment it, then update the value in memory)
T1 might read counter=0 then increment it to 1 but before if updates the value in
memory, T2 reads counter=0

To avoid this we can use synchronized to restrict the access of that operation to
only one thread at a time
*/
