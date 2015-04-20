package engine.gameobject;

import java.io.Serializable;
import engine.fieldsetting.Settable;
import engine.pathfinding.EndOfPathException;


/**
 * A strategy that is used by an object in cartesian space to find its next desired position.
 * 
 * @author Kaighn
 *
 */
public interface Mover {

    @Settable
    /**
     * Based on mover's state, returns next destination for moving.
     * 
     * @param current mover's current state
     * @return point of the destination
     * @throws EndOfPathException if unit can move no longer
     */
    public PointSimple move (PointSimple current, double distance) throws EndOfPathException;

    @Settable
    public void setSpeed (double speed);

    public Mover clone ();

}
