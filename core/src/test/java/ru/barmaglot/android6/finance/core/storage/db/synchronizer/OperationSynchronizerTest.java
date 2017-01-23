package ru.barmaglot.android6.finance.core.storage.db.synchronizer;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import ru.barmaglot.andoroid6.finance.core.storage.dao.decotation.OperationSynchronizer;
import ru.barmaglot.andoroid6.finance.core.storage.dao.decotation.SourceSynchronizer;
import ru.barmaglot.andoroid6.finance.core.storage.dao.decotation.StorageSynchronizer;
import ru.barmaglot.andoroid6.finance.core.storage.dao.impls.OperationDAO;
import ru.barmaglot.andoroid6.finance.core.storage.dao.impls.SourceDAO;
import ru.barmaglot.andoroid6.finance.core.storage.dao.impls.StorageDAO;
import ru.barmaglot.andoroid6.finance.core.storage.exception.CurrencyException;
import ru.barmaglot.andoroid6.finance.core.storage.objects.impl.operation.IncomeOperation;
import ru.barmaglot.andoroid6.finance.core.storage.objects.impl.operation.OutcomeOperation;
import ru.barmaglot.andoroid6.finance.core.storage.objects.interfaces.operation.IOperation;
import ru.barmaglot.andoroid6.finance.core.storage.objects.type.OperationType;

@RunWith(Parameterized.class)
public class OperationSynchronizerTest {

    private final SourceSynchronizer sourceSynchronizer = new SourceSynchronizer(new SourceDAO());
    private final StorageSynchronizer storageSynchronizer = new StorageSynchronizer(new StorageDAO());

    private final OperationSynchronizer operationSynchronizer = new OperationSynchronizer(
            sourceSynchronizer,
            storageSynchronizer,
            new OperationDAO(
                    sourceSynchronizer.getIdentityMap(),
                    storageSynchronizer.getIdentityMap()
            ));




    @Parameterized.Parameter
    public OperationType operationType;

    @Parameterized.Parameters
    public static Collection<OperationType> getParameters() {
        return Arrays.asList(
                OperationType.values()
        );
    }


    @Test
    public void getList() {
        List<IOperation> operationTypeList = operationSynchronizer.getList(operationType);
        for (IOperation operation : operationTypeList) {
            Assert.assertEquals(operation.getOperationType(), operationType);
        }
    }

    @Test
    public void getAll() {
        Assert.assertTrue(operationSynchronizer.getAll().size() > 1);
    }

    @Test
    public void get() {
        List<IOperation> allOperation = operationSynchronizer.getAll();
        IOperation lastOperation = allOperation.get(allOperation.size() - 1);

        IOperation operation = operationSynchronizer.get(lastOperation.getId());
        Assert.assertEquals(lastOperation, operation);
    }

    @Test
    public void addOperationIncome() throws CurrencyException {
        long id = 1;
        IncomeOperation incomeOperation = new IncomeOperation(
                Calendar.getInstance(),
                "купил продуктов",
                OperationType.INCOME,
                sourceSynchronizer.get(id),
                storageSynchronizer.get(id),
                BigDecimal.valueOf(10),
                storageSynchronizer.getIdentityMap().get(id).getAvailableCurrencies().get(0)
        );



        Assert.assertTrue(operationSynchronizer.add(incomeOperation));

        //проверка добавления в коллекции
        Assert.assertTrue(operationSynchronizer.getOperationList().contains(incomeOperation));
        Assert.assertTrue(operationSynchronizer.getOperationMap().get(incomeOperation.getOperationType()).contains(incomeOperation));
        Assert.assertEquals(operationSynchronizer.getIdentityMap().get(incomeOperation.getId()),incomeOperation);

        //TODO: 13.01.17 нужно написать проверку обновление банса в хранилищах и обновление коллекций для INCOME
        Assert.assertTrue(false);

    }

    @Test
    public void addOperationOutcome() throws CurrencyException {
        long id = 1;
        OutcomeOperation outcomeOperation = new OutcomeOperation();


        Assert.assertTrue(operationSynchronizer.add(incomeOperation));

        //проверка добавления в коллекции
        Assert.assertTrue(operationSynchronizer.getOperationList().contains(incomeOperation));
        Assert.assertTrue(operationSynchronizer.getOperationMap().get(incomeOperation.getOperationType()).contains(incomeOperation));
        Assert.assertEquals(operationSynchronizer.getIdentityMap().get(incomeOperation.getId()),incomeOperation);

        //TODO: 13.01.17 нужно написать проверку обновление банса в хранилищах и обновление коллекций для OUTCOME
        Assert.assertTrue(false);

    }
//
//    @Test
//    public void addOperationTransfer() throws CurrencyException {
//        incomeOperation.setOperationType(OperationType.TRANSFER);
//        incomeOperation.getToStorage().getAvailableCurrencies().get(0);
//        Assert.assertTrue(operationSynchronizer.add(incomeOperation));
//
//        //проверка добавления в коллекции
//        Assert.assertTrue(operationSynchronizer.getOperationList().contains(incomeOperation));
//        Assert.assertTrue(operationSynchronizer.getOperationMap().get(incomeOperation.getOperationType()).contains(incomeOperation));
//        Assert.assertEquals(operationSynchronizer.getIdentityMap().get(incomeOperation.getId()),incomeOperation);
//
//        //TODO: 13.01.17 нужно написать проверку обновление банса в хранилищах и обновление коллекций для TRANSFER
//        Assert.assertTrue(false);
//
//    }
//
//    @Test
//    public void addOperationConvert() throws CurrencyException {
//        incomeOperation.setOperationType(OperationType.CONVERT);
//        incomeOperation.getToStorage().getAvailableCurrencies().get(0);
//        Assert.assertTrue(operationSynchronizer.add(incomeOperation));
//
//        //проверка добавления в коллекции
//        Assert.assertTrue(operationSynchronizer.getOperationList().contains(incomeOperation));
//        Assert.assertTrue(operationSynchronizer.getOperationMap().get(incomeOperation.getOperationType()).contains(incomeOperation));
//        Assert.assertEquals(operationSynchronizer.getIdentityMap().get(incomeOperation.getId()),incomeOperation);
//
//        //TODO: 13.01.17 нужно написать проверку обновление банса в хранилищах и обновление коллекций для CONVERT
//        Assert.assertTrue(false);
//    }

    @Test
    public void update() {
        //Откат предыдущих значений операции(удаление старой операции)
        //Добавление новой информации(добавление обновленной операции)
        //Не даем менять тип операции
      //  Assert.assertTrue(operationSynchronizer.update(incomeOperation));
    }

    @Test
    public void delete() throws CurrencyException {
        long id = 1;
        IncomeOperation incomeOperation = new IncomeOperation(
                Calendar.getInstance(),
                "купил продуктов",
                OperationType.INCOME,
                sourceSynchronizer.get(id),
                storageSynchronizer.get(id),
                BigDecimal.valueOf(10),
                storageSynchronizer.getIdentityMap().get(id).getAvailableCurrencies().get(0)
        );


        boolean add = operationSynchronizer.add(incomeOperation);
        Assert.assertTrue(add);

        boolean delete = operationSynchronizer.delete(incomeOperation);
        Assert.assertTrue(delete);

        IOperation iOperation = operationSynchronizer.get(incomeOperation.getId());

        Assert.assertNull(iOperation);
    }
}