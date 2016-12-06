package ru.barmaglot.andoroid6.finance.core.storage.run;

import java.sql.ResultSet;
import java.sql.Statement;

import ru.barmaglot.andoroid6.finance.core.storage.database.SQLiteConnection;

public class Start {
    public static void main(String[] args) {

        try (
                Statement stmt = SQLiteConnection.getInstance().getConnection().createStatement();
                ResultSet rs = stmt.executeQuery("select * from storage")

        ) {

            while (rs.next()) {
                System.out.println(rs.getString("name"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
