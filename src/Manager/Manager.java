package Manager;

import GUI.MainWindow;
import Port.Port;

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
        port = new Port[COUNT_OF_PORTS];
        for(int i = 0; i < COUNT_OF_PORTS; ++i)
        {
            port[i] = new Port();
        }
    }
    
}
