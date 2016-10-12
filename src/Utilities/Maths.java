package Utilities;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Maths {

    /**
     * Method to approximate squared roots
     * @param val value to be calculated
     * @param precision the number of digits to be used for an operation; results are rounded to this precision
     * @return approximated squared root of value
     * 
     * This method calculates squared roots through a series of approximations, starting at any value near the real squared root value.
     * Dividing val by the first approximation gives the second approximation.
     * The average between those two is a value closer to the desired squared root
     * This is repeated until the 3º approx in iteration X = 3º approx in iteration X-1
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
