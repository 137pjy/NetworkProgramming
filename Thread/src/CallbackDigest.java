import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CallbackDigest implements Runnable {

    private String filename;

    public CallbackDigest(String filename){
        this.filename=filename;
    }


    @Override
    public void run() {
        FileInputStream in = null;
        try {
            in = new FileInputStream(filename);
            MessageDigest sha = MessageDigest.getInstance("SHA-256");
            DigestInputStream din = new DigestInputStream(in, sha);
            while(din.read()!=-1);
            din.close();
            byte[] digest = sha.digest();
            CallbackDigestUserInterFace.receieveDigest(digest,filename);
            //끝난다음에 호출해서 널포인터 안일어남

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
