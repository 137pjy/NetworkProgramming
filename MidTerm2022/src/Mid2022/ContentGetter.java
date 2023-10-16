package Mid2022;

import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ContentGetter {
    private static ConcurrentHashMap<Integer, Integer> numberCount = new ConcurrentHashMap<>();


    public static void main(String[] args) throws IOException {
        URL u = null;
        InputStream is = null;
        FileInputStream fis = null;
        InputStreamReader isr = null;
        OutputStreamWriter osw = null;
        BufferedInputStream bis=null;

        int fileCnt = 0;
        int missingFileCnt=0;



//            u = new URL("https://www.gutenberg.org/files/11/11-0.txt");
//            Object o = u.getContent();
//            is=(InputStream) o;
//            InputStreamReader isr =new InputStreamReader(is);

        long start= System.nanoTime();
        for (int i = 1; i <= 50; i++) {
            for (int j = 1; j <= 50; j++) {
                boolean fileExist=false;
                for (int k = 1; k <= 4; k++) {
                    String name;
                    if (k == 1) {
                        name = "file (c=" + i + ")_" + "(d=" + j + ").txt";
                    } else if (k == 2) {
                        name = "file (c=" + i + ")_" + "<d=" + j + ">.txt";
                    } else if (k == 3) {
                        name = "file <c=" + i + ">_" + "(d=" + j + ").txt";
                    } else {
                        name = "file <c=" + i + ">_" + "<d=" + j + ">.txt";
                    }


                    try {


                        String PackagePath = "/Users/jiyeon/IdeaProjects/MidTerm2022/src/Archive/";

                        fis = new FileInputStream(PackagePath + name);
                        bis = new BufferedInputStream(fis);
                        isr = new InputStreamReader(bis);
//                        System.out.println(name + " 파일 찾음");
                        fileExist=true;
                        fileCnt++;


                        //메소드 호출
                        FrequencyTask ft=new FrequencyTask(isr, fis, bis);
                        ExecutorService service = Executors.newFixedThreadPool(2);


                        char[] buffer = new char[512];
                        List<Integer> numbers = new ArrayList<Integer>();
                        StringBuilder numBuffer = new StringBuilder();

                        int readcount = 0;
                        while ((readcount = isr.read(buffer)) != -1) {

                            char[] slicedBuffer = Arrays.copyOfRange(buffer, 0, readcount);
                            for (char c : slicedBuffer) {
                                if (Character.isDigit(c)) {
                                    numBuffer.append(c);
                                } else if (numBuffer.length() > 0) {
                                    int number = Integer.parseInt(numBuffer.toString());
                                    numbers.add(number);
                                    numBuffer.setLength(0); // Reset the buffer
                                }
                            }


                        }

                        // 리스트를 순회하면서 각 숫자의 개수를 세기
                        for (Integer number : numbers) {
                            if (numberCount.containsKey(number)) {
                                // 이미 해당 숫자가 맵에 있을 경우, 개수를 증가
                                numberCount.put(number, numberCount.get(number) + 1);
                            } else {
                                // 해당 숫자가 맵에 없을 경우, 새로운 항목을 추가
                                numberCount.put(number, 1);
                            }
                        }

                        try {
                            isr.close();
                            fis.close();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }


                    } catch (FileNotFoundException e) {
                        // 파일을 찾을 수 없을 때 처리할 코드를 여기에 추가
                        continue;

                    }


                }
                if(!fileExist)
                    missingFileCnt++;
            }


        }




        int maxKey = -1; // 가장 큰 값을 가진 키를 저장할 변수
        int maxValue = Integer.MIN_VALUE; // 현재까지의 최대 값을 저장할 변수

        int minKey = -1; // 가장 적게 등장한 키를 저장할 변수
        int minValue = Integer.MAX_VALUE; // 현재까지의 최소 값을 저장할 변수

        for (Map.Entry<Integer, Integer> entry : numberCount.entrySet()) {
            int key = entry.getKey();
            int value = entry.getValue();

            if (value > maxValue) {
                maxKey = key;
                maxValue = value;
            }

            if (value < minValue) {
                minKey = key;
                minValue = value;
            }
        }

        if (minKey != -1) {
            System.out.println("가장 적게 등장한 키: " + minKey);
            System.out.println("가장 적게 등장한 값: " + minValue);
        } else {
            System.out.println("맵이 비어있습니다.");
        }

        if (maxKey != -1) {
            System.out.println("가장 큰 값을 가진 키: " + maxKey);
            System.out.println("가장 큰 값: " + maxValue);
        } else {
            System.out.println("맵이 비어있습니다.");
        }

        System.out.println("missingFileCnt :"+ (2500-fileCnt));
        System.out.println("missingFileCnt : "+missingFileCnt);

        long end= System.nanoTime();
        System.out.println("elapsedTime:"+(end-start)/1000.0);
    }

}