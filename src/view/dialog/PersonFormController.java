/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.dialog;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import model.Person;
import model.PersonDAO;
import util.Date;

/**
 * FXML Controller class
 *
 * @author Sidi
 */
public class PersonFormController implements Initializable {

    @FXML
    private Label nameLabel;

    @FXML
    private TextField nameField;

    @FXML
    private TextField phoneField;

    @FXML
    private TextField emailField;

    @FXML
    private CheckBox genderField;

    @FXML
    private DatePicker birthdayField;

    @FXML
    private Button saveButton;

    @FXML
    private Button cancelButton;

    private Stage personFormStage;
    private PersonDAO personDAO;
    private Person person;
    private boolean method;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        saveButton.setOnAction((event)->{
            if (isValid()) {
                person.setName(nameField.getText());
                person.setPhone(phoneField.getText());
                person.setEmail(emailField.getText());
                person.setGender(genderField.isSelected());
                person.setBirthday(birthdayField.getValue());

                if (method) {
                    personDAO.insert(person);
                } else {
                    personDAO.update(person);
                }

                personFormStage.close();
            }
        });

        cancelButton.setOnAction((event)->{
            personFormStage.close();
        });
    }

    public void init(Stage personFormStage, PersonDAO personDAO, Person person, boolean method) {
        this.personFormStage = personFormStage;
        this.personDAO = personDAO;
        this.person = person;
        this.method = method;

        initForm();
    }

    private void initForm() {
        nameField.setText(person.getName());
        phoneField.setText(person.getPhone());
        emailField.setText(person.getEmail());
        genderField.setSelected(person.getGender());
        birthdayField.setValue(person.getBirthday());

        birthdayField.setConverter(new StringConverter<LocalDate>() {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(Date.getPattern());

            @Override
            public String toString(LocalDate date) {
                return (date != null ? dateTimeFormatter.format(date) : "");
            }

            @Override
            public LocalDate fromString(String string) {
                return (string != null && !string.isEmpty() ? LocalDate.parse(string, dateTimeFormatter) : null);
            }
        });

        Platform.runLater(()->nameField.requestFocus());

    }

    private boolean isValid() {
        boolean isValid = true;

        if (nameField.getText() == null || nameField.getText().length() == 0) {
            nameLabel.setTextFill(Color.RED);
            nameField.requestFocus();
            isValid = false;
        }

        return isValid;
    }

}
