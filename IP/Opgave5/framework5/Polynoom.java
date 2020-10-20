import java.util.*;
import java.lang.*;
import java.io.IOException;
import java.io.File;

class Polynoom implements PolynoomInterface {
    private ArrayList<Paar> termen;

    /* Creeert een polynoom aan de hand van een Arraylist van Paren. */
    Polynoom() {
        termen = new ArrayList<Paar>();
    }

    /* Vult de polynoom met Paren uit het bestand. */
    Polynoom(File bestand) {
        this(leesPolynoom(bestand));
    }

    /* Vult de polynoom met Paren met de meegegeven argumenten. */
    Polynoom(double... lijst) {
        this();

        /* Als argumenten niet een even aantal is wordt er geen polynoom gemaakt. */
        if(lijst.length % 2 != 0) {
            return;
        }

        /* Voor alle argumenten wordt er een paar gegenereerd met twee waardes uit de */
        for(int i = 0; i < lijst.length; i += 2) {
            int macht = (int)lijst[i+1];
            Paar paar = new Paar(lijst[i], macht);
            this.termen.add(paar);
        }
    }

    /* Vult een Polynoom met de waardes uit een ArrayList. En versimpelt deze direct. */
    Polynoom(ArrayList<Paar> termen) {
        this();
        termen = versimpel(termen);

        for(Paar i: termen) {
            this.termen.add(i);
        }
    }

    /*
     * Telt twee Polynomen bij elkaar op door ze beiden in een nieuwe list
     * toe te voegen en deze te versimpelen.
     */
    public Polynoom telOp(Polynoom ander) {
        ArrayList<Paar> resultaat = new ArrayList<Paar>();

        for(Paar i : this.termen) {
            resultaat.add(i);
        }

        for(Paar i : ander.termen) {
            resultaat.add(i);
        }

        Polynoom terug = new Polynoom(resultaat);
        return terug;
    }

    /*
     * Trekt twee Polynomen van elkaar af door Polynoom2 negatief te maken en
     * beiden vervolgens toe te voegen aan een nieuwe list en deze te versimpelen.
     */
    public Polynoom trekAf(Polynoom ander) {
        ArrayList<Paar> resultaat = new ArrayList<Paar>();

        for(Paar i : this.termen) {
            resultaat.add(i);
        }

        for(Paar i : ander.termen) {
            resultaat.add(i.maakNeg());
        }
        Polynoom terug = new Polynoom(resultaat);
        return terug;
    }

    /* Vermenigvuldigt twee Polynomen door voor elke termen met elkaar te vermenigvuldigen. */
    public Polynoom vermenigvuldig(Polynoom ander) {
        ArrayList<Paar> resultaat = new ArrayList<Paar>();

        for (int i = 0; i < this.termen.size(); i++) {
            for (int j = 0; j < ander.termen.size(); j++) {
                resultaat.add(this.termen.get(i).vermenigvuldig(ander.termen.get(j)));
            }
        }
        Polynoom terug = new Polynoom(resultaat);
        return terug;
    }

    /* Differentieert een polynoom door elk Paar 1 voor 1 te differentieren. */
    public Polynoom differentieer() {
        ArrayList<Paar> resultaat = new ArrayList<Paar>();

        for (int i = 0; i < this.termen.size(); i++) {
            resultaat.add(this.termen.get(i).differentieer());
        }
        Polynoom terug = new Polynoom(resultaat);
        return terug;
    }

    /*
     * Integreert een Polynoom door elk Paar 1 voor 1 te integreren
     * en tot slot de integratie-constante toe te voegen.
     */
    public Polynoom integreer(double c) {
        ArrayList<Paar> resultaat = new ArrayList<Paar>();

        for (int i = 0; i < this.termen.size(); i++) {
            resultaat.add(this.termen.get(i).integreer());
        }
        resultaat.add(new Paar(c, 0));
        Polynoom terug = new Polynoom(resultaat);
        return terug;
    }

    /* Checkt of de termen van twee Polynomen hetzelfde zijn. */
    public boolean equals(Polynoom ander) {
        boolean gelijk = true;

        if (this.termen.size() == ander.termen.size()) {

            for (int i = 0; i < this.termen.size(); i++) {

                if (this.termen.get(i).equals(ander.termen.get(i)) == false) {
                    gelijk = false;
                }
            }
            return gelijk;
        } else {
            gelijk = false;
            return gelijk;
        }
    }

    /* Vergelijkt twee objecten als deze twee Polynomen zijn. */
    public boolean equals(Object o) {
        if (o instanceof Polynoom)
            return this.equals((Polynoom)o);

        return false;
    }

    /* Elke term in de Polynoom wordt aan een String toegevoegd om deze te kunnen printen. */
    public String toString() {
        String polynoomZin = "";

        for(int i = 0; i < this.termen.size(); i++) {
            polynoomZin += this.termen.get(i);
            if(i != this.termen.size()-1 && this.termen.get(i + 1).check() == false) {
                polynoomZin += " + ";
            }
        }
        return polynoomZin;
    }

    /* Versimpeld de Polynoom door alle termen met een gemeenschappelijke machten bij elkaar op te tellen. */
    public ArrayList<Paar> versimpel(ArrayList<Paar> ander) {
        ArrayList<Paar> simpel = new ArrayList<Paar>();
        ander.toString();
        Collections.sort(ander);

        for (int i = 0; i < ander.size(); i++) {

            if (i + 1 < ander.size()) {

                /*
                 * Als de term een gelijke macht heeft met de volgende term in de reeks
                 *  worden deze opgeteld. Deze uitkomst wordt vervolgens weer toegevoegd
                 * aan de reeks. Dit is om te voorkomen dat er meerdere termen met dezelfde
                 * macht in de Polynoom komen te staan.
                 */
                if (ander.get(i).equalsMacht(ander.get(i + 1))) {
                    ander.add(ander.get(i).telOp(ander.get(i + 1)));
                    i++;
                } else {
                    simpel.add(ander.get(i));
                }
            } else {
                simpel.add(ander.get(i));
            }
        }
        /* Zet de verschillende termen op de aflopende volgorde. */
        Collections.sort(simpel);
        return simpel;
    }

    /* Leest de meegegeven bestanden in en haalt de waardes voor de Polynoom uit dit bestand. */
    private static ArrayList<Paar> leesPolynoom(File bestand) {
        ArrayList<Paar> resultaat = new ArrayList<Paar>();

        try {
            Scanner input = new Scanner(bestand);

            /*
             * Zolang er een volgende lijn in het bestand zit worden de 2 argumenten op dezelfde regel
             * toevoegd aan de Polynoom door deze eerst als coefficient en bijbehorende macht op te slaan.
             */
            while (input.hasNextLine()) {
                String[] regel = input.nextLine().split(",");

                /* Als de regel meer dan 2 of maar 1 argument bevat geeft het programma een foutmelding. */
                if (regel.length > 2 || regel.length == 1) {
                    throw new IllegalArgumentException("Er staat een fout in het bestand.");
                }
                double coefficient = Double.parseDouble(regel[0].trim());
                int macht = Integer.parseInt(regel[1].trim());
                Paar paar = new Paar(coefficient, macht);
                resultaat.add(paar);
            }

            input.close();
        } catch (IOException e) {
            throw new IllegalArgumentException("Er is iets fout gegaan met inlezen.");
        }
        return resultaat;
    }
}
