package InputStream1;

import java.io.FileInputStream;
import java.io.IOException;

public class FileView {
    public static void main(String[] args) {
        long start=System.currentTimeMillis();

//        if(args.length!=1){
//            System.out.println("args개수 1이아님");
//            System.exit(0);
//        }

        FileInputStream fis = null;

        try {
            fis=new FileInputStream("/Users/jiyeon/IdeaProjects/stream/src/InputStream1/hello.txt");
//            fis=new FileInputStream(args[0]);

//            int i=0;
//            while((i=fis.read())!=-1){
//                System.out.write(i);
//            }

//Improvement
            int readcount = 0;
            byte[] buffer = new byte[512];

            while((readcount=fis.read(buffer))!=-1){
                System.out.write(buffer,0,readcount);
//                System.out.println("\n==================================================================");
//                System.out.println(readcount);
//                System.out.println("==================================================================");
            }


            System.out.println(readcount);
            System.out.println();
        } catch (Exception ex) {
            System.out.println(ex);
        }finally {

            try {
                fis.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        long end=System.currentTimeMillis();
//        long end=System.nanoTime();
        System.out.println("Run-time: "+(end-start)/1000.0);


    }
}
