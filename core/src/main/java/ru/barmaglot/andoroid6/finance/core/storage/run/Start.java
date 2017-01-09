package ru.barmaglot.andoroid6.finance.core.storage.run;

import ru.barmaglot.andoroid6.finance.core.storage.dao.decotation.SourceSynchronizer;
import ru.barmaglot.andoroid6.finance.core.storage.dao.impls.SourceDAO;
import ru.barmaglot.andoroid6.finance.core.storage.dao.impls.StorageDAO;

public class Start {
    public static void main(String[] args) {

//        try (
//                Statement stmt = SQLiteConnection.getInstance().getConnection().createStatement();
//                ResultSet rs = stmt.executeQuery("select * from storage")
//
//        ) {
//
//            while (rs.next()) {
//                System.out.println(rs.getString("name"));
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

      //  new StorageDAO().getAll();
        System.out.println( new SourceSynchronizer(new SourceDAO()).getAll().get(1));
        System.out.println((new StorageDAO()).getAll());
    }
}
