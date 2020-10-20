/*
* Naam : W. van der LInde
* UvAnetID : 12857130
* Studie : BSc Informatica
*
* Kamer.java:
*   Maakt een kamer aan met een groep van gasten. Hier kunnen nieuwe gasten aan worden toegevoegd of
*   worden verwijderd.
*/

abstract class Kamer implements KamerInterface {
    private final VasteGrootteGroep<Gast> gastenKamer;
    private final int maxGasten;

    /* Creeert een nieuw kamer object met een maximale grootte en vaste objecten array. */
    Kamer(int grootte) {
        this.maxGasten = grootte;
        this.gastenKamer = new VasteGrootteGroep<Gast>(grootte);
    }

    /*
     * Er wordt gecheckt of de kamer leeg is en groot genoeg is om de groep toe te voegen aan de kamer.
     * Als dit zo is wordt elke gast 1 voor 1 toegevoegd aan de kamer.
     */
    protected void checkinKamer(Groep<Gast> groep) {
        VasteGrootteGroep<Gast> gastenIn = getGasten();

        /* Checkt of de kamer leeg is. */
        if (gastenIn.get(0) == null) {

            /* Checkt of de kamer groot genoeg is voor de groep gasten. */
            if (groep.getCapaciteit() > this.gastenKamer.getCapaciteit()) {
                throw new KamerTeKleinException("De Kamer is te klein om deze gasten in te checken.");
            }

            /* Voegt gasten toe aan de kamer. */
            for (Gast i : groep) {
                this.gastenKamer.add(i);
            }
        } else {
            throw new KamerAlBezetException("Kamer zit helaas al vol.");
        }
    }

    /* De groep Array van de kamer wordt leeg gemaakt. */
    protected void checkoutKamer() {
        for (int i = 0; i < this.gastenKamer.getCapaciteit(); i++) {
            this.gastenKamer.lijst[i] = null;
        }
    }

    /* Geeft een VasteGrootteGroep van gasten in de huidige kamer terug. */
    public VasteGrootteGroep<Gast> getGasten() {
        VasteGrootteGroep<Gast> terug = new VasteGrootteGroep<Gast>(this.gastenKamer.lijst.length);

        for (Gast i : this.gastenKamer) {
            terug.add(i);
        }
        return terug;
    }

    /* Geeft de grote van de kamer terug. */
    public int maxKamer() {
        return this.maxGasten;
    }
}

/* Creeert een kamer met een grote van 1. */
class EenPersoonsKamer extends Kamer {
    EenPersoonsKamer() {
        super(1);
    }
}

/* Creeert een kamer met een grote van 2. */
class TweePersoonsKamer extends Kamer {
    TweePersoonsKamer() {
        super(2);
    }
}

/* Creeert een kamer met een grote van 4. */
class VierPersoonsKamer extends Kamer {
    VierPersoonsKamer() {
        super(4);
    }
}

/* Deze exceptie wordt aangeroepen zodra de kamer al bezet is. */
class KamerAlBezetException extends RuntimeException {
    public KamerAlBezetException(String errorMessage) {
        super(errorMessage);
    }
}

/* Deze exceptie wordt aangeroepen zodra de kamer kleiner is dan de grootte van de groep. */
class KamerTeKleinException extends RuntimeException {
    public KamerTeKleinException(String errorMessage) {
        super(errorMessage);
    }
}