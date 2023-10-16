import java.net.InetAddress;
import java.net.UnknownHostException;

public class AddressResolutionByName {
    public static void main(String[] args) {
        try {
            InetAddress address = InetAddress.getByName("www.konkuk.ac.kr");
            InetAddress address2 = InetAddress.getByName("202.30.38.108");
            System.out.println("address : "+address);
            System.out.println("address2 HostAddress : "+address2.getHostAddress());
            System.out.println("address2 HostName: "+address2.getHostName());
            System.out.println("address2.getHostName() : "+address2.getHostName());

            InetAddress[] addresses=InetAddress.getAllByName("www.yahoo.com");
            for(InetAddress address3:addresses)
                System.out.println(address3);



        } catch (UnknownHostException e) {
            System.out.println("Could not find www.konkuk.ac.kr");
        }
    }
}
