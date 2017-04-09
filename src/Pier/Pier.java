package Pier;

import Port.Port;
import Ship.Ship;

/**
 * Created by Tarasevich Vladislav on 09.04.2017.
 * @author Tarasevich Vladislav
 * This class used by the Port clas to communicate between
 * Ship and Port
 */
public class Pier
{
    private Port  parentPort;
    private State state;
    private Ship  ship;

    public Pier()
    {
        parentPort = null;
        state = State.BLOCKED;
        ship = null;
    }

    public Pier(Port parentPort)
    {
        this.parentPort = parentPort;
        state = State.WAITING;
        ship = null;
    }
}
