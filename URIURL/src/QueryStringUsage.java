import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class QueryStringUsage {
    public static void main(String[] args) {
        QueryString qs = new QueryString();
        qs.add("hl","en");
        qs.add("as_q","Java");
        qs.add("as_epq","I/O");
        String url="https://www.google.com/search?"+qs;
        System.out.println(url);

        StringBuilder pathURL = new StringBuilder();
        try {
            pathURL.append(URLEncoder.encode("JAVA I/0","UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        System.out.println(pathURL);
    }
}
