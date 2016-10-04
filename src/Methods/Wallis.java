package Methods;

public class Wallis {
    // Wallis Product
    public static double Wallis(){
        double sum = 1.0;
        
        // i needs to be double
        for (double i = 1; i < 100000000; i++) {
            sum = sum * (((2.0*i)/((2.0*i)-1.0)) * ((2.0*i)/((2.0*i)+1.0)));
        }
        
        // ... = pi/2
        return sum*2.0;
    }
}
