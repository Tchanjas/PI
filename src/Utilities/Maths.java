package Utilities;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Maths {

    /**
     * Method to aproximate squared roots
     * @param val
     * @param precision
     * @return
     * 
     * This method calculates squared roots through a series of aproximations, starting at any value near the real squared root value.
     * Dividing val by the first aproximation gives the second aproximation.
     * The average between those two is a value closer to the desired squared root
     * This is repeated until the 3º aprox in iteration X = 3º aprox in iteration X-1
     */
    
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
