package com.example.demo.excutor;

import com.google.common.util.concurrent.*;
import org.assertj.core.util.Lists;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ExcutorTestDemo {

}
class ExecutorDemo implements Executor{
    //它是一个接口，也可通过实现这个接口，重写内部的execute方法，直接run，在这之前，有代码则执行一个，在实现类调取时再去执行实现类里的一下，
    // 这样会内部方法先执行，实现类调取再后执行；或者直接run，直接实现类lamdb方式/实现类在调取时再实现runable运行
    @Override
    public void execute(Runnable command) {
//        System.out.println("先打印zxczxc");
        command.run();
    }

    public static void main(String[] args) {
      /*  new ExecutorDemo().execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("后打印zzzz");
            }
        });*/
        new ExecutorDemo().execute(()-> {System.out.println("实现run，Lanmdb方式直接运行");});
    }
}

class ExecutorServiceDemo{
    //Callable，对Runnable进行了扩展
    // * 对Callable的调用，可以有返回值
    public static void main(String[] args) throws Exception {
        Callable callable = new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                return "callAble返回值";
            }
        };
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("zxczxczxc");
            }
        };
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future submit = executorService.submit(callable);
        System.out.println(submit.get());//阻塞
        executorService.shutdown();
    }
}

class ThreadPoolExecutorTest{
    static class Task implements Runnable{
        private int num;
        public Task(int num){
            this.num = num;
        }
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+"  task-"+num);
            try {
                //阻塞当前线程
                System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public String toString() {
            return "Task{" +
                    "num=" + num +
                    '}';
        }
    }

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 4, 10,
                TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(4), Executors.defaultThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());
        for (int i = 0; i < 8; i++) {
            executor.execute(new Task(i));
        }

//        System.out.println("正在活动"+executor.getActiveCount());
//        System.out.println("已完成"+executor.getCompletedTaskCount());
        System.out.println("执行前队列 "+executor.getQueue());
        //最大线程数4个，阻塞队列长度为4，则最多存储8个，
        executor.execute(new Task(100));//根据拒绝策略，一旦队列慢了，则直接插入到当前队列前去执行该任务，后面在执行等待队列的任务
        //而一旦没有空余的线程时，则直接调用主线程执行
        System.out.println("执行后队列 "+executor.getQueue());
        executor.shutdown();
    }
}

class newFixedThreadPoolTest{

    public static void main(String[] args) throws InterruptedException {
        //从目前表象看，提交任务后，后台会进行并行的操作，但是若在执行过程中
        //shutdown会导致后续等待队列的线程去执行完成后才会真正关闭，而不是立刻关闭
        ExecutorService service = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            service.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        TimeUnit.MILLISECONDS.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName());
                }
            });
        }

        System.out.println(service);

        service.shutdownNow();
        System.out.println(service.isTerminated());
        System.out.println(service.isShutdown());
        System.out.println(service);

        TimeUnit.SECONDS.sleep(5);
        System.out.println(service.isTerminated());
        System.out.println(service.isShutdown());
        System.out.println(service);

    }
}

class FutureTaskDemo{
    //FutureTask内部实现runable和callable，可以使用有返回值得线程，且get时会阻塞直到取到数据
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Object> futureTask = new FutureTask<>(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                TimeUnit.MILLISECONDS.sleep(500);
                return 1111;
            }
        });
        FutureTask<Object> vFutureTask = new FutureTask<Object>(new Runnable() {
            @Override
            public void run() {
                System.out.println("asdasd");
            }
        }, Object.class);

//        new Thread(vFutureTask).start();
        new Thread(futureTask).start();
        System.out.println(futureTask.get());
    }
}

class testOne{
    //假设能够提供一个服务
    //这个服务查询各大电商网站同一类产品的价格并汇总展示
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start, end;

        start = System.currentTimeMillis();

        priceOfTM();
        priceOfTB();
        priceOfJD();

        end = System.currentTimeMillis();
        System.out.println("use serial method call! " + (end - start));
        Double aDouble = CompletableFuture.supplyAsync(() -> priceOfTM()).get();//单独提交执行某个单一方法

