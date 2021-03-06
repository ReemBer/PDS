package Manager;

import GUI.MainWindow;
import GUI.View.ShipRequestsOverviewController;
import Port.*;
import Ship.*;
import javafx.collections.ObservableList;
import org.apache.log4j.Logger;

/**
 * Created by Tarasevich Vladislav on 11.04.2017.
 * @author name  : Tarasevich Vladislav
 * @author gmail : tarasevich.vlad.97@gmail.com
 * @author phone : +375336492547
 * This is the main classm, used to do communication
 * between GUI and working nodes of program
 */
public class Manager
{
    private final int COUNT_OF_PORTS = 3;

    private static final Logger logger = Logger.getLogger(Manager.class);

    private MainWindow mainWindow;
    private Port port[];
    private int indexOfCurrentPort;

    public Manager(ShipRequestsOverviewController controller)
    {
        indexOfCurrentPort = -1;
        port = new Port[COUNT_OF_PORTS];
        for(int i = 0; i < COUNT_OF_PORTS; ++i)
        {
            port[i] = new Port(controller);
        }
    }

    /**
     * This method used to set new index of the working port
     * @param index -- index of the working port.
     * @throws IndexOutOfBoundsException
     */
    public void setIndexOfWorkingPort(int index) throws IndexOutOfBoundsException
    {
        if(index < 0 || index >= COUNT_OF_PORTS) throw new IndexOutOfBoundsException();

        for(int i = 0; i < COUNT_OF_PORTS; ++i)
        {
            port[i].suspendProcess();
        }

        logger.info("Изменён индекс активного порта : " + index);

        indexOfCurrentPort = index;
    }

    /**
     * This method are used to start working of the current port.
     */
    public void getStarted()
    {
        port[indexOfCurrentPort].getStarted();
    }

    public void suspendWorking()
    {
        port[indexOfCurrentPort].suspendProcess();
    }

    /**
     * method, used to suspend process of current Port
     */
    public void stopWorking()
    {
        port[indexOfCurrentPort].stopProcess();
    }

    /**
     * method, used to resume process of current Port
     */
    public void resumeWorking()
    {
        port[indexOfCurrentPort].resumeProcess();
    }

    public ObservableList<Ship> getShipRequestsData()
    {
        return port[indexOfCurrentPort].getShipRequestsData();
    }

    public ObservableList<StateUnit> getStatusLogData()
    {
        return port[indexOfCurrentPort].getStatusLog();
    }

    public void suspendGenerator()
    {
        port[indexOfCurrentPort].suspendGenerator();
    }

    public void resumeGenerator()
    {
        port[indexOfCurrentPort].resumeGenerator();
    }
}
