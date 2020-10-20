/*
* Naam : W. van der LInde
* UvAnetID : 12857130
* Studie : BSc Informatica
*
* Deel2.java:
    In deze opdracht word er gekeken naar een getal dat door de gebruiker
    word gegeven. Hierna wordt d.m.v. een loop gezorgd dat zowel alle
    even en oneven getallen bij behorende soort worden opgetelt. Tot slot
    worden de sommen geprint en daarbij het verschillen tussen beide.
*/

import java.util.*;

public class Deel2{
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        int somOneven = 0;
        int somEven = 0;

        System.out.println("Voer een geheel positief getal in: ");

        if(! scanner.hasNextInt()){
            System.out.println("Dit is geen nummer!");
            return;
        }
        
        int userInput = scanner.nextInt();

        if(!(userInput > 0)) {
            System.out.println("Dit getal is niet positief");
        } else {
            
            /*
            For loop checkt welke getallen tot en met het 
            gegeven getal oneven en even zijn en telt ze op 
            bij de correcte variable.
            */

            for(int i = 0; i <= userInput; i++){
                int checkEven = i % 2;

                if( checkEven ==  0){
                    somEven = somEven + i;
                } else {
                    somOneven = somOneven + i;
                }
            }

            int verschil = Math.abs(somEven -  somOneven);

            System.out.println("som van oneven getallen tot en met " + userInput + " = " + somOneven);
            System.out.println("som van even getallen tot en met " + userInput + "= " + somEven);
            System.out.println("verschil tussen twee sommen is " + verschil);
        }
    }
}