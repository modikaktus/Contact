/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDate;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Sidi
 */
public class Person {

    private final IntegerProperty id;
    private final StringProperty name;
    private final StringProperty phone;
    private final StringProperty email;
    private final BooleanProperty gender;
    private final ObjectProperty<LocalDate> birthday;

    public Person() {
        this(null);
    }

    public Person(String name) {
        this.id = new SimpleIntegerProperty(0);
        this.name = new SimpleStringProperty(name);
        this.phone = new SimpleStringProperty("");
        this.email = new SimpleStringProperty("");
        this.gender = new SimpleBooleanProperty(true);
        this.birthday = new SimpleObjectProperty<>(LocalDate.of(1945, 1, 1));
    }

    public int getId() {
        return id.get();
    }

    public Person setId(int id) {
        this.id.set(id);

        return this;
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public String getName() {
        return name.get();
    }

    public Person setName(String name) {
        this.name.set(name);

        return this;
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getPhone() {
        return phone.get();
    }

    public Person setPhone(String phone) {
        this.phone.set(phone);

        return this;
    }

    public StringProperty phoneProperty() {
        return phone;
    }

    public String getEmail() {
        return email.get();
    }

    public Person setEmail(String email) {
        this.email.set(email);

        return this;
    }

    public StringProperty emailProperty() {
        return email;
    }

    public boolean getGender() {
        return gender.get();
    }

    public Person setGender(boolean gender) {
        this.gender.set(gender);

        return this;
    }

    public BooleanProperty genderProperty() {
        return gender;
    }

    public LocalDate getBirthday() {
        return birthday.get();
    }

    public Person setBirthday(LocalDate birthday) {
        this.birthday.set(birthday);

        return this;
    }

    public ObjectProperty<LocalDate> birthdayProperty() {
        return birthday;
    }

}
