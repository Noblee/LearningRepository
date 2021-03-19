package io;

import java.util.ArrayList;
import java.util.List;

public class Main {


    public static void main(String[] args) {

        ExportManager exportManager = new ExportManager();


//        List<Long> cidList = Arrays.asList(123L, 456L, 789L);

//        Data data = exportManager.exportCorcurrentDemo(cidList, true);

        Main main = new Main();

        main.testCorcurrent(exportManager, generateCidList(45));

    }

    private static List<Long> generateCidList(int size) {
        List<Long> ans = new ArrayList<>();
        for(long i = 0L; i < size; i++){
            ans.add(i);
        }
        return ans;
    }

    public void testCorcurrent(ExportManager exportManager, List<Long> cidList){

//
//        long start2 = System.currentTimeMillis();
//        Data export2 = exportManager.exportSerialize(cidList);
//        long end2 = System.currentTimeMillis();
//        System.out.println("data : " + export2 + " time " + (end2 - start2));

//        System.out.println();
//
//        long start3 = System.currentTimeMillis();
//        Data export3 = exportManager.exportCorcurrent(cidList, false);
//        long end3 = System.currentTimeMillis();
//        System.out.println("data : " + export3 + " time " + (end3 - start3));


        System.out.println();

        long start = System.currentTimeMillis();
        Data export = exportManager.exportCorcurrent(cidList, true);
        long end = System.currentTimeMillis();
        System.out.println("data : " + export + " time " + (end - start));
    }
}
