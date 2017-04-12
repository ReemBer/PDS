package Manager.View;

import Manager.Manager;
import Ship.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;

/**
 * Created by Tarasevich Vladislav on 12.04.2017.
 * @author name  : Tarasevich vladislav
 * @author gmail : tarasevich.vlad.97@gmail.com
 */
public class ShipRequestsOverviewController
{
    @FXML
    private TableView<Ship> shipRequestTable;

    @FXML
    private TableColumn<Ship, String> nameColumn;

    @FXML
    private TableColumn<Ship, Cargo> cargoColumn;

    @FXML
    private TableColumn<Ship, Integer> countColumn;

    @FXML
    private TableColumn<Ship, Boolean> isLoadRequestColumn;

    private Manager mainProgramObject;

    
}
