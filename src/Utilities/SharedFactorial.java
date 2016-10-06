package Utilities;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

public class SharedFactorial {

    // the number of max entries the hashmap should have
    static final int MAX_ENTRIES = 10;
    
    // initialize a volatile LinkedHashMap with a remove eldest entry method
    // so we only have a hashmap with the size of max_entries
    volatile static LinkedHashMap lhm = new LinkedHashMap(MAX_ENTRIES + 1, 1, false) {
        @Override
        protected boolean removeEldestEntry(Map.Entry eldest) {
            return size() > MAX_ENTRIES;
        }
    };

    // Recursive factorial method that checks first if the hashmap has the
    //  (n-1) factorial so we can multiply by n
    // This allows us to reduce the computation time
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
