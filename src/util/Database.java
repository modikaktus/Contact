/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import org.sqlite.javax.SQLiteConnectionPoolDataSource;

/**
 *
 * @author Sidi
 */
public final class Database {

    private final String DATABASE = "jdbc:sqlite:data.db";
    private Connection connection;

    public Database() throws SQLException {
        connect(DATABASE);
    }

    private Database connect(String database) throws SQLException {
        SQLiteConnectionPoolDataSource dataSource = new SQLiteConnectionPoolDataSource();
        dataSource.setUrl(database);

        connection = dataSource.getConnection();

        return this;
    }

    public ResultSet getQuery(String query, Object... params) {
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            if (params != null) {
                setParameters(pstmt, params);
            }

            return pstmt.executeQuery();
        } catch (SQLException e) {
            return null;
        }
    }

    public int doQuery(String query, Object... params) {
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            if (params != null) {
                setParameters(pstmt, params);
            }

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            return -1;
        }
    }

    private void setParameters(PreparedStatement pstmt, Object[] params) throws SQLException{
        if (params == null) {
            return;
        }

        for (int i = 0; i < params.length; i++) {
            if (params[i] != null) {
                pstmt.setObject(i + 1, params[i]);
            } else {
                pstmt.setNull(i + 1, Types.OTHER);
            }
        }
    }

}
