import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class ReachabilityTest {
    public static void main(String[] args) {
        byte[] addr = {(byte) 202, 30, 38, 108};
        InetAddress address = null;
        try {
//            address = InetAddress.getByAddress(addr);
            address = InetAddress.getByName("ecampus.konkuk.ac.kr");
            System.out.println(address);
            int timeout = 3000;
            int ttl = 10;
            if (address.isReachable(timeout)) {
                System.out.println(address.getHostName() + "CAN BE reached within " + timeout);
            } else {
                System.out.println(address.getHostName() + "CANNOT BE reached within " + timeout);
            }
        } catch (UnknownHostException e) {
            System.out.println("unknown hostname");

        }catch (IOException e) {
                throw new RuntimeException(e);
        }
    }
}
