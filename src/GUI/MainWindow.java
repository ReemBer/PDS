package GUI;

import GUI.View.ShipRequestsOverviewController;
import Manager.Manager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sun.applet.Main;

import java.io.IOException;

/**
 * Created by Tarasevich Vladislav on 11.04.2017.
 * @author name :  Tarasevich Vladislav
 * @author gmail : tarasevich.vlad.97@gmail.com
 * @author phone : +375336492547
 */
public class MainWindow extends Application
{
    private VBox rootLayout;
    private Stage primaryStage;
    private Manager manager;

    @Override
    public void start(Stage primaryStage)
    {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Port Dispatch System");

        initRootLayout();

        showShipRequestsOverview();
    }

    public void initRootLayout()
    {
        try
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainWindow.class.getResource("View/ShipRequestsOverview.fxml"));
            rootLayout = loader.load();
            primaryStage.setScene(new Scene(rootLayout));

            primaryStage.show();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void showShipRequestsOverview()
    {
        try
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainWindow.class.getResource("View/ShipRequestsOverview.fxml"));

            rootLayout = loader.load();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public Stage getPrimaryStage()
    {
        return primaryStage;
    }

    public static void main(String args[])
    {
        launch(args);
    }
}
