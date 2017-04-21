package Port;

import javafx.collections.ObservableList;

import java.io.*;
import java.nio.Buffer;
import java.nio.file.Watchable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Tarasevich Vladislav on 16.04.2017.
 * @author name  : Tarasevich Vladislav
 * @author gmail : tarasevich.vlad.97@gmail.com
 */
public class StatusLog extends Thread
{
    private final String fileName = "Warehouse logs";

    private final int LOG_SIZE = 100;
    private final int DELAY    = 5;
    private final int SECOND   = 1000;

    private final SimpleDateFormat date;
    private final SimpleDateFormat time;

    private Port parentPort;
    private ObservableList<StateUnit> statusLog;
    private Warehouse warehouse;

    private boolean fileEnable;

    public StatusLog(Port parentPort)
    {
        date = new SimpleDateFormat("dd.MM.yyyy");
        time = new SimpleDateFormat("hh:mm");
        this.parentPort = parentPort;
        statusLog = parentPort.getStatusLog();
        warehouse = parentPort.getWarehouse();
        setDaemon(true);
    }

    @Override
    public void run()
    {

        File file = new File(fileName);
        FileWriter writer;
        try
        {

            if (!file.exists())
            {
                file.createNewFile();
            }
            fileEnable = true;
        }
        catch (IOException e)
        {
            fileEnable = false;
            writer = null;
            // TODO: 21.04.2017
        }

        while (true)
        {
            try
            {
                sleep(DELAY * SECOND);
                Date currentDate = new Date();
                StateUnit currentState = new StateUnit(date.format(currentDate), time.format(currentDate),
                                                       warehouse.getOilCount(), warehouse.getGasCount(),
                                                       warehouse.getFoodCount(), warehouse.getCarsCount(),
                                                       parentPort.getQueueSize(), parentPort.getProcessedCount());

                if (statusLog.size() == LOG_SIZE) statusLog.remove(0);

                if(fileEnable)
                {
                    try
                    {
                        writer = new FileWriter(file.getAbsolutePath(), true);
                        writer.write(currentState.getFulLog());
                        writer.close();
                    }
                    catch(IOException e)
                    {
                        e.printStackTrace();
                        // TODO: 21.04.2017 ...
                    }
                }

                statusLog.add(currentState);
            }
            catch (InterruptedException e)
            {
                // TODO: 18.04.2017 ...
            }
        }
    }
}
