package Port;

import Ship.Cargo;
import org.apache.log4j.Logger;

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

    private static final Logger logger = Logger.getLogger(Warehouse.class);

    private int oilCount;
    private int gasCount;
    private int foodCount;
    private int carsCount;

    public Warehouse()
    {
        try
        {
            readCountsOfGoods();
            logger.info("Считывание начального состояния из файла произведено успешно");
        }
        catch (FileNotFoundException ex)
        {
            logger.error("Файл с начальным состоянием не найден");
            logger.info("Используется нулевое начальное состояние");

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

    /**
     * This method is used to trying to take cargo from the Warehouse
     * @param cargo Type of cargo, which is necessary to take from the Warehouse
     * @param count Count of cargo, which is necessary to take from the Warehouse
     * @return true in case of successful taking of cargo from the Warehouse
     */
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

    /**
     * This method is used to trying to put cargo into the Warehouse
     * @param cargo Type of cargo, which is want to put into the Warehouse
     * @param count Count of cargo, which is want to put into the Warehouse
     */
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
                        oilCount = Integer.parseInt(currentCount);
                        break;
                    }
                    case "GAS":
                    {
                        gasCount = Integer.parseInt(currentCount);
                        break;
                    }
                    case "FOOD":
                    {
                        foodCount = Integer.parseInt(currentCount);
                        break;
                    }
                    case "CARS":
                    {
                        carsCount = Integer.parseInt(currentCount);
                        break;
                    }
                    default: throw new IOException();
                }
            }
        }
        catch (IOException ex)
        {
            logger.error("Неверный формат файла начального состояния.");
            logger.info("Используется нулевое начальное состояние.");

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