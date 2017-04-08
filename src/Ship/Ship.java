package Ship;

/**
 * Created by Tarasevich Vladislav on 08.04.2017.
 * This class is used to store data about a ship.
 * Used by Queue of Requests.
 */
public class Ship
{
    /**
     * Default values of all fields
     */
    private final String  DEFAULT_NAME    = "Pobeda";
    private final Cargo   DEFAULT_CARGO   = Cargo.OIL;
    private final int     DEFAULT_COUNT   = 10;
    private final boolean DEFAULT_REQUEST = false;

    private String  name;
    private Cargo  cargo;
    private int    count;
    private boolean isLoadRequest;

    /**
     * This constructor is used by Default
     */
    public Ship()
    {
        name  = DEFAULT_NAME;
        cargo = DEFAULT_CARGO;
        count = DEFAULT_COUNT;
        isLoadRequest = DEFAULT_REQUEST;
    }

    /**
     * Creating ship and set name only
     * @param name
     */
    public Ship(String name)
    {
        this.name = name;
        cargo = DEFAULT_CARGO;
        count = DEFAULT_COUNT;
        isLoadRequest = DEFAULT_REQUEST;
    }

    /**
     * Creating new ship. All field will be initialized, except "isLoadRequest"
     * isLoadRequest initializing by default (false).
     * @param name
     * @param cargo
     * @param count
     */
    public Ship(String name, Cargo cargo, int count)
    {
        this.name   = name;
        this.cargo  = cargo;
        this.count  = count;
        isLoadRequest = DEFAULT_REQUEST;
    }

    /**
     * This constructor used to specify all fields
     * @param name
     * @param cargo
     * @param count
     * @param isLoadRequest
     */
    public Ship(String name, Cargo cargo, int count, boolean isLoadRequest)
    {
        this.name = name;
        this.count = count;
        this.cargo = cargo;
        this.count = count;
        this.isLoadRequest = isLoadRequest;
    }

    public String getName()
    {
        return name;
    }

    public Cargo getCargo()
    {
        return cargo;
    }

    public int getCount()
    {
        return count;
    }

    public boolean isLoadRequest()
    {
        return isLoadRequest;
    }

    /**
     * set new name of Ship
     * @param name
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * set new type of Cargo
     * @param cargo
     */
    public void setCargo(Cargo cargo)
    {
        this.cargo = cargo;
    }

    /**
     * set new count of Cargo
     * @param count
     */
    public void setCount(int count)
    {
        this.count = count;
    }

    /**
     * @param isLoadRequest true -- ship is requesting a beth for loading;
     *                      false - ship is requesting a beth for unloading.
     */
    public void setLoadRequest(boolean isLoadRequest)
    {
        this.isLoadRequest = isLoadRequest;
    }
}
