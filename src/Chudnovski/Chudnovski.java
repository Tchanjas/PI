package Chudnovski;

import Utilities.SharedFactorial;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Chudnovski {
    
    MathContext context;

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

    BigDecimal result = BigDecimal.ZERO;

    public void calcPI(int iter, int precision) {
        context = new MathContext(precision);
        SharedFactorial sharedFact_1 = new SharedFactorial();
        SharedFactorial sharedFact_2 = new SharedFactorial();
        SharedFactorial sharedFact_3 = new SharedFactorial();
        for (int i = 0; i < iter; i++) {
            first = MONE.pow(i, context);
            second = sharedFact_1.calc(SIX.multiply(BigDecimal.valueOf(i), context));
            third = CONST1.add(CONST2.multiply(BigDecimal.valueOf(i), context));

            fourth = sharedFact_2.calc(THREE.multiply(BigDecimal.valueOf(i), context));
            fifth = sharedFact_3.calc(BigDecimal.valueOf(i)).pow(3);
            sixth = CONST3.pow(3 * i).multiply(CONST4, context);

            num = first.multiply(second, context).multiply(third, context);
            denum = fourth.multiply(fifth, context).multiply(sixth, context);

            result = result.add(num.divide(denum, context));
        }

        result = result.pow(-1, context).divide(TWELVE, context);
        System.out.println(result);
    }

    public static BigDecimal sqrt(BigDecimal val, int precision) {
        BigDecimal a = BigDecimal.ZERO;
        BigDecimal b = new BigDecimal(Math.sqrt(val.doubleValue()));
        while (!a.equals(b)) {
            a = b;
            b = val.divide(a, precision, RoundingMode.HALF_UP);
            b = b.add(a);
            b = b.divide(BigDecimal.valueOf(2), precision, RoundingMode.HALF_UP);
        }
        return b;
    }

}
