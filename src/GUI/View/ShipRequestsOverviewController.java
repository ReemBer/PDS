package GUI.View;

import Manager.Manager;
import Ship.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Observable;

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
        mainProgramObject = new Manager();
        mainProgramObject.setIndexOfWorkingPort(0);
    }

    @FXML
    private void initialize()
    {
        setShipRequestTable();

        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        cargoColumn.setCellValueFactory(cellData -> cellData.getValue().cargoProperty());
        countColumn.setCellValueFactory(cellData -> cellData.getValue().countProperty().asObject());
        isLoadRequestColumn.setCellValueFactory(cellData -> cellData.getValue().isLoadRequestProperty());
    }

    public void setMainProgramObject(Manager mainProgramObject)
    {
        this.mainProgramObject = mainProgramObject;
    }

    public void setShipRequestTable()
    {
        mainProgramObject.getStarted();

//        ObservableList<Ship> data = FXCollections.observableArrayList();
//        for(int i = 0; i < 10; ++i)
//        {
//            data.add(new Ship());
//        }
//
//        shipRequestTable.setItems(data);
        shipRequestTable.setItems(mainProgramObject.getShipRequestsData());
    }
}
