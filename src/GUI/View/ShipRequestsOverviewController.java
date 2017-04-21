package GUI.View;

import Manager.Manager;
import Port.StateUnit;
import Ship.*;
import javafx.beans.property.IntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;

import javax.swing.plaf.nimbus.State;
import java.awt.font.ImageGraphicAttribute;
import java.util.Observable;

/**
 * Created by Tarasevich Vladislav on 12.04.2017.
 * @author name  : Tarasevich vladislav
 * @author gmail : tarasevich.vlad.97@gmail.com
 */
public class ShipRequestsOverviewController
{
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
    private TextArea pierText1 = new TextArea();
    @FXML
    private TextArea pierText2 = new TextArea();
    @FXML
    private TextArea pierText3 = new TextArea();
    @FXML
    private TextArea pierText4 = new TextArea();
    @FXML
    private TextArea pierText5 = new TextArea();

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

    @FXML
    private TableView<StateUnit> stateLogTable;
    @FXML
    private TableColumn<StateUnit, String> dateColumn;
    @FXML
    private TableColumn<StateUnit, String> timeColumn;
    @FXML
    private TableColumn<StateUnit, Integer> cargoCountsColumn;
    @FXML
    private TableColumn<StateUnit, Integer> oilCountColumn;
    @FXML
    private TableColumn<StateUnit, Integer> gasCountColumn;
    @FXML
    private TableColumn<StateUnit, Integer> foodCountColumn;
    @FXML
    private TableColumn<StateUnit, Integer> carsCountColumn;
    @FXML
    private TableColumn<StateUnit, Integer> requestsColumn;
    @FXML
    private TableColumn<StateUnit, Integer> waitingShipsColumn;
    @FXML
    private TableColumn<StateUnit, Integer> processedShipsColumn;

    @FXML
    private Button sus_res = new Button("Suspend");;
    @FXML
    private Button start_stop = new Button("Start");
    @FXML
    private Button generator = new Button("Suspend Generator");

    private boolean suspended = false;
    private boolean started   = false;
    private boolean genSuspended = false;

    @FXML
    public void onStartClicked()
    {
        if(!started)
        {
            mainProgramObject.getStarted();
            start_stop.setText("Stop");
            started = true;
        }
        else
        {
            mainProgramObject.stopWorking();
            started = false;
        }
    }
    @FXML
    public void onSuspendClicked()
    {
        if(!suspended)
        {
            mainProgramObject.suspendWorking();
            sus_res.setText("Resume");
            suspended = true;
        }
        else
        {
            mainProgramObject.resumeWorking();
            sus_res.setText("Suspend");
            suspended = false;
        }

    }
    @FXML
    public void onGeneratorClicked()
    {
        if(!genSuspended)
        {
            mainProgramObject.suspendGenerator();
            generator.setText("Resume Generator");
            genSuspended = true;
        }
        else
        {
            mainProgramObject.resumeGenerator();
            generator.setText("Suspend Generator");
            genSuspended = false;
        }
    }

    private Manager mainProgramObject;

    public ShipRequestsOverviewController() {
        mainProgramObject = new Manager(this);
        mainProgramObject.setIndexOfWorkingPort(0);
    }

    @FXML
    private void initialize()
    {
        setShipRequestTable();
        setStateLogTable();

        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        cargoColumn.setCellValueFactory(cellData -> cellData.getValue().cargoProperty());
        countColumn.setCellValueFactory(cellData -> cellData.getValue().countProperty().asObject());
        isLoadRequestColumn.setCellValueFactory(cellData -> cellData.getValue().isLoadRequestProperty());

        dateColumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        timeColumn.setCellValueFactory(cellData -> cellData.getValue().timeProperty());

        oilCountColumn = new TableColumn<>("Oil");
        gasCountColumn = new TableColumn<>("Gas");
        foodCountColumn = new TableColumn<>("Food");
        carsCountColumn = new TableColumn<>("Cars");

        oilCountColumn.setCellValueFactory(cellData -> cellData.getValue().oilCountProperty().asObject());
        gasCountColumn.setCellValueFactory(cellData -> cellData.getValue().gasCountProperty().asObject());
        foodCountColumn.setCellValueFactory(cellData -> cellData.getValue().foodCountProperty().asObject());
        carsCountColumn.setCellValueFactory(cellData -> cellData.getValue().carsCountProperty().asObject());

        cargoCountsColumn.getColumns().setAll(oilCountColumn, gasCountColumn, foodCountColumn, carsCountColumn);

        waitingShipsColumn = new TableColumn<>("Waiting");
        processedShipsColumn = new TableColumn<>("Processed");

        waitingShipsColumn.setCellValueFactory(cellData -> cellData.getValue().waitingShipsProperty().asObject());
        processedShipsColumn.setCellValueFactory(cellData -> cellData.getValue().processedShipsProperty().asObject());


        requestsColumn.getColumns().setAll(waitingShipsColumn, processedShipsColumn);
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

    public void setStateLogTable()
    {
        stateLogTable.setItems(mainProgramObject.getStatusLogData());
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

    public void setCurrentPierRequest(int index, String value)
    {
        switch (index)
        {
            case 0:
            {
                pierText1.setText(value);
                break;
            }
            case 1:
            {
                pierText2.setText(value);
                break;
            }
            case 2:
            {
                pierText3.setText(value);
                break;
            }
            case 3:
            {
                pierText4.setText(value);
                break;
            }
            case 4:
            {
                pierText5.setText(value);
                break;
            }
        }
    }
}
