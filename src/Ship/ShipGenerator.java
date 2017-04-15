package Ship;

import Port.Port;

import java.io.*;
import java.util.Random;
import java.util.Vector;

/**
 * Created by Tarasevich Vladislav on 10.04.2017.
 * @author Tarasevich Vladislav
 * @author +375336492547
 * @version 1.0
 *
 * This class needed to generate random information
 * to fill fields of data of Ship.
 */
public class ShipGenerator extends Thread
{
    private final String DEFAULT_SHIP_NAMES_FILE = "ShipNames.txt";
    private final String[] DEFAULT_SHIP_NAMES;
    private final int UPPER_BOUND = 100;
    private final int LOWER_BOUND = 10;
    private final int CARGO_UPPER_BOUND = 4;
    private final int CARGO_LOWER_BOUND = 1;
    private final int SLEEP_TIME = 1000;

    private Port parentPort;

    {
        DEFAULT_SHIP_NAMES = new String[]{"Pobeda", "Titanic", "Costa Concordia", "Admiral", "Mermaid"};
    }

    private String[] shipNames;
    private Random  randomizer;

    public ShipGenerator(Port parentPort)
    {
        try
        {
            this.parentPort = parentPort;
            randomizer = new Random(0L);
            Vector<String> names = readNames(DEFAULT_SHIP_NAMES_FILE);
            shipNames = names.toArray(new String[names.size()]);
        }
        catch (FileNotFoundException ex)
        {
            // TODO: 11.04.2017 Добавить логи тут
            shipNames = DEFAULT_SHIP_NAMES;
        }
    }

    public ShipGenerator(Port parentPort, String fileName)
    {
        try
        {
            this.parentPort = parentPort;
            randomizer = new Random(0L);
            Vector<String> names = readNames(fileName);
            shipNames = names.toArray(new String[names.size()]);
        }
        catch (FileNotFoundException ex)
        {
            // TODO: 11.04.2017 Добавить логи тут
            shipNames = DEFAULT_SHIP_NAMES;
        }
    }

    @Override
    public void run()
    {
        try
        {
            while (true)
            {
                parentPort.putCurrentRequest(generateNewShip());
                sleep(SLEEP_TIME);
            }
        } catch (InterruptedException ex)
        {
            // TODO: 11.04.2017 придумать как обработать такого рода исключение
        }
    }
    
    private Ship generateNewShip()
    {
        return new Ship(getRandomName(), getRandomCargo(), getRandomCount(), getRandomRequest());
    }

    private String getRandomName()
    {
        int choose = Math.abs(randomizer.nextInt())%(shipNames.length);
        return shipNames[choose];
    }

    private Cargo getRandomCargo()
    {
        int choose = (Math.abs(randomizer.nextInt())%(CARGO_UPPER_BOUND - CARGO_LOWER_BOUND)) + CARGO_LOWER_BOUND;
        switch (choose)
        {
            case 1: // OIL selected
            {
                return Cargo.OIL;
            }
            case 2: // GAS selected
            {
                return  Cargo.GAS;
            }
            case 3: // FOOD selected
            {
                return  Cargo.FOOD;
            }
            case 4: // CARS selected
            {
                return Cargo.CARS;
            }
            default:
            {
                return Cargo.OIL;
            }
        }
    }

    private int getRandomCount()
    {
        return ((Math.abs(randomizer.nextInt())%UPPER_BOUND) + LOWER_BOUND);
    }

    private boolean getRandomRequest()
    {
        return randomizer.nextBoolean();
    }

    /**
     * This method check existing of file
     * @param fileName are name of file, needed to check
     * @throws FileNotFoundException
     */
    private void exist(String fileName) throws FileNotFoundException
    {
        File file = new File(fileName);
        if(!file.exists())
        {
            throw new FileNotFoundException(file.getName());
        }
    }

    /**
     * This metod used to read existing set of Names of Ships (needed to do random)
     * @param fileName name of file with template Names
     * @return Vector of String -- template names of Ships
     * @throws FileNotFoundException
     */
    public Vector<String> readNames(String fileName) throws  FileNotFoundException
    {
        Vector<String> data = new Vector<String>();

        exist(fileName);

        File file = new File(fileName);

        try
        {
            BufferedReader in = new BufferedReader(new FileReader(file.getAbsoluteFile()));
            String currentName;
            while(true)
            {
                if((currentName = in.readLine()) == null) break;
                data.add(currentName);
            }
            in.close();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

        return  data;
    }
}
