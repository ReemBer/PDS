package Port;

import javafx.beans.property.*;

import java.util.Date;

/**
 * Created by Tarasevich Vladislav on 16.04.2017.
 * @author name  : Tarasevich Vladislav
 * @author gmail : tarasevich.vlad.97@gmail.com
 */
public class StateUnit
{
    private StringProperty  date;
    private StringProperty  time;
    private IntegerProperty oilCount;
    private IntegerProperty gasCount;
    private IntegerProperty foodCount;
    private IntegerProperty carsCount;
    private IntegerProperty waitingShips;
    private IntegerProperty processedShips;

    public StateUnit(String date, String time, int oil, int gas, int food, int cars, int waitingShips, int processedShips)
    {
        this.date = new SimpleStringProperty(date);
        this.time = new SimpleStringProperty(time);

        oilCount = new SimpleIntegerProperty(oil);
        gasCount = new SimpleIntegerProperty(gas);
        foodCount = new SimpleIntegerProperty(food);
        carsCount = new SimpleIntegerProperty(cars);

        this.waitingShips   = new SimpleIntegerProperty(waitingShips);
        this.processedShips = new SimpleIntegerProperty(processedShips);
    }

    public StringProperty dateProperty()
    {
        return date;
    }

    public StringProperty timeProperty()
    {
        return time;
    }

    public IntegerProperty oilCountProperty()
    {
        return oilCount;
    }

    public IntegerProperty gasCountProperty()
    {
        return  gasCount;
    }

    public IntegerProperty foodCountProperty()
    {
        return foodCount;
    }

    public IntegerProperty carsCountProperty()
    {
        return carsCount;
    }

    public IntegerProperty waitingShipsProperty()
    {
        return waitingShips;
    }

    public IntegerProperty processedShipsProperty()
    {
        return processedShips;
    }

    public String getDate()
    {
        return date.get();
    }

    public String getTime()
    {
        return time.get();
    }

    public int getOilCount() {
        return oilCount.get();
    }

    public int getCarsCount() {
        return carsCount.get();
    }

    public int getFoodCount() {
        return foodCount.get();
    }

    public int getGasCount() {
        return gasCount.get();
    }

    public int getProcessedShips() {
        return processedShips.get();
    }

    public int getWaitingShips() {
        return waitingShips.get();
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public void setOilCount(int oilCount) {
        this.oilCount.set(oilCount);
    }

    public void setFoodCount(int foodCount) {
        this.foodCount.set(foodCount);
    }

    public void setTime(String time) {
        this.time.set(time);
    }

    public void setGasCount(int gasCount) {
        this.gasCount.set(gasCount);
    }

    public void setCarsCount(int carsCount) {
        this.carsCount.set(carsCount);
    }

    public void setProcessedShips(int processedShips) {
        this.processedShips.set(processedShips);
    }

    public void setWaitingShips(int waitingShips) {
        this.waitingShips.set(waitingShips);
    }

    public String getFulLog()
    {
        return date.get() + " " + time.get() + " OIL = " + oilCount.get() + " GAS = " + gasCount.get() +
               " FOOD = " + foodCount.get() + " CARS = " + carsCount.get();
    }
}