        CompletableFuture futureTM = CompletableFuture.supplyAsync(()->priceOfTM());
        CompletableFuture futureTB = CompletableFuture.supplyAsync(()->priceOfTB());
        CompletableFuture futureJD = CompletableFuture.supplyAsync(()->priceOfJD());
        //invokeAll最后会返回该invokeAll中所有集合线程执行的各个结果
//        List<Future<Object>> futures = new ThreadPoolExecutor().invokeAll();
//        List<Future<Object>> futures = Executors.newFixedThreadPool(3).invokeAll();
        //保证执行但无返回值
        CompletableFuture.allOf(futureTM, futureTB, futureJD).toCompletableFuture().join();

        //可以进行后续操作，获取操作后的数据
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.supplyAsync(() -> priceOfTM())
//                .thenApply(sr->{priceOfTB(); return "sss";})  //最后数据会变更为sss
                .thenApply(String::valueOf)
                .thenApply(str -> "price " + str)
                .thenAccept(System.out::println);

        System.out.println("======"+voidCompletableFuture.get());
        end = System.currentTimeMillis();
        System.out.println("use completable future! " + (end - start));

        try {
            //进行阻塞，观察异步线程,也可不阻塞，直接返回CompletableFuture<Void>查看返回值
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private static double priceOfTM() {
        delay();
        return 1.00;
    }

    private static double priceOfTB() {
        delay();
        return 2.00;
    }

    private static double priceOfJD() {
        delay();
        return 3.00;
    }

    /*private static double priceOfAmazon() {
        delay();
        throw new RuntimeException("product not exist!");
    }*/

    private static void delay() {
        int time = new Random().nextInt(500);
        try {
            TimeUnit.MILLISECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("After %s sleep!\n", time);
    }
}

class newSingleThreadExecutorTest{
    public static void main(String[] args) {
        ExecutorService service = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++) {
            final int j = i;
            service.execute(()-> System.out.println(Thread.currentThread().getName()+"=="+j));
        }
    }
}

class newCachedThreadPoolTest{
    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            final int j = i;
            service.execute(()-> System.out.println(Thread.currentThread().getName()+"=="+j));
        }
    }
}


class newFixedThreadPoolDemo{
    //多线程并行执行方式同比一个线程要高效很多
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        getPrime(1, 200000);
        long end = System.currentTimeMillis();
        System.out.println("单个线程数据计算时间："+(end - start));

        final int cpuCoreNum = 4;

        ExecutorService service = Executors.newFixedThreadPool(cpuCoreNum);
//        ExecutorService service = Executors.newWorkStealingPool(cpuCoreNum);

        MyTask t1 = new MyTask(1, 80000); //1-5 5-10 10-15 15-20
        MyTask t2 = new MyTask(80001, 130000);
        MyTask t3 = new MyTask(130001, 170000);
        MyTask t4 = new MyTask(170001, 200000);

//        Future<List<Integer>> f1 = service.submit(t1);
//        Future<List<Integer>> f2 = service.submit(t2);
//        Future<List<Integer>> f3 = service.submit(t3);
//        Future<List<Integer>> f4 = service.submit(t4);
        ArrayList<MyTask> list = Lists.newArrayList();
        list.add(t1);list.add(t2);list.add(t3);list.add(t4);

        start = System.currentTimeMillis();
        List<Future<List<Integer>>> futures = service.invokeAll(list);
        for (Future<List<Integer>> future : futures) {
            future.get();
        }
//        f1.get();
//        f2.get();
//        f3.get();
//        f4.get();
        end = System.currentTimeMillis();
        System.out.println("多个线程并行计算："+(end - start));
    }

    static class MyTask implements Callable<List<Integer>> {
        int startPos, endPos;

        MyTask(int s, int e) {
            this.startPos = s;
            this.endPos = e;
        }

        @Override
        public List<Integer> call() throws Exception {
            System.out.println("线程"+Thread.currentThread().getName());
            List<Integer> r = getPrime(startPos, endPos);
            return r;
        }

    }

    static boolean isPrime(int num) {
        for(int i=2; i<=num/2; i++) {
            if(num % i == 0) return false;
        }
        return true;
    }

    static List<Integer> getPrime(int start, int end) {
        List<Integer> results = new ArrayList<>();
        for(int i=start; i<=end; i++) {
            if(isPrime(i)) results.add(i);
        }

        return results;
    }
}

