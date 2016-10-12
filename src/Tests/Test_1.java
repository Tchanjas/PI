package Tests;

import BBP.BBP;
import Chudnovsky.Chudnovsky;
import static Methods.Leibniz.Leibniz;
import static Methods.MonteCarlo.MonteCarlo;
import static Methods.Wallis.Wallis;

public class Test_1 {
    
    // Sequential methods test
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
        
        BBP piBBP = new BBP();
        startTime = System.nanoTime();
        System.out.print("BBP: " + piBBP.calcPI(16, 16)+ "\t\t | Execution Time: ");
        endTime = System.nanoTime();
        System.out.println(endTime - startTime + " ns");
        
        Chudnovsky chud = new Chudnovsky();
        startTime = System.nanoTime();
        System.out.print("Chudnovski: " + chud.calcPI(1, 16)+ "\t | Execution Time: ");
        endTime = System.nanoTime();
        System.out.println(endTime - startTime + " ns");
        System.out.println("");
    }    
}
