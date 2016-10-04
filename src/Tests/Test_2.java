package Tests;

import static BBP.BBP.BBP;

public class Test_2 {
    
    public static void main(String[] args) {
        
        System.out.println("--- BBP Methods ---");
        
        long startTime = System.currentTimeMillis();
        System.out.print("BBP: " + BBP(1000)+ "\t\t\n | Execution Time: ");
        long endTime = System.currentTimeMillis();
        System.out.print(endTime - startTime + " ms");
        System.out.println("");
        
        // BBP Parallel
    }    
}
