// package Test;

// import Persons.*;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.Scanner;


public class Duel {

    @Test
    public void Warrior_Wizard () { // weryfikacja czy zaklęcia z enamu dizałają poprawnie
        Hero hero = new Warrior("Tom", 100, 30);
        Hero other = new Wizard("Gendalf", 200, 5, 100 );


        System.out.println("================= START WALKI =================");
        // Walka
        while (hero.isAlive() && other.isAlive()) {

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

    @Test
    public void Warrior_Necromancer () { // weryfikacja czy szkielet u niekromancera działa zgodznie z logiką
        Hero hero = Necromancer.generateRandomNecromancer();
        Hero other = Necromancer.generateRandomNecromancer();


        System.out.println("================= START WALKI =================");
        // Walka
        while (hero.isAlive() && other.isAlive()) {

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
               if (new Random().nextBoolean()){hero.rest();}
               if (new Random().nextBoolean()){other.rest();}
           }

        }
        System.out.println("================== ZWYCIĘŻCA ==================");

        System.out.println( hero.isAlive() ? hero.getName() : other.getName() + " Wygral walke");

    }


}
