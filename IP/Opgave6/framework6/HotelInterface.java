import java.util.Collection;

interface HotelInterface {
    void checkIn(Groep<Gast> groep) throws GroepPastNietException;
    void checkUit(int kamernummer);
    Kamer getKamer(int nummer);
    int getAantalKamers();
}
