package ThreadScheduling;

import java.io.*;
import java.util.zip.GZIPOutputStream;

public class GZipRunnable implements Runnable{
    public final File input;

    public GZipRunnable(File input) {
        this.input = input;
    }

    @Override
    public void run() {
        if(!input.getName().endsWith(".gz")){
            File output = new File(input.getParent(),input.getName()+".gz");
            if(!output.exists()){
                try{
                    InputStream in = new BufferedInputStream(new FileInputStream(input));
                    OutputStream out = new BufferedOutputStream(new GZIPOutputStream(new FileOutputStream(output)));
                    {
                        int b;
                        while((b=in.read())!=-1) out.write(b);
                        out.flush();
                    }
                } catch (FileNotFoundException e) {
                    System.out.println(e);
                } catch (IOException e) {
                    System.out.println(e);
                }
            }
        }
    }
}
