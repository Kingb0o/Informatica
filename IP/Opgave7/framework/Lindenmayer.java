/*
* Naam : W. van der LInde
* UvAnetID : 12857130
* Studie : BSc Informatica
*
* Lindenmayer.java:
*   Generates a string that cycled through a set of rules for a given recursion depth.
*   The starting string is also given by the input file.
*/

import java.io.*;
import java.util.*;

public class Lindenmayer {
    private int depth = 0;
    private String axiom = "";
    private String rule = "";
    public Map<String, String> rules;
    public String output;

    Lindenmayer(String inputFile) throws Exception {

        /* Reads the depth, axiom and rule keys from the property file and addresses those to their matching variables. */
         try (InputStream input = new FileInputStream(inputFile)) {
            Properties prop = new Properties();
            prop.load(input);

            /* Gets the values from the file and assigns them to their corresponding variables. */
            this.depth = Integer.parseInt(prop.getProperty("recursion.depth", "3"));
            this.axiom = prop.getProperty("axiom", "F");
            this.rule = prop.getProperty("rules", " ");
            this.rules = new Hashtable<>();

            input.close();
        } catch(Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        /* Generates all rules and the total output string that has been generated according to
         * the rules and the recursion depth.
         */
        genRules();
        this.output = this.generate();
    }

    /* Generates a hashtable that contains the stored rules for the Character sequence. */
    public void genRules() {
        String temp = "";
        String in = "";
        String out = "";
        int last = 0;

        /* For the entire string that contains all the rules in the file it detects all commas and
         * splices the string accordingly. Storing the encounter value as the key and the change value
         * as the corresponding value inside the hashtable.
         */
        for (int i = 0; i < this.rule.length(); i++) {
            if (this.rule.charAt(i) == ',') {
                temp = this.rule.substring(last, i);

                /* For every character inside the string for one rule it searches for a space and
                 * than stores the part of the spring before the space as the key and the rest of the
                 * string as the get value for that key. Thus defining the rule.
                 */
                for (int j = 0; j < temp.length(); j++) {
                    if (temp.charAt(j) == ' ') {
                        in = temp.substring(0, j);
                        out = temp.substring(j + 1, temp.length());
                        this.rules.put(in, out);
                    }
                }
                last = i + 1;
            /* This for loop also includes the last rule in the string without giving a exception. */
            } else if ( i + 1 == this.rule.length()) {
                temp = this.rule.substring(last, i + 1);

                for (int j = 0; j < temp.length(); j++) {
                    if (temp.charAt(j) == ' ') {
                        in = temp.substring(0, j);
                        out = temp.substring(j + 1, temp.length());
                        this.rules.put(in, out);
                    }
                }
            }
        }
    }

    /* Generates a string sequence according to the given rules and recursion depth */
    public String generate() {
        String sentence = this.axiom;

        for (int h = 0; h < this.depth; h++) {
            String tempSentence = "";

            /* For every character in the sentence the rules get applied. */
            for (int i = 1; i <= sentence.length(); i++) {
                String temp = sentence.substring(i - 1, i);

                /* If the character is present as a rule it adds the output to the temporary sentence. */
                if (this.rules.containsKey(temp)) {
                    tempSentence += this.rules.get(temp);
                } else {
                    tempSentence += temp;
                }
            }
            /* If the rules are applied for every character in the sentence the new string replaces the old one. */
            sentence = tempSentence;
        }
        return sentence;
    }
}