/*
* Naam : W. van der LInde
* UvAnetID : 12857130
* Studie : BSc Informatica
*
* WebReceptie.java:
*   Dit bestand is de gebruikers webinterface voor het aangemaakte hotel waar de gebruiker met gebruik
*   van functionaliteit van de website de gegevens van het hotel kan manipuleren om zo het in en uit
*   checken van gasten te simuleren.
*/

import java.io.IOException;
import java.io.BufferedReader;
import java.io.UnsupportedEncodingException;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

class WebReceptie implements ReceptieInterface {
    final static String TEMPLATE = "" +
        "<html><head><title>Hotel systeem</title><style>" +
        "body { margin: auto; max-width: 900px; line-height: 1.6; font-size: 18px; }" +
        ".alert { color: #D8000C; padding: 20px; background-color: #FFBABA; }" +
        ".success { color: #4F8A10; padding: 20px; background-color: #DFF2BF; }" +
        "</style></head><body>" +
        "%s" +
        "<h1>%s &ndash; %s</h1>" +
        "%s" +
        "<br><a href='/'>Status</a> <a href='/checkin'>Check-in</a> <a href='/checkUit'>Check-uit</a></body></html>";

    String lastMessage = null;
    HttpServer server;
    Hotel hotel;

    public static void main(String[] args) {
        if (args.length == 1) {
            new WebReceptie(new Hotel(Integer.parseInt(args[0]))).loop();
        } else {
            new WebReceptie(new Hotel()).loop();
        }
    }

    WebReceptie(Hotel hotel) {
        this.hotel = hotel;
        try {
            server = HttpServer.create(new InetSocketAddress(8000), 0);
            server.createContext("/", new StatusHandler());
            server.createContext("/checkin", new CheckInHandler());
            server.createContext("/checkUit", new checkUitHandler());
        } catch (IOException e) {
            return;
        }
    }

    public void loop() {
        server.start();
        System.out.println("WebReceptie gestart! http://127.0.0.1:8000");
    }

    private int getPostNummertje(HttpExchange t) {
        try {
            InputStreamReader isr = new InputStreamReader(t.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            StringBuilder buf = new StringBuilder(512);
            int b;

            while ((b = br.read()) != -1) {
                buf.append((char) b);
            }

            br.close();
            isr.close();

            return Integer.parseInt(buf.toString().split("=")[1].trim());
        } catch (UnsupportedEncodingException e) {
            return -1;
        } catch (IOException e) {
            return -1;
        }
    }

    private String consumeMessage() {
        String tmp = lastMessage;
        lastMessage = null;
        return tmp;
    }

    private class CheckInHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) {

            if (t.getRequestMethod().equals("POST")) {
                int aantal = getPostNummertje(t);

                try {
                    hotel.maakGasten(aantal);
                    lastMessage = "<div class='success'>Groep is succesvol ingecheckt!</div>";
                } catch (GroepPastNietException e) {
                    lastMessage = "<div class='alert'>Deze groep past helaas niet in het hotel.</div>";
                }
            }

            String response = String.format(TEMPLATE, (lastMessage != null ? consumeMessage() : ""), hotel, "Check-in", "Aantal mensen dat je wilt inchecken:<br><form method='post'><input type='number' name='quantity' min='1'><input type='submit'></form>");

            try {
                t.sendResponseHeaders(200, response.length());
                OutputStream os = t.getResponseBody();
                os.write(response.getBytes());
                os.close();
            } catch (IOException e) {
                return;
            }
        }
    }

    private class checkUitHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) {
            if (t.getRequestMethod().equals("POST")) {
                int kamernummer = getPostNummertje(t);
                hotel.checkUit(kamernummer);
                lastMessage = "<div class='success'>Kamer is succesvol geleegd!</div>";
            }

            String response = String.format(TEMPLATE, (lastMessage != null ? consumeMessage() : ""), hotel, "Check-uit", "Welke kamer wil je uitchecken?<br><form method='post'><input type='number' name='quantity' min='1'><input type='submit'></form>");

            try {
                t.sendResponseHeaders(200, response.length());
                OutputStream os = t.getResponseBody();
                os.write(response.getBytes());
                os.close();
            } catch (IOException e) {
                return;
            }
        }
    }


    private class StatusHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) {
            String content = "Er zijn " + hotel.getAantalKamers() + " kamers in het hotel.<dl>";

            for (int i = 0; i < hotel.getAantalKamers(); i++) {
                content += "<dt><strong>Kamer " + (i) + " (maximaal " + hotel.getKamer(i).getGasten().getCapaciteit() + " gasten)</strong></dt>";

                if (hotel.getKamer(i) == null) {
                    content += "<dd>Nog in aanbouw</dd>";
                } else if (hotel.getKamer(i).getGasten().isLeeg()) {
                    content += "<dd>Leeg</dd>";
                } else {
                    for (Gast g : hotel.getKamer(i).getGasten()) {
                        content += "<dd>" + g + "</dd>";
                    }
                }
            }

            content += "</dl>";

            String response = String.format(TEMPLATE, (lastMessage != null ? consumeMessage() : ""), hotel, "Status", content);

            try {
                t.sendResponseHeaders(200, response.length());
                OutputStream os = t.getResponseBody();
                os.write(response.getBytes());
                os.close();
            } catch (IOException e) {
                return;
            }
        }
    }
}
