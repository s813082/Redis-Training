public class Metrics_02_Meter {

    public static void main(String[] args) {

        // *** 執行業務邏輯的coding block <<START>> *** //
        System.out.println("===== Coding Block <<S>> ======");
        long start = System.nanoTime(); // 整個coding blocking的計時開始
        long operations_count = Integer.MAX_VALUE; // 設定要業務邏輯要執行的次數

        // ==> Coding Here
        long COUNTER_CODING_BLOCK = 0;

        //main code
        for(int i=0; i< operations_count; i++){
            // => do something --- start
            COUNTER_CODING_BLOCK++;
            // => do something --- end
        }


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
