/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contact;

import java.io.IOException;
import java.sql.SQLException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.PersonController;

/**
 *
 * @author Sidi
 */
public class Contact extends Application {

    private Stage mainStage;

    public Stage getMainStage() {
        return mainStage;
    }

    @Override
    public void start(Stage mainStage) throws IOException, SQLException {
        displayMainStage(mainStage);
    }

    private void displayMainStage(Stage mainStage) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/Person.fxml"));
        Parent root = fxmlLoader.load();

        mainStage.setScene(new Scene(root));
        mainStage.setMinWidth(600);

        PersonController personController = fxmlLoader.getController();
        personController.init(this);

        mainStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
