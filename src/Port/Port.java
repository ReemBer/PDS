package Port;

import Pier.Pier;
import Ship.Cargo;
import Ship.Ship;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by Tarasevich Vladislav on 09.04.2017.
 * This class used to store the state of the warehouse and work with ships through the piers
 * @version 1.0
 */
public class Port
{
    private final int COUNT_OF_PIERS =  5;
    private final int QUEUE_SIZE     = 20;

    private Warehouse warehouse;
    private ArrayBlockingQueue<Ship> shipRequests;
    private Pier pier[];

    private boolean processing;
    private boolean suspended;

    public Port()
    {
        processing = false;
        suspended  = false;
        warehouse  = new Warehouse();
        shipRequests = new ArrayBlockingQueue<Ship>(QUEUE_SIZE);
        pier = new Pier[COUNT_OF_PIERS];
    }

    /**
     * This method used to get started Working
     */
    public void getStarted()
    {
        if(!processing)
        {
            for (int i = 0; i < COUNT_OF_PIERS; ++i)
            {
                pier[i].start();
            }
            processing = true;
        }
    }

    /**
     * Used to paused working of all the Piers
     */
    public void suspendProcess()
    {
        if(processing && !suspended)
        {
            suspended = true;
            for (int i = 0; i < COUNT_OF_PIERS; ++i)
            {
                pier[i].suspend();
            }
        }
    }

    /**
     * Used to resume working of all the Piers
     */
    public void resumeProcess()
    {
        if(processing && suspended)
        {
            suspended = false;
            for (int i = 0; i < COUNT_OF_PIERS; ++i)
            {
                pier[i].resume();
            }
        }
    }

    /**
     * This method used to trying to take some cargo from the warehouse
     * @param cargo type of cargo, needed to take
     * @param count count of type of cargo, needed to take
     * @return true, if you can take such count of such cargo
     */
    public synchronized boolean takeCargo(Cargo cargo, int count)
    {
        return warehouse.takeCargo(cargo, count);
    }

    /**
     * This method used to put some cargo into the warehouse
     * @param cargo type of cargo, wanted to put
     * @param count count of type of cargo, wanted to put
     */
    public synchronized void putCargo(Cargo cargo, int count)
    {
        warehouse.putCargo(cargo, count);
    }

    /**
     * This method used to take first ship request in the queue.
     * @return first ship Request in the queue of ship requests.
     * @throws InterruptedException
     */
    public synchronized Ship takeCurrentRequest() throws InterruptedException
    {
        return shipRequests.take();
    }

    /**
     * This method used to putting current request to the queue of requests.
     * @param currentShip putting to the queue of requests.
     * @throws InterruptedException
     */
    public synchronized void putCurrentRequest(Ship currentShip) throws InterruptedException
    {
        shipRequests.put(currentShip);
    }
}
