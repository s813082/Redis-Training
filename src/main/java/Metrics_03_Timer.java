import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;

import java.util.concurrent.TimeUnit;

public class Metrics_03_Timer {

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


    }
}
