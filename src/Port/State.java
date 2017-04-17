package Port;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;

import java.util.Date;

/**
 * Created by Tarasevich Vladislav on 16.04.2017.
 * @author name  : Tarasevich Vladislav
 * @author gmail : tarasevich.vlad.97@gmail.com
 */
public class State
{
    private StringProperty  date;
    private StringProperty  time;
    private IntegerProperty oilCount;
    private IntegerProperty gasCount;
    private IntegerProperty foodCount;
    private IntegerProperty carsCount;
    private IntegerProperty waitingShips;
    private IntegerProperty processedShips;
}
