package ru.barmaglot.andoroid6.finance.core.storage.database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by antonpavlov on 05.12.16.
 */
public class SQLiteConnection {
    private static SQLiteConnection ourInstance = new SQLiteConnection();
    private Connection connection = null;


    public static SQLiteConnection getInstance() {
        return ourInstance;
    }

    private SQLiteConnection() {
    }

    public Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection("jdbc:sqlite:/Users/antonpavlov/AndroidStudioProjects/AndroidFinance/core/src/main/java/resource/money.db");
                connection.createStatement().execute("PRAGMA foreign_keys = ON");
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }


}
