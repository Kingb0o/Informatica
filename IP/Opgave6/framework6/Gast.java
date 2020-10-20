/*
* Naam : W. van der LInde
* UvAnetID : 12857130
* Studie : BSc Informatica
*
* Gast.java:
*   Dit bestand creeert een gast die aan een groep toegevoegd kan worden voor gebruik in het hotel.
*/

class Gast {

    final private String voorNaam;
    final private String achterNaam;
    private static final String[] VOORNAMEN = {
        "Adam", "Sofie", "Johan", "Yuri", "Marie", "Fred", "Lisa", "Robin",
        "Claartje", "Freek", "Piet", "Pietje", "Erik", "Bas", "Pavlov", "Igor",
        "Ivan", "Geertje", "Klaas", "Snorri", "Anna", "Lotte", "Sara", "Roos",
        "Noor", "Thor", "Jean", "Karel", "Dick", "Richard", "Dzjengis", "Emma",
        "Howard Phillips", "Peter", "Sint", "Albert", "Dirk Jan", "Taylor",
        "Linus", "James", "Bjarne", "Jurriaan", "Jan-Klaassen"
    };
    private static final String[] ACHTERNAMEN = {
        "de Vries", "Smit", "Petersen", "Vonk", "Janssen", "Klaassen",
        "de Trompetter", "Baantjes", "de Jong", "Sanchez", "Bakker", "Eggertsson",
        "Sturluson", "Valjean", "de Grote", "Precies", "Khan", "Snorremans",
        "de Cock met C-O-C-K", "Stallman", "Lovecraft", "Erklaas", "Gagarin",
        "Einstein", "Heijn", "de Geer", "Swift", "Torvalds", "Gosling",
        "Stroustrup"
    };

    /* Geeft de gast een voornaam en achter met de gegeven Strings. */
    Gast(String voor, String achter) {
        this.voorNaam = voor;
        this.achterNaam = achter;
    }

    /*
     * Als de gast constructor wordt aangeroepen zonder argumenten pakt deze een voornaam en achternaam
     * uit een lijst met voor en achternamen.
     */
    Gast() {
        this(VOORNAMEN[(int)(VOORNAMEN.length * Math.random())], ACHTERNAMEN[(int)(ACHTERNAMEN.length * Math.random())]);
    }

    /* Zet de gegevens van het Gast object in een String formaat om deze te kunnen printen. */
    public String toString() {
        String terug = " ";
        if (this.voorNaam != null) {
            terug = this.voorNaam + " " + this.achterNaam;
        }
        return terug;
    }
}
