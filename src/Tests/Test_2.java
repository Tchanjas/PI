package Tests;

import BBP.*;

public class Test_2 {

    // BBP sequential vs parallel
    public static void main(String[] args) {

        System.out.println("--- BBP Methods ---");

        BBP piBBP = new BBP();
        BBP_Parallel piPara = new BBP_Parallel();

        long startTime = System.currentTimeMillis();
        System.out.print("BBP: " + piBBP.calcPI(1000, 1000) + "\t\t\n | Execution Time: ");
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime + " ms");
        System.out.println("");

        // BBP Parallel
        startTime = System.currentTimeMillis();
        System.out.print("BBP: " + piPara.calcPi(1000, 1000) + "\t\t\n | Execution Time: ");
        endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime + " ms");
    }
}
