package Port;

import GUI.View.ShipRequestsOverviewController;
import Pier.Pier;
import Ship.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.log4j.Logger;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by Tarasevich Vladislav on 09.04.2017.
 * This class used to store the state of the warehouse and work with ships through the piers
 * @version 1.2
 */
public class Port
{
    private final int COUNT_OF_PIERS = 5;
    private final int QUEUE_SIZE     = 15;

    private static final Logger logger = Logger.getLogger(Port.class);

    private final Object shipRequestListLock = new Object();
    private final Object warehouseLock = new Object();
    private final Object incrementLock = new Object();

    private volatile Warehouse warehouse;
    private volatile ArrayBlockingQueue<Ship> shipRequests;
    private volatile ObservableList<Ship> shipRequestsList = FXCollections.observableArrayList();
    private volatile ObservableList<StateUnit>   statusLog = FXCollections.observableArrayList();

    private ShipGenerator shipGenerator;
    private StatusLog     statusLogThread;

    private Pier pier[];

    private int processedCount = 0;

    private ShipRequestsOverviewController controller;

    private volatile boolean processing;
    private volatile boolean suspended;
    private volatile boolean generatorSuspended;

    public Port(ShipRequestsOverviewController controller)
    {
        this.controller = controller;
        processing = false;
        suspended  = false;

        warehouse  = new Warehouse();
        shipRequests = new ArrayBlockingQueue<Ship>(QUEUE_SIZE);
        shipRequestsList.addAll(shipRequests);
        pier = new Pier[COUNT_OF_PIERS];
        shipGenerator = new ShipGenerator(this);
        statusLogThread = new StatusLog(this);
        statusLogThread.setDaemon(true);
        shipGenerator.setDaemon(true);

        for(int i = 0; i < COUNT_OF_PIERS; ++i)
        {
            pier[i] = new Pier(this, i);
            pier[i].setName("Pier " + (i+1));
            pier[i].setDaemon(true);
        }
    }

    public ShipRequestsOverviewController getController()
    {
        return controller;
    }

    public ObservableList<Ship> getShipRequestsData()
    {
        return shipRequestsList;
    }


    public ObservableList<StateUnit> getStatusLog()
    {
        return statusLog;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public int getQueueSize()
    {
        return shipRequests.size();
    }

    public void incrementProcessedCount()
    {
        synchronized (incrementLock)
        {
            ++processedCount;
        }
    }

    public int getProcessedCount()
    {
        return processedCount;
    }

    /**
     * method, used to view current state of queue of requests;
     * @return array of ships (requests)
     */
    public Ship[] getShipRequests()
    {
        return shipRequests.toArray(new Ship[shipRequests.size()]);
    }

    /**
     * This method used to get started Working
     */
    public synchronized void getStarted()
    {
        if(!processing)
        {
            for (int i = 0; i < COUNT_OF_PIERS; ++i)
            {
                pier[i].start();
            }
            shipGenerator.start();
            generatorSuspended = false;
            statusLogThread.start();
            processing = true;

            logger.info("Запуск рабботы порта.");
        }
    }

    /**
     * Used to paused working of all the Piers
     */
    public synchronized void suspendProcess()
    {
        if(processing && !suspended)
        {
            suspended = true;
            if(!generatorSuspended)
            {
                shipGenerator.suspend();
                generatorSuspended = true;
            }
            for (int i = 0; i < COUNT_OF_PIERS; ++i)
            {
                pier[i].suspend();
            }
            logger.info("Работа порта приостановлена.");
        }
    }

    /**
     * Used to resume working of all the Piers
     */
    public synchronized void resumeProcess()
    {
        if(processing && suspended)
        {
            suspended = false;
            if(generatorSuspended)
            {
                shipGenerator.resume();
                generatorSuspended = false;
            }
            for (int i = 0; i < COUNT_OF_PIERS; ++i)
            {
                pier[i].resume();
            }
            logger.info("Работа порта возобновлена.");
        }
    }

    /**
     * Used for stop processing
     */
    public synchronized void stopProcess()
    {
        shipGenerator.stop();
        for(int i = 0; i < COUNT_OF_PIERS; ++i)
        {
            pier[i].stop();
        }
        logger.info("Работа порта завершена.");
    }

    public synchronized void suspendGenerator()
    {
        if(!generatorSuspended)
        {
            shipGenerator.suspend();
            generatorSuspended = true;
            logger.info("Работа генератора запросов приостановлена.");
        }
    }

    public synchronized void resumeGenerator()
    {
        if(generatorSuspended)
        {
            shipGenerator.resume();
            generatorSuspended = false;

            logger.info("Работа генератора запросов возобновлена.");
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
        synchronized (warehouseLock)
        {
            return warehouse.takeCargo(cargo, count);
        }
    }

    /**
     * This method used to put some cargo into the warehouse
     * @param cargo type of cargo, wanted to put
     * @param count count of type of cargo, wanted to put
     */
    public void putCargo(Cargo cargo, int count)
    {
        synchronized (warehouseLock)
        {
            warehouse.putCargo(cargo, count);
        }
    }

    /**
     * This method used to take first ship request in the queue.
     * @return first ship Request in the queue of ship requests.
     * @throws InterruptedException
     */
    public Ship takeCurrentRequest() throws InterruptedException
    {
        Ship result = shipRequests.take();
        synchronized (shipRequestListLock)
        {
            shipRequestsList.setAll(shipRequests);
        }
        return result;
    }

    /**
     * This method used to putting current request to the queue of requests.
     * @param currentShip putting to the queue of requests.
     * @throws InterruptedException
     */
    public void putCurrentRequest(Ship currentShip) throws InterruptedException
    {
        shipRequests.put(currentShip);
        synchronized (shipRequestListLock)
        {
            shipRequestsList.setAll(shipRequests);
        }
    }
}
