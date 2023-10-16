import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ReturnDigest extends Thread {

    private String filename;
    private byte[] digest;

    public ReturnDigest(String filename) {
        this.filename = filename;
    }

    @Override
    public void run() {
        try {
            FileInputStream in = new FileInputStream(filename);
            MessageDigest sha = MessageDigest.getInstance("SHA-256");
            DigestInputStream din = new DigestInputStream(in, sha);
            while (din.read() != -1);
            din.close();
            digest = sha.digest();
        } catch (IOException ex) {
            System.err.println(ex);
        } catch (NoSuchAlgorithmException ex){
            System.err.println(ex);
        }
    }

    public static String toHexString(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();

        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public byte[] getDigest() {
        return digest;
    }

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, InterruptedException {
        long start = System.nanoTime();

        String[] file = {"./src/test3.txt", "./src/test4.rtf", "./src/test5.txt"};

        ReturnDigest[] digests = new ReturnDigest[file.length];

        for (int i = 0; i < file.length; i++) {
            digests[i] = new ReturnDigest(file[i]);
            digests[i].start();
        }

        for (int i = 0; i < file.length; i++) {
            while (true) {
                int[] k1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40};
                byte[] digest = digests[i].getDigest();
                if (digest != null) {
                    StringBuilder result = new StringBuilder(file[i]);
                    result.append(": ");
                    result.append(toHexString(digest));
                    System.out.println(result);
                    break;
                }
            }
        }

        long end = System.nanoTime();
        System.out.println("elapsed time(us): " + (end - start) / 1000.0);
    }
}