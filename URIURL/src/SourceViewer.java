import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class SourceViewer {
    public static void main(String[] args) {

//        if(args.length>0){
            InputStream in=null;
            try {
                URL u= new URL("https://www.lolcats.com");
                in=u.openStream();
                in=new BufferedInputStream(in);
                Reader r = new InputStreamReader(in);
                int c;
                while((c=r.read())!=-1){
                    System.out.print((char) c);
                }
            } catch (MalformedURLException e) {
                System.err.println(args[0]+" is not a parseable URL");
            } catch (IOException e) {
                System.out.println(e);

            }finally{
                if(in!=null){
                    try{
                        in.close();
                    } catch (IOException e) {

                    }
                }
            }
        }

//    }
}
