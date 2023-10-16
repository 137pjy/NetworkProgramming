package Mid2022;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.Callable;

public class FrequencyTask implements Callable<HashMap> {
    InputStreamReader isr;
    FileInputStream fis;
    BufferedInputStream bis;
    HashMap<Integer, Integer> numberCount = new HashMap<>();
    public FrequencyTask(InputStreamReader isr, FileInputStream fis, BufferedInputStream bis) {
        this.isr=isr;
        this.fis=fis;
        this.bis=bis;
    }


    @Override
    public HashMap<Integer,Integer> call() throws Exception {
        char[] buffer = new char[512];
        List<Integer> numbers = new ArrayList<Integer>();
        StringBuilder numBuffer = new StringBuilder();

        int readcount = 0;
        while (true) {
            try {
                if (!((readcount = isr.read(buffer)) != -1)) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

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
        synchronized (numberCount) {
            for (Integer number : numbers) {
                if (numberCount.containsKey(number)) {
                    numberCount.put(number, numberCount.get(number) + 1);
                } else {
                    numberCount.put(number, 1);
                }
            }
        }


        bis.close();
        isr.close();
        fis.close();



        return numberCount;
    }
}
