import java.net.MalformedURLException;
import java.net.URL;

public class ProtocolTester {
    public static void main(String[] args) {
        //hypertext transfer protocol
        testProtocol("http://www.adc.org");

        //secure http
        testProtocol("https://www.amazon.com/exec/obidos/order2/");

        //file transfer protocol
        testProtocol("ftp://ibiblio.org/pub/languages/java/javafaq/");

        //Simple Mail Transfer Protocol
        testProtocol("mailto:elharo@ibiblio.org");

        //telnet
        testProtocol("telnet://dibner.poly.edu/");

        //local file access
        testProtocol("file:///etc/passwd");


    }

    private static void testProtocol(String url) {
        try {
            URL u=new URL(url);
            System.out.println(u.getProtocol()+" is supported");
        } catch (MalformedURLException e) {
            String protocol = url.substring(0, url.indexOf(':'));
            System.out.println(protocol+" is not supported");

        }

    }
}
