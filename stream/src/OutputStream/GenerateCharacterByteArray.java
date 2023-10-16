package OutputStream;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;



public class GenerateCharacterByteArray {
    public static void main(String[] args){
        try {
            generateCharacters(System.out);
        } catch (IOException e) {

        }

    }

    public static void generateCharacters(OutputStream out) throws IOException{

        int count=0;

        int numberOfCharactersPerLine=72;
        int firstPrintableCharacter = 33;
        int numberOfPrintableCharcters = 94;
        int start=firstPrintableCharacter;
        byte[] line;


        long beforeTime = System.currentTimeMillis(); // 코드 실행 전에 시간 받아오기
        line=new byte[numberOfPrintableCharcters+numberOfCharactersPerLine-1];
        for(int i = 0; i<numberOfPrintableCharcters+numberOfCharactersPerLine-1; i++){
            byte data= (byte) ((i % numberOfPrintableCharcters) +firstPrintableCharacter);
            line[i]=data;
            System.out.println(data);
        }



        while(count<1000000){
            out.write(line,count%numberOfPrintableCharcters,numberOfCharactersPerLine);

            out.write((byte)'\r');//carriage return
            out.write((byte)'\n');//line feed
            count++;

//            for(int i = start; i<start+numberOfCharactersPerLine; i++){
//                byte data= (byte) ((i-firstPrintableCharacter)%numberOfPrintableCharcters + firstPrintableCharacter);
//                dataArr[i-start]=data;
////                out.write((byte)((i-firstPrintableCharacter)%numberOfPrintableCharcters + firstPrintableCharacter));
//                //33 34 .. 104-33=71+33=104
//            }
//            out.write(dataArr,0,numberOfCharactersPerLine);
//
//            out.write((byte)'\r');//carriage return
//            out.write((byte)'\n');//line feed
//
//            start=((start+1)-firstPrintableCharacter)%numberOfPrintableCharcters+firstPrintableCharacter;
//            count++;
        }
        long afterTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기
        long diffTime = afterTime - beforeTime; // 두 개의 실행 시간
        System.out.println("실행 시간(ms): " + diffTime); // 세컨드(초 단위 변환)
    }
}
