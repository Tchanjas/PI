package BBP;

import java.math.BigDecimal;
import java.math.MathContext;

public class BBP {
    
    MathContext context;
    BigDecimal sum = BigDecimal.ZERO;
    
    // Constants of the formula
    BigDecimal CONST_1 = BigDecimal.valueOf(1);
    BigDecimal CONST_2 = BigDecimal.valueOf(2);
    BigDecimal CONST_4 = BigDecimal.valueOf(4);
    BigDecimal CONST_5 = BigDecimal.valueOf(5);
    BigDecimal CONST_6 = BigDecimal.valueOf(6);
    
    /**
     * Calculate PI with the Bailey–Borwein–Plouffe Formula in a sequencial execution
     * @param iterations the number of k of the sum
     * @param precision the number of digits to be used for an operation; results are rounded to this precision
     * @return the value of PI in BigDecimal
     */
    public BigDecimal calcPI(int iterations, int precision){
        
        context = new MathContext(precision);
        
        // the sum of the formula
        for (int i = 0; i < iterations; i++) {
            
            BigDecimal CONST_8i = new BigDecimal(8*i);
            
            BigDecimal P1 = CONST_1.divide(new BigDecimal(16).pow(i, context), context);
            
            BigDecimal P2 = CONST_4.divide(CONST_8i.add(CONST_1), context);
            
            BigDecimal P3 = CONST_2.divide(CONST_8i.add(CONST_4), context);
            
            BigDecimal P4 = CONST_1.divide(CONST_8i.add(CONST_5), context);
            
            BigDecimal P5 = CONST_1.divide(CONST_8i.add(CONST_6), context);
            
            BigDecimal P = P2.subtract(P3).subtract(P4).subtract(P5).multiply(P1, context);
            sum = sum.add(P);
        }
        
        return sum.round(context);
    }
}
