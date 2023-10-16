package InputStream1.practice;

import java.io.FileInputStream;
import java.io.IOException;

public class ReadBytes {
    public static void main(String[] args) {
        int bytesRead=0;
        int bytesToRead=10;
        byte[] input= new byte[bytesToRead];
        while(bytesRead<bytesToRead){
            try {
//                int result=System.in.read(input,bytesRead,bytesToRead-bytesRead);
                FileInputStream fis = new FileInputStream("/Users/jiyeon/IdeaProjects/stream/src/InputStream/practice/memo");
                int result=fis.read(input,bytesRead,bytesToRead-bytesRead);
                if(result==-1) {
                    System.out.println("EOF 도달");
                    break;
                }

                bytesRead+=result;
                System.out.println(bytesRead);
//                System.out.write(input);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
