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
    private int     steepTime;

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
    public void process()
    {
        // TODO: 10.04.2017 нужно добавить проверку на наличие на складе необходимого количества товаров для запроса загрузки

        if(ship.isLoadRequest())
        {
            state = PierState.LOADING;
        }
        else state = PierState.UNLOADING;


        int fullTime = calculateTime();

        for(int time = 0; time <= fullTime; ++time)
        {
            //sleep(steepTime); // TODO: 10.04.2017 отлавливать этот эксепшн
            // TODO: 10.04.2017 Забацать обновление прогресс бара
        }

        state = PierState.WAITING;
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
