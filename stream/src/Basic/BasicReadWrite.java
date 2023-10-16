package Basic;

import java.io.IOException;

public class BasicReadWrite {
    public static void main(String[] args) {
        int input = 0;
        try {
            input = System.in.read();
            System.out.println(input);
            System.out.write(input);
            System.out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
