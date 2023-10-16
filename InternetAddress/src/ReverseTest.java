import java.net.InetAddress;
import java.net.UnknownHostException;

public class ReverseTest {
    public static void main(String[] args) {
        try {
            InetAddress ia = InetAddress.getByName("172.217.161.36");//DNS 컨텍 안이루워짐
            System.out.println(ia.getCanonicalHostName());
            System.out.println(ia.getHostName());

            InetAddress ia2 = InetAddress.getByName("www.ibm.com");//DNS 컨텍 안이루워짐
            System.out.println(ia2.getCanonicalHostName());
            System.out.println(ia2.getHostName());


        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }
}
