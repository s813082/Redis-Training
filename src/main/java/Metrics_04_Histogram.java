import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Histogram;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Metrics_04_Histogram {

    //度量數據流中值的統計分佈。除了最小、最大、平均之外，他還測量中位數、75 90 95 98 99 99.9的百分位數

    //產生一個MetricRegistry 的 instance
    private static final MetricRegistry metrics = new MetricRegistry();

    //註冊一個meter的histogram
    private static  final Histogram ops_histogram = metrics.histogram("com.wistron.witlab.ops_histogram");

    public static void main(String[] args) throws InterruptedException {

        //產生一個consolereporter的instance來定的
        ConsoleReporter reporter = ConsoleReporter.forRegistry(metrics)
                .convertDurationsTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .build();

        //讓console Report 每五秒打印一次統計結果
        reporter.start(30, TimeUnit.SECONDS);

        double MEAN = 100.0f;
        double VARIANCE = 5.0f;

        // *** 執行業務邏輯的coding block <<START>> *** //
        System.out.println("===== Coding Block <<S>> ======");
        long start = System.nanoTime(); // 整個coding blocking的計時開始
        long operations_count = Integer.MAX_VALUE; // 設定要業務邏輯要執行的次數

        // ==> Coding Here
        long COUNTER_CODING_BLOCK = 0;

        //main code
        for(int i=0; i< operations_count; i++){



            // => do something --- start
            ops_histogram.update((int)getGaussian(MEAN,VARIANCE));
            // => do something --- end


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
        com.wistron.witlab.ops_histogram
             count = 359953363 總共撈了這麼多資料
               min = 84
               max = 116
              mean = 99.62 平均撈取資料量
            stddev = 5.11
            median = 100.00
              75% <= 103.00
              95% <= 108.00
              98% <= 110.00
              99% <= 111.00
            99.9% <= 116.00
        */
    }
    private static Random fRandom = new Random();

    private  static  double getGaussian(double aMean, double aVariance){
        return  aMean + fRandom.nextGaussian() * aVariance;
    }
}
