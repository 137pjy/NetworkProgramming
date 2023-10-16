import java.net.MalformedURLException;
import java.net.URL;

public class RelativeURLTest {
    public static void main(String[] args) {
        try {
            URL u1=new URL("http://www.ibiblio.org/javafaq/index.html");
            URL u2=new URL(u1,"mailinglists.html");
            System.out.println(u1);
            System.out.println(u2);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
