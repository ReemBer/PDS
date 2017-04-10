package Port;

import Ship.Cargo;
import Ship.Ship;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by Tarasevich Vladislav on 09.04.2017.
 * This class used to store the state of the warehouse and work with ships through the piers
 */
public class Port
{
    private Warehouse warehouse;
    private ArrayBlockingQueue<Ship> shipRequests;

    /**
     * This method used to trying to take some cargo from the warehouse
     * @param cargo type of cargo, needed to take
     * @param count count of type of cargo, needed to take
     * @return true, if you can take such count of such cargo
     */
    public boolean takeCargo(Cargo cargo, int count)
    {
        return warehouse.takeCargo(cargo, count);
    }

    /**
     * This method used to put some cargo into the warehouse
     * @param cargo type of cargo, wanted to put
     * @param count count of type of cargo, wanted to put
     */
    public void putCargo(Cargo cargo, int count)
    {
        warehouse.putCargo(cargo, count);
    }

    /**
     * This method used to take first ship request in the queue.
     * @return first ship Request in the queue of ship requests.
     * @throws InterruptedException
     */
    public Ship takeCurrentRequest() throws InterruptedException
    {
        return shipRequests.take();
    }

    /**
     * This method used to putting current request to the queue of requests.
     * @param currentShip putting to the queue of requests.
     * @throws InterruptedException
     */
    public void putCurrentRequest(Ship currentShip) throws InterruptedException
    {
        shipRequests.put(currentShip);
    }
}
