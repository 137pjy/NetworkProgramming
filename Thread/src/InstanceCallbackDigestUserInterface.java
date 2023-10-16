public class InstanceCallbackDigestUserInterface {
    private String filename;
    private byte[] digest;

    public InstanceCallbackDigestUserInterface(String filename) {
        this.filename = filename;
    }

    public void caculateDigest(){
        InstanceCallbackDigest cb = new InstanceCallbackDigest(filename, this);
        Thread t = new Thread(cb);
        t.start();
    }



    void receiveDigest(byte[] digest){
        this.digest=digest;
        System.out.println(this);
    }



    public static String toHexString(byte[] bytes){
        StringBuilder hexString = new StringBuilder();

        for(int i=0;i<bytes.length;i++){
            String hex = Integer.toHexString(0xFF&bytes[i]);
            if(hex.length()==1){
                hexString.append('0');//한자리수(0-15)면 0을 붙여줌
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    @Override
    public String toString() {
        String result = filename + ": ";
        if (digest != null) {
            result += toHexString(digest);
        } else {
            result += "digest not available";
        }
        return result; // this.toString()으로 수정
    }

    public static void main(String[] args) {
        String file1="/Users/jiyeon/IdeaProjects/Thread/src/test3.txt";
        String file2="/Users/jiyeon/IdeaProjects/Thread/src/test4.rtf";
        String file3="/Users/jiyeon/IdeaProjects/Thread/src/test5.txt";
        String[] filelist={file1,file2,file3};
        for(String filename:filelist){
            InstanceCallbackDigestUserInterface d = new InstanceCallbackDigestUserInterface(filename);
            d.caculateDigest();

        }
    }
}
