package Basic;

import java.io.*;

public class methodDevelop {
    public static void main(String[] args) {
        FileInputStream fis=null;
        try {
            fis = new FileInputStream("src/Basic/input.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
       long start = System.nanoTime();
        readAndCreateFile(fis);
        long end = System.nanoTime();
        System.out.println((end-start)/1000.0);
    }

    private static void readAndCreateFile(InputStream fis) {
        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream("newFile.txt");
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            BufferedInputStream bis= new BufferedInputStream(fis);

            //기본 코드
//            int b;
//            while((b=bis.read())!=-1){
////                fos.write(b);
//                bos.write(b);
//            }

            //속도 향상
            int readcount = 0;
            byte[] buffer = new byte[512];
            while((readcount=bis.read(buffer))!=-1){
//                fos.write(buffer,0,readcount);
                bos.write(buffer,0,readcount);
            }


            bos.close();
            fos.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
