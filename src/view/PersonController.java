/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import contact.Contact;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Person;
import model.PersonDAO;
import util.Date;
import view.dialog.PersonFormController;

/**
 * FXML Controller class
 *
 * @author Sidi
 */
public class PersonController implements Initializable {

    @FXML
    private Button addButton;

    @FXML
    private TextField searchField;

    @FXML
    private TableView<Person> personTable;

    @FXML
    private TableColumn<Person, String> nameColumn;

    @FXML
    private TableColumn<Person, String> phoneColumn;

    @FXML
    private TableColumn<Person, String> emailColumn;

    @FXML
    private GridPane personDetails;

    @FXML
    private Label nameLabel;

    @FXML
    private Label phoneLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Label genderLabel;

    @FXML
    private Label birthdayLabel;

    @FXML
    private ButtonBar actionBar;

    @FXML
    private Button editButton;

    @FXML
    private Button deleteButton;

    private Contact app;
    private PersonDAO personDAO;
    private ObservableList<Person> personData;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        phoneColumn.setCellValueFactory(cellData -> cellData.getValue().phoneProperty());
        emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());

        personTable.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> showPersonDetails(newValue)
        );

        addButton.setOnAction((event)->{
            Person newPerson = new Person();

            try {
                showPersonForm(newPerson, true);
            } catch (IOException ioException) {
                Logger.getLogger(PersonController.class.getName()).log(Level.SEVERE, null, ioException);
            }

            try {
                refreshPersonTable(personDAO.get());
            } catch (SQLException sqlException) {
                Logger.getLogger(PersonController.class.getName()).log(Level.SEVERE, null, sqlException);
            }
        });

        searchField.textProperty().addListener(
            (observable, oldValue, newValue) -> {

                try {
                    ObservableList<Person> filteredPersonData = FXCollections.observableArrayList();
                    filteredPersonData = personDAO.get(newValue);
                    refreshPersonTable(filteredPersonData);
                } catch (SQLException sqlException) {
                    Logger.getLogger(PersonController.class.getName()).log(Level.SEVERE, null, sqlException);
                }

            }
        );

        editButton.setOnAction((event)->{
            Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
            int selectedIndex = personTable.getSelectionModel().getSelectedIndex();

            if (selectedPerson != null) {
                try {
                    showPersonForm(selectedPerson, false);
                } catch (IOException ioException) {
                    Logger.getLogger(PersonController.class.getName()).log(Level.SEVERE, null, ioException);
                }

                try {
                    refreshPersonTable(personDAO.get());
                } catch (SQLException sqlException) {
                    Logger.getLogger(PersonController.class.getName()).log(Level.SEVERE, null, sqlException);
                }

                showPersonDetails(selectedPerson);
                personTable.getSelectionModel().select(selectedIndex);
            }
        });

        deleteButton.setOnAction((event)->{
            Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
            int selectedIndex = personTable.getSelectionModel().getSelectedIndex();

            if (selectedPerson != null) {
                personDAO.delete(selectedPerson);

                try {
                    refreshPersonTable(personDAO.get());
                } catch (SQLException sqlException) {
                    Logger.getLogger(PersonController.class.getName()).log(Level.SEVERE, null, sqlException);
                }
            }
        });
    }

    public void init(Contact app) throws SQLException {
        this.app = app;

        personDAO = new PersonDAO();
        personData = personDAO.get();

        personTable.setItems(personData);

        showPersonDetails(null);
    }

    private void showPersonDetails(Person person) {
        personDetails.setVisible(person != null);
        actionBar.setVisible(person != null);

        if (person != null) {
            nameLabel.setText(person.getName());
            phoneLabel.setText(person.getPhone());
            emailLabel.setText(person.getEmail());
            genderLabel.setText(person.getGender() ? "Male" : "Female");
            birthdayLabel.setText(Date.format(person.getBirthday()));
        }
    }

    private void showPersonForm(Person person, boolean method) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/dialog/PersonForm.fxml"));
        AnchorPane personFormPage = fxmlLoader.load();

        Stage personFormStage = new Stage();
        personFormStage.setTitle(method ? "New Contact" : "Edit Contact");
        personFormStage.initModality(Modality.APPLICATION_MODAL);
        personFormStage.initOwner(app.getMainStage());
        personFormStage.setScene(new Scene(personFormPage));

        PersonFormController personFormController = fxmlLoader.getController();
        personFormController.init(personFormStage, personDAO, person, method);

        personFormStage.showAndWait();
    }

    private void refreshPersonTable(ObservableList<Person> newData) {
        personData = newData;
        personTable.getItems().clear();
        personTable.setItems(personData);
    }

}
