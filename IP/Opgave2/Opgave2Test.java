/*
* Naam : W. van der LInde
* UvAnetID : 12857130
* Studie : BSc Informatica
*
* Opgave2.java:

*/

import java.util.*;

public class Opgave2Test {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Geef een zin: ");

        String userInput = scanner.nextLine();
        scanner.close();

        char[] reeks = userInput.toCharArray();
        char[] eind = new char[reeks.length];

        int aantalWoorden = 0;
        int aantalSpaties = 0;
        int aantalKlinkers = 0;

        for(int i = 0; i < reeks.length; i++) {
            boolean check = CheckZin(reeks[i]);

            if(check == true){

                /*
                Als de CharCode overeenkomt met een getal tussen de A en Z wordt deze code met 32
                verhoogt om de kleine letter hiervan te krijgen.
                */
                if( reeks[i] >= 'A' && reeks[i] <= 'Z') {
                    reeks[i] = (char)((int)reeks[i] + 32);
                    eind[i] = reeks[i];
                }
                eind[i] = reeks[i];
            }
        }

        /*
        Deze for-loop berekend het aantal spaties en klinkers in de gefilterde reeks.
        */
        for(int h = 0; h < eind.length; h++){
            if(eind[h] == ' '){
                aantalSpaties = aantalSpaties + 1;
            }
            if( eind[h] == 'a' || 
                eind[h] == 'e' || 
                eind[h] == 'i' || 
                eind[h] == 'o' || 
                eind[h] == 'y' || 
                eind[h] == 'u' ) {
                aantalKlinkers = aantalKlinkers +1;
            }
        }
        aantalWoorden = aantalSpaties + 1;

        /*
        * De volgende 'if-for-if' verhouding zorgt ervoor dat de array bij 80 word afgekapt.
        * Vervolgens telt deze terug tot dat de eerste spatie word gevonden. Hier na wordt
        * de array afgekapt zodat er alleen gehele woorden worden geprint. 
        */
        boolean afgekapt = false;
        if(eind.length >= 80) {
            eind = Arrays.copyOfRange(eind, 0, 81);

            for(int j = 0; j < eind.length; j++) {
                if(eind[80-j] == ' ') {
                    eind = Arrays.copyOfRange(eind, 0, (80-j));
                    afgekapt = true;
                break;
                }
            }
        }


        //Reverse Array
        boolean palindroom = false;
        String string = new String("");
        int lengte;

        for(int f = 0; f < eind.length; f++) {
            if(eind[f] >= 'a' && eind[f] <= 'z') {
                string = string + eind[f];
            }
        }

        lengte = string.length();
        string = string.trim();

        for(int j = 0; j < Math.round(lengte/2); j++) {
            char check = string.charAt(j);
            char check2 = string.charAt(lengte-j-1);
            
            if(check != check2) {
                palindroom = false;
                break;
            } else {
                palindroom = true;
            }
        }

        /*
        * Deze for-loop checkt of er nog lege spaties staan in de array "eind".
        * Zodra deze er wel zijn worden deze verwijderd.
        */
        for(int j = 0; j < eind.length; j++) {
            if(eind[eind.length-j-1] == ' ' && eind[eind.length-j-2] >= 'a' && eind[eind.length-j-2] <= 'z' ) {
                eind = Arrays.copyOfRange(eind, 0, (eind.length-j));
            break;
            } else if(eind[eind.length-j-1] >= 'a' && eind[eind.length-j-1] <= 'z') {
                eind = Arrays.copyOfRange(eind, 0, (eind.length-j)); 
                break;
            }
        }

        System.out.println("Lengte ongefilterde zin: " + reeks.length);
        System.out.println("Gefilterd: ");
        System.out.print(eind);
        if(afgekapt){
            System.out.println("...");
        } else {
            System.out.println("");
        }
        System.out.println("Lengte gefilterde zin: " + eind.length);
        System.out.println("Aantal woorden: " + aantalWoorden);
        System.out.println("Aantal klinkers: " + aantalKlinkers);
        System.out.println("De zin is een palindroom: " + palindroom);
    }

    /*
    Deze methode checkt of de gegeven Char een karakter is in het alfabet of een getal.
    Dit gebeurd met behulp van ASCII codes.
    */

    static boolean CheckZin(char charCheck) {

        if( charCheck >= 'a' && charCheck <= 'z' || 
            charCheck >= '0' && charCheck <= '9' ||
            charCheck >= 'A' && charCheck <= 'Z' ||
            charCheck == ' ') {
            return true;
        }
        return false;
    }
}