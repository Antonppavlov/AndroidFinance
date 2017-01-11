package ru.barmaglot.android6.finance.core.storage.db.dao;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import ru.barmaglot.andoroid6.finance.core.storage.dao.decotation.SourceSynchronizer;
import ru.barmaglot.andoroid6.finance.core.storage.dao.decotation.StorageSynchronizer;
import ru.barmaglot.andoroid6.finance.core.storage.dao.impls.OperationDAO;
import ru.barmaglot.andoroid6.finance.core.storage.dao.impls.SourceDAO;
import ru.barmaglot.andoroid6.finance.core.storage.dao.impls.StorageDAO;
import ru.barmaglot.andoroid6.finance.core.storage.exception.CurrencyException;
import ru.barmaglot.andoroid6.finance.core.storage.objects.impl.operation.IncomeOperation;
import ru.barmaglot.andoroid6.finance.core.storage.objects.interfaces.operation.IOperation;
import ru.barmaglot.andoroid6.finance.core.storage.objects.type.OperationType;

public class OperationDAOTest {

    private final OperationDAO operationDAO = new OperationDAO(
            new SourceSynchronizer(new SourceDAO()).getIdentityMap(),
            new StorageSynchronizer(new StorageDAO()).getIdentityMap());

    @Test
    public void getListOperationType() {
        OperationType operationType = OperationType.INCOME;

        List<IOperation> list = operationDAO.getList(operationType);
        for (IOperation iOperation : list) {
            Assert.assertEquals(iOperation.getOperationType(), operationType);
        }
    }

    @Test
    public void getAll() {
        Assert.assertTrue(operationDAO.getAll().size() > 0);
    }

    @Test
    public void get() {
        long idObject = operationDAO.getAll().get(0).getId();
        IOperation iOperation = operationDAO.get(idObject);

        Assert.assertNotNull(iOperation);
    }

    @Test
    public void add() throws CurrencyException {
        IncomeOperation incomeOperation;
        incomeOperation = new IncomeOperation(
                Calendar.getInstance(),
                "купил продуктов",
                OperationType.INCOME,
                operationDAO.getSourceIdentityMap().get(1),
                operationDAO.getStorageIdentityMap().get(1),
                BigDecimal.valueOf(10),
                operationDAO.getStorageIdentityMap().get(1).getAvailableCurrencies().get(0)
        );


       operationDAO.add(incomeOperation);

    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }
}