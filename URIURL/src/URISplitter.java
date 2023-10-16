import java.net.URI;
import java.net.URISyntaxException;

public class URISplitter {
    public static void main(String[] args) {
        String[] URIarr= new String[2];
//        URIarr[0]="ftp://mp3:mp3@138.247.121.61:21000/c%3a/";
        URIarr[0]="a:hhhhh";

//        URIarr[0]="jdbc:mysql://localhost:3306/database";
//        URIarr[0]="https://user:passwd@cdn.oreillystatic.com:80/oreilly/images/odot?q=xx#frag";


//        URIarr[1]="https://oreilly.com";
        URIarr[1]="http:):((((:0";

        //user info란?
        //http://username:password@konkuk.ac.kr

        for(int i=0;i<URIarr.length;i++){
            try {
                URI u=new URI(URIarr[i]);
                System.out.println("The URI is "+u);
                if(u.isOpaque()){
                    System.out.println("This is an opaque URI.");
                    System.out.println("The scheme is "+u.getScheme());
                    System.out.println("The scheme specific part is "+u.getSchemeSpecificPart());
                    System.out.println("The fragment ID is "+u.getFragment());
                }else{
                    System.out.println("This is a hierarchical URI");
                    System.out.println("The scheme is "+u.getScheme());

                }
                try{
                    u=u.parseServerAuthority();
                    System.out.println("The host is "+u.getHost());
                    System.out.println("The user info is "+u.getUserInfo());
                    System.out.println("The port is "+u.getPort());
                } catch (URISyntaxException e) {
                    System.out.println("The authority is "+u.getAuthority());
                }
                System.out.println("The path is "+u.getPath());
                System.out.println("The query string is "+u.getQuery());
                System.out.println("The fragment ID is "+u.getFragment());
            } catch (URISyntaxException e) {
               System.err.println(URIarr[i]+"does not seem to be a URI.");
            }
            System.out.println();
        }
    }
}
