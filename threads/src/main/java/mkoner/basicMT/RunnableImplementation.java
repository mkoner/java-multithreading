package mkoner.basicMT;

public class RunnableImplementation {
    public static void main(String[] args) {
        Thread t1 = new Thread(()->{
            for(int i=0; i<10; i++){
                System.out.println(Thread.currentThread().getName()+" "+i);
            }
        });
        Thread t2 = new Thread(()->{
            for(int i=0; i<10; i++){
                System.out.println(Thread.currentThread().getName()+" "+i);
            }
        });

        t1.start();
        t2.start();
        /*
        The seq of execution of the threads depends on the scheduler anytime it finds
        that they are available resources to run a thread it is allocated execution time.
         */
    }
}
