/*
* Naam : W. van der LInde
* UvAnetID : 12857130
* Studie : BSc Informatica
*
* Turtle.java:
*   Generates a turtle object with a position, angle, width and length. It can give back lines according
*   to a set rules that have been hard coded in the file.
*/

import java.io.*;
import java.util.*;
import java.awt.geom.Point2D;
import java.awt.geom.Line2D;

public class Turtle {
    public double turtleX;
    public double turtleY;
    public double length;
    public final int width;
    public double angle;
    public double modifier;
    public Stacks stacks = new Stacks();

    /* Creates a new Turtle object with the given input file. This object is responsible for calculating
     * the next positions for the lines that have to drawn according to the string that was given.
     */
    Turtle(String inputFile) throws Exception {

        try (InputStream input = new FileInputStream(inputFile)) {
            Properties prop = new Properties();
            prop.load(input);

            /* Gets the values from the file and assigns them to their corresponding variables.
             * If the properties are not present they are set to default values. */
            this.turtleX = Double.parseDouble(prop.getProperty("turtle.pos.x", "400"));
            this.turtleY = Double.parseDouble(prop.getProperty("turtle.pos.y", "400"));
            this.angle = Double.parseDouble(prop.getProperty("turtle.angle", "180"));
            this.length = Double.parseDouble(prop.getProperty("turtle.line.length", "2"));
            this.width = Integer.parseInt(prop.getProperty("turtle.line.width", "2"));
            this.modifier = Double.parseDouble(prop.getProperty("turtle.angle.modifier", "60.0"));
        } catch (Exception e) {
            throw new Exception("Incorrect inlees bestand.");
        }
    }

    /* For every character that is given it calculates the action it needs to take (returning a line
     * or a null.
     */
    public Line2D.Double teken(char instruction) {
        Point2D.Double begin = new Point2D.Double(this.turtleX, this.turtleY);

        switch(instruction) {
            /* If the instructions 'F' or 'G' are given the method returns a line with the old position
             * as a starting position and calculates the new ending position according to given/defined
             * values.
             */
            case 'F': case 'G':
                begin = new Point2D.Double(this.turtleX, this.turtleY);
                this.turtleX += this.length * Math.cos(Math.toRadians(this.angle));
                this.turtleY += this.length * Math.sin(Math.toRadians(this.angle));
                Point2D.Double end = new Point2D.Double(this.turtleX, this.turtleY);
                return new Line2D.Double(begin, end);
            /* Modifies the angle by adding the angle modifier to the angle. */
            case '+':
                this.angle += this.modifier;
                break;
            /* Modifies the angle by substracting the angle modifier of the angle. */
            case '-':
                this.angle -= this.modifier;
                break;
            /* Stores the current position of the turtle in a stack. */
            case '[':
                stacks.point_push(this.turtleX, this.turtleY, this.angle);
                break;
            /* Retrieves the last stored positions of the turtle in the stack. */
            case ']':
                this.turtleX = (double)stacks.getLastPointX();
                this.turtleY = (double)stacks.getLastPointY();
                this.angle = (double)stacks.getLastAngle();
                break;
            default:
                return null;
        }
        return null;
    }
}