/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author Bruno
 */
public class Maths {

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
