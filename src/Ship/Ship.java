package Ship;

import javafx.beans.property.*;

/**
 * Created by Tarasevich Vladislav on 12.04.2017.
 * @author name : Tarasevich Vladislav
 * @author mail : tarasevich.vlad.97@gmail.com
 * @version 2.0
 * This is the Class-model for TableView.
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

    private final StringProperty          name;
    private final ObjectProperty<Cargo>  cargo;
    private final IntegerProperty        count;
    private final ObjectProperty<Boolean> isLoadRequest;

    public Ship()
    {
        name  = new SimpleStringProperty(DEFAULT_NAME);
        cargo = new SimpleObjectProperty<Cargo>(DEFAULT_CARGO);
        count = new SimpleIntegerProperty(DEFAULT_COUNT);
        isLoadRequest = new SimpleObjectProperty<>(DEFAULT_REQUEST);
    }

    public Ship(String name, Cargo cargo, int count, boolean isLoadRequest)
    {
        this.name = new SimpleStringProperty(name);
        this.cargo = new SimpleObjectProperty<Cargo>(cargo);
        this.count = new SimpleIntegerProperty(count);
        this.isLoadRequest = new SimpleObjectProperty<>(isLoadRequest);
    }

    public StringProperty nameProperty()
    {
        return name;
    }

    public String getName()
    {
        return name.get();
    }

    public void setName(String name)
    {
        this.name.set(name);
    }

    public ObjectProperty<Cargo> cargoProperty()
    {
        return cargo;
    }

    public Cargo getCargo()
    {
        return cargo.get();
    }

    public void setCargo(Cargo cargo)
    {
        this.cargo.set(cargo);
    }

    public IntegerProperty countProperty()
    {
        return count;
    }

    public int getCount()
    {
        return count.get();
    }

    public void setCount(Integer count)
    {
        this.count.set(count);
    }

    public ObjectProperty<Boolean> isLoadRequestProperty()
    {
        return isLoadRequest;
    }

    public String getIsLoadReques()
    {
        return isLoadRequest.get() ? "TRUE" : "FALSE";
    }

    public void setIsLoadRequest(Boolean isLoadRequest)
    {
        this.isLoadRequest.set(isLoadRequest);
    }

    public void setLoadRequest(boolean loadRequest)
    {
        this.isLoadRequest.set(loadRequest);
    }

    public boolean isLoadRequest()
    {
        return isLoadRequest.get();
    }
}
