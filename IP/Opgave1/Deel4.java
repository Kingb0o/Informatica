/*
* Naam : W. van der LInde
* UvAnetID : 12857130
* Studie : BSc Informatica
*
* Deel4.java:
    In dit programma word er gevraagd voor een positief getal.
    Hierna wordt het aantal Lucas-getallen geprojecteerd die 
    de gebruiker heeft aangevraagd.
*/

import java.util.*;

public class Deel4 {
    public static void main(String[] args) {
        
        System.out.println("Geef een natuurlijk getal: ");

        Scanner scanner = new Scanner(System.in);

        if(! scanner.hasNextInt()){
            System.out.println("Dit is geen nummer!");
            return;
        }

        int userInput = scanner.nextInt();
        scanner.close();
        int lucasGetal = 2;
        int printGetal = 1;
        int tussenGetal = 0;

        /*
        Het nummer 44 is gekozen omdat na dit getal de lucas-getallen
        niet meer goed functioneren
        */

        if(userInput > 0 && userInput < 44){

            System.out.println("Hier volgen de eerste " + userInput + " Lucas-getallen:");
            System.out.print(lucasGetal + " ");
            System.out.print(printGetal + " ");

            /*
            Aan de hand van deze for loop word het volgende Lucas-getal op
            de correcte manier berekend. Er word gebruik gemaakt van een
            tussen variable om de reeks goed op te slaan.
            */

            for(int i = 0; i < userInput-2; i++){
                tussenGetal = printGetal;
                printGetal = printGetal + lucasGetal;
                lucasGetal = tussenGetal;

                System.out.print(printGetal + " ");
            }
            System.out.println(" ");
        } else {
            if(userInput < 0){
                System.out.println("Dit getal is kleiner dan 0");
            } else {
                System.out.println("Dit getal is te groot");
            }
        }
    }
}