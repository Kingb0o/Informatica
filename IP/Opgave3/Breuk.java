/*
* Naam : W. van der LInde
* UvAnetID : 12857130
* Studie : BSc Informatica
*
* Breuk.java:
*   Dit bestand bevat een aantal verschillende constructoren en methodes om te kunnen rekenen met breuken
*   en deze ook op correcte wijze op te slaan.
*/

public class Breuk {
    public int noemerBreuk, tellerBreuk;

    /*
     * Deze constructor slaat 2 gegevens getallen op als een breuk. Er wordt ook gecheckt of
     * de breuk kleiner kan worden opgeschreven en of deze uberhaupt mogelijk is.
     */
    public Breuk(int teller, int noemer) {
        this.tellerBreuk = teller;
        this.noemerBreuk = noemer;

        if(noemer == 0) {
            this.tellerBreuk = 0;
            this.noemerBreuk = 1;
            return;
        }
        this.telDeel();

        if(noemer < 0 && noemer != 0) {
            this.noemerBreuk = noemer * -1;
            this.tellerBreuk = teller * -1;
        }
    }

    /*
     * Deze constructor Maakt een breuk van 1 gegeven getal door de noemer 1 te maken.
     */
    public Breuk(int teller) {
        this(teller, 1);
    }

    /*
     * Deze constructor creeert een breuk met een teller van 0 en een noemer van 1 als er geen
     * verdere argumenten voor de breuk worden gegeven.
     */
    public Breuk() {
        this(0, 1);
    }

    /*
     * Deze constructor kopieert een breuk naar een andere breuk als het argument een breuk is.
     */
    public Breuk(Breuk breuk) {
        this(breuk.tellerBreuk, breuk.noemerBreuk);
    }

    /*8o
     * Deze methode maakt de noemer van twee breuken gelijk zodat deze bij elkaar kunnen
     * worden opgeteld.
     */
    public Breuk telOp(Breuk breuk) {
        int tellerNieuw =   (this.tellerBreuk * breuk.noemerBreuk) +
                            (this.noemerBreuk * breuk.tellerBreuk);
        int noemerNieuw = this.noemerBreuk * breuk.noemerBreuk;
        return new Breuk(tellerNieuw, noemerNieuw);
    }

    /*
     * Deze methode maakt de noemer van twee breuken gelijk zodat breuken van elkaar
     * kunnen worden afgetrokken.
     */
    public Breuk trekAf(Breuk breuk) {
        int tellerNieuw =   (this.tellerBreuk * breuk.noemerBreuk) -
                            (this.noemerBreuk * breuk.tellerBreuk);
        int noemerNieuw = this.noemerBreuk * breuk.noemerBreuk;
        return new Breuk(tellerNieuw, noemerNieuw);
    }

    /*
     * Deze methode vermenigvuldigd de tellers en noemers van beide breuken om zo
     * de breuken keer elkaar te kunnen doen.
     */
    public Breuk vermenigvuldig(Breuk breuk) {
        int tellerNieuw = this.tellerBreuk * breuk.tellerBreuk;
        int noemerNieuw = this.noemerBreuk * breuk.noemerBreuk;
        return new Breuk(tellerNieuw, noemerNieuw);
    }

    /*
     * Deze methode deelt de breuk door een breuk om te keren en vervolgens deze
     * te vermenigvuldigen met de andere breuk.
     */
    public Breuk deel(Breuk breuk) {
        Breuk omgekeerd = breuk.omgekeerde();
        int tellerNieuw = this.tellerBreuk * omgekeerd.tellerBreuk;
        int noemerNieuw = this.noemerBreuk * omgekeerd.noemerBreuk;
        return new Breuk(tellerNieuw, noemerNieuw);
    }

    /*
     * Deze methode draait de breuk om door van de teller de noemer maken en andersom.
     */
    public Breuk omgekeerde() {
        int tellerNieuw = this.noemerBreuk;
        int noemerNieuw = this.tellerBreuk;
        return new Breuk(tellerNieuw, noemerNieuw);
    }

    /*
     * Deze methode print de opgeslagen gegevens van de breuk naar een leesbare vertaling
     * van de gegevens.
     */
    public String toString() {
        String breukZin;
        if(this.noemerBreuk == 0) {
            breukZin = "0";
        /*
         * Deze else if statement checkt of de noemer 1 is. Als dit het geval is dan wordt
         * alleen de teller geprint aangezien deze alleen nodig is.
         */
        } else if(this.noemerBreuk == 1) {
            breukZin = this.tellerBreuk + "";
        } else {
            breukZin = this.tellerBreuk + "/" + this.noemerBreuk;
        }
        return breukZin;
    }

    /*
     * Deze methode checkt of de tellers en noemers van verschillende breuken gelijk zijn.
     */
    public boolean equals(Breuk breuk) {
        if(this.tellerBreuk == breuk.tellerBreuk && this.noemerBreuk == breuk.noemerBreuk) {
            return true;
        }
        return false;
    }

    /*
     * Deze methode schrijft de waarden van de teller en de noemer in een zo'n klein mogelijke
     * breuk.
     */
    public Breuk telDeel() {
        int ggd = Ggd(this.tellerBreuk , this.noemerBreuk);
        this.tellerBreuk = this.tellerBreuk/ggd;
        this.noemerBreuk = this.noemerBreuk/ggd;
        return this;
    }

    /*
     * Deze methode berekend de grootste gemeenschappelijke teller van de twee breuken.
     * dit gebeurd door telkens de modulus van de teller en noemer te nemen en dezelfde
     * methode weer aan te roepen (recursie) totdat de noemer gelijk is aan 0. Deze is zelf uitgedacht
     * maar achteraf bleek deze gelijk te zijn aan het algoritme van Euclides om de GGD te
     * bepalen. src: https://nl.wikipedia.org/wiki/Algoritme_van_Euclides.
     */
    static int Ggd(int teller, int noemer) {
        if (noemer == 0) {
            return teller;
        }
        return Ggd(noemer, teller % noemer);
    }
}