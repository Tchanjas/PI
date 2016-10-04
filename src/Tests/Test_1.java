package Tests;

import static BBP.BBP.BBP;
import static Methods.Leibniz.Leibniz;
import static Methods.MonteCarlo.MonteCarlo;
import static Methods.Wallis.Wallis;

public class Test_1 {
    
    public static void main(String[] args) {
        System.out.println("--- Different methods to calculate PI ---");
        
        long startTime = System.nanoTime();
        System.out.print("Java API: " + Math.PI + "\t | Execution Time: ");
        long endTime = System.nanoTime();
        System.out.print(endTime - startTime + " ns");
        System.out.println("");
        
        startTime = System.nanoTime();
        System.out.print("Monte Carlo: " + MonteCarlo(100000000) + "\t\t | Execution Time: ");
        endTime = System.nanoTime();
        System.out.print(endTime - startTime + " ns");
        System.out.println("");
        
        startTime = System.nanoTime();
        System.out.print("Leibniz: " + Leibniz() + "\t | Execution Time: ");
        endTime = System.nanoTime();
        System.out.print(endTime - startTime + " ns");
        System.out.println("");
        
        startTime = System.nanoTime();
        System.out.print("Wallis: " + Wallis()+ "\t\t | Execution Time: ");
        endTime = System.nanoTime();
        System.out.print(endTime - startTime + " ns");
        System.out.println("");
        
        startTime = System.nanoTime();
        System.out.print("BBP: " + BBP(100)+ "\t\t\n | Execution Time: ");
        endTime = System.nanoTime();
        System.out.print(endTime - startTime + " ns");
        System.out.println("");
    }    
}
