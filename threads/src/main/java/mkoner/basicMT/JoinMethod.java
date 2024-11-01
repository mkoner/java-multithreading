package mkoner.basicMT;

public class JoinMethod {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(()->{
            for(int i=0;i<10;i++){
                System.out.println(Thread.currentThread().getName()+":"+i);
            }
        });
        Thread t2 = new Thread(()->{
            for(int i=0;i<10;i++){
                System.out.println(Thread.currentThread().getName()+":"+i);
            }
        });
        t1.start();
        t1.join();
        t2.start();
        t2.join();
        System.out.println("After");
    }
}
/*
Join is used to make the running thread to wait for the calling thread
to finish it execution
throws InterruptedException
 */
