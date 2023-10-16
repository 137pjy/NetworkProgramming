package ThreadScheduling;

public class ThreadJoinTest {
    public static void main(String[] args) {
        Thread t = new Thread(){
            public void run(){
                try {
                    Thread.sleep(2000);
                    System.out.println("Mythread ended");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        };
        t.start();

        try {
            t.join();
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("main() ended");
    }


}
