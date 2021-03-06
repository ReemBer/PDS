package Pier;

import Port.Port;
import Ship.Ship;
import org.apache.log4j.Logger;



/**
 * Created by Tarasevich Vladislav on 09.04.2017.
 * @author Tarasevich Vladislav
 * @version 1.0
 * This class used by the Port class to communicate between
 * Ship and Port
 */
public class Pier extends Thread
{
    private final int STEEP_TIME = 100;
    private Port  parentPort;
    private PierState state;

    private Ship  ship;
    private TimeCost timeCost;

    private static final Logger logger = Logger.getLogger(Pier.class);

    private int myIndex;

    public Pier()
    {
        timeCost = new TimeCost();
        parentPort = null;
        state = PierState.BLOCKED;
        ship = null;
    }

    public Pier(Port parentPort, int myIndex)
    {
        this.myIndex = myIndex;
        timeCost = new TimeCost();
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
     * The main method to process requests.
     */
    public void run()
    {
        try
        {
            while(true)
            {
                ship = parentPort.takeCurrentRequest();
                process();
            }
        }
        catch (InterruptedException ex)
        {
            logger.fatal("Работа " + getName() + " Прервана исключением InterruptedException.");
        }
    }

    /**
     * This method are designed to processing currentShip
     * and update progressBar state
     */
    private void process() throws InterruptedException
    {
        String message = "Name : " + ship.getName() + "\nCargo : " + ship.getCargo() +
                         "\nCount : " + ship.getCount() + "\nRequest : " +
                         (ship.isLoadRequest() ? "Loading" : "Unloading");
        parentPort.getController().setCurrentPierRequest(myIndex, message);
        if(ship.isLoadRequest())
        {
            state = PierState.LOADING;
            if(!parentPort.takeCargo(ship.getCargo(), ship.getCount()))
            {
                logger.error(getName() + " : " + ship.getName() + " : Недостаточное количество товара " +
                        ship.getCargo() + " на складе (нужно " + ship.getCount() + " едениц).");
                for(int i = 0; i < 5; ++i)
                {
                    parentPort.getController().setPierProgress(myIndex, 1);
                    sleep(10*STEEP_TIME);
                    parentPort.getController().setPierProgress(myIndex, 0);
                    sleep(10*STEEP_TIME);
                }
                parentPort.incrementProcessedCount();
                return;
            }
        }
        else
        {
            state = PierState.UNLOADING;
            parentPort.putCargo(ship.getCargo(), ship.getCount());
        }


        int fullTime = calculateTime();

        for(int time = 0; time <= fullTime; ++time)
        {
            parentPort.getController().setPierProgress(myIndex, ((double)time/(double)fullTime));
            sleep(STEEP_TIME);
        }
        parentPort.getController().setPierProgress(myIndex, 0.);
        System.out.println(getName() + " : Completed : " + ship.getName() + " , " + ship.getCargo() + " , " + ship.getCount() + " , " + ship.isLoadRequest());
        parentPort.incrementProcessedCount();
        logger.info(getName() + " : " + ship.getName() + " : Запрос обработан успешно (" + (ship.isLoadRequest() ? '-' : '+') +
                    ship.getCount() + " " + ship.getCargo() + ")");

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
