package ru.barmaglot.android6.finance.core.storage.db.dao;

import org.junit.Test;

import ru.barmaglot.andoroid6.finance.core.storage.dao.decotation.SourceSynchronizer;
import ru.barmaglot.andoroid6.finance.core.storage.dao.decotation.StorageSynchronizer;
import ru.barmaglot.andoroid6.finance.core.storage.dao.impls.OperationDAO;
import ru.barmaglot.andoroid6.finance.core.storage.dao.impls.SourceDAO;
import ru.barmaglot.andoroid6.finance.core.storage.dao.impls.StorageDAO;
import ru.barmaglot.andoroid6.finance.core.storage.objects.impl.source.DefaultSource;

public class OperationDAOTest {

    OperationDAO operationDAO = new OperationDAO(
            new SourceSynchronizer(new SourceDAO()).getIdentityMap(),
            new StorageSynchronizer(new StorageDAO()).getIdentityMap());

    @Test
    public void getList() {

    }

    @Test
    public void getAll() {
        System.out.println(operationDAO.getAll());
    }

    @Test
    public void get() {
    }

    @Test
    public void add() {
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }
}