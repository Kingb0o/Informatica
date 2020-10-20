/*
* Naam : W. van der LInde
* UvAnetID : 12857130
* Studie : BSc Informatica
*
* Receptie.java:
*   Dit bestand is de gebruikers interface voor het aangemaakte hotel waar de gebruiker met gebruik
*   van commando's de gegevens van het hotel kan manipuleren om zo het in en uit checken van gasten
*   te simuleren.
*/

import java.util.*;

class Receptie {
    public static void main(String[] args) throws GroepPastNietException {
        Hotel hotel;

        /* Maakt een nieuw hotel aan met of zonder argumeten wanneer Receptie.java wordt aangeroepen. */
        if (args.length == 1) {
            hotel = new Hotel(Integer.parseInt(args[0]));
        } else {
            hotel = new Hotel();
        }
        System.out.println("Welkom bij jet mainfraim van " + hotel);
        System.out.println("Geldige commando's zijn 'checkin', 'checkuit', 'status' en 'stop'.");
        int altijd = 1;

        /* Deze oneindige loop zorgt ervoor dat er oneidig veel commando's kunnen worden ingevoerd. */
        while(altijd == 1) {
            System.out.print("Voer een commando in: ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            /* Voor elk commando wordt de correcte methode aangeroepen. */
            switch(input) {

                /* Checkt het aantal gasten in bij het hotel dat wordt meegegeven. */
                case "checkin":
                    System.out.print("Hoeveel gasten? ");
                    scanner = new Scanner(System.in);
                    int gasten = scanner.nextInt();
                    try {
                        hotel.maakGasten(gasten);
                    } catch(GroepPastNietException e) {
                        System.out.println("Deze groep past helaas niet in het hotel.");
                    }
                    break;

                /* Checkt de kamer uit in het hotel dat wordt aangegeven. */
                case "checkuit":
                    System.out.print("Welke kamer? ");
                    scanner = new Scanner(System.in);
                    int kamer = scanner.nextInt();
                    hotel.checkUit(kamer);
                    break;

                /* Voor elke kamer in het hotel wordt de kamer met het aantal gasten in de kamer geprint. */
                case "status":
                    for (int i = 0; i < hotel.getAantalKamers(); i++) {
                        System.out.println("Kamer " + i + " (maximaal " + hotel.getKamer(i).maxKamer() + " gast)");

                        /* Als de kamer leeg is wordt er 'Leeg' geprint. */
                        for (int j = 0; j < hotel.getKamer(i).maxKamer(); j++) {
                            if (hotel.getKamer(i).getGasten().isLeeg()) {
                                System.out.println("    Leeg");
                                break;
                            }

                            /* Print elke gast in de kamer. */
                            if (hotel.getKamer(i).getGasten().get(j) != null) {
                                System.out.println("    " + hotel.getKamer(i).getGasten().get(j));
                            }
                        }
                    }
                    break;

                /* Bij dit commando wordt het programma gestopt. */
                case "stop":
                    return;

                default:
                    break;
            }
        }
    }
}