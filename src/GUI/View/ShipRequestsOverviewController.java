package GUI.View;

import Manager.Manager;
import Ship.*;
import javafx.beans.property.IntegerProperty;
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
public class ShipRequestsOverviewController {
    @FXML
    private ProgressBar pierProgress1 = new ProgressBar();
    @FXML
    private ProgressBar pierProgress2 = new ProgressBar();
    @FXML
    private ProgressBar pierProgress3 = new ProgressBar();
    @FXML
    private ProgressBar pierProgress4 = new ProgressBar();
    @FXML
    private ProgressBar pierProgress5 = new ProgressBar();

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

    public ShipRequestsOverviewController() {
        mainProgramObject = new Manager(this);
        mainProgramObject.setIndexOfWorkingPort(0);
    }

    @FXML
    private void initialize() {
        mainProgramObject.getStarted();
        setShipRequestTable();

        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        cargoColumn.setCellValueFactory(cellData -> cellData.getValue().cargoProperty());
        countColumn.setCellValueFactory(cellData -> cellData.getValue().countProperty().asObject());
        isLoadRequestColumn.setCellValueFactory(cellData -> cellData.getValue().isLoadRequestProperty());
    }

    public Manager getMainProgramObject()
    {
        return mainProgramObject;
    }

    public void setMainProgramObject(Manager mainProgramObject)
    {
        this.mainProgramObject = mainProgramObject;
    }

    public void setShipRequestTable()
    {
        shipRequestTable.setItems(mainProgramObject.getShipRequestsData());
    }

    public void setPierProgress(int index, double value)
    {
        switch (index)
        {
            case 0:
            {
                pierProgress1.setProgress(value);
                break;
            }
            case 1:
            {
                pierProgress2.setProgress(value);
                break;
            }
            case 2:
            {
                pierProgress3.setProgress(value);
                break;
            }
            case 3:
            {
                pierProgress4.setProgress(value);
                break;
            }
            case 4:
            {
                pierProgress5.setProgress(value);
                break;
            }
            default: throw new IndexOutOfBoundsException();
        }
    }
}
