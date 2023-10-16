package Mid2021;


import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class ThreadContentGetter2021 {
    private static HashMap<String, Object> TotalresultMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        TotalresultMap.put("MaxValue",0);
        TotalresultMap.put("MaxRow",0);
        TotalresultMap.put("MaxCol",0);

        URL u = null;
        InputStream is = null;
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br=null;

        int fileCnt = 0;
        int missingFileCnt=0;


//            u = new URL("https://www.gutenberg.org/files/11/11-0.txt");
//            Object o = u.getContent();
//            is=(InputStream) o;
//            InputStreamReader isr =new InputStreamReader(is);

        long start= System.nanoTime();
        List<FrequencyTask2021> frequencyTaskList = new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 1; i <= 50; i++) {
            for (int j = 1; j <= 50; j++) {
                boolean fileExist=false;
                System.out.println("i : "+i+"j : "+j);
                for (int k = 1; k <= 4; k++) {
//                    System.out.println("3중 for문 (k=1~4) : " + k);
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
//                        bis = new BufferedInputStream(fis);
                        isr = new InputStreamReader(fis);
                        br=new BufferedReader(isr);
                        System.out.println(name + " 파일 찾음");
                        fileExist=true;
                        fileCnt++;

                        //메소드 호출
                        frequencyTaskList.add(new FrequencyTask2021(fis,isr,br,name));





                    } catch (FileNotFoundException e) {
                        // 파일을 찾을 수 없을 때 처리할 코드를 여기에 추가
                        continue;

                    }


                }
                if(!fileExist)
                    missingFileCnt++;
            }


        }

        List<Future<HashMap>> futures=null;
        try {
            //작업 실행
            futures = executorService.invokeAll(frequencyTaskList);
            //작업 종료
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        for(Future<HashMap> future:futures){

            HashMap<String,Object> taskMap= null;

            try {
                taskMap = future.get();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }



            // 리스트를 순회하면서 각 숫자의 개수를 세기
            synchronized (TotalresultMap) {
                if(TotalresultMap.containsKey("MaxValue")) {
                    if ((Integer)taskMap.get("MaxValue") > (Integer)TotalresultMap.get("MaxValue")) {
                        TotalresultMap.put("MaxValue", taskMap.get("MaxValue"));
                        TotalresultMap.put("MaxRow", taskMap.get("MaxRow"));
                        TotalresultMap.put("MaxCol", taskMap.get("MaxCol"));
                        TotalresultMap.put("FileName", taskMap.get("FileName"));
                    }
                }else{
                    TotalresultMap.putAll(taskMap);
                }

                if(TotalresultMap.containsKey("MinValue")) {
                    if ((Integer)taskMap.get("MinValue") < (Integer)TotalresultMap.get("MinValue")) {
                        TotalresultMap.put("MinValue", taskMap.get("MinValue"));
                        TotalresultMap.put("MinRow", taskMap.get("MinRow"));
                        TotalresultMap.put("MinCol", taskMap.get("MinCol"));
                        TotalresultMap.put("FileName", taskMap.get("FileName"));
                    }
                }else{
                    TotalresultMap.putAll(taskMap);
                }
            }
        }


        // Print the MaxValue, MaxRow, and MaxCol in TotalResultMap
        System.out.println("MaxValue in TotalresultMap: " + TotalresultMap.get("MaxValue"));
        System.out.println("MaxRow in TotalresultMap: " + TotalresultMap.get("MaxRow"));
        System.out.println("MaxCol in TotalresultMap: " + TotalresultMap.get("MaxCol"));
        System.out.println("FileName in TotalresultMap: " + TotalresultMap.get("FileName"));

        // Print the MinValue, MinRow, and MinCol in TotalResultMap
        System.out.println("MinValue in TotalresultMap: " + TotalresultMap.get("MinValue"));
        System.out.println("MinRow in TotalresultMap: " + TotalresultMap.get("MinRow"));
        System.out.println("MinCol in TotalresultMap: " + TotalresultMap.get("MinCol"));
        System.out.println("FileName in TotalresultMap: " + TotalresultMap.get("FileName"));


        System.out.println("fileCnt : "+fileCnt);
        System.out.println("missingFileCnt : 2500-fileCnt"+ (2500-fileCnt));
        System.out.println("missingFileCnt : "+missingFileCnt);

        long end= System.nanoTime();
        System.out.println("elapsedTime:"+(end-start)/1000.0);
    }
}
