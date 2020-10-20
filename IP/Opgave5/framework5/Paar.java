class Paar implements Comparable<Paar> {

    final private double coefficient;
    final private int macht;

    /* Geeft elk paar een waarde met een positieve macht */
    Paar(double coefficient, int macht) {
        this.coefficient = coefficient;
        this.macht = Math.abs(macht);
    }

    /*
     * Telt twee paren bij elkaar op. Als de uitkomst nul is geeft de functie
     * een paar met een coefficient en macht van 0.
     */
    public Paar telOp(Paar ander) {
        double nieuwCo = this.coefficient + ander.coefficient;

        if (nieuwCo == 0) {
            return new Paar(0 , 0);
        } else {
            return new Paar(nieuwCo, this.macht);
        }
    }

    /* Vermenigvuldigt de coefficienten en telt de machten op van de twee meegegeven paren. */
    public Paar vermenigvuldig(Paar ander) {
        double nieuwCo = this.coefficient * ander.coefficient;
        int nieuwMa = this.macht + ander.macht;
        return new Paar(nieuwCo, nieuwMa);
    }

    /*
     * Differentieert het gegeven paar. Als de macht 0 is,
     * wordt een paar met een coefficient en macht van 0.
     */
    public Paar differentieer() {
        double nieuwCo;
        int nieuwMa;

        if (this.macht > 0) {
            nieuwCo = this.coefficient * this.macht;
            nieuwMa = this.macht - 1;
        } else {
            nieuwCo = 0;
            nieuwMa = 0;
        }
        return new Paar(nieuwCo, nieuwMa);
    }

    /* Integreert het gegeven paar. */
    public Paar integreer() {
        int nieuwMa = this.macht + 1;
        double nieuwCo = this.coefficient / nieuwMa;

        return new Paar(nieuwCo, nieuwMa);
    }

    /* Maakt de coefficient van het paar negatief */
    public Paar maakNeg() {
        double nieuwCo = this.coefficient * -1;
        Paar terug = new Paar(nieuwCo, this.macht);
        return terug;
    }

    /* Checkt of de macht en coefficient van het paar nul zijn. */
    public boolean check() {
        boolean check = false;
        if (this.coefficient == 0 && this.macht == 0) {
            check = true;
        }
        return check;
    }

    /* Checkt of de coefficient nul is. */
    public boolean checkNull() {
        boolean check = false;
        if (this.coefficient == 0.0) {
            check = true;
        }
        return check;
    }

    /* Past de compareTo functie aan zodat de variable Paar ook vergeleken kan worden met deze methode. */
    public int compareTo(Paar ander) {

        if(this.macht == ander.macht) {
            return 0;
        } else if(this.macht < ander.macht) {
            return 1;
        } else {
            return -1;
        }
    }

    /* Checkt of twee paren gelijk aan elkaar zijn. */
    public boolean equals(Paar ander) {
        if(this.coefficient == ander.coefficient && this.macht == ander.macht) {
            return true;
        }
        return false;
    }

    /* Checkt of twee machten gelijk aan elkaar zijn. */
    public boolean equalsMacht(Paar ander) {
        if (this.macht == ander.macht) {
            return true;
        }
        return false;
    }

    /* Schrijft de gegevens van het paar om naar een String. */
    public String toString() {
        String polynoomZin = "";

        /* Als de coefficient kleiner dan nul is wordt er een '-' aan de String toegevoegd. */
        if (this.coefficient < 0.0) {
            polynoomZin += "-";
        }

        /* Als de coefficient niet 1.0 of -1.0 is wordt de coefficient geprint. */
        if (Math.abs(this.coefficient) != 1.0 && this.coefficient != 0 || this.macht == 0) {
            polynoomZin += Math.abs(this.coefficient);
        }

        /* Als de macht niet nul is wordt er gecheckt hoe deze aan de String toegevoegd kan worden*/
        if (this.macht != 0) {

            /*
             * Als de waarde van de coefficient 1.0 of -1.0 is wordt er een spatie aan de String toegevoegd
             * om het makkelijker af te kunnen lezen.
             */
            if (Math.abs(this.coefficient) != 1.0 && this.coefficient != 0) {
                polynoomZin += " ";
            }
            polynoomZin += "x";

            /* Als de macht niet 1 is wordt deze aan de String toegevoegd. */
            if (this.macht != 1) {
                polynoomZin += "^" + this.macht;
            }
        }
        return polynoomZin;
    }
}
