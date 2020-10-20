/*
* Naam : W. van der LInde
* UvAnetID : 12857130
* Studie : BSc Informatica
*
* Deel3.java:
    In deze opdracht maakt het programma een random nummer aan. 
    De gebruiker krijgt dan vervolgens 3 kansen om het goede 
    getal te raden. Als hij niet voldoet verliest hij het spel.
*/

import java.util.*;

public class Deel3 {
    public static void main(String[] args){
        
        Random random = new Random();
        int randomGetal = random.nextInt(10)+1;
        Scanner scanner = new Scanner(System.in);

        System.out.print("Geef een getal tussen 1 en 10, je mag 3 keer raden: ");

        /*
        Met de 'for loop' wordt er gecheckt of het getal klopt of
        dat deze te klein of te groot is. Dit word 3 keer herhaald 
        omdat de gebruiker 3 kansen heeft.
        */

        for(int i = 1; i < 4; i++) {

            if(! scanner.hasNextInt()){
                System.out.println("Dit is geen nummer!");
                return;
            }
            
            int userInput = scanner.nextInt();
            System.out.println("Gok " + i + ": " + userInput);

            if(userInput <= 0 && userInput > 11){
                System.out.println("Dit getal zit niet tussen de 1 en 10");
            } else { 
                if (userInput == randomGetal){
                    System.out.println("Het goede getal is geraden");
                } else {   
                    if(userInput < randomGetal){
                        System.out.println("Te klein");
                    } else {
                        System.out.println("Te groot");
                    }
                }
            }
        }
        scanner.close();
    }
}