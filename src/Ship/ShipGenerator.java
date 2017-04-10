package Ship;

import java.util.Random;

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
    private final String DEFAULT_SHIP_NAMES = "ShipNames.txt";
    private final int UPPER_BOUND = 100;
    private final int LOWER_BOUND = 10;
    private final int CARGO_UPPER_BOUND = 4;
    private final int CARGO_LOWER_BOUND = 1;

    private String[] shipNames;
    private Random  randomizer;

    public ShipGenerator()
    {
        randomizer = new Random(0L);
        // TODO: 10.04.2017 сделать считывание имён из файла
    }

    public ShipGenerator(String fileName)
    {
        randomizer = new Random(0L);
        // TODO: 10.04.2017 сделать считывание имён из файла
    }

    public Ship generateNewShip()
    {
        return new Ship(getRandomName(), getRandomCargo(), getRandomCount(), getRandomRequest());
    }

    private String getRandomName()
    {
        int choose = randomizer.nextInt()%(shipNames.length);
        return shipNames[choose];
    }

    private Cargo getRandomCargo()
    {
        int choose = (randomizer.nextInt()%(CARGO_UPPER_BOUND-CARGO_LOWER_BOUND)) + CARGO_LOWER_BOUND;
        switch (choose)
        {
            case 1: // OIL selected
            {
                return Cargo.OIL;
            }выбран
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
        }
    }

    private int getRandomCount()
    {
        return ((randomizer.nextInt()%UPPER_BOUND) + LOWER_BOUND);
    }

    private boolean getRandomRequest()
    {
        return randomizer.nextBoolean();
    }
}
