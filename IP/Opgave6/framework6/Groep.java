/*
* Naam : W. van der LInde
* UvAnetID : 12857130
* Studie : BSc Informatica
*
* Groep.java:
*   Dit bestand maakt een groep aan waar objecten aan toegevoegd kunnen worden. Zodra deze 'groep' vol
*   zit wordt de lengte van de groep verdubbeld.
*/

import java.util.Iterator;

class Groep<T> implements GroepInterface<T>, Iterable<T> {
    protected int lengte = 0;
    protected Object[] lijst;

    /* Maakt een groep aan met een nieuwe lege object Array met de lengte die is meegegeven. */
    Groep(int aantal) {
        this.lijst = new Object[aantal];
    }

    /* In een groep van objecten wordt de lengte van waardes in de Array die niet null zijn. */
    protected int getLengte() {
        return this.lengte;
    }

    /* In een groep van objecten wordt de totale lengte van de Array opgehaald. */
    public int getCapaciteit() {
        return this.lijst.length;
    }

    /* Checkt of de Array van objecten leeg is. */
    public boolean isLeeg() {
        return this.lijst[0] == null;
    }

    /* Haalt de waarde op uit de Array met de index van de meegegeven waarde. */
    @SuppressWarnings("unchecked")
    public T get(int i) {
        return (T)this.lijst[i];
    }

    /* Maakt de objecten Array leeg door een nieuwe met een lengte van 1 te creeren. */
    public void maakLeeg() {
        this.lijst = new Object[1];
        this.lengte = 0;
    }

    /* Voegt een object toe aan de object Array. */
    public void add(Object ander) {

        /*
         * Als de lengte variabele 'lengte' gelijk is aan de totale capaciteit
         * (en de array dus vol zit) of dat de capaciteit gelijk is aan 1,
         * wordt de lengte van de objecten Array verdubbeld.
         */
        if (this.lengte == getCapaciteit() || getCapaciteit() == 1) {
            Object[] nieuw = new Object[getCapaciteit() * 2];

            for (int i = 0; i < this.lengte; i++) {
                nieuw[i] = this.lijst[i];
            }
            this.lijst = new Object[nieuw.length];

            for (int i = 0; i < nieuw.length; i++) {
                this.lijst[i] = nieuw[i];
            }
            /* Voegt het object toe aan de objecten Array. */
            this.lijst[lengte] = ander;
        } else {
            this.lijst[lengte] = ander;
        }
        /* Verhoogt de waarde van lengte met 1 omdat er een nieuw object is toegevoegd aan de Array. */
        this.lengte++;
    }

    /* Creeert een nieuwe iterator voor deze klasse. */
    @Override
    public Iterator<T> iterator() {
        return this.new GroepIterator();
    }

    private class GroepIterator implements Iterator<T> {
        int teller = 0;

        /* Geeft de waarde terug van de volgende waarde in de Array. */
        public T next() {
            return get(teller++);
        }

        /* Checkt of het volgende element in de Array een waarde is die niet gelijk is aan null. */
        public boolean hasNext() {
            return teller < getLengte();
        }
    }
}