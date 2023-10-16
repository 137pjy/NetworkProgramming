package InputStream1.practice;

import java.io.IOException;

public class ReadByteArray {
    public static void main(String[] args){

        System.out.println("Enter a Character");
        try{
//            byte[] input = new byte[1024];
//            int bytesRead=System.in.read(input);
//            System.out.println(bytesRead);
//            System.out.println(input);


            byte[] input = new byte[10];
            for (int i = 0; i < input.length; i++) {
                int b = System.in.read();
                System.out.write(b);
                if (b == -1) break;
                input[i] = (byte) b;
            }
//            for(int i=0;i<10;i++){
//                System.out.write(input[i]);
//            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
