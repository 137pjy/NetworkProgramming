import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.Buffer;

public class DMoz {
    public static void main(String[] args) {
//        String target="";
//        for(int i=0;i<args.length;i++){
//            target+=args[i]+" ";
//        }
//        target = target.trim();

        QueryString query=new QueryString();
        query.add("p","Java I/O");

        try {
            /*URL u=new URL("https://search.yahoo.com/search?p=java&fr=yfp-t&fp=1&toggle=1&cop=mss&ei=UTF-8");*/
//            URL u=new URL("https://www.dmoz-odp.org/search?"+query);
            URL u=new URL("https://search.yahoo.com/search?"+query);
            try (InputStream in = new BufferedInputStream(u.openStream())){
                InputStreamReader theHTML = new InputStreamReader(in);
                int c;
                while((c=theHTML.read())!=-1){
                    System.out.print((char) c);
                }

            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
