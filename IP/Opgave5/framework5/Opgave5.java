/**
 * Polynomencalculator
 *
 * Naam: Wessel van der Linde
 * UvAnetID: 12857130
 * Datum: 30-09-2019
 */

import org.apache.commons.cli.*;
import java.io.File;

class Opgave5 {
    static Options opties = initOpties();
    static Polynoom polynoom1, polynoom2;
    static String[] bestanden;

    /* Leest de argumenten in de commandline en voert hier de bijbehorende methoden voor uit. */
    public static void main(String[] args) {
        CommandLine cmd = parseOpties(args);
        maakPolynomen();

        System.out.println("Polynoom 1: " + polynoom1);
        System.out.println("Polynoom 2: " + polynoom2);

        verwerkOpties(cmd);
    }

    /* De argumenten die meegegeven kunnen worden in de commandline worden hier gedefinieert. */
    private static Options initOpties() {
        Options opties = new Options();
        opties.addOption("o", "optellen", false, "Tel beiden polynomen bij elkaar op");
        opties.addOption("a", "aftrekken", false, "Trek beiden polynomen van elkaar af");
        opties.addOption("v", "vermenigvuldigen", false, "vermenigvuldig beiden polynomen");
        opties.addOption("d", "differentieer", false, "Differentieert polynoom1");
        opties.addOption("i", "integreer", false, "Differentieert polynoom1");
        opties.addOption("e", "equals", false, "Vergelijkt beiden polynomen met elkaar");
        return opties;
    }

    /* Checkt of er correcte argumenten mee worden gegeven in de commandline. */
    private static CommandLine parseOpties(String[] args) {
        CommandLine cmd = null;

        try {
            cmd = new DefaultParser().parse(opties, args);
            bestanden = cmd.getArgs();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }

        return cmd;
    }

    private static void maakPolynomen() {
        /*
         * Hier lezen we de twee polynomen in uit bestanden. Als de eerste niet
         * bestaat stoppen we maar de tweede is optioneel.
         */
        try {
            switch (bestanden.length) {
                case 2:
                    polynoom2 = new Polynoom(new File(bestanden[1]));
                case 1:
                    polynoom1 = new Polynoom(new File(bestanden[0]));
                    break;
                default:
                    throw new IllegalArgumentException("Het aantal bestanden moet een of twee zijn.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    private static void verwerkOpties(CommandLine cmd) {
        /* Dit handelt alle operaties af die worden meegegeven in de commandline. */
        if (cmd.hasOption("optellen")) {
            if (polynoom2 != null) {
                System.out.println("Som: " + polynoom1.telOp(polynoom2));
            } else {
                System.out.println("Polynoom 2 nodig om op te tellen!");
            }
        }

        if (cmd.hasOption("aftrekken")) {
            if (polynoom2 != null) {
                System.out.println("Verschil: " + polynoom1.trekAf(polynoom2));
            } else {
                System.out.println("Polynoom 2 nodig om af te trekken!");
            }
        }

        if (cmd.hasOption("vermenigvuldigen")) {
            if (polynoom2 != null) {
                System.out.println("Product: " + polynoom1.vermenigvuldig(polynoom2));
            } else {
                System.out.println("Polynoom 2 nodig om te vermenigvuldigen!");
            }
        }

        if (cmd.hasOption("differentieer")) {
            if (polynoom2 == null) {
                System.out.println("Gedifferentieerde polynoom1: " + polynoom1.differentieer());
            } else {
                System.out.println("Gedifferentieerde polynoom1: " + polynoom1.differentieer());
                System.out.println("Gedifferentieerde polynoom2: " + polynoom2.differentieer());
            }
        }

        if (cmd.hasOption("integreer")) {
            if (polynoom2 == null) {
                System.out.println("Geintegreerde polynoom1: " + polynoom1.integreer(1.0));
            } else {
                System.out.println("Geintegreerde polynoom1: " + polynoom1.integreer(1.0));
                System.out.println("Geintegreerde polynoom2: " + polynoom2.integreer(1.0));
            }
        }

        if (cmd.hasOption("equals")) {
            if (polynoom2 != null) {
                System.out.println("Gelijk?: " + polynoom1.equals(polynoom2));
            } else {
                System.out.println("Polynoom 2 nodig om te vergelijken!");
            }
        }
    }
}
