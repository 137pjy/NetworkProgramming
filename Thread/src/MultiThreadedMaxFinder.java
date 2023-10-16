import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MultiThreadedMaxFinder {
    public static int max(int[] data) throws InterruptedException, ExecutionException{
        if(data.length==1){
            return data[0];
        }else if (data.length==0){
            throw new IllegalArgumentException();
        }

        //split the job into 2 pieces
        FindMaxTask task1 = new FindMaxTask(data,0,data.length/2);
        FindMaxTask task2 = new FindMaxTask(data,data.length/2,data.length);

        //spawn 2 threads
        ExecutorService service= Executors.newFixedThreadPool(2);

        Future<Integer> future1 = service.submit(task1);
        Future<Integer> future2 = service.submit(task2);

        service.shutdown();//이 시점 이후로는 task를 더 이상 받아들이지 않는다

        return Math.max(future1.get(),future2.get());
        //blocking? 호출?
        //future1이 오래걸리면 future1.get() 가져올때까지 blocking -> futrue2.get()이 호출되지 않는다

    }

    public static void main(String[] args) throws Exception {
        int[] data = {100,88,0,30,75,34,22,0,1,78,53,69,389};
        int result= max(data);
        System.out.println(Runtime.getRuntime().availableProcessors());//#CPU core
        System.out.println(result);
        System.out.println(data.length/2);
    }
}
