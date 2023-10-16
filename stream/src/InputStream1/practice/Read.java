package InputStream1.practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Read {

    public static void main(String[] args) {
        try {
            FileInputStream is = new FileInputStream("./src/InputStream/practice/memo");//상대경로
//            FileInputStream is = new FileInputStream("/Users/jiyeon/IdeaProjects/stream/src/InputStream/practice/memo");//절대경로
            int readByte;
            while((readByte=is.read())!=-1){

                System.out.write(readByte);
                System.out.println();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
