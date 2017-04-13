package GUI.View;

import Manager.Manager;
import Ship.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

    public ShipRequestsOverviewController()
    {

    }

    @FXML
    private void initialize()
    {
        test();

        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        cargoColumn.setCellValueFactory(cellData -> cellData.getValue().cargoProperty());
        countColumn.setCellValueFactory(cellData -> cellData.getValue().countProperty().asObject());
        isLoadRequestColumn.setCellValueFactory(cellData -> cellData.getValue().isLoadRequestProperty());
    }

    private void test()
    {
        ObservableList<Ship> t = FXCollections.observableArrayList();
        t.add(new Ship());
        t.add(new Ship("IGOR", Cargo.GAS, 100, true));

        shipRequestTable.setItems(t);
    }

    public void setMainProgramObject(Manager mainProgramObject)
    {
        this.mainProgramObject = mainProgramObject;

        shipRequestTable.setItems(mainProgramObject.getShipRequestsData());
    }
}
