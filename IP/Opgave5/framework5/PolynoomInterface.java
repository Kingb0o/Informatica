/**
 * Contract voor classen die veeltermen ondersteunen.
 *
 * XXX: PAS DIT BESTAND NIET AAN!
 */
interface PolynoomInterface
{
    /**
     * Bereken de optelling van de huidige veelterm met <tt>p</tt>.
     * @param p Een veelterm
     * @return De optelling van <tt>this</tt> met <tt>p</tt>
     */
    Polynoom telOp(Polynoom p);
    /**
     * Bereken het verschil van de huidige veelterm met <tt>p</tt>.
     * @param p Een veelterm
     * @return Het verschil van <tt>this</tt> met <tt>p</tt>
     */
    Polynoom trekAf(Polynoom p);
    /**
     * Bereken het product van de huidige veelterm met <tt>p</tt>.
     * @param p Een veelterm
     * @return Het product van <tt>this</tt> met <tt>p</tt>
     */
    Polynoom vermenigvuldig(Polynoom p);
    /**
     * Bepaal de afgeleide.
     * @return De afgeleide veelterm
     */
    Polynoom differentieer();
    /**
     * Integreer de veelterm met integratieconstante <tt>c</tt>.
     * @param c De integratieconstante
     * @return De geïntegreerde polynoom
     */
    Polynoom integreer(double c);
    /**
     * {@inheritdoc}
     */
    boolean equals(Object o);
    /**
     * {@inheritdoc}
     */
    String toString();
}
