package GUI;

import GUI.View.ShipRequestsOverviewController;
import Manager.Manager;
import com.sun.org.apache.xpath.internal.SourceTree;
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
    private FXMLLoader loader;
    private ShipRequestsOverviewController controller;
    private Manager mainProgramObject;

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
            loader = new FXMLLoader();
            loader.setLocation(MainWindow.class.getResource("View/ShipRequestsOverview.fxml"));
            rootLayout = loader.load();
            setController();
            primaryStage.setScene(new Scene(rootLayout));

            mainProgramObject = controller.getMainProgramObject();

            for(int i = 0; i < 5; ++i)
            {
                controller.setPierProgress(i, 0.5);
            }
            primaryStage.show();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void setController()
    {
        controller = loader.getController();
    }

    public ShipRequestsOverviewController getController()
    {
        return controller;
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
