package Tests;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

public class Test_3 {

    // Factorial vs factorial resursive with the last factorials stored
    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();
        for (BigDecimal i = BigDecimal.ONE; i.compareTo(new BigDecimal(1000)) <= 0; i = i.add(BigDecimal.ONE)) {
            factorial(i);
        }
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime + " ms");

        System.out.println("------------------");

        startTime = System.currentTimeMillis();
        for (BigDecimal i = BigDecimal.ONE; i.compareTo(new BigDecimal(1000)) <= 0; i = i.add(BigDecimal.ONE)) {
            factorialHash(i);
        }
        endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime + " ms");
    }

    static BigDecimal factorial(BigDecimal number) {
        BigDecimal factor = BigDecimal.ONE;
        for (BigDecimal i = BigDecimal.ONE; i.compareTo(number) <= 0; i = i.add(BigDecimal.ONE)) {
            factor = factor.multiply(i);
        }
        return factor;
    }

    static final int MAX_ENTRIES = 10;
    volatile static LinkedHashMap lhm = new LinkedHashMap(MAX_ENTRIES + 1, 1, false) {
        protected boolean removeEldestEntry(Map.Entry eldest) {
            return size() > MAX_ENTRIES;
        }
    };

    static BigDecimal factorialHash(BigDecimal number) {
        if (number.equals(BigDecimal.ONE)) {
            return BigDecimal.ONE;
        } else if (lhm.get(number) != null) {
            return number.multiply((BigDecimal) lhm.get(number));
        } else {
            BigDecimal result = number.multiply(factorialHash(number.subtract(BigDecimal.ONE)));
            lhm.put(number, result);
            return result;
        }
    }
}
