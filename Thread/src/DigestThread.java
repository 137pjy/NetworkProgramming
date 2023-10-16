import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DigestThread extends Thread{
    private String filename;


    public DigestThread(String filename) {
        this.filename = filename;
    }

    @Override
    public void run() {
        try {
            FileInputStream in = new FileInputStream(filename);
            MessageDigest sha=MessageDigest.getInstance("SHA-256"); //헤시값 계산하고 저장할 객체
            DigestInputStream din=new DigestInputStream(in,sha);//'in'으로부터 데이터를 읽을 때 SHA-256해시 계산
                                                                //데이터가 읽힐 때마다 해당 데이터의 해시가 업데이트
            while(din.read()!=-1){
//                System.out.println(filename+"해시 업데이트 중");
            };//file로부터 읽어들여서 해시값을 업데이트
            din.close();
            byte[] digest = sha.digest();

            StringBuilder result = new StringBuilder(filename);
            result.append(":");
            result.append(toHexString(digest));
            System.out.println(result);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static String toHexString(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();

        for(int i=0;i<bytes.length;i++){
            String hex = Integer.toHexString(0xFF&bytes[i]);
            if(hex.length()==1){
                hexString.append('0');//한자리수(0-15)면 0을 붙여줌
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static void main(String[] args) {
        String file1="/Users/jiyeon/IdeaProjects/Thread/src/test3.txt";
        String file2="/Users/jiyeon/IdeaProjects/Thread/src/test4.rtf";
        String file3="/Users/jiyeon/IdeaProjects/Thread/src/test5.txt";

        //multiThread
        long start = System.nanoTime();


        String[] fileList = {file1,file2,file3};
        for(String filename : fileList){
            Thread t=new DigestThread(filename);
            t.start();
            //각 file에 대해 thread 생성
        }
        long end=System.nanoTime();
        System.out.println("elapsed time(us):"+(end-start)/1000.0);



    }
}
