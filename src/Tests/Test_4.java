package Tests;

import Chudnovski.Chudnovski;

public class Test_4 {

    // Chudnovski sequential vs parallel
    public static void main(String[] args) {

        System.out.println("--- Chudnovski Algorithm ---");

        Chudnovski sequen = new Chudnovski();
        sequen.calcPI(15);
        
    }
}
