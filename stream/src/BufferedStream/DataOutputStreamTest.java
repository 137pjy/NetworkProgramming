package BufferedStream;

import java.io.*;

public class DataOutputStreamTest {
    public static void main(String[] args) {
        FileOutputStream fos = null;
        DataOutputStream dos = null;

        boolean addTab = false;

        try {
            fos = new FileOutputStream("data.bin");
            dos= new DataOutputStream(fos);
            dos.writeBoolean(false);
            if (addTab) dos.writeChar('\n');
            dos.writeByte((byte)257);
            if (addTab) dos.writeChar('\n');
            dos.writeInt(10);//4byte
            if (addTab) dos.writeChar('\n');
            dos.writeDouble(200.5);//8byte
            if (addTab) dos.writeChar('\n');



            dos.writeUTF("hello world은 한글이 아닙니다");
            dos.writeUTF("추석이 기다려진다");

            System.out.println("저장");

            fos.close();
            dos.close();

            FileInputStream fis=null;
            DataInputStream dis=null;

            fis=new FileInputStream("data.bin");
            dis=new DataInputStream(fis);

            boolean boolVar=dis.readBoolean();
            if(addTab) dis.readChar();
            byte byteVar=dis.readByte();
            if(addTab) dis.readChar();
            int intVar=dis.readInt();
            if(addTab) dis.readChar();
            double doubleVar=dis.readDouble();
            if(addTab) dis.readChar();
            String[] stringVar=new String[2];

            int cnt=0;
//            while(dis.available()>0){
//                stringVar[cnt] = dis;
//            }

            System.out.println(boolVar);
            System.out.println(byteVar);
            System.out.println(intVar);
            System.out.println(doubleVar);
            System.out.println(stringVar);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
