import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Metrics_04_Histogram {

    //計算一段代碼被調用的速率和其持續時間的分佈

    //產生一個MetricRegistry 的 instance
    private static final MetricRegistry metrics = new MetricRegistry();

    //註冊一個meter的metric
    private static  final Timer ops_timer = metrics.timer("com.wistron.witlab.ops_timer");

    public static void main(String[] args) throws InterruptedException {

        //產生一個consolereporter的instance來定的
        ConsoleReporter reporter = ConsoleReporter.forRegistry(metrics)
                .convertDurationsTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .build();

        //讓console Report 每五秒打印一次統計結果
        reporter.start(5, TimeUnit.SECONDS);

        Random random = new Random();

        // *** 執行業務邏輯的coding block <<START>> *** //
        System.out.println("===== Coding Block <<S>> ======");
        long start = System.nanoTime(); // 整個coding blocking的計時開始
        long operations_count = Integer.MAX_VALUE; // 設定要業務邏輯要執行的次數

        // ==> Coding Here
        long COUNTER_CODING_BLOCK = 0;

        //main code
        for(int i=0; i< operations_count; i++){

            final Timer.Context context = ops_timer.time();//計時開始

            // => do something --- start
            COUNTER_CODING_BLOCK++;
            Thread.sleep(random.nextInt(100));
            // => do something --- end

            context.close();//計時結束
        }

        //1 minute rate 離打印時間最近的一分鐘的執行速度

        long end = System.nanoTime(); // 整個coding blocking的計時開始
        System.out.println("===== Coding Block <<E>> ======");
        // *** 執行業務邏輯的coding block <<END>> *** //

        // 打印整個coding blocking的所花費的時間(Duration()

        // 1 nano  second = 1 / 1,000,000,000 second (10億分之1秒)
        // 1 milli second = 1 /         1,000 second (1000分之1秒)

        System.out.println("Total operations  : " + operations_count + " ops");
        System.out.println("Total time spent  : " + (end-start) + " nanos || " + (end-start)/(float)1000000 + " millis || " + (end-start)/(float)1000000000 +"seconds");
        System.out.println("Average ratio     : " + (((end-start) / (float)operations_count)) * 1000000 + " ops/millis sec");

        /*
        *              count = 169
         mean rate = 16.13 calls/second   每秒平均
         1-minute rate = 14.47 calls/second 最近一分鐘平均
         5-minute rate = 14.26 calls/second
         15-minute rate = 14.22 calls/second
               min = 1.91 milliseconds 最小直
               max = 173.95 milliseconds 最大直
              mean = 58.46 milliseconds 平均
            stddev = 32.65 milliseconds 標準差
            median = 55.44 milliseconds 中位數
              75% <= 85.79 milliseconds
              95% <= 109.82 milliseconds
              98% <= 123.31 milliseconds
              99% <= 135.01 milliseconds
            99.9% <= 173.95 milliseconds
        *
        *
        *
        *
        * */


    }
}
