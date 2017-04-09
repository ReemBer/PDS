package Pier;

import Port.Port;
import Ship.Ship;

/**
 * Created by Tarasevich Vladislav on 09.04.2017.
 * @author Tarasevich Vladislav
 * This class used by the Port clas to communicate between
 * Ship and Port
 */
public class Pier extends Thread
{
    private Port  parentPort;
    private PierState state;
    private Ship  ship;

    private TimeCost timeCost;

    public Pier()
    {
        parentPort = null;
        state = PierState.BLOCKED;
        ship = null;
    }

    public Pier(Port parentPort)
    {
        this.parentPort = parentPort;
        state = PierState.WAITING;
        ship = null;
    }

    public PierState getPierState()
    {
        return state;
    }

    public Ship getShip()
    {
        return ship;
    }

    public void setParentPort(Port parentPort)
    {
        this.parentPort = parentPort;
    }

    public void setPierState(PierState state)
    {
        this.state = state;
    }

    public void setShip(Ship ship)
    {
        this.ship = ship;
    }

    /**
     * This method are designed to processing currentShip
     * and update progressBar state
     */
    public void process() // TODO: 10.04.2017 Доделать  
    {
        int fullTime = calculateTime();
        int steep;

        switch(ship.getCargo())
        {
            case OIL:
            {
                steep = timeCost.FOR_OIL;
            }
            case GAS:
            {
                steep = timeCost.FOR_GAS;
            }
            case FOOD:
            {
                steep = timeCost.FOR_FOOD;
            }
            case CARS:
            {
                steep =  timeCost.FOR_CARS;
            }
        }
    }


    /**
     * This method used by process method to get full processing time
     * for current ship
     * @return full time, needed to process current Ship
     */
    private int calculateTime()
    {
        switch(ship.getCargo())
        {
            case OIL:
            {
                return timeCost.FOR_OIL * ship.getCount();
            }
            case GAS:
            {
                return timeCost.FOR_GAS * ship.getCount();
            }
            case FOOD:
            {
                return timeCost.FOR_FOOD * ship.getCount();
            }
            case CARS:
            {
                return timeCost.FOR_CARS * ship.getCount();
            }
            default:
            {
                return 0;
            }
        }
    }
}
