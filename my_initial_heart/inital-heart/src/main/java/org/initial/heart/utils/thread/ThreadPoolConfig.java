//package org.initial.heart.utils.thread;
//
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//import java.util.concurrent.*;
//
//public class ThreadPoolConfig {
//
//    static ThreadPoolExecutor threadPool;
//    private static int POOL_SIZE = 30;
//
//    static {
//        threadPool =
//                new ThreadPoolExecutor(20, 30, 40, TimeUnit.SECONDS, new ArrayBlockingQueue<>(POOL_SIZE));
//    }
//
//    public static List<MyVehiclesAnnualReview> futureTask(int recordListCount, MyVehiclesAnnualReviewBiz myVehiclesAnnualReviewBiz, Dto dto) {
//        Long start = System.currentTimeMillis();
//        //开启多线程
////        ExecutorService exs = Executors.newFixedThreadPool(10);
//        // 放弃拒绝的任务并抛出异常
//
//        //结果集
//        List<MyVehiclesAnnualReview> list = new ArrayList<>();
//        try {
//            List<Future<List<MyVehiclesAnnualReview>>> futureList = new ArrayList<Future<List<MyVehiclesAnnualReview>>>();
//
////            根据分的队列去查询条数
//            int index = (recordListCount + POOL_SIZE - 1) / POOL_SIZE;
//            for (int i = 0; i < recordListCount; i += index) {
//                int end = i + index;
//                if (end > recordListCount) {
//                    end = recordListCount;
//                }
//                dto.put("start", i);
//                dto.put("limit", end - i);
//
//                Dto dtoNew = Dtos.newDto(dto);
//                CallableTask task = new CallableTask(myVehiclesAnnualReviewBiz, dtoNew);
////                FutureTask futureTask = new FutureTask(task);
//                Future future = threadPool.submit(task);
//                futureList.add(future);
//            }
//            Long getResultStart = System.currentTimeMillis();
//            //2.结果归集，用迭代器遍历futureList,高速轮询（模拟实现了并发），任务完成就移除
//            while (futureList.size() > 0) {
//                Iterator<Future<List<MyVehiclesAnnualReview>>> iterable = futureList.iterator();
//                //遍历一遍
//                while (iterable.hasNext()) {
//                    Future<List<MyVehiclesAnnualReview>> future = iterable.next();
//                    //如果任务完成取结果，否则判断下一个任务是否完成
//                    if (future.isDone() && !future.isCancelled()) {
//                        //获取结果
//                        List<MyVehiclesAnnualReview> newList = future.get();
//                        list.addAll(newList);
//                        //任务完成移除任务
//                        iterable.remove();
//                    } else {
//                        Thread.sleep(1);//避免CPU高速运转，这里休息1毫秒，CPU纳秒级别
//                    }
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return list;
//    }
//
//    public static class CallableTask implements Callable<List<MyVehiclesAnnualReview>> {
//
//        Dto dto;
//        MyVehiclesAnnualReviewBiz myVehiclesAnnualReviewBiz;
//
//        public CallableTask(MyVehiclesAnnualReviewBiz myVehiclesAnnualReviewBiz, Dto dto) {
//            super();
//            this.dto = dto;
//            this.myVehiclesAnnualReviewBiz = myVehiclesAnnualReviewBiz;
//        }
//
//        @Override
//        public List<MyVehiclesAnnualReview> call() throws Exception {
//
//            List<MyVehiclesAnnualReview> voList = myVehiclesAnnualReviewBiz.getTaskList(dto);
//            return voList;
//        }
//    }
//}
//
//
