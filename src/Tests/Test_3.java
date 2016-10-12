package Tests;

import Utilities.SharedFactorial;
import java.math.BigDecimal;

public class Test_3 {

    // Factorial vs factorial resursive with the last factorials stored
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        for (BigDecimal i = BigDecimal.ONE; i.compareTo(new BigDecimal(1000)) <= 0; i = i.add(BigDecimal.ONE)) {
            factorial(i);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Factorial: \t\t\t" + (endTime - startTime) + " ms");

        System.out.println("--------------------------------------");

        SharedFactorial factorialHash = new SharedFactorial();
        
        startTime = System.currentTimeMillis();
        for (BigDecimal i = BigDecimal.ZERO; i.compareTo(new BigDecimal(1000)) <= 0; i = i.add(BigDecimal.ONE)) {
            factorialHash.calc(i);
        }
        endTime = System.currentTimeMillis();
        System.out.println("Factorial com LinkedHashMap: \t" + (endTime - startTime) + " ms");
    }

    static BigDecimal factorial(BigDecimal number) {
        BigDecimal factor = BigDecimal.ONE;
        for (BigDecimal i = BigDecimal.ONE; i.compareTo(number) <= 0; i = i.add(BigDecimal.ONE)) {
            factor = factor.multiply(i);
        }
        return factor;
    }
}
