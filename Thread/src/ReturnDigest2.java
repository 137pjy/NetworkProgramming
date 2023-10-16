import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ReturnDigest2 extends Thread{

    private String filename;
    private byte[] digest;

    public ReturnDigest2(String filename){
        this.filename=filename;
    }



    @Override
    public void run() {
        try {
            FileInputStream in = new FileInputStream(filename);
            MessageDigest sha=MessageDigest.getInstance("SHA-256"); //헤시값 계산하고 저장한 객체
            DigestInputStream din=new DigestInputStream(in,sha);
            while(din.read()!=-1);//file로부터 읽어들여서 해시값을 업데이트
            din.close();
            digest = sha.digest();


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
    public byte[] getDigest() {
        return digest;

    }

    public static void main(String[] args) {
        String file1="/Users/jiyeon/IdeaProjects/Thread/src/test3.txt";
        String file2="/Users/jiyeon/IdeaProjects/Thread/src/test4.rtf";
        String file3="/Users/jiyeon/IdeaProjects/Thread/src/test5.txt";

        //multiThread
        long start = System.nanoTime();


        String[] fileList = {file1,file2,file3};
        ReturnDigest2[] digests=new ReturnDigest2[3];
        for(int i = 0; i<fileList.length; i++){
            digests[i]= new ReturnDigest2(fileList[i]);
            digests[i].start();
        }
        //polling 해결
        for(int i =0; i<fileList.length;i++){
//            System.out.println("FileNum : "+i);
            while(true){
//                int[] k1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40};
                byte[] digest=digests[i].getDigest();
                StringBuilder result = new StringBuilder(fileList[i]);
                result.append(": ");
                if(digest!=null){
                    result.append(toHexString(digest));
                    System.out.println(result);
                    break;

                }
            }
        }

    }

}
