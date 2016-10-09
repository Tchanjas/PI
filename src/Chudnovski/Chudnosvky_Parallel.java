/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chudnovski;

import static Chudnovski.Chudnovski.fact;
import static Chudnovski.Chudnovski.sqrt;
import Utilities.SharedBigDecimal;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author Clara
 */
public class Chudnosvky_Parallel extends Thread {

    static AtomicInteger iterations = new AtomicInteger(1000);
    static SharedBigDecimal result = new SharedBigDecimal(0.0);
    static AtomicInteger next = new AtomicInteger(0);

    BigDecimal CONST1 = BigDecimal.valueOf(13591409);
    BigDecimal CONST2 = BigDecimal.valueOf(545140134);
    BigDecimal CONST3 = BigDecimal.valueOf(640320);
    BigDecimal CONST4 = sqrt(BigDecimal.valueOf(640320).pow(3), 1000);

    BigDecimal SIX = BigDecimal.valueOf(6);
    BigDecimal THREE = BigDecimal.valueOf(3);
    BigDecimal MONE = BigDecimal.valueOf(-1);
    BigDecimal TWELVE = BigDecimal.valueOf(12);

    BigDecimal first;
    BigDecimal second;
    BigDecimal third;
    BigDecimal fourth;
    BigDecimal fifth;
    BigDecimal sixth;

    BigDecimal num;
    BigDecimal denum;

    MathContext context;

    public Chudnosvky_Parallel() {
        context = new MathContext(1000);
    }

    @Override
    public void run() {
        int k;
        while (next.get() != iterations.get()) {
            k = next.getAndIncrement();

            first = MONE.pow(k, context);
            second = fact(SIX.multiply(BigDecimal.valueOf(k), context));
            third = CONST1.add(CONST2.multiply(BigDecimal.valueOf(k), context));

            fourth = fact(THREE.multiply(BigDecimal.valueOf(k), context));
            fifth = fact(BigDecimal.valueOf(k)).pow(3);
            sixth = CONST3.pow(3 * k).multiply(CONST4, context);

            num = first.multiply(second, context).multiply(third, context);
            denum = fourth.multiply(fifth, context).multiply(sixth, context);

            result.add(num.divide(denum, context));
            
        }
    }

    public BigDecimal calcPi() {
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
        result.setSharedValue(result.getSharedValue().pow(-1, context).divide(TWELVE, context));
        System.out.println(result.getVal());
        return result.getVal();
    }

}
