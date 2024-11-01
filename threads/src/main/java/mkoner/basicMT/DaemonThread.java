package mkoner.basicMT;

public class DaemonThread {
    public static void main(String[] args) {
        Thread daemonThread = new Thread(()->{
            while(true){
                System.out.println("Daemon running");
            }
        });
        Thread userThread = new Thread(()->{
            for(int i=0; i<5; i++){
                System.out.println("User thread");
            }
        });
        daemonThread.setDaemon(true);
        daemonThread.start();
        userThread.start();
    }

}
