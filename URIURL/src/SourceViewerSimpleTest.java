import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class SourceViewerSimpleTest {
    public static void main(String[] args) {
        InputStream in = null;

        try {
//            URL u = new URL("https://www.lolcats.com");
            URL u = new URL("http://ecampus.konkuk.ac.kr");

            in = u.openStream();
            int c;

            while((c=in.read())!=-1) {
                System.out.write(c);
            }

        } catch (MalformedURLException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }finally {
            try{
                if(in!=null){
                    in.close();
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
