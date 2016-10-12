package Chudnovsky;

import Utilities.Maths;
import Utilities.SharedFactorial;
import java.math.BigDecimal;
import java.math.MathContext;

public class Chudnovsky {
    
    MathContext context;
    static int sqrtPrec; // precision for the Maths.sqrt method
    
    // Constants of the algorithm
    BigDecimal CONST1 = BigDecimal.valueOf(13591409);
    BigDecimal CONST2 = BigDecimal.valueOf(545140134);
    BigDecimal CONST3 = BigDecimal.valueOf(640320);
    BigDecimal CONST4 = Maths.sqrt(BigDecimal.valueOf(640320).pow(3), sqrtPrec);

    BigDecimal SIX = BigDecimal.valueOf(6);
    BigDecimal THREE = BigDecimal.valueOf(3);
    BigDecimal MONE = BigDecimal.valueOf(-1);
    BigDecimal TWELVE = BigDecimal.valueOf(12);

    // Elements of each part of the algorithm
    BigDecimal first;
    BigDecimal second;
    BigDecimal third;
    BigDecimal fourth;
    BigDecimal fifth;
    BigDecimal sixth;

    BigDecimal num;
    BigDecimal denum;

    BigDecimal result = BigDecimal.ZERO;

    /**
     * Calculate PI with the Chudnovsky algorithm in a sequencial execution
     * @param iter the number of k of the sum
     * @param precision the number of digits to be used for an operation; results are rounded to this precision
     * @return the value of PI in BigDecimal
     */
    public BigDecimal calcPI(int iter, int precision) {
        context = new MathContext(precision);
        sqrtPrec = precision;
        
        // Factorials for each factorial function in the algorithm
        SharedFactorial sharedFact_1 = new SharedFactorial();
        SharedFactorial sharedFact_2 = new SharedFactorial();
        SharedFactorial sharedFact_3 = new SharedFactorial();
        
        // the sum of the formula
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
        
        // result = ((sum of the algorithm)^-1)/12
        result = result.pow(-1, context).divide(TWELVE, context);
        return result;
    }

}
