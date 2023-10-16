package InputStream1.practice;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SystemInReadOutWriteTest {
    public static void main(String[] args) {
        InputStream in = System.in;
        OutputStream out = System.out;

        int input = 0;
        try {
            input = in.read();
            System.out.println(input);
            out.write(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //fix

        byte[] inputArr= new byte[2]; // 두 개의 바이트를 읽기 위한 배열

        try {
            in.read(inputArr); // 두 개의 바이트 읽기
            out.write(inputArr); // 읽은 바이트를 출력
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
