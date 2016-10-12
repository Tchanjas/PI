package BBP;

import Utilities.SharedBigDecimal;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.concurrent.atomic.AtomicInteger;

public class BBP_Parallel extends Thread {

    static MathContext context;
    static AtomicInteger iterations;

    static SharedBigDecimal result = new SharedBigDecimal(0.0);
    static AtomicInteger next = new AtomicInteger(0); // the current iteration
    
    // Constants of the formula
    BigDecimal ONE = BigDecimal.valueOf(1.0);
    BigDecimal TWO = BigDecimal.valueOf(2.0);
    BigDecimal FOUR = BigDecimal.valueOf(4.0);
    BigDecimal FIVE = BigDecimal.valueOf(5.0);
    BigDecimal SIX = BigDecimal.valueOf(6.0);
    BigDecimal EIGTH = BigDecimal.valueOf(8.0);
    BigDecimal SIXTEEN = BigDecimal.valueOf(16.0);
    
    // Calculate the sum of the formula
    @Override
    public void run() {
        // while the next iteration != iterations
        //  get the next iteration, increment it and do the sum of the algorithm
        int k;
        while (next.get() < iterations.get()) {
            k = next.getAndIncrement();
           
            BigDecimal denomParc = EIGTH.multiply(BigDecimal.valueOf(k), context);

            BigDecimal first = ONE.divide(SIXTEEN.pow(k), context);
            BigDecimal second = FOUR.divide(denomParc.add(ONE), context);
            BigDecimal third = TWO.divide(denomParc.add(FOUR), context);
            BigDecimal fourth = ONE.divide(denomParc.add(FIVE), context);
            BigDecimal fifth = ONE.divide(denomParc.add(SIX), context);

            second = second.subtract(third).subtract(fourth).subtract(fifth);
            first = first.multiply(second, context);
            result.add(first);
        }
    }

    /**
     * Calculate PI with the Bailey–Borwein–Plouffe Formula in a parallel execution
     * @param iterations the number of k of the sum
     * @param precision the number of digits to be used for an operation; results are rounded to this precision
     * @return the value of PI in BigDecimal
     */
    public BigDecimal calcPi(int iterations, int precision) {
        this.context = new MathContext(iterations);
        this.iterations = new AtomicInteger(precision);
        
        // get the number of processors available
        int procs = Runtime.getRuntime().availableProcessors();
        BBP_Parallel[] arrThr = new BBP_Parallel[procs];
        
        // create a new thread for each number o processors available and start it
        for (int i = 0; i < arrThr.length; i++) {
            arrThr[i] = new BBP_Parallel();
            arrThr[i].start();
        }
        
        // join each thread in the array
        for (int i = 0; i < arrThr.length; i++) {
            try {
                arrThr[i].join();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        return result.getValue().round(context);
    }
}
