package Basic;

import java.io.*;
import java.util.Arrays;

public class WriteExample {
    public static void main(String[] args) {
        try {
//            OutputStream os = new FileOutputStream("src/Basic/test.txt");
            Writer os = new FileWriter("src/Basic/test.txt");

            byte[] array = {10,20,30,40,50};
            System.out.println(Arrays.toString(array));

//            os.write(Arrays.toString(array));
            os.write(Arrays.toString(array));

            os.flush();
            os.close();




        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
