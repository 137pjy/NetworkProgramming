import java.net.InetAddress;
import java.net.UnknownHostException;

public class MyAddress {
    public static void main(String[] args) {
        InetAddress address = null;
        try {
            address = InetAddress.getLocalHost();
            String dottedQuad = address.getHostAddress();
            System.out.println("My Address is "+ dottedQuad);
        } catch (UnknownHostException e) {
            System.out.println("Could not find this computer's address");
        }
        System.out.println(address);
    }
}
