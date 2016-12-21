package ru.barmaglot.andoroid6.finance.core.storage.dao.impls;


import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

import ru.barmaglot.andoroid6.finance.core.storage.dao.interfaces.IStorageDAO;
import ru.barmaglot.andoroid6.finance.core.storage.database.SQLiteConnection;
import ru.barmaglot.andoroid6.finance.core.storage.impl.storage.DefaultStorage;
import ru.barmaglot.andoroid6.finance.core.storage.interfaces.storage.IStorage;
import ru.barmaglot.andoroid6.finance.core.storage.utils.TreeUtils;

public class StorageDAO implements IStorageDAO {

    private static final String CURRENCY_AMOUNT_TABLE = "currency_amount";
    private static final String STORAGE_TABLE = "storage";
    private TreeUtils<IStorage> treeUtils =new TreeUtils();

    private List<IStorage> storageList = new ArrayList<>();

    @Override
    public boolean addCurrency(IStorage storage, Currency currency) {
        try (PreparedStatement preparedStatement = SQLiteConnection.getInstance().getConnection()
                .prepareStatement(
                        "INSERT INTO " + CURRENCY_AMOUNT_TABLE + "(storage_id,currency_code,amount) values(?,?,?)")
             ;) {
            preparedStatement.setLong(1, storage.getId());
            preparedStatement.setString(2, currency.getCurrencyCode());
            preparedStatement.setBigDecimal(3, BigDecimal.ZERO);

            if (preparedStatement.executeUpdate() == 1) { //если добавлена одна запить то выбрасываем тру
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
        // TODO: 07.12.16 реализовать исключени и сообщать пользователю что такую операцию сделать нельзя т.к. валюта уже существует
    }

    @Override
    public boolean deleteCurrency(IStorage storage, Currency currency) {
        // TODO: 07.12.16 Необходимо проверять есть ли у сумма на счету и если есть выбрасывать ошибку
        // TODO: 07.12.16 или предлагать пользователю подтверждения удаления с суммой
        try (PreparedStatement preparedStatement = SQLiteConnection.getInstance().getConnection()
                .prepareStatement(
                        "DELETE FROM " + CURRENCY_AMOUNT_TABLE + " where storage_id=? and currency_code=?)")
             ;) {
            preparedStatement.setLong(1, storage.getId());
            preparedStatement.setString(2, currency.getCurrencyCode());

            if (preparedStatement.executeUpdate() == 1) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateAmount(IStorage storage, BigDecimal amount) {
        return false;
    }

    @Override
    public List<IStorage> getAll() {
        if (!storageList.isEmpty()) {
            storageList.clear();
        }
        try (PreparedStatement preparedStatement = SQLiteConnection.getInstance().getConnection()
                .prepareStatement(
                        "SELECT * FROM " + STORAGE_TABLE + ";")
             ;) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                DefaultStorage defaultStorage = new DefaultStorage();
                defaultStorage.setId(resultSet.getLong("id"));
                defaultStorage.setName(resultSet.getString("name"));

// TODO: 10.12.16 нужно сделать получение всех объектов
                long parentId = resultSet.getLong("parent_id");


                treeUtils.addToTree(parentId,defaultStorage,storageList);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return storageList;//должен содержать только корневые элементы
    }

    @Override
    public IStorage get(long id) {
        // TODO: 07.12.16 реализовать
        return null;
    }

    @Override
    public boolean add(IStorage object) {
        try (PreparedStatement preparedStatement = SQLiteConnection.getInstance().getConnection()
                .prepareStatement(
                        "INSERT FROM " + STORAGE_TABLE + "(name,parent_id) values(?,?)")
             ;) {
            preparedStatement.setString(2, object.getName());
            preparedStatement.setLong(1, object.getParent().getId());

            if (preparedStatement.executeUpdate() == 1) { //если обновлена одна запить то выбрасываем тру

                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(IStorage object) {
        try (PreparedStatement preparedStatement = SQLiteConnection.getInstance().getConnection()
                .prepareStatement(
                        "UPDATE INTO " + STORAGE_TABLE + "set name=? where id=?")
             ;) {
            preparedStatement.setString(2, object.getName());
            preparedStatement.setLong(1, object.getId());

            if (preparedStatement.executeUpdate() == 1) { //если обновлена одна запить то выбрасываем тру

                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(IStorage object) {
        // TODO: 07.12.16 проверять если есть заведенные валюты и операции по данному хранилищу то запрещать удаление
        try (PreparedStatement preparedStatement = SQLiteConnection.getInstance().getConnection()
                .prepareStatement(
                        "DELETE FROM " + STORAGE_TABLE + " where storage_id=?;")
             ;) {
            preparedStatement.setLong(1, object.getId());

            if (preparedStatement.executeUpdate() == 1) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
//SELECT
//        s1.id as id,
//        s1.name as name_storage,
//        s2.name as name_parent,
//        c.currency_code,
//        c.amount
//        From storage as s1
//        left JOIN storage as s2
//        ON s1.parent_id=s2.id
//
//        left JOIN currency_amount as c
//        ON s1.id=c.storage_id
//
//
//
//        ;