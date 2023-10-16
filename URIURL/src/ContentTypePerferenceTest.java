import java.awt.image.ImageProducer;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;

public class ContentTypePerferenceTest {
    public static void main(String[] args) {
        URL u= null;
        try {
//            u = new URL("https://www.oreilly.com");
            u=new URL("http://home.konkuk.ac.kr/~leehw/Site/nptest/files/logo.png");
            Class<?>[] types=new Class[4];
            types[0]=String.class;
            types[1]= Reader.class;
            types[2]= InputStream.class;
            types[3]=ImageProducer.class;
            Object o = u.getContent(types);
            if(o == null){
                System.out.println("null object");
            }
            System.out.println("I got a "+o.getClass().getName());
            if(o instanceof String){
                System.out.println(o);
            }else if(o instanceof Reader){
                int c;
                Reader r=(Reader) o;
                while((c=r.read())!=-1) System.out.println((char)c);
                r.close();
            }else if(o instanceof InputStream){
                int c;
                InputStream in =(InputStream) o;
                System.out.println(u);
                while((c=in.read())!=-1) System.out.write(c);
                in.close();
            }else if(o instanceof ImageProducer){
                System.out.println("ImageProducer returned");
            }else {
                System.out.println("Error: unexpected type"+o.getClass());
            }


        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
