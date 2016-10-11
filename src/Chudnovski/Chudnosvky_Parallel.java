package Chudnovski;

import static Chudnovski.Chudnovski.sqrt;
import Utilities.SharedBigDecimal;
import Utilities.SharedFactorial;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.concurrent.atomic.AtomicInteger;

public class Chudnosvky_Parallel extends Thread {
    
    static MathContext context;
    static AtomicInteger iterations;
    
    static SharedBigDecimal result = new SharedBigDecimal(0.0);
    static AtomicInteger next = new AtomicInteger(0);

    BigDecimal CONST1 = BigDecimal.valueOf(13591409);
    BigDecimal CONST2 = BigDecimal.valueOf(545140134);
    BigDecimal CONST3 = BigDecimal.valueOf(-640320);
    BigDecimal CONST4 = sqrt(BigDecimal.valueOf(640320).pow(3), 1000);

    BigDecimal SIX = BigDecimal.valueOf(6);
    BigDecimal THREE = BigDecimal.valueOf(3);
    BigDecimal TWELVE = BigDecimal.valueOf(12);

    BigDecimal first;
    BigDecimal second;
    BigDecimal third;
    BigDecimal fourth;
    BigDecimal fifth;

    BigDecimal num;
    BigDecimal denum;

    SharedFactorial sharedFact_1 = new SharedFactorial();
    SharedFactorial sharedFact_2 = new SharedFactorial();
    SharedFactorial sharedFact_3 = new SharedFactorial();

    @Override
    public void run() {
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

    public BigDecimal calcPi(int iterations, int precision) {
        this.iterations = new AtomicInteger(iterations);
        this.context = new MathContext(precision);
        int procs = Runtime.getRuntime().availableProcessors();
        Chudnosvky_Parallel[] arrThr = new Chudnosvky_Parallel[procs];
        for (int i = 0; i < arrThr.length; i++) {
            arrThr[i] = new Chudnosvky_Parallel();
            arrThr[i].start();
        }

        for (Chudnosvky_Parallel thread : arrThr) {
            try {
                thread.join();
            }catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        result.setSharedValue(result.getValue().pow(-1, context).divide(TWELVE, context));
        //System.out.println(result.getValue());
        return result.getValue();
    }
}
