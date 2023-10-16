package BufferedStream;

import java.io.*;

public class StreamReaderWriterTest {
    public static void main(String[] args) {
        FileInputStream fis=null;
        InputStreamReader isr=null;
        OutputStreamWriter osw=null;

        try{
            fis=new FileInputStream("/Users/jiyeon/Documents/Konkuk7/NetworkProgramming/test3.txt");
            isr=new InputStreamReader(fis,"EUC-KR");
            osw=new OutputStreamWriter(System.out,"UTF-8");
            char[] buffer = new char[512];
            int readcount=0;
            while((readcount=isr.read(buffer))!=-1){
                osw.write(buffer,0,readcount);

            }
            fis.close();
            isr.close();
            osw.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
