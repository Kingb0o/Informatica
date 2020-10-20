/*
* Naam : W. van der LInde
* UvAnetID : 12857130
* Studie : BSc Informatica
*
* Stacks.java:
*   This object can store different values in stacks. This works a bit like a pile of boxes by adding
*   new values on top or taking them of the top of the pile.
*/

import java.util.*;

public class Stacks {
    private Stack pointX;
    private Stack pointY;
    private Stack turn;

    /* Creates a new Stacks object containing multiple stacks to store the angle and position of the turtle. */
    Stacks() {
        pointX = new Stack();
        pointY = new Stack();
        turn = new Stack();
    }

    /* Stores the values of the turtle in the correspong stacks. */
    @SuppressWarnings("unchecked")
    public void point_push(double TurX, double TurY, double angle) {
        pointX.push(TurX);
        pointY.push(TurY);
        turn.push(angle);
    }

    /* Return the last saved value of the X position of the turtle. */
    public Object getLastPointX() {
        return pointX.pop();
    }

    /* Return the last saved value of the Y position of the turtle. */
    public Object getLastPointY() {
        return pointY.pop();
    }

    /* Return the last saved value of the angle of the turtle. */
    public Object getLastAngle() {
        return turn.pop();
    }
}
