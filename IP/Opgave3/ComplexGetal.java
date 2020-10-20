/*
* Naam : W. van der LInde
* UvAnetID : 12857130
* Studie : BSc Informatica
*
* ComplexGetal.java:
*   Dit bestand bevat een aantal constructoren en methoden die het mogelijk maken om
*   complexe getallen op te kunnen slaan en om hier vervolgens mee te kunnen rekenen.
*/

public class ComplexGetal {
    public Breuk re, im;

    /*
     * Deze constructor maakt van vier gegeven getallen twee breuken en slaat deze
     * vervolgens op als de waarde van het reeele en imaginaire gedeelte van het
     * complexe getal.
     */
    public ComplexGetal(int reInTel, int reInNom, int imInTel, int imInNom) {
        Breuk breukRe = new Breuk(reInTel, reInNom);
        Breuk breukIm = new Breuk(imInTel, imInNom);
        this.re = breukRe;
        this.im = breukIm;
    }

    /*
     * Deze constructor maakt een complex getal met twee gegevens getallen. Hier worden
     * deze getallen nogsteeds eerst opgeslagen als breuken om hier later goed mee
     * te kunnen rekenen.
     */
    public ComplexGetal(int re, int im) {
        Breuk breukRe = new Breuk(re, 1);
        Breuk breukIm = new Breuk(im, 1);
        this.re = breukRe;
        this.im = breukIm;
    }

    /*
     * Deze constructor slaat twee bestaande breuken op als complex getal.
     */
    public ComplexGetal(Breuk breukRe, Breuk breukIm) {
        Breuk re = new Breuk(breukRe.tellerBreuk, breukRe.noemerBreuk);
        Breuk im = new Breuk(breukIm.tellerBreuk, breukIm.noemerBreuk);
        this.re = re;
        this.im = im;
    }

    /*
     * Deze methode telt twee complexe getallen op d.m.v. de telOp() fucntie die voor
     * losse breuken gebruikt wordt. Hierna worden deze waarden weer opgeslagen
     * als nieuw complex getal.
     */
    public ComplexGetal telOp(ComplexGetal getal) {
        Breuk reNieuw = this.re.telOp(getal.re);
        Breuk imNieuw = this.im.telOp(getal.im);
        return new ComplexGetal(reNieuw, imNieuw);
    }

    /*
     * Deze methode trekt twee complexe getallen van elkaar af d.m.v de trekAf() functie
     * die voor losse breuken gebruikt wordt. Hierna worden deze waarden weer opgeslagen
     * als nieuw complex getal.
     */
    public ComplexGetal trekAf(ComplexGetal getal) {
        Breuk reNieuw = this.re.trekAf(getal.re);
        Breuk imNieuw = this.im.trekAf(getal.im);
        return new ComplexGetal(reNieuw, imNieuw);
    }

    /*
     * Deze methode volgt de regels van complexe getallen door beide reeele en imaginaire
     * getallen te vermenigvuldigen en daarna van elkaar af te trekken. Hierna wordt het
     * nieuwe imaginaire getal bepaald door de reeele getallen te vermenigvuldigen met
     * de imaginaire getallen en deze vervolgens op te tellen.
     */
    public ComplexGetal vermenigvuldig(ComplexGetal getal) {
        Breuk reNieuw = (this.re.vermenigvuldig(getal.re)).trekAf(this.im.vermenigvuldig(getal.im));
        Breuk imNieuw = (this.re.vermenigvuldig(getal.im)).telOp(this.im.vermenigvuldig(getal.re));
        return new ComplexGetal(reNieuw, imNieuw);
    }

    /*
     * Deze methode is een zelf gemaakte deel methode die is gemaakt om deze goede deel functie
     * te kunnen checken. De tellers van beide breuken worden op dezelfde manier bepaald als bij
     * het vermenigvuldigen alleen zijn telOp en trekAf omgedraaid. De noemer wordt bepaald door
     * beide delen van het tweede gegeven getal tot de macht 2 te doen.
     */
    public ComplexGetal deelAnders(ComplexGetal getal) {
        Breuk tellerLinks = (this.re.vermenigvuldig(getal.re)).telOp((this.im.vermenigvuldig(getal.im)));
        Breuk tellerRechts = (this.im.vermenigvuldig(getal.re)).trekAf((this.re.vermenigvuldig(getal.im)));
        Breuk noemer = getal.re.vermenigvuldig(getal.re).telOp(getal.im.vermenigvuldig(getal.im));
        Breuk reNieuw = tellerLinks.deel(noemer);
        Breuk imNieuw = tellerRechts.deel(noemer);
        return new ComplexGetal(reNieuw, imNieuw);
    }

    /*
     * Deze methode deelt de twee complexe getallen door te vermenigvuldigen met het omgekeerde van
     * het andere complexe getal. Hierna wordt het imaginaire getal nog een keer met -1 vermenigvuldigt
     * om de breuk kloppend te maken.
     */
    public ComplexGetal deel(ComplexGetal getal) {
        return this.vermenigvuldig(getal.omgekeerde());
    }

    /*
     * Deze methode draait een complex getal om door deze op te delen in 2 breuken en deze op die manier
     * om te draaien. Hier worden de methode uit Breuk.java voor gebruikt.
     */
    public ComplexGetal omgekeerde() {
        Breuk tellerIm = new Breuk(this.im);
        Breuk tellerRe = this.re;
        tellerIm.tellerBreuk *= -1;
        Breuk noemer = this.re.vermenigvuldig(this.re).telOp(this.im.vermenigvuldig(this.im));
        Breuk reNieuw = tellerRe.deel(noemer);
        Breuk imNieuw = tellerIm.deel(noemer);
        return new ComplexGetal(reNieuw, imNieuw);
    }

    /*
     * Deze methode maakt een String van de opgeslagen waarden van het complexe getal.
     */
    public String toString() {
        String getalZin;
        getalZin = this.re + " + " + this.im + "i";
        return getalZin;
    }

    /*
     * Deze methode checkt op de complexe getallen gelijk aan elkaar zijn door met de
     * equals() functie uit Breuk.java te checken of beide breuken gelijk zijn aan
     * elkaar.
     */
    public Boolean equals(ComplexGetal getal) {
        return this.re.equals(getal.re) && this.im.equals(getal.im);
    }
}