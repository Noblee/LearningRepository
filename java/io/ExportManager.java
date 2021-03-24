package io;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class ExportManager {


    public static final ExecutorService e = Executors.newFixedThreadPool(100, r -> {
        Thread t = new Thread(r);
        t.setDaemon(true);
        return t;
    });

    final static int uidSize = 333;

    public Data exportCorcurrentDemo(List<Long> cids, boolean isCor) {

        System.out.println("begin export concurrent");

        ExecutorService executorService = Executors.newSingleThreadExecutor(r -> {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        });
        List<CompletableFuture<Data>> tasks = cids.stream()
                .map(cid -> CompletableFuture.supplyAsync(() -> {
                    return exportSubTask(cid, isCor, uidSize);
                }, executorService))
                .collect(Collectors.toList());

        List<Data> dataList = tasks.stream().map(CompletableFuture::join).collect(Collectors.toList());
        return new Data(123L);
    }

    public Data exportCorcurrentDemo2(List<Long> cids, boolean isCor) {

        System.out.println("begin export corcurrent 2");

        ExecutorService executorService = Executors.newSingleThreadExecutor(r -> {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        });
        List<CompletableFuture<Data>> tasks = cids.stream()
                .map(cid -> CompletableFuture.supplyAsync(() -> {
                    return exportSubTask(cid, isCor, uidSize);
                }, executorService))
                .collect(Collectors.toList());

        List<Data> dataList = tasks.stream().map(CompletableFuture::join).collect(Collectors.toList());
        return new Data(123L);
    }

    public Data exportCorcurrent(List<Long> cids, boolean isCor) {


        System.out.println("begin export concurrent");

        List<CompletableFuture<Data>> tasks = cids.stream()
                .map(cid -> CompletableFuture.supplyAsync(() -> {
                    return exportSubTask(cid, isCor, uidSize);
                }, e))
                .collect(Collectors.toList());
        List<Data> dataList = tasks.stream().map(CompletableFuture::join).collect(Collectors.toList());
        return new Data(123L);
    }

    public Data exportSerialize(List<Long> cids) {


        System.out.println("begin export serialize");
        List<Data> collect = cids.stream().map(cid -> exportSubTask(cid, false, uidSize)).collect(Collectors.toList());

        return new Data(456L);
    }

    public Data exportSubTask(Long cid, boolean isCor, int uidSize) {
        Thread thread = Thread.currentThread();
        System.out.println(thread + "  cid : " + cid);
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println(e);
        }
        List<Long> uids = getUids(uidSize);

        if (isCor) {
            long startTime = System.currentTimeMillis();
            System.out.println("exportSubTask is concurrent ");
            mockName(uids);
            long endTime = System.currentTimeMillis();
            System.out.println("exportSubTask time : " + (endTime - startTime));

            mockDepart(uids);

            mockNum(uids);

            mockName(uids);

            mockDepart(uids);

            mockNum(uids);

            mockName(uids);

            mockDepart(uids);


        } else {
            System.out.println("exportSubTask is serize ");

            mockNameSerize(uids);

            mockDepartSerize(uids);

            mockNumSerize(uids);


            mockNameSerize(uids);

            mockDepartSerize(uids);

            mockNumSerize(uids);


            mockNameSerize(uids);

            mockDepartSerize(uids);

        }

        return new Data(cid);
    }

    private void mockNumSerize(List<Long> uids) {
        helper(uids);
    }

    private void mockDepartSerize(List<Long> uids) {
        helper(uids);
    }

    private void mockNameSerize(List<Long> uids) {
        helper(uids);
    }

    public void helper(List<Long> uids) {
        uids.forEach(v -> {
            helper(v + "");
        });
    }


    private List<Long> getUids(int size) {
        List<Long> uids = new ArrayList<>();
        for (long i = 0L; i < size; i++) {
            uids.add(i);
        }
        return uids;
    }

    private void mockNum(List<Long> uids) {

        List<CompletableFuture<Integer>> mockNum = uids.stream().map(uid ->
                CompletableFuture.supplyAsync(() -> {
                    return helper("mockNum");
                }, e)
        ).collect(Collectors.toList());
        List<Integer> collect = mockNum.stream().map(CompletableFuture::join).collect(Collectors.toList());

    }

    private Integer helper(String s) {
        try {
//            System.out.println("method: " + s + " " + Thread.currentThread());
            Thread.sleep(5);
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    private void mockDepart(List<Long> uids) {
        List<CompletableFuture<Integer>> mockNum = uids.stream().map(uid ->
                CompletableFuture.supplyAsync(() -> {
                    return helper("mockDepart");
                }, e)
        ).collect(Collectors.toList());
        List<Integer> collect = mockNum.stream().map(CompletableFuture::join).collect(Collectors.toList());

    }

    private void mockName(List<Long> uids) {
        List<CompletableFuture<Integer>> mockNum = uids.stream().map(uid ->
                CompletableFuture.supplyAsync(() -> {
                    return helper("mockName");
                }, e)
        ).collect(Collectors.toList());
        List<Integer> collect = mockNum.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }
}
