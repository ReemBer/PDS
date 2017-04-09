package Port;

import Ship.Cargo;

/**
 * Created by Tarasevich Vladislav on 09.04.2017.
 * @author Tarasevich Vladislav
 * This class used by Port class to store
 * information about existing goods.
 */
public class Warehouse
{
    private final String DEFAULT_PATH_TO_GOODS = "Goods.txt";

    private String pathToGoods = DEFAULT_PATH_TO_GOODS;

    private int oilCount;
    private int gasCount;
    private int foodCount;
    private int carsCount;

    public Warehouse()
    {
        readCountsOfGoods();
    }

    public Warehouse(int oilCount, int gasCount, int foodCount, int carsCount)
    {
        this.oilCount = oilCount;
        this.gasCount = gasCount;
        this.foodCount = foodCount;
        this.carsCount = carsCount;
    }

    public synchronized boolean takeCargo(Cargo cargo, int count)
    {
        switch(cargo)
        {
            case OIL:
            {
                if(count > oilCount) return false;

                oilCount -= count;
                break;
            }
            case GAS:
            {
                if(count > gasCount) return false;

                gasCount -= count;
                break;
            }
            case FOOD:
            {
                if(count > foodCount) return false;

                foodCount -= count;
                break;
            }
            case CARS:
            {
                if(count > carsCount) return false;

                carsCount -= count;
                break;
            }
        }

        return true;
    }

    public synchronized void putCargo(Cargo cargo, int count)
    {
        switch(cargo)
        {
            case OIL:
            {
                oilCount += count;
                break;
            }
            case GAS:
            {
                gasCount += count;
                break;
            }
            case FOOD:
            {
                foodCount += count;
                break;
            }
            case CARS:
            {
                carsCount += count;
                break;
            }
        }
    }

    public synchronized int getOilCount()
    {
        return oilCount;
    }

    public synchronized int getGasCount()
    {
        return  gasCount;
    }

    public synchronized int getFoodCount()
    {
        return foodCount;
    }

    public synchronized int getCarsCount()
    {
        return carsCount;
    }

    private void readCountsOfGoods()
    {

    }
}