import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class SourceViewewrSimpleTest {
    public static void main(String[] args) {
        InputStream in = null;

        try {
//            URL u = new URL("http://www.lolcats.com/images/logo.png");
            URL u = new URL("http://ecampus.konkuk.ac.kr/ilos/main/main_form.acl");
            in=u.openStream();
            int c;
            while((c=in.read())!=-1) System.out.write(c);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            if(in!=null){
                try {
                    in.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }
}
