/*
* Naam : W. van der LInde
* UvAnetID : 12857130
* Studie : BSc Informatica
*
* Opgave4.java:
*   In this assignment we created a java database in which
*   we can store strings and compare those strings. Futhermore
*   we can also manipulate this database.
*/

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Opgave4 {
    public static ArrayList<String> database;

    /* Reads input of user. */
    public static String ReadLine() {
        Scanner input = new Scanner(System.in);

        if(input.hasNextLine()) {
            return input.nextLine();
        }
        return "No sentence";
    }

    /* Reads sentences in text file and stores them in the database. */
    public static void Read(String file) {
        File fl = new File(file);
        Scanner s;
        try{
            s = new Scanner(fl);
        } catch (FileNotFoundException e) {
            System.out.println("Error");
            return;
        }

        while(s.hasNextLine()) {
            Add(s.nextLine());
        }
    }

    /* Prints list of commands. */
    public static String helpuser() {
        System.out.println("LIST OF COMMANDS\n");
        System.out.println("list                print database");
        System.out.println("add ...             add to database");
        System.out.println("compare ... ...     compare two strings");
        System.out.println("read ...            read from file and add to database");
        System.out.println("retrieve ...        find in database");
        System.out.println("remove ...          remove from database");
        System.out.println("quit                stop");
        System.out.println("help                print this text");
        return "";
    }

    /* Prints every sentence that is stored in the database. */
    public static void List() {
        for(String s : database) {
            System.out.println(s);
        }
        System.out.println();
    }

    /*
     * Adds sentence to database. Also checks if the same sentence
     * is not already in the database otherwise it gets skipped.
     */
    public static void Add(String i) {
        for(int j = 0; j < database.size()-1; j++) {
            if(database.get(j).equals(i)) {
                System.out.println("Warning: " + i + " already added, skipping.");
                return;
            }
        }
        database.add(i);
    }

    /* Removes string from database if the string is actually present. */
    public static void Remove(String i) {
        for(int j = 0; j < database.size(); j++) {
            if(database.get(j).equals(i)) {
                database.remove(database.indexOf(i));
                return;
            }
        }
        System.out.println(i + " not in database, nothing done");
    }

    /* Prints output of compare function. */
    public static void Compare(String a, String aa) {
        System.out.println("Difference = " + Min(a, aa, true));
    }

    /*
     * Compares two strings using the Levenshtein algorithm. And returns
     * the three values which mostly correspond with the given string.
     * src: https://en.wikipedia.org/wiki/Levenshtein_distance.
     */
    public static int Min(String a, String b, boolean c) {
        int[][] d = new int[a.length() + 1][b.length() + 1];
        int i = 0;
        int j = 0;
        int subCost;

        for(; i <= a.length(); i++) {
            d[i][0] = i;
        }

        for(; j <= b.length(); j++) {
            d[0][j] = j;
        }

        /*
         * Checks if two strings are compared via an algorithm that gives
         * a value if chars at the same index are equal or need to be replaced.
         * This algortihm gives back a certain value for each change.
         */
        for(j = 1; j <= b.length(); j++) {
            for(i = 1; i <= a.length(); i++) {
                if(a.charAt(i-1) == b.charAt(j-1)) {
                    subCost = 0;
                } else {
                    subCost = 1;
                }
                d[i][j] = Min_3(d[i-1][j] + 1,
                                d[i][j-1] + 1,
                                d[i-1][j-1] + subCost);
            }
        }

        /* Prints a table of the values stored by the Levensteihn Algortihm. */
        if(c) {
            for(int l1 = 0; l1 <= a.length(); l1++) {
                for(int l2 = 0; l2 <= b.length(); l2++) {
                    System.out.print(d[l1][l2] + "  ");
                }
                System.out.println("\n");
            }
        }
        return d[a.length()][b.length()];
    }

    /* Return lowest int from three given values. */
    private static int Min_3(int a, int b, int c) {
        if(a < b && a < c) {
            return a;
        } else if (b < c && b < a) {
            return b;
        } else {
            return c;
        }
    }

    /*
     * Using the gnome sort algorithm a given string is compared to the
     * strings in the database. src: https://en.wikipedia.org/wiki/Gnome_sort.
     */
    public static void Retrieve(String i) {
        if(database.size() == 0) {
            System.out.println("The database is empty, there are no matches.");
            return;
        }
        String[] sq = new String[database.size()];
        database.toArray(sq);
        int r = 0, x = 1;
        int[] ds = new int[database.size()];

        for(String s : database) {
            ds[r++] = Min(i, s, false);
        }

        /*
         * Check two values in an array. If the right value in the array
         * is larger than the left, both items get swapped. After this
         * action the x value gets decremented by one and the cycle continues.
         * If the left value is larger the cycle just continues.
         */
        while(x < ds.length) {
            if(x == 0) {
                x++;
            } else if(ds[x] >= ds[x - 1]) {
                x++;
            } else {
                int tmp1 = ds[x];
                String tmp2 = sq[x];
                ds[x] = ds[x - 1];
                sq[x] = sq[x - 1];
                ds[x - 1] = tmp1;
                sq[x - 1] = tmp2;
                x--;
            }
        }
        System.out.println("Best matches: ");

        for(r = 0; r < Math.min(3, sq.length); r++) {
            System.out.println(ds[r] + "\t" + sq[r]);
        }
    }

    /* This is the user interface. */
    public static void exeCnsl() {
        boolean quit = false;
        do {
            System.out.print("console>");
            String A;
            A = ReadLine();
            String[] P = A.split("\\s");
            String C = P[0].toLowerCase();

            if(C.equals("help")) {
                helpuser();
            } else if(C.equals("quit")) {
                quit = true;
            } else if(C.equals("list")) {
                List();
            } else if(C.equals("add") && P.length > 1) {
                Add(P[1]);
            } else if(C.equals("remove") && P.length > 1) {
                Remove(P[1]);
            } else if(C.equals("compare") && P.length > 2) {
                Compare(P[1], P[2]);
            } else if(C.equals("retrieve")  && P.length > 1) {
                Retrieve(P[1]);
            } else if(C.equals("read") && P.length > 1) {
                Read(P[1]);
            } else {
                System.out.println("Skipping...");
            }
        } while(quit == false);
        return;
    }

    /* This is the starting screen. */
    public static void main(String[] args) {
        System.out.println("Welcome to DNA Matcher v0.1\n");
        database = new ArrayList<String>();
        exeCnsl();
    }
}
