package Basic;

import java.io.*;

public class ReadExample {
    public static void main(String[] args) {
        try {
            Reader reader = new FileReader("src/Basic/test.txt");

            char[] buffer = new char[5];

            int readCharNum = reader.read(buffer,2,3);
            if(readCharNum!=-1){
                System.out.println(buffer.length);
                for(int i=0; i<buffer.length;i++){
                    System.out.println(buffer[i]);
                }
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
