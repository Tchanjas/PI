package Methods;

public class MonteCarlo {
    // Monte Carlo Method
    public static double MonteCarlo(long totalPoint){
        double insideCircle = 0.0;
        for (int i = 0; i < totalPoint; i++) {
            double x = Math.random();
            double y = Math.random();
            if(x*x+y*y < 1){
                insideCircle++;
            }
        }
        return 4*(insideCircle/totalPoint);
    }
}
