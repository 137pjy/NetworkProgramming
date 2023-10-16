package InputStream1.practice;

import java.io.IOException;

public class SystemRead {
    public static void main(String[] args) {
        int inChar=0;

        System.out.println("Enter a Character:");
        int i=0;
        try {

            inChar=System.in.read();//read는 char로 받아서(?) int를 반환 1byte=8bit=0-255
            System.out.println(inChar);
            System.out.write(inChar);
            System.out.println();
//            System.out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
