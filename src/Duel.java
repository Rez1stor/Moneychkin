// package Test;

// import Persons.*;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.Scanner;


public class Duel {

    Warrior warior1 = Warrior.generateRandomWarrior();
    Warrior warior2 = Warrior.generateRandomWarrior();

    Wizard wizard1 = Wizard.generateRandomWizard();
    Wizard wizard2 = Wizard.generateRandomWizard();


    Hero hero = warior1;
    Hero other = wizard1;


    @Test
    public void duel () {


        System.out.println("================= START WALKI =================");
        // Walka
        while (hero.isAlive() && other.isAlive()) {
            System.out.println();
            System.out.println("================== PARAMETRY ==================");

            System.out.println(hero);
            System.out.println(other);
            System.out.println("===============================================");

            try {
                hero.atak(other);
                Thread.sleep(1000);
                other.atak(hero);
                Thread.sleep(1000);

            }catch (InterruptedException e) {}

           if (new Random().nextBoolean()) {
               System.out.println("================= ODPOCZUNEK =================");
               hero.rest();
               other.rest();
           }

        }
        System.out.println("================== ZWYCIĘŻCA ==================");

        System.out.println( hero.isAlive() ? hero.getName() : other.getName() + " Wygral walke");

    }


}
