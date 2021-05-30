package org.trent.jcamp.concurrece;

import java.util.concurrent.*;

public class GetRet {

    static volatile int result;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /*
        使用runnable通过类变量获取返回值
         */
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                result = sum();
            }
        });
        t1.start();

        /*
        通过FixedThreadPool来执行Callable
         */
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<Integer> sumFuture = executorService.submit(GetRet::sum);
        System.out.println("get result " + sumFuture.get() + " from fixed thread pool ");
        /*
        通过自定义线程池来执行Callable
         */
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3,5,1000,TimeUnit.MILLISECONDS,new ArrayBlockingQueue<>(5));
        Future<Integer> sumFuture1 = threadPoolExecutor.submit(GetRet::sum);
        System.out.println("get result " + sumFuture1.get() + " from custom thread pool");

        System.out.println("get result " + result +" from thread " + t1.getName());
    }


    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if ( a < 2)
            return 1;
        return fibo(a-1) + fibo(a-2);
    }

}
