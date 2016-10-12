package Tests;

import Chudnovsky.Chudnosvky_Parallel;
import Chudnovsky.Chudnovsky;

public class Test_4 {

    // Chudnovski sequential vs parallel
    public static void main(String[] args) {

        System.out.println("--- Chudnovski Algorithm ---");

        Chudnovsky chud = new Chudnovsky();
        Chudnosvky_Parallel chudp = new Chudnosvky_Parallel();

        double timeStampStart = System.currentTimeMillis();
        chud.calcPI(1000, 1000);
        double timeStampStop = System.currentTimeMillis();
        double timeSeq = timeStampStop - timeStampStart;
        System.out.println("Time : " + timeSeq + " ms");

        System.out.println("--- Chudnovski Algorithm Parallel---");

        timeStampStart = System.currentTimeMillis();
        chudp.calcPi(1000, 1000);
        timeStampStop = System.currentTimeMillis();
        double timePara = timeStampStop - timeStampStart;
        System.out.println("Time : " + timePara + " ms");

        System.out.println("--- Speedup ---");
        double speedup = timeSeq / timePara;
        System.out.println("Value: " + speedup);
    }
}
