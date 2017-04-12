package Port;

import Ship.Cargo;

import java.io.*;

/**
 * Created by Tarasevich Vladislav on 09.04.2017.
 * @author Tarasevich Vladislav
 * This class used by Port class to store
 * information about existing goods.
 */
public class Warehouse
{
    private final String DEFAULT_PATH_TO_GOODS = "Goods.txt";
    private final int    COUNT_OF_SORTS = 4;

    private String pathToGoods = DEFAULT_PATH_TO_GOODS;

    private int oilCount;
    private int gasCount;
    private int foodCount;
    private int carsCount;

    public Warehouse()
    {
        try
        {
            readCountsOfGoods();
        }
        catch (FileNotFoundException ex)
        {
            // TODO: 10.04.2017 добавить логи вот здеся
            oilCount = gasCount = foodCount = carsCount = 0;
        }
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

    /**
     * This method using by default constructor
     * to initialisation counts of goods
     * @throws FileNotFoundException
     */
    private void readCountsOfGoods() throws FileNotFoundException
    {
        exist();

        File file = new File(pathToGoods);

        try
        {
            BufferedReader in = new BufferedReader(new FileReader(file.getAbsoluteFile()));
            String currentCargo;
            for(int i = 0; i < COUNT_OF_SORTS; ++i)
            {
                if((currentCargo = in.readLine()) == null) throw new IOException();


                String currentCount;

                if((currentCount = in.readLine()) == null) throw new IOException();

                switch (currentCargo)
                {
                    case "OIL":
                    {
                        oilCount = Integer.getInteger(currentCount);
                        break;
                    }
                    case "GAS":
                    {
                        gasCount = Integer.getInteger(currentCount);
                        break;
                    }
                    case "FOOD":
                    {
                        foodCount = Integer.getInteger(currentCount);
                        break;
                    }
                    case "CARS":
                    {
                        carsCount = Integer.getInteger(currentCount);
                        break;
                    }
                    default: throw new IOException();
                }
            }
        }
        catch (IOException ex)
        {
            // TODO: 10.04.2017 Добавить логи ошибки вот здеся
            oilCount = gasCount = foodCount = carsCount = 0;
        }
    }

    /**
     * This method check existing of file
     * @throws FileNotFoundException
     */
    private void exist() throws FileNotFoundException
    {
        File file = new File(pathToGoods);
        if(!file.exists())
        {
            throw new FileNotFoundException(file.getName());
        }
    }
}