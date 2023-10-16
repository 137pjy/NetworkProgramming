import java.net.URI;
import java.net.URISyntaxException;

public class UriConstructorTest {
    public static void main(String[] args) {
        URI voice= null;
        try {
            voice = new URI("callme+82-2-450-1234");
            URI web=new URI("http://www.xml.com/pub/a/2003/09/17/stax.html#id=_hdc");
            URI book= new URI("urn:isbn:1-565-92870-9");
            URI test= new URI("a:");
            URI test2= new URI(":bc");

            System.out.println(voice);
            System.out.println(web);
            System.out.println(book);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

    }
}
