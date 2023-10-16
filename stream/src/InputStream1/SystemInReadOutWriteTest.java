package InputStream1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SystemInReadOutWriteTest {
    public static void main(String[] args) {
        InputStream in = System.in;
        OutputStream out = System.out;

        int input= 0;
        try {
            input = in.read();
            System.out.println(input);
            out.write(input);
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
