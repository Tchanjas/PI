package BBP;

import Utilities.SharedBigDecimal;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.concurrent.atomic.AtomicInteger;

public class BBP_Parallel extends Thread {

    MathContext context;
    static AtomicInteger iterations = new AtomicInteger(1000);

    static SharedBigDecimal result = new SharedBigDecimal(0.0);
    static AtomicInteger next = new AtomicInteger(0);

    BigDecimal one = BigDecimal.valueOf(1.0);
    BigDecimal two = BigDecimal.valueOf(2.0);
    BigDecimal four = BigDecimal.valueOf(4.0);
    BigDecimal five = BigDecimal.valueOf(5.0);
    BigDecimal six = BigDecimal.valueOf(6.0);
    BigDecimal eigth = BigDecimal.valueOf(8.0);
    BigDecimal sixteen = BigDecimal.valueOf(16.0);

    public BBP_Parallel() {
        this.context = new MathContext(1000);
    }

    @Override
    public void run() {
        int k;
        while (next.get() < iterations.get()) {
            k = next.getAndIncrement();
           
            BigDecimal denomParc = eigth.multiply(BigDecimal.valueOf(k), context);

            BigDecimal first = one.divide(sixteen.pow(k), context);
            BigDecimal second = four.divide(denomParc.add(one), context);
            BigDecimal third = two.divide(denomParc.add(four), context);
            BigDecimal fourth = one.divide(denomParc.add(five), context);
            BigDecimal fifth = one.divide(denomParc.add(six), context);

            second = second.subtract(third).subtract(fourth).subtract(fifth);
            first = first.multiply(second, context);
            result.add(first);
        }
    }

    public BigDecimal calcPi() {
        int procs = Runtime.getRuntime().availableProcessors();
        BBP_Parallel[] arrThr = new BBP_Parallel[procs];
        for (int i = 0; i < arrThr.length; i++) {
            arrThr[i] = new BBP_Parallel();
            arrThr[i].start();
        }

        for (int i = 0; i < arrThr.length; i++) {
            try {
                arrThr[i].join();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        return result.getValue();
    }
}