class newScheduledThreadPoolTest{
    //定时线程
    public static void main(String[] args) {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(4);
        service.scheduleAtFixedRate(()->{
            try {
                TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
        }, 0, 500, TimeUnit.MILLISECONDS);
    }
}

class newWorkStealingPoolTest{
    //forkJoinPool的分叉方式去使用
    public static void main(String[] args) throws IOException {
        ExecutorService service = Executors.newWorkStealingPool();
        //获取系统cpu硬件支持的线程资源
        System.out.println(Runtime.getRuntime().availableProcessors());
        service.execute(new R(1000));
        service.execute(new R(2000));
        service.execute(new R(2000));
        service.execute(new R(2000)); //daemon
        service.execute(new R(2000));

        //由于产生的是精灵线程（守护线程、后台线程），主线程不阻塞的话，看不到输出
        System.in.read();
    }
    static class R implements Runnable {
        int time;
        R(int t) {
            this.time = t;
        }
        @Override
        public void run() {
            try {
                TimeUnit.MILLISECONDS.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(time  + " " + Thread.currentThread().getName());
        }
    }
}

class ForkJoinPoolTest{
    static int[] nums = new int[1000000];
    static final int MAX_NUM = 50000;
    static Random r = new Random();
    static {
        for(int i=0; i<nums.length; i++) {
            nums[i] = r.nextInt(100);
        }

        System.out.println("---" + Arrays.stream(nums).sum()); //stream api
    }
    static class MyAction extends RecursiveAction{
        //无返回值方式
        int start, end;
        MyAction(int s, int e) {
            start = s;
            end = e;
        }
        @Override
        protected void compute() {
            if(end-start <= MAX_NUM) {
                long sum = 0L;
                for(int i=start; i<end; i++) sum += nums[i];
                System.out.println("from:" + start + " to:" + end + " = " + sum);
            } else {

                int middle = start + (end-start)/2;

                MyAction subTask1 = new MyAction(start, middle);
                MyAction subTask2 = new MyAction(middle, end);
                subTask1.fork();
                subTask2.fork();
            }

        }
    }
    static class MyTask extends RecursiveTask<Long>{
        int start, end;

        MyTask(int s, int e) {
            start = s;
            end = e;
        }
        //有返回值的方式
        @Override
        protected Long compute() {
            if(end-start <= MAX_NUM) {
                long sum = 0L;
                for(int i=start; i<end; i++) sum += nums[i];
                return sum;
            }

            int middle = start + (end-start)/2;

            MyTask subTask1 = new MyTask(start, middle);
            MyTask subTask2 = new MyTask(middle, end);
            subTask1.fork();
            subTask2.fork();
            //join确保执行完成后拿到结果，汇总返回
            return subTask1.join() + subTask2.join();
        }
    }


    public static void main(String[] args) throws IOException {
      /*  ForkJoinPool fjp = new ForkJoinPool();
        MyAction task = new MyAction(0, nums.length);
		fjp.execute(task);
*/
        ForkJoinPool fjp = new ForkJoinPool();
        MyTask task = new MyTask(0, nums.length);
        fjp.execute(task);
        long result = task.join();
        System.out.println(result);

        System.in.read();
    }
}

class MyRejectedHandler {
    //自定义拒绝策略
    static class myRejected implements RejectedExecutionHandler{

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            //log("r rejected")
            //save r kafka mysql redis
            //try 3 times
            if(executor.getQueue().size() < 10000) {
                //try put again();
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService service = new ThreadPoolExecutor(4, 4,
                0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(6),
                Executors.defaultThreadFactory(),
                new myRejected());
    }
}

class FutureDemo{
    /*
     * 使用future进行异步编程
     * 缺点：
     * 不知道何时结束
     * 阻塞获取结果
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();
        Future<Integer> future = service.submit(() -> {
            return 111;
        });
        future.get();
    }
}

class ListenableFutureDemo{
    //不好维护
    public static void main(String[] args) {
        ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator( Executors.newCachedThreadPool());
        ListenableFuture<Integer> future = listeningExecutorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return 1111;
            }
        });
        Futures.addCallback(future,new FutureCallback<Integer>() {
            @Override
            public void onSuccess(Integer v) {
                System.out.println("提交"+v);
            }

            @Override
            public void onFailure(Throwable throwable) {
                throwable.printStackTrace();
            }
        });
      /*  Futures.addCallback(future, new FutureCallback<Integer>() {
            @Override
            public void onSuccess(Integer v) {
                System.out.println("提交"+v);
            }

            @Override
            public void onFailure(Throwable throwable) {
                throwable.printStackTrace();
            }
        },listeningExecutorService);*/
        listeningExecutorService.shutdown();
    }
}

