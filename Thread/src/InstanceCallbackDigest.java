import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class InstanceCallbackDigest implements Runnable{

    private String filename;
    private InstanceCallbackDigestUserInterface callback;
    public InstanceCallbackDigest(String filename, InstanceCallbackDigestUserInterface callback) {
        this.filename=filename;
        this.callback=callback;
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
            callback.receiveDigest(digest);
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
