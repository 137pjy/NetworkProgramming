package InputStream1;

import java.io.IOException;

public class InputStream {


    public static void main(String[] args){
        int inChar=0;
        int inChar2=0;
        byte[] ch ={'q','w','e'};
        System.out.println("Enter a Character");
        try{
            int i=0;
            inChar=System.in.read();
            System.out.write(inChar);

            System.out.flush();
//            System.out.println(inChar);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }



}
