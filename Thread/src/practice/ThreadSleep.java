package practice;

public class ThreadSleep implements Runnable{
    private int num;
    public ThreadSleep(int num) {
        this.num=num;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(num*1000);
            System.out.println(num);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        int[] nums = {7,3,2,1,5};
        for(int num : nums){
            ThreadSleep ts= new ThreadSleep(num);
            Thread th = new Thread(ts);
            th.start();
        }
    }
}
