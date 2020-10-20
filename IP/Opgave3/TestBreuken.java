/*
* Naam : W. van der LInde
* UvAnetID : 12857130
* Studie : BSc Informatica
*
* TestBreuken.java:
*   Test verschillende fucnties van Breuk.java.
*/

public class TestBreuken {
    public static void main(String[] args) {
        Breuk breuk1 = new Breuk(1, 5);
        Breuk breuk2 = new Breuk(2, 4);
        Breuk breuk = breuk2.deel(breuk1);
        Breuk breukKeer = breuk1.vermenigvuldig(breuk2);
        System.out.println(breuk1.toString());
        System.out.println(breuk2.toString());
        System.out.println("Deel: " + breuk);
        System.out.println("keer: " + breukKeer);
        if(breuk1.equals(breuk2) == true) {
            System.out.println("Deze breuken zijn gelijk");
        } else {
            System.out.println("Deze breuken zijn niet gelijk");
        }
    }
}