/*
* Naam : W. van der LInde
* UvAnetID : 12857130
* Studie : BSc Informatica
*
* TestComplexGetal.java:
*   Test verschillende functies van ComplexGetal.java.

*/

public class TestComplexGetal {
    public static void main(String[] args) {
        Breuk b1 = new Breuk(1,2);
        Breuk b2 = new Breuk();
        Breuk b3 = new Breuk(4,2);
        ComplexGetal cg1 = new ComplexGetal(1, 2, 4, 5);
        ComplexGetal cg2 = new ComplexGetal(b1, b2);
        ComplexGetal cg3 = new ComplexGetal(b2, b3);

        System.out.println(cg1);
        System.out.println(cg2);
        System.out.println(cg3);
        System.out.println("--------------------------------------------------");

        Breuk breuk1 = new Breuk(8, -34);
        Breuk breuk2 = new Breuk(1, 23);
        Breuk breuk3 = new Breuk(1, 9);
        Breuk breuk4 = new Breuk(1, 17);
        ComplexGetal comp1 = new ComplexGetal(breuk1, breuk2);
        ComplexGetal comp2 = new ComplexGetal(breuk3, breuk4);
        ComplexGetal compGetal = comp1.deel(comp2);
        System.out.println("(" + comp1.toString() + ") / (" + comp2.toString() + ") = " + compGetal);
        System.out.println("--------------------------------------------------");

        ComplexGetal deel = cg1.deel(cg2);
        ComplexGetal keer = cg1.vermenigvuldig(cg2.omgekeerde());
        Boolean check = keer.equals(deel);
        System.out.println("(" + cg1.toString() + ") / (" + cg2.toString() + ") = " + deel);
        System.out.println("(" + cg1.toString() + ") * (" + cg2.omgekeerde().toString() + ") = " + keer);

        if(check == true) {
            System.out.println("Deze complexe getallen zijn gelijk");
        } else {
            System.out.println("Deze complexe getallen zijn niet gelijk");
        }
        System.out.println("--------------------------------------------------");

        ComplexGetal Huts1 = new ComplexGetal(2, 4, 5, 6);
        ComplexGetal Huts2 = new ComplexGetal(1, 2, 3, 4);
        ComplexGetal deelHuts = Huts1.deel(Huts2);
        ComplexGetal keerHuts = Huts1.vermenigvuldig(Huts2);
        Boolean checkHuts = deelHuts.equals(keerHuts);
        System.out.println("(" + Huts1.toString() + ") / (" + Huts2.toString() + ") = " + deelHuts);
        System.out.println("(" + Huts1.toString() + ") * (" + Huts2.toString() + ") = " + keerHuts);

        if(checkHuts == true) {
            System.out.println("Deze complexe getallen zijn gelijk");
        } else {
            System.out.println("Deze complexe getallen zijn niet gelijk");
        }
    }
}