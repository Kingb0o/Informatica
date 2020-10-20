/*
* Naam : W. van der LInde
* UvAnetID : 12857130
* Studie : BSc Informatica
*
* Hotel.java:
*   Dit bestand maakt een hotel aan met een vast aantal kamers en een naam. Bij deze kamers kunnen
*   gasten worden toegevoegd of verwijderd om zo het idee van een hotel na te bootsen.
*/

class Hotel implements HotelInterface {
    private final VasteGrootteGroep<Kamer> kamers;
    private final String naam;
    final int hotelGrootte;
    private static final String[] steden = {
        "Budapest", "Bucharest", "Chisinau", "Tbilisi", "Munich", "Warsaw",
        "Cluj", "Moscow", "Zagreb", "Prague", "Ljubljana", "California"
    };
        private static final String[] naamTemp = {
        "Grand %s Hotel", "Royal %s Hotel", "Hotel %s", "%s Hotel",
        "Hotel of %s"
    };

    /* Creeert een nieuw Hotel object met een aantal kamers en naam. */
    Hotel(int aantalKamers) {
        this.hotelGrootte = aantalKamers;
        this.naam = String.format(naamTemp[(int)(naamTemp.length * Math.random())], steden[(int)(steden.length * Math.random())]);
        this.kamers = new VasteGrootteGroep<Kamer>(getAantalKamers());
        int aantalTwee = getAantalKamers() - Math.round(getAantalKamers() / 4);
        int volgende = 0;

        /* 1/4 van de kamer groep wordt gevuld met eenpersoonskamers. */
        for (int i = 0; i < Math.round(getAantalKamers() / 4); i++) {
            this.kamers.lijst[i] = new EenPersoonsKamer();
            volgende++;
        }

        /* De helft van de kamer groep wordt gevuld met tweepersoonskamers. */
        for (int i = volgende; i < aantalTwee; i++) {
            this.kamers.lijst[i] = new TweePersoonsKamer();
            volgende++;
        }

        /* Het laatste 1/4 van de kamer groep wordt gevuld met vierpersoonskamers. */
        for (int i = volgende; i < getAantalKamers(); i++) {
            this.kamers.lijst[i] = new VierPersoonsKamer();
        }
    }

    /* Als het hotel wordt aangeroepen zonder argumenten krijgt deze een standaard grootte van 10. */
    Hotel() {
        this(10);
    }

    /* Creeert het aantal gasten in een vastegroottegroep om in te checken. */
    public void maakGasten(int aantal) throws GroepPastNietException {
        VasteGrootteGroep<Gast> gasten = new VasteGrootteGroep<Gast>(aantal);
        for (int i = 0; i < aantal; i++) {
            gasten.add(new Gast());
        }
        checkIn(gasten);
    }

    /* Checkt een groep gasten in bij het hotel. */
    public void checkIn(Groep<Gast> groep) throws GroepPastNietException {

        /* Voor elke kamer in het hotel wordt gecheckt of de groep in de kamer past. */
        for (int i = 0; i < getAantalKamers(); i++) {
            try {
                getKamer(i).checkinKamer(groep);
                return;
            } catch (KamerAlBezetException | KamerTeKleinException e) {

            }
        }
        throw new GroepPastNietException("Groep past niet in deze kamer.");
    }

    /* Checkt de groep uit van de huidige kamer. */
    public void checkUit(int kamerUit) {
        getKamer(kamerUit).checkoutKamer();
    }

    /* Haalt de kamer op met de gegeven argument. */
    public Kamer getKamer(int i) {
        return this.kamers.get(i);
    }

    /* Geeft het aantal kamers in het hotel terug. */
    public int getAantalKamers() {
        return this.hotelGrootte;
    }

    /*
     * De toString() methode zorgt ervoor dat de naam van het hotel goed geprint wordt in
     * bijvoorbeeld de WebReceptie.java.
     */
    public String toString() {
        return this.naam;
    }
}

/* Deze exceptie wordt aangeroepen als de groep niet in het hotel kan worden ingecheckt. */
class GroepPastNietException extends Exception {
    public GroepPastNietException(String errorMessage) {
        super(errorMessage);
    }
}