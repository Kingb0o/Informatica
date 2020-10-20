/*
* Naam : W. van der LInde
* UvAnetID : 12857130
* Studie : BSc Informatica
*
* Opgave2.java:
* Dit programma neemt een zin als invoer. Hier worden alle leestekens uit de zin verwijderd.
* Vervolgens worden alle hoofdletters naar kleine letters omgezet. De lengte van de
* gefilterde en ongefilterde zin worden bepaald. Het aantal woorden en klinkers worden gecheckt.
* De gefilterde zin wordt gecheckt of dit een palindroom is. En tenslotte wordt er een frequentie
* tabel gemaakt.
*/

import java.util.*;

public class Opgave2{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Geef een zin: ");
        String userInput = scanner.nextLine();

        if(userInput.equals(" ") || userInput.equals("")){
            System.err.println("Dit is geen zin");
            System.exit(1);
        }
        scanner.close();
        char[] reeks = userInput.toCharArray();
        int stapGrootte = 0;
        String aantalLetters = new String();

        char[] eind = CheckHL(reeks);
        int[] freq = CheckFreq(eind);
        eind = CheckKap(eind);
        eind = CheckLeegte(eind);
        boolean palindroom = CheckPalin(eind);
        int woorden = CheckWoorden(eind);
        int klinkers = CheckKlinkers(eind);
        int max = CheckMax(freq);
        aantalLetters = MaakZin(freq);
        int lengteZin = CheckLengte(freq);

        System.out.println("Lengte ongefilterde zin: " + reeks.length);
        System.out.println("Gefilterd: ");
        System.out.print(eind);
        if(reeks.length > 80){
            System.out.println("...");
            System.out.println("Lengte gefilterde zin: " + (lengteZin-1));
        } else {
            System.out.println("");
            System.out.println("Lengte gefilterde zin: " + lengteZin);
        }
        System.out.println("Aantal woorden: " + woorden);
        System.out.println("Aantal klinkers: " + klinkers);
        System.out.println("De zin is een palindroom: " + palindroom);
        System.out.println("---------------------------------------------------");
        stapGrootte = StapCheck(max);
        System.out.println("Ter controle de frequentie van alle karakters: ");
        System.out.println(aantalLetters);
        System.out.println("max is " + max + " en stapgrootte " + stapGrootte);
        System.out.println("---------------------------------------------------");

        for(int i = 0; i < freq.length; i++){
            freq[i] = Math.round(freq[i]/stapGrootte);
        }

