import org.junit.*;
import static org.junit.Assert.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestPolynoom {

    Polynoom p1 = new Polynoom(2, 1, 3, 2);
    Polynoom p2 = new Polynoom(-1, -1, 4, 2);
    Polynoom p3 = new Polynoom(3, 5, 2, 4, 1, 3, 0, 2, -1, 1, -2, 0);
    Polynoom p4 = new Polynoom(2, 2);

    /* Er wordt gecheckt of de polynomen correct worden geprint. */
    @Test
    public void TestToString() {
        assertEquals("toString p1 test", "2.0 x + 3.0 x^2", p1.toString());
        assertEquals("toString p2 test", "-x + 4.0 x^2", p2.toString());
        assertEquals("toString p3 test", "3.0 x^5 + 2.0 x^4 + x^3 + x^2 + -x + -2.0", p3.toString());
        assertEquals("toString p4 test", "2.0 x^2", p4.toString());
    }

    /* Er wordt gecheckt of de uitkomsten van het optellen, aftrekken en vermenigvuldigen correct zijn. */
    @Test
    public void OAVTest() {
        assertEquals("Tel p1 en p2 op test", "7.0 x^2 + x", p1.telOp(p2).toString());
        assertEquals("Trek p1 en p2 af test", "-x^2 + 3.0 x", p1.trekAf(p2).toString());
        assertEquals("vermenigvuldig p1 en p4 op test", "6.0 x^4 + 4.0 x^3", p1.vermenigvuldig(p4).toString());

    }

    /* Er wordt gecheckt of de polynomen op correcte wijze worden gedifferentieert en geprimitiveerd. */
    @Test
    public void DITest() {
        assertEquals("Tel p1 en p2 op test", "6.0 x + 2.0", p1.differentieer().toString());
        assertEquals("Tel p1 en p2 op test", "15.0 x^4 + 8.0 x^3 + 3.0 x^2 + x + -1.0", p3.differentieer().toString());
        assertEquals("Tel p1 en p2 op test", "1.3333333333333333 x^3 + -0.5 x^2 + -3.0", p2.integreer(-3.0).toString());

    }

    public static void main(String[] args) {
        System.out.println("Opgave 5 Tester\n---------------");
        Result result = JUnitCore.runClasses(TestPolynoom.class);

        if (result.wasSuccessful()) {
            System.out.println("Hoera, alle tests zijn geslaagd!");
        } else {
            if (result.getFailureCount() > 1) {
                System.out.println("Er zijn " + result.getFailureCount() + " fouten opgetreden:");
            } else {
                System.out.println("Er is 1 fout opgetreden:");
            }

            for (Failure failure : result.getFailures()) {
                System.out.println(failure.toString());
            }
        }

        System.out.println("\nAantal tests uitgevoerd: " + result.getRunCount());
        System.out.println("Het uitvoeren van de tests duurde " + (result.getRunTime() / 1000.0) + " seconden.");
    }
}
