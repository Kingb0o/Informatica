/*
 * Naam : W. van der LInde
 * UvAnetID : 12857130
 * Studie : BSc Informatica
 *
 * Opgave7.java:
 *  Generates a Lindenmayer and turtle object and paints every line for the step size that is given
 *  in the commandline.
 *----------------------------------------------------------------------OLD COMMENT
 * @file         Opgave7.java
 * @author       Jordy Perlee <jordy@perlee.net> (Based on work by Stephen Swatman)
 * @version      2018-10-11
 * @institution  Universiteit van Amsterdam
 * @course       Inleiding Programmeren
 * @assignment   Lindenmayer-systeem (week 7)
 *
 * A graphical interface to display the results of the Lindenmayer-system in.
 *
 */

import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.Timer;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.geom.Line2D;

public class Opgave7 extends JPanel implements ActionListener {
    private final static int HEIGHT = 800, WIDTH = 800;
    private static String inputFile;
    private static Lindenmayer tree;
    private static Turtle turtle;
    private int i = 0;
    private static int linesPerFrame;

    /*
     * Increase the frames per second to speed up the drawing process.
     */
    private final static double FRAMES_PER_SECOND = 10000.0;
    Timer timer = new Timer((int) (1000.0 / FRAMES_PER_SECOND), this);

    private ArrayList<Line2D.Double> lineBuffer;
    static boolean startProcessing = false;

    public static void main(String[] args) throws InterruptedException {
        char b = 'r' - 2;
        System.out.println(b);
        /*
         * Reads the arguments given in the commandline. Where the first argument gives
         * the the file that needs to be read and the second argument reads the speed at
         * which the drawing should be drawn.
         */
        if (args.length == 1) {
            inputFile = "invoer/" + args[0] + ".properties";
            setSpeed(0);
        } else if (args.length == 2) {
            inputFile = "invoer/" + args[0] + ".properties";
            setSpeed(Integer.parseInt(args[1]));
        } else {
            inputFile = "invoer/koch.properties";
            setSpeed(0);
        }
        /*
         * Initialize the JFrame and JPanel used to construct the visual interface.
         */
        JFrame frame = new JFrame("Inleiding Programmeren - Lindenmayer");
        JPanel simulation = new Opgave7();
        simulation.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        frame.add(simulation);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
    }

    /* Creates a new Lindermayer and Turtle object with the given input file. */
    public static void setSpeed(int speed) {
        try {
            tree = new Lindenmayer(inputFile);
            turtle = new Turtle(inputFile);
            startProcessing = true;

            /*
             * If the argument for drawing speed is 0 or larger than the total amount of
             * lines in the sentence the step size (speed) is set to the total amount of
             * lines. Thus drawing it in one go.
             */
            if (speed == 0 || speed > tree.output.length()) {
                linesPerFrame = tree.output.length();
            } else {
                linesPerFrame = speed;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * Constructor for the Opgave7 class, which is used to construct the graphical
     * interface. An example of how to create lines to be drawn on the screen can
     * also be found here.
     */
    Opgave7() {
        timer.start();
        lineBuffer = new ArrayList<Line2D.Double>();
    }

    /*
     * This method is called on every registered event. Currently, the only
     * registered event is the timer defined at the top of this class. Every time
     * this method is called, it calls the repaint method to update the content of
     * the graphical interface.
     */
    public void actionPerformed(ActionEvent e) {
        repaint();
        Toolkit.getDefaultToolkit().sync();
    }

    /*
     * The paint method draws the content of the listBuffer ArrayList onto the
     * screen. It is important to note that the screen is cleared every time this
     * method is called, thus requiring us to redraw all the lines in the buffer.
     * This might seem counter-intuitive now, but it will all be made clear why this
     * is the default behavior during the course 'Graphics and Game Technology' in
     * year 2. ;-)
     */
    public void paint(Graphics graphics) {
        /*
         * Graphics objects are typically 3D objects. Since we're working in only two
         * dimensions, we cast it to a 2D representation of itself.
         */
        Graphics2D graphics2D = (Graphics2D) graphics;

        /* Set the color of the line */
        graphics2D.setStroke(new BasicStroke(turtle.width));
        graphics2D.setColor(new Color(0.0F, 0.6F, 0.1F));

        if (startProcessing) {
            /* The lines of the drawing are added to the linebuffer in a given amount of steps.
             * If all lines for the characters in the string are added to the linebuffer the
             * step size is set to 0 and won't draw any further.
             */
            for (int h = i; h < i + linesPerFrame; h++) {
                if (h == tree.output.length()) {
                    startProcessing = false;
                    return;
                }
                Line2D.Double line = turtle.teken(tree.output.charAt((h)));
                lineBuffer.add(line);
            }
        }

        /* Draw all the lines in the buffer.
         */
        for (Line2D.Double line : lineBuffer) {
            if (line != null)
                graphics2D.draw(line);
        }
        /* The variable i keeps track of the character we passed and thus the linesPerFrame have to
         * be added after each step to keep track of which characters already passed the buffer function.
         */
        i += linesPerFrame;
    }
}

