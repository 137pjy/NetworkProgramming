package InputStream1.practice;

import java.io.IOException;

public class WrongBytesRead {
    public static void main(String[] args) {
        int bytesRead=0;
        int bytesToRead =1024;
        byte[] input = new byte[bytesToRead];
        while(bytesRead<bytesToRead){
            System.out.println("bytesRead"+bytesRead);

            try {
                int result=System.in.read(input,bytesRead,bytesToRead-bytesRead);
                System.out.println("return result : "+result);
                if(result ==-1)
                    break;
                bytesRead=bytesRead+result;
                System.out.println("bytesRead : "+bytesRead);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