        /*
         * Print aantal * van de frequentie van elk karakter d.m.v. een dubbele for loop
         * die andersom door de reeks heen loopt.
         */
        for(int i = 10; i > 0; i--){
            for(int j = 0; j < 37; j++){
                if(freq[j] == i){
                    freq[j] = freq[j]-1;
                    System.out.print("* ");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println("");
        }
        System.out.println("a b c d e f g h i j k l m n o p q r s t u v w x y z 1 2 3 4 5 6 7 8 9 0 _");
    }


    /*
     * Checkt of het meegegeven karakter een van de gegevens Chars is
     */
    static boolean CheckZin(char charCheck){
        if( charCheck >= 'a' && charCheck <= 'z' ||
            charCheck >= '0' && charCheck <= '9' ||
            charCheck >= 'A' && charCheck <= 'Z' ||
            charCheck == ' '){
            return true;
        }
        return false;
    }

    /*
     * Deze functie checkt het aantal woorden door het aantal spaties te tellen.
     * Er word echter ook gecheckt of de zin tussen woorden niet meerdere spaties bevat.
     */
    static int CheckWoorden(char[] check){
        int aantalWoorden = 0;

        for(int h = 0; h < check.length-1; h++){
            if(check[h] == ' ' && check[h+1] != ' '){
                aantalWoorden = aantalWoorden + 1;
            }
        }
        return aantalWoorden+1;
    }

    /*
     * Checkt het aantal klinkers in de zin.
     */
    static int CheckKlinkers(char[] check){
        int aantalKlinkers = 0;
        for(int h = 0; h < check.length; h++){
            if( check[h] == 'a' ||
                check[h] == 'e' ||
                check[h] == 'i' ||
                check[h] == 'o' ||
                check[h] == 'y' ||
                check[h] == 'u' ){
                aantalKlinkers = aantalKlinkers +1;
            }
        }
        return aantalKlinkers;
    }

    /*
     * Checkt of de zin een palindroom is. Door char aan het begin en einde
     * te vergelijken met elkaar. Dit gebeurd voor de helft van de zin
     * omdat hieruit al te concluderen is of de zin een palindroom is.
     */
    static boolean CheckPalin(char[] check){
        boolean palindroom = false;
        String string = new String("");
        char checkBegin;
        char checkEind;
        int lengte;

        for(int f = 0; f < check.length; f++){
            if(check[f] >= 'a' && check[f] <= 'z'){
                string = string + check[f];
            }
        }
        lengte = string.length();
        string = string.trim();

        for(int j = 0; j < Math.round(lengte/2); j++){
            checkBegin = string.charAt(j);
            checkEind = string.charAt(lengte-j-1);

            if(checkBegin != checkEind){
                palindroom = false;
                break;
            } else{
                palindroom = true;
            }
        }
        return palindroom;
    }

    /*
     * De laatste spaties in de zin worden verwijderd indien dit het geval is.
     * Er wordt rekening gehouden met meerdere spaties aan het einde van de zin.
     */
    static char[] CheckLeegte(char[] check){
        for(int j = 0; j < check.length; j++){
            if( check[check.length-j-1] == ' ' &&
                check[check.length-j-2] >= 'a' &&
                check[check.length-j-2] <= 'z' ){
                check = Arrays.copyOfRange(check, 0, (check.length-j-1));
            break;
            } else if(check[check.length-j-1] >= 'a' && check[check.length-j-1] <= 'z'){
                check = Arrays.copyOfRange(check, 0, (check.length-j));
                break;
            }
        }
        return check;
    }

    /*
     * Er wordt gecheckt of het maximale aantal
     * frequentie van karakters (10) is overschreden.
     * Zo ja, wordt deze geschaald.
     */
    static int StapCheck(int check){
        int stap = 1;
        int max = 10;
        if(check > max){
            stap = check / max;
            if(stap < 10){
                stap = stap + 1;
            }
        }
        return stap;
    }

    /*
     * Zin wordt afgekapt indien deze langer is dan 80 karakters.
     * Indien er nog halve woorden staan worden deze weggehaald.
     */
    static char[] CheckKap(char[] check){
        if(check.length >= 80){
            check = Arrays.copyOfRange(check, 0, 81);
            for(int j = 0; j < check.length; j++){
                if(check[80-j] == ' ') {
                    check = Arrays.copyOfRange(check, 0, (80-j));
                    return check;
                }
            }
        }
        return check;
    }

    /*
     * Bepaalt samen de frequentie van elke letter in de zin die word gegeven.
     * Ook wordt hierna de stapgrootte bepaald.
     */
    static int[] CheckFreq(char[] check){
        String zin = new String(check);
        int[] freq = new int[]{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        char[] letter = new char[]{ 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
                                    'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
                                    'u', 'v', 'w', 'x', 'y', 'z', '1', '2', '3', '4',
                                    '5', '6', '7', '8', '9', '0', ' '};

        for(int h = 0; h < letter.length; h++){
            int aantal = 0;
            for(int i = 0; i < zin.length(); i++) {
                if(letter[h] == zin.charAt(i)) {
                    aantal = aantal + 1;
                }
            }
            if(h == 36){
                aantal = zin.length() - zin.replaceAll(" ", "").length();
            }
            freq[h] = aantal;
        }
        return freq;
    }

    /*
     * De hoogste frequentie in de reeks wordt gecheckt.
     */
    static int CheckMax(int[] check){
        int max = 0;
        for(int i = 0; i < check.length-1; i++){
            if(check[i] > max){
                max = check[i];
            }
        }
        return max;
    }

    /*
     * Maakt een zin van de frequentie om dit goed te kunnen printen.
     */
    static String MaakZin(int[] check){
        String aantalLetters = new String();
        for(int i = 0; i < check.length-1; i++){
            aantalLetters = aantalLetters + " " + String.valueOf(check[i]);
        }
        return aantalLetters;
    }

    /*
     * Als een Char in de reeks een hoofdletter is wordt deze naar een kleine
     * letter omgezet.
     */
    static char[] CheckHL(char[] reeks){
        char[] eind = new char[reeks.length];

        for(int i = 0; i < reeks.length; i++){
            boolean checkZin = CheckZin(reeks[i]);
            if(checkZin == true){
                if( reeks[i] >= 'A' && reeks[i] <= 'Z'){
                    reeks[i] = (char)((int)reeks[i] + 32);
                    eind[i] = reeks[i];
                }
                eind[i] = reeks[i];
            }
        }
        return eind;
    }

    /*
     * Berekend lengte van de zin als deze korter is dan 80 karakters.
     * De reden van het getal 80; Als de zin langer is dan 80 karakters
     * wordt de zin afgekapt en worden er puntjes geplaatst.
     */
    static int CheckLengte(int[] check){
        int lengteZin = 0;
        for(int i = 0; i < check.length-1; i++){
            lengteZin = lengteZin + check[i];
            if(lengteZin > 80){
                lengteZin = 80;
            }
        }
        return lengteZin;
    }
}