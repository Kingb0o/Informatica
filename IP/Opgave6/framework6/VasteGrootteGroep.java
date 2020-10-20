/*
* Naam : W. van der LInde
* UvAnetID : 12857130
* Studie : BSc Informatica
*
* VasteGrootteGroep.java:
*   Deze sub klasse van groep maakt een vaste groep aan waar de lengte van de groep vast staat.
*/

class VasteGrootteGroep<T> extends Groep<T> {

    /* Deze constructor roept de Groep constructor aan met het meegegeven aantal objecten. */
    VasteGrootteGroep(int aantal) {
        super(aantal);
    }

    /* Deze methode voegt een object toe aan de Array als deze niet vol zit. */
    public void add(Object ander) {

        if (getLengte() == getCapaciteit()) {
            throw new GroepVolException("Object lijst zit vol.");
        }
        /*
         * Voegt obejct toe aan de Array als er geen exception plaatsvindt. Ook wordt wederom de variabele
         * 'lengte' met 1 verhoogt omdat er een nieuw element in de Array zit.
         */
        this.lijst[getLengte()] = ander;
        this.lengte++;
    }
}

/* Deze exceptie is een extentie van een RuntimeException en wordt gebruikt voor de methode hierboven. */
class GroepVolException extends RuntimeException {
    public GroepVolException(String errorMessage) {
        super(errorMessage);
    }
}