import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class EncoderTest {
    public static void main(String[] args) {
        try {
            System.out.println(URLEncoder.encode("This string has spaces","UTF-8"));
            System.out.println(URLEncoder.encode("This*string*has*asterisks","UTF-8"));
            System.out.println(URLEncoder.encode("This%string%has%percent%signs","UTF-8"));
            System.out.println(URLEncoder.encode("This+string+has+pluses","UTF-8"));//의도한 name이 +라면 %2B로 인코딩해서 보내야함
            System.out.println(URLEncoder.encode("This/string/has/slashes","UTF-8"));

        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
