import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class ContentGetter {
    public static void main(String[] args) {
        try {
            URL u = new URL("https://www.oreilly.com");
            Object o = u.getContent();
            int c;
            InputStream r = (InputStream) o;
            while((c=r.read())!=-1) System.out.print((char) c);
            r.close();
            System.out.println("I got a "+o.getClass().getName());
        } catch (MalformedURLException e) {
            System.out.println(args[0]+" is not a parseable URL");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
