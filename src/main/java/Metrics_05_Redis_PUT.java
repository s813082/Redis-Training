import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import redis.clients.jedis.Jedis;

import java.util.concurrent.TimeUnit;

public class Metrics_05_Redis_PUT {



    //產生一個MetricRegistry 的 instance
    private static final MetricRegistry metrics = new MetricRegistry();

    //註冊一個meter的metric
    private static  final Meter ops_meter = metrics.meter("com.wistron.witlab.redis_put");

    public static void main(String[] args) throws InterruptedException {

        //產生一個consolereporter的instance來定的
        ConsoleReporter reporter = ConsoleReporter.forRegistry(metrics)
                .convertDurationsTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .build();

        //讓console Report 每五秒打印一次統計結果
        reporter.start(5, TimeUnit.SECONDS);

        Jedis jedis = new Jedis("localhost");


        // *** 執行業務邏輯的coding block <<START>> *** //
        System.out.println("===== Coding Block <<S>> ======");
        long start = System.nanoTime(); // 整個coding blocking的計時開始
        long operations_count = 1000000; // 設定要業務邏輯要執行的次數

        // ==> Coding Here
        long COUNTER_CODING_BLOCK = 0;

        //main code
        for(int i=0; i< operations_count; i++){
            jedis.set(""+i,""+i);
            // => do something --- start
            // => do something --- end

            ops_meter.mark(); //紀錄備執行一次
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
