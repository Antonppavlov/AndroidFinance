package ru.barmaglot.andoroid6.finance.core.storage.database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by antonpavlov on 05.12.16.
 */
public class SQLiteConnection {
    private static SQLiteConnection ourInstance = new SQLiteConnection();
    private Connection connection;


    public static SQLiteConnection getInstance() {
        return ourInstance;
    }

    private SQLiteConnection() {
    }


    public Connection getConnection() {
        try {

            Class.forName("org.sqlite.JDBC").newInstance();// можно эту строчку удалить - драйвер автоматически будет найден

            // создание подключение к базе данных по пути, указанному в урле
            //  String url = "jdbc:sqlite:/Users/antonpavlov/AndroidStudioProjects/AndroidFinance/core/src/main/resource/money.db";
          String url = "jdbc:sqlite:\\Users\\ap_pavlov\\StudioProjects\\AndroidFinance\\core\\src\\main\\resource\\money.db";

            if (connection ==null){
                connection = DriverManager.getConnection(url);
            }
            
            connection.createStatement().execute("PRAGMA foreign_keys = ON");
            connection.createStatement().execute("PRAGMA encoding = \"UTF-8\"");

            return connection;

        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(SQLiteConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

}
