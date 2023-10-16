public interface CallbackDigestUserInterFace {
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

    public static void receieveDigest(byte[] digest, String name){
        StringBuilder result = new StringBuilder(name);
        result.append(":" );
        result.append(toHexString(digest));
        System.out.println(result);
    }

    public static void main(String[] args) {

        String file1="/Users/jiyeon/IdeaProjects/Thread/src/test3.txt";
        String file2="/Users/jiyeon/IdeaProjects/Thread/src/test4.rtf";
        String file3="/Users/jiyeon/IdeaProjects/Thread/src/test5.txt";

        //multiThread
        long start = System.nanoTime();


        String[] fileList = {file1,file2,file3};
        for(String filename:fileList){
            CallbackDigest cb=new CallbackDigest(filename);
            Thread t=new Thread(cb);
            t.start();
        }
    }
}
