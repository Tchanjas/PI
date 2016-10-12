package Chudnovsky;

import Utilities.Maths;
import Utilities.SharedBigDecimal;
import Utilities.SharedFactorial;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.concurrent.atomic.AtomicInteger;

public class Chudnosvky_Parallel extends Thread {

    static MathContext context;
    static AtomicInteger iterations; // the current iteration

    static SharedBigDecimal result;
    static AtomicInteger next;
    static int sqrtPrec; // precision for the Maths.sqrt method
    
    // Constants of the algorithm
    BigDecimal CONST1 = BigDecimal.valueOf(13591409);
    BigDecimal CONST2 = BigDecimal.valueOf(545140134);
    BigDecimal CONST3 = BigDecimal.valueOf(-640320);
    BigDecimal CONST4 = Maths.sqrt(BigDecimal.valueOf(640320).pow(3), sqrtPrec);

    BigDecimal SIX = BigDecimal.valueOf(6);
    BigDecimal THREE = BigDecimal.valueOf(3);
    BigDecimal TWELVE = BigDecimal.valueOf(12);

    // Elements of each part of the algorithm
    BigDecimal first;
    BigDecimal second;
    BigDecimal third;
    BigDecimal fourth;
    BigDecimal fifth;

    BigDecimal num;
    BigDecimal denum;

    // Sahred factorials for each factorial function in the algorithm
    SharedFactorial sharedFact_1 = new SharedFactorial();
    SharedFactorial sharedFact_2 = new SharedFactorial();
    SharedFactorial sharedFact_3 = new SharedFactorial();

    // Calculate the sum of the formula
    @Override
    public void run() {
        // while the next iteration != iterations
        //  get the next iteration, increment it and do the sum of the algorithm
        int k;
        while (next.get() != iterations.get()) {
            k = next.getAndIncrement();

            first = sharedFact_1.calc(SIX.multiply(BigDecimal.valueOf(k), context));
            second = CONST1.add(CONST2.multiply(BigDecimal.valueOf(k), context));

            third = sharedFact_2.calc(THREE.multiply(BigDecimal.valueOf(k), context));
            fourth = sharedFact_3.calc(BigDecimal.valueOf(k)).pow(3);
            fifth = CONST3.pow(3 * k).multiply(CONST4, context);

            num = first.multiply(second, context);
            denum = third.multiply(fourth, context).multiply(fifth, context);

            result.add(num.divide(denum, context));
        }
    }

    /**
     * Calculate PI with the Chudnovsky algorithm in a parallel execution
     * @param iterations the number of k of the sum
     * @param precision the number of digits to be used for an operation; results are rounded to this precision
     * @return the value of PI in BigDecimal
     */
    public BigDecimal calcPi(int iterations, int precision) {
        next = new AtomicInteger(0);
        result = new SharedBigDecimal(0.0);
        sqrtPrec = precision;
        this.iterations = new AtomicInteger(iterations);
        this.context = new MathContext(precision);
        
        // get the number of processors available
        int procs = Runtime.getRuntime().availableProcessors();
        Chudnosvky_Parallel[] arrThr = new Chudnosvky_Parallel[procs];
        
        // create a new thread for each number of processors available and starts it
        for (int i = 0; i < arrThr.length; i++) {
            arrThr[i] = new Chudnosvky_Parallel();
            arrThr[i].start();
        }
        
        // join each thread in the array
        for (Chudnosvky_Parallel thread : arrThr) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        
        // result = ((sum of the algorithm)^-1)/12
        result.setSharedValue(result.getValue().pow(-1, context).divide(TWELVE, context));
        return result.getValue();
    }

}
