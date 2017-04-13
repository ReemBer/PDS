package GUI;

import Manager.Manager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
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
    private Stage primaryStage;
    private AnchorPane rootLayout;
    private Manager mainPrgramObject;

    //public MainWindow(Manager mainProgramObject)
    //{
    //    this.mainProgramObject = mainProgramObject;
    //}

    @Override
    public void start(Stage primaryStage)
    {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Port Dispatch System");

        initRootLayout();
    }

    public void initRootLayout()
    {
        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("View/ShipRequestsOverview.fxml"));

            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

//    public void showShipRequestsOverview()
//    {
//        try
//        {
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(MainWindow.class.getResource("View/ShipRequestsOverview.fxml"));
//            AnchorPane personOverview = (AnchorPane) loader.load();
//
//            rootLayout.add(personOverview);
//        }
//        catch(IOException e)
//        {
//            e.printStackTrace();
//        }
//    }
}
