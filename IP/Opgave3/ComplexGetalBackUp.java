/*
* Naam : W. van der LInde
* UvAnetID : 12857130
* Studie : BSc Informatica
*
* ComplexGetal.java:
*
*/

import java.util.*;

public class ComplexGetalBackUp {
    public double re, im;

    public ComplexGetalBackUp(double reIn, double imIn) {
        this.re = reIn;
        this.im = imIn;
    }

    public ComplexGetalBackUp telOp(ComplexGetalBackUp getal) {
        double reNieuw = this.re + getal.re;
        double imNieuw = this.im + getal.im;
        return new ComplexGetal(reNieuw, imNieuw);
    }

    public ComplexGetalBackUp trekAf(ComplexGetalBackUp getal) {
        double reNieuw = this.re - getal.re;
        double imNieuw = this.im - getal.im;
        return new ComplexGetal(reNieuw, imNieuw);
    }

    public ComplexGetalBackUp vermenigvuldig(ComplexGetalBackUp getal) {
        double reNieuw = (this.re * getal.re) - (this.im * getal.im);
        double imNieuw = (this.re * getal.im) + (this.im * getal.re);
        return new ComplexGetal(reNieuw, imNieuw);
    }

    public ComplexGetalBackUp deel(ComplexGetalBackUp getal) {
        double ac = this.re * getal.re;
        double bd = this.im * getal.im;
        double bc = this.im * getal.re;
        double ad = this.re * getal.im;
        double tellerIm = ac + bd;
        double tellerRe = bc - ad;
        double noemer = getal.re * getal.re + getal.im * getal.im;
        double breukRe = tellerRe/noemer;
        double breukIm = tellerIm/noemer;
        return new ComplexGetal(breukRe, breukIm);
    }

    public ComplexGetalBackUp omgekeerde() {
        double noemer = (this.re * this.re + this.im * this.im);
        double tellerRe = (this.re);
        double tellerIm = (this.im);
        double reNieuw = tellerRe/noemer;
        double imNieuw = tellerIm/noemer;
        return new ComplexGetal(reNieuw, imNieuw);
    }

    public String toString() {
        String getalZin;
        getalZin = this.re + " + " + this.im + "i";
        return getalZin;
    }

    public ComplexGetalBackUp equals(ComplexGetalBackUp getal) {
        if(this.re == getal.re && this.im == getal.im) {
            System.out.println("Gelijk");
            return getal;
        }
        System.out.println("Ongelijk");
        return getal;
    }
}