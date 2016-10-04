
package BBP;

import java.math.BigDecimal;
import java.math.MathContext;

public class BBP {
    // Bailey–Borwein–Plouffe Formula in a sequencial execution
    public static BigDecimal BBP(int iterations){
        
        BigDecimal sum = BigDecimal.ZERO;
        MathContext context = new MathContext(1000);
        BigDecimal CONST_1 = BigDecimal.valueOf(1);
        BigDecimal CONST_2 = BigDecimal.valueOf(2);
        BigDecimal CONST_4 = BigDecimal.valueOf(4);
        BigDecimal CONST_5 = BigDecimal.valueOf(5);
        BigDecimal CONST_6 = BigDecimal.valueOf(6);
        
        for (int i = 0; i < iterations; i++) {
            
            BigDecimal CONST_8i = new BigDecimal(8*i, context);
            
            BigDecimal P1 = CONST_1.divide(new BigDecimal(16).pow(i), context);
            
            BigDecimal P2 = CONST_4.divide(CONST_8i.add(CONST_1), context);
            
            BigDecimal P3 = CONST_2.divide(CONST_8i.add(CONST_4), context);
            
            BigDecimal P4 = CONST_1.divide(CONST_8i.add(CONST_5), context);
            
            BigDecimal P5 = CONST_1.divide(CONST_8i.add(CONST_6), context);
            
            BigDecimal P = P2.subtract(P3).subtract(P4).subtract(P5).multiply(P1);
            sum = sum.add(P);
        }
        
        return sum;
    }
}
