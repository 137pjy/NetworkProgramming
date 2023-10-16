package OutputStream;

import java.io.IOException;
import java.io.OutputStream;

public class GenerateCharacterSingleByte {
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
            long beforeTime = System.currentTimeMillis(); // 코드 실행 전에 시간 받아오기
            while(count<1000000){

                for(int i = start; i<start + numberOfCharactersPerLine; i++){
                    out.write((byte)((i-firstPrintableCharacter)%numberOfPrintableCharcters + firstPrintableCharacter));
                    //33 34 .. 104-33=71+33=104
                }
                out.write((byte)'\r');//carriage return
                out.write((byte)'\n');//line feed

                start=((start+1)-firstPrintableCharacter) % numberOfPrintableCharcters + firstPrintableCharacter;
                count++;
            }
            long afterTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기
            long diffTime = afterTime - beforeTime; // 두 개의 실행 시간
            System.out.println("실행 시간(ms): " + diffTime); // 세컨드(초 단위 변환)
        }
}
