package Port;

import Pier.Pier;
import Ship.Cargo;

/**
 * Created by Tarasevich Vladislav on 09.04.2017.
 * This class used to store the state of the warehouse and work with ships through the piers
 */
public class Port
{
    private final int COUNT_OF_PIERS = 5;

    private Warehouse warehouse;

    private Pier pier[];

    {
        pier = new Pier[COUNT_OF_PIERS];
    }

    /**
     * This method used to trying to take some cargo from the warehouse
     * @param cargo type of cargo, needed to take
     * @param count count of type of cargo, needed to take
     * @return true, if you can take such count of such cargo
     */
    public boolean takeCargo(Cargo cargo, int count)
    {
        return warehouse.takeCargo(cargo, count);
    }

    /**
     * This method used to put some cargo into the warehouse
     * @param cargo type of cargo, wanted to put
     * @param count count of type of cargo, wanted to put
     */
    public void putCargo(Cargo cargo, int count)
    {
        warehouse.putCargo(cargo, count);
    }
}
