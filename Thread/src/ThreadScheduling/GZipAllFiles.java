package ThreadScheduling;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GZipAllFiles {
    public final static int THREAD_COUNT=4;


    public static void main(String[] args) {
        ExecutorService pool= Executors.newFixedThreadPool(THREAD_COUNT);

        String[] fileArr={"/Users/jiyeon/IdeaProjects/Thread/src/test3.txt","/Users/jiyeon/IdeaProjects/Thread/src/test4.rtf"};
        for(String filename:fileArr){
            File f= new File(filename);
            if(f.exists()){
                if(f.isDirectory()){
                    File[] files=f.listFiles();
                    for(int i=0;i<files.length;i++){
                        if(!files[i].isDirectory()){
                            Runnable task=new GZipRunnable(files[i]);
                            pool.submit(task);
                        }
                    }

                }
                else{
                    Runnable task = new GZipRunnable(f);
                    pool.submit(task);
                }
            }
        }

        pool.shutdown();

    }
}
