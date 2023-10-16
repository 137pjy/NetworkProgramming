package Mid2021;

import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ContentGetter2021 {
    private static  Map<String, Integer> TotalresultMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        TotalresultMap.put("MaxValue",0);
        TotalresultMap.put("MaxRow",0);
        TotalresultMap.put("MaxCol",0);

        URL u = null;
        InputStream is = null;
        FileInputStream fis = null;
        InputStreamReader isr = null;
        OutputStreamWriter osw = null;
        BufferedInputStream bis=null;
        BufferedReader br=null;

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



                        List<int[]> dataList = new ArrayList<>();
                        String line;
                        while ((line=br.readLine()) != null) {

                            String[] numbers=line.split(" ");
                            int[] row=new int[numbers.length];
                            for(int s=0;s<numbers.length;s++){
                                row[s]=Integer.parseInt(numbers[s]);
                            }
                            dataList.add(row);

                        }

                        // Convert ArrayList to 2D array
                        int[][] data = new int[dataList.size()][];
                        for (int kk = 0; kk < dataList.size(); kk++) {
                            data[kk] = dataList.get(kk);
                        }


                        // Find row, column, and max value using Map
                        Map<String, Integer> resultMap = new HashMap<>();
                        int max = Integer.MIN_VALUE;
                        int maxRow = 0;
                        int maxCol = 0;
                        for (int m = 0; m < data.length; m++) {
                            for (int n = 0; n < data[m].length; n++) {
                                if (data[m][n] > max) {
                                    max = data[m][n];
                                    maxRow = m;
                                    maxCol = n;
                                }
                            }
                        }
                        resultMap.put("MaxValue", max);
                        resultMap.put("MaxRow", maxRow);
                        resultMap.put("MaxCol", maxCol);


                        // Find row, column, and min value using Map
                        Map<String, Integer> MinresultMap = new HashMap<>();
                        int min = Integer.MAX_VALUE;
                        int minRow = 0;
                        int minCol = 0;
                        for (int m = 0; m < data.length; m++) {
                            for (int n = 0; n < data[m].length; n++) {
                                if (data[m][n] < min) {
                                    min = data[m][n];
                                    minRow = m;
                                    minCol = n;
                                }
                            }
                        }
                        MinresultMap.put("MinValue", min);
                        MinresultMap.put("MinRow", minRow);
                        MinresultMap.put("MinCol", minCol);

                        System.out.println("data.length : "+data.length);

                        //totalResultMap이랑 비교하기
                        if (TotalresultMap.containsKey("MaxValue")) {
                            if (resultMap.get("MaxValue") > TotalresultMap.get("MaxValue")) {
                                TotalresultMap.put("MaxValue", resultMap.get("MaxValue"));
                                TotalresultMap.put("MaxRow", resultMap.get("MaxRow"));
                                TotalresultMap.put("MaxCol", resultMap.get("MaxCol"));
                            }
                        }else {
                            TotalresultMap.putAll(resultMap);
                        }


                        //totalResultMap이랑 비교하기
                        if (TotalresultMap.containsKey("MinValue")) {
                            if (MinresultMap.get("MinValue") < TotalresultMap.get("MinValue")) {
                                TotalresultMap.put("MinValue", resultMap.get("MinValue"));
                                TotalresultMap.put("MinRow", resultMap.get("MinRow"));
                                TotalresultMap.put("MinCol", resultMap.get("MinCol"));
                            }
                        }else {
                            TotalresultMap.putAll(MinresultMap);
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

        // Print the MaxValue, MaxRow, and MaxCol in TotalResultMap
        System.out.println("MaxValue in TotalresultMap: " + TotalresultMap.get("MaxValue"));
        System.out.println("MaxRow in TotalresultMap: " + TotalresultMap.get("MaxRow"));
        System.out.println("MaxCol in TotalresultMap: " + TotalresultMap.get("MaxCol"));

        // Print the MinValue, MinRow, and MinCol in TotalResultMap
        System.out.println("MinValue in TotalresultMap: " + TotalresultMap.get("MinValue"));
        System.out.println("MinRow in TotalresultMap: " + TotalresultMap.get("MinRow"));
        System.out.println("MinCol in TotalresultMap: " + TotalresultMap.get("MinCol"));


        System.out.println("fileCnt : "+fileCnt);
        System.out.println("missingFileCnt : 2500-fileCnt"+ (2500-fileCnt));
        System.out.println("missingFileCnt : "+missingFileCnt);

        long end= System.nanoTime();
        System.out.println("elapsedTime:"+(end-start)/1000.0);
    }

}
