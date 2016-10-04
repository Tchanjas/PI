package Methods;

public class Leibniz {
    // Leibniz Formula
    public static double Leibniz(){
        double sum = 1;
        double sumDivider = 3;
        boolean sumNegative = true;
        
        for (int i = 0; i < 100000000; i++) {
            // do the formula and flip the signal of the sum
            if (sumNegative == true) {
                sum = sum + (1/(sumDivider*(-1)));
                sumNegative = false;
            } else {
                sum = sum + (1/sumDivider);
                sumNegative = true;
            }
            // +2 for the next divider
            sumDivider = sumDivider + 2;
        }
        
        // *4 due to being = pi/4
        return sum*4;
    }
}
