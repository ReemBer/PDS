package Manager;

import GUI.MainWindow;
import Port.Port;
import Ship.*;

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

    private MainWindow mainWindow;
    private Port port[];
    private int indexOfCurrentPort;

    public Manager()
    {
        indexOfCurrentPort = -1;
        port = new Port[COUNT_OF_PORTS];
        for(int i = 0; i < COUNT_OF_PORTS; ++i)
        {
            port[i] = new Port();
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
        indexOfCurrentPort = index;
    }

    /**
     * This method are used to start working of the current port.
     */
    public void getStrated()
    {
        port[indexOfCurrentPort].getStarted();
    }

    /**
     * method, used to suspend process of current Port
     */
    public void stopWorking()
    {
       port[indexOfCurrentPort].suspendProcess();
    }

    /**
     * method, used to resume process of current Port
     */
    public void resumeWorking()
    {
        port[indexOfCurrentPort].resumeProcess();
    }

    /**
     * This method used to convert Ship requests to Array of Array of strings
     * @return array of array of string
     */
    public String[][] getShipRequests()
    {
        Ship[] currentState = port[indexOfCurrentPort].getShipRequests();

        String[][] res = new String[currentState.length][4];

        for(int i = 0; i < currentState.length; ++i)
        {
            String name          = currentState[i].getName();
            String isLoadRequest = Boolean.toString(currentState[i].isLoadRequest());
            String count         = Integer.toString(currentState[i].getCount());
            String cargo;

            switch(currentState[i].getCargo())
            {
                case GAS:
                {
                    cargo = new String("GAS");
                    break;
                }
                case OIL:
                {
                    cargo = new String("OIL");
                    break;
                }
                case CARS:
                {
                    cargo = new String("CARS");
                    break;
                }
                case FOOD:
                {
                    cargo = new String("FOOD");
                    break;
                }
                default:
                {
                    cargo = new String("OIL");
                }
            }

            String[] cur = new String[]{name, cargo, count, isLoadRequest};
            res[i] = cur;
        }
        return res;
    }
}
