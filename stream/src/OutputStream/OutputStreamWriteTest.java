package OutputStream;

public class OutputStreamWriteTest {
    public static void main(String[] args) {
        // Press Opt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.


        for(int i=33;i<300;i++){
            System.out.print("write: ");
            System.out.write(i); //아스키코드 출력
            System.out.println();
            System.out.println("println: "+i);
            System.out.flush();
        }
    }
}
