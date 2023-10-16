package Mid2021;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

public class FrequencyTask2021 implements Callable<HashMap> {

    BufferedReader br;
    FileInputStream fis;
    InputStreamReader isr;
    String name;

    public FrequencyTask2021(FileInputStream fis, InputStreamReader isr, BufferedReader br, String name) {
        this.br=br;
        this.fis=fis;
        this.isr=isr;
        this.name=name;

    }

    @Override
    public HashMap<String,Object> call() throws Exception {

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
        Map<String, Object> resultMap = new HashMap<>();
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
//        Map<String, Integer> MinresultMap = new HashMap<>();
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
        resultMap.put("MinValue", min);
        resultMap.put("MinRow", minRow);
        resultMap.put("MinCol", minCol);
        resultMap.put("FileName", name);




        fis.close();
        br.close();
        isr.close();


        HashMap<String, Object> resultMap1 = (HashMap<String, Object>) resultMap;
        return resultMap1;
    }


}
