/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.Database;
import util.Date;

/**
 *
 * @author Sidi
 */
public class PersonDAO {

    private final Database database;
    private ObservableList<Person> personData;

    public PersonDAO() throws SQLException {
        this.database = new Database();
    }

    public ObservableList<Person> get(String... filter) throws SQLException {
        personData = FXCollections.observableArrayList();
        ResultSet result;

        if (filter.length > 0) {
            result = database.getQuery("SELECT * FROM person WHERE name LIKE ? ", "%" + filter[0] + "%");
        } else {
            result = database.getQuery("SELECT * FROM person");
        }

        Date.setPattern("yyyy-MM-dd");
        while (result.next()) {
            Person person = new Person();
            person.setId(result.getInt("id"))
                    .setName(result.getString("name"))
                    .setPhone(result.getString("phone"))
                    .setEmail(result.getString("email"))
                    .setGender(result.getBoolean("gender"))
                    .setBirthday(Date.parse(result.getString("birthday")));

            personData.add(person);
        }
        Date.setPattern("dd/MM/yyyy");

        return personData;
    }

    public int insert(Person person) {
        Date.setPattern("yyyy-MM-dd");

        String query = "INSERT INTO person (name, phone, email, gender, birthday) VALUES (?, ?, ?, ?, ?)";
        Object[] params = {
            person.getName(),
            person.getPhone(),
            person.getEmail(),
            person.getGender(),
            Date.format(person.getBirthday())
        };

        Date.setPattern("dd/MM/yyyy");

        return database.doQuery(query, params);
    }

    public int update(Person person) {
        Date.setPattern("yyyy-MM-dd");

        String query = "UPDATE person SET name = ?, phone = ?, email = ?, gender = ?, birthday = ? WHERE id = ?";
        Object[] params = {
            person.getName(),
            person.getPhone(),
            person.getEmail(),
            person.getGender(),
            Date.format(person.getBirthday()),
            person.getId()
        };

        Date.setPattern("dd/MM/yyyy");

        return database.doQuery(query, params);
    }

    public int delete(Person person) {
        String query = "DELETE FROM person WHERE id = ?";
        Object[] params = {person.getId()};

        return database.doQuery(query, params);
    }

}
