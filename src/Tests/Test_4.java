package Tests;

import Chudnovski.Chudnosvky_Parallel;
import Chudnovski.Chudnovski;

public class Test_4 {

    // Chudnovski sequential vs parallel
    public static void main(String[] args) {

        System.out.println("--- Chudnovski Algorithm ---");
        
        Chudnovski chud = new Chudnovski();
        Chudnosvky_Parallel chudp = new Chudnosvky_Parallel();

        long timeStampStart = System.currentTimeMillis();
        chud.calcPI(1000, 1000);
        long timeStampStop = System.currentTimeMillis();
        long time = timeStampStop-timeStampStart;
        System.out.println("Tempo : " + time + " ms");
        
        System.out.println("--- Chudnovski Algorithm Parallel---");
        
        timeStampStart = System.currentTimeMillis();
        chudp.calcPi();
        timeStampStop = System.currentTimeMillis();
        time = timeStampStop-timeStampStart;
        System.out.print("Tempo : " + time + " ms\n");
    }
}
