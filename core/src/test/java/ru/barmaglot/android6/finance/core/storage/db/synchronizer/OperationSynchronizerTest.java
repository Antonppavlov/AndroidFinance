package ru.barmaglot.android6.finance.core.storage.db.synchronizer;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Currency;
import java.util.List;

import ru.barmaglot.andoroid6.finance.core.storage.dao.decotation.OperationSynchronizer;
import ru.barmaglot.andoroid6.finance.core.storage.dao.decotation.SourceSynchronizer;
import ru.barmaglot.andoroid6.finance.core.storage.dao.decotation.StorageSynchronizer;
import ru.barmaglot.andoroid6.finance.core.storage.dao.impls.OperationDAO;
import ru.barmaglot.andoroid6.finance.core.storage.dao.impls.SourceDAO;
import ru.barmaglot.andoroid6.finance.core.storage.dao.impls.StorageDAO;
import ru.barmaglot.andoroid6.finance.core.storage.exception.AmountException;
import ru.barmaglot.andoroid6.finance.core.storage.exception.CurrencyException;
import ru.barmaglot.andoroid6.finance.core.storage.objects.impl.operation.ConvertOperation;
import ru.barmaglot.andoroid6.finance.core.storage.objects.impl.operation.IncomeOperation;
import ru.barmaglot.andoroid6.finance.core.storage.objects.impl.operation.OutcomeOperation;
import ru.barmaglot.andoroid6.finance.core.storage.objects.impl.operation.TransferOperation;
import ru.barmaglot.andoroid6.finance.core.storage.objects.interfaces.operation.IOperation;
import ru.barmaglot.andoroid6.finance.core.storage.objects.interfaces.storage.IStorage;
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
    public void addOperationIncome() throws CurrencyException, AmountException {
        //подготовка операции
        long id = 1;
        BigDecimal money = BigDecimal.valueOf(10);
        IStorage storage = storageSynchronizer.get(id);
        Currency currencyInStorage = storageSynchronizer.getIdentityMap().get(id).getAvailableCurrencies().get(0);
        IncomeOperation incomeOperation = new IncomeOperation(
                Calendar.getInstance(),
                OperationType.INCOME,
                "купил продуктов",
                sourceSynchronizer.get(id),
                storageSynchronizer.getIdentityMap().get(id).getAvailableCurrencies().get(0),
                money,
                storage
        );

        double amountInStorageBeforeIncome = storage.getAmount(currencyInStorage).doubleValue();

        //провередние операции
        Assert.assertTrue(operationSynchronizer.add(incomeOperation));

        //проверка добавления в коллекции
        Assert.assertTrue(operationSynchronizer.getOperationList().contains(incomeOperation));

        Assert.assertTrue(operationSynchronizer.getOperationMap().get(incomeOperation.getOperationType()).contains(incomeOperation));

        Assert.assertEquals(
                operationSynchronizer.getIdentityMap().get(incomeOperation.getId()),
                incomeOperation);

        //проверка обновления банса в хранилищах
        double amountInStorageAfterIncome = storage.getAmount(currencyInStorage).doubleValue();

        Assert.assertTrue(amountInStorageAfterIncome == amountInStorageBeforeIncome + money.doubleValue());
    }

    @Test
    public void addOperationOutcome() throws CurrencyException, AmountException {
        long id = 1;
        BigDecimal money = BigDecimal.valueOf(10);
        IStorage storage = storageSynchronizer.get(id);
        Currency currencyInStorage = storageSynchronizer.getIdentityMap().get(id).getAvailableCurrencies().get(0);
        OutcomeOperation outcomeOperation = new OutcomeOperation(
                Calendar.getInstance(),
                OperationType.OUTCOME,
                "Нашел 10 рубчиков",
                storage,
                currencyInStorage,
                money,
                sourceSynchronizer.getListSource(OperationType.OUTCOME).get(0)
        );

        double amountInStorageBeforeIncome = storage.getAmount(currencyInStorage).doubleValue();


        Assert.assertTrue(operationSynchronizer.add(outcomeOperation));

        //проверка добавления в коллекции
        Assert.assertTrue(operationSynchronizer.getOperationList().contains(outcomeOperation));
        Assert.assertTrue(operationSynchronizer.getOperationMap().get(outcomeOperation.getOperationType()).contains(outcomeOperation));

        Assert.assertEquals(
                operationSynchronizer.getIdentityMap().get(outcomeOperation.getId()),
                outcomeOperation);

        //проверка обновление банса в хранилищах и обновление коллекций для OUTCOME
        //проверка обновления банса в хранилищах
        double amountInStorageAfterIncome = storage.getAmount(currencyInStorage).doubleValue();

        Assert.assertTrue(amountInStorageAfterIncome == amountInStorageBeforeIncome - money.doubleValue());

    }

    @Test
    public void addOperationTransfer() throws CurrencyException, AmountException {
        BigDecimal money = BigDecimal.valueOf(10);
        IStorage storageOne = storageSynchronizer.get(1);
        IStorage storageTwo = storageSynchronizer.get(2);
        Currency currencyRUB = Currency.getInstance("RUB");

        BigDecimal amountOneStorageRubBefore = storageOne.getAmount(currencyRUB);
        BigDecimal amountTwoStorageRubBefore = storageTwo.getAmount(currencyRUB);

        TransferOperation transferOperation = new TransferOperation(
                Calendar.getInstance(),
                OperationType.TRANSFER,
                "Перевел 10 рублей на другое хранилище",
                storageOne,
                currencyRUB,
                money,
                storageTwo
        );
        Assert.assertTrue(operationSynchronizer.add(transferOperation));

        //обновление коллекций для TRANSFER
        Assert.assertTrue(operationSynchronizer.getOperationList().contains(transferOperation));
        Assert.assertTrue(operationSynchronizer.getOperationMap().get(transferOperation.getOperationType()).contains(transferOperation));
        Assert.assertEquals(operationSynchronizer.getIdentityMap().get(transferOperation.getId()), transferOperation);

        //проверку обновление банса в хранилищах и
        Assert.assertTrue(amountOneStorageRubBefore.doubleValue() - money.doubleValue() == storageOne.getAmount(currencyRUB).doubleValue());
        Assert.assertTrue(amountTwoStorageRubBefore.doubleValue() + money.doubleValue() == storageTwo.getAmount(currencyRUB).doubleValue());


    }

    @Test
    public void addOperationConvert() throws CurrencyException, AmountException {
        IStorage storageOne = storageSynchronizer.get(1);
        IStorage storageTwo = storageSynchronizer.get(2);
        Currency currencyRUB = Currency.getInstance("RUB");
        Currency currencyENG = Currency.getInstance("USD");
        BigDecimal moneyRUB = BigDecimal.valueOf(10);
        BigDecimal moneyENG = BigDecimal.valueOf(1);


        double doubleOneRUBBeforeAdd = storageOne.getAmount(currencyRUB).doubleValue();
        double doubleTwoENGBeforeAdd = storageTwo.getAmount(currencyENG).doubleValue();


        ConvertOperation convertOperation = new ConvertOperation(
                Calendar.getInstance(),
                OperationType.CONVERT,
                "Конвертация 10 рублей на другое хранилище в доллараы",
                storageOne,
                currencyRUB,
                moneyRUB,
                storageTwo,
                currencyENG,
                moneyENG
        );


        Assert.assertTrue(operationSynchronizer.add(convertOperation));

        //проверка добавления в коллекции
        Assert.assertTrue(operationSynchronizer.getOperationList().contains(convertOperation));
        Assert.assertTrue(operationSynchronizer.getOperationMap().get(convertOperation.getOperationType()).contains(convertOperation));
        Assert.assertEquals(operationSynchronizer.getIdentityMap().get(convertOperation.getId()), convertOperation);

        // проверку обновление банса в хранилищах и обновление коллекций для TRANSFER
        Assert.assertTrue(doubleOneRUBBeforeAdd - moneyRUB.doubleValue() == storageOne.getAmount(currencyRUB).doubleValue());

        Assert.assertTrue(doubleTwoENGBeforeAdd + moneyENG.doubleValue() == storageTwo.getAmount(currencyENG).doubleValue());
        // storageOne.getAmount()
    }

    @Test
    public void update() throws CurrencyException, AmountException {
        //Откат предыдущих значений операции(удаление старой операции)
        //Добавление новой информации(добавление обновленной операции)
        //Не даем менять тип операции
        //  Assert.assertTrue(operationSynchronizer.update(incomeOperation));
        long id = 1;
        BigDecimal money = BigDecimal.valueOf(10);
        IStorage storage = storageSynchronizer.get(id);
        Currency currencyInStorage = storageSynchronizer.getIdentityMap().get(id).getAvailableCurrencies().get(0);
        IncomeOperation incomeOperation = new IncomeOperation(
                Calendar.getInstance(),
                OperationType.INCOME,
                "купил продуктов",
                sourceSynchronizer.get(id),
                currencyInStorage,
                money,
                storage
        );

        Assert.assertTrue(operationSynchronizer.add(incomeOperation));

        double beforeAmount = storage.getAmount(currencyInStorage).doubleValue();

        String newNameOperation = "New Name Operation";
        incomeOperation.setDescription(newNameOperation);
        BigDecimal newMoney = BigDecimal.valueOf(20);
        incomeOperation.setFromAmount(newMoney);

        Assert.assertTrue(operationSynchronizer.update(incomeOperation));

        double afterAmount = storage.getAmount(currencyInStorage).doubleValue();


        IOperation iOperation = operationSynchronizer.get(incomeOperation.getId());

        // TODO: 29.01.17 добработать чтобы проверял измение баланса для всех типов операции

        Assert.assertEquals(iOperation.getDescription(), newNameOperation);
        System.out.println(beforeAmount);

        System.out.println(afterAmount);
        Assert.assertTrue(beforeAmount-money.doubleValue()+newMoney.doubleValue()==afterAmount);

    }

    @Test
    public void delete() throws CurrencyException, AmountException {
        long id = 1;
        BigDecimal money = BigDecimal.valueOf(10);
        IStorage storage = storageSynchronizer.get(id);
        Currency currencyInStorage = storageSynchronizer.getIdentityMap().get(id).getAvailableCurrencies().get(0);
        IncomeOperation incomeOperation = new IncomeOperation(
                Calendar.getInstance(),
                OperationType.INCOME,
                "купил продуктов",
                sourceSynchronizer.get(id),
                storageSynchronizer.getIdentityMap().get(id).getAvailableCurrencies().get(0),
                money,
                storage
        );

        boolean add = operationSynchronizer.add(incomeOperation);
        Assert.assertTrue(add);

        BigDecimal amountBeforeDelete = storage.getAmount(currencyInStorage);
        boolean delete = operationSynchronizer.delete(incomeOperation);
        Assert.assertTrue(delete);

        IOperation iOperation = operationSynchronizer.get(incomeOperation.getId());
        BigDecimal amountAfterDelete = storage.getAmount(currencyInStorage);

        Assert.assertNull(iOperation);
        Assert.assertTrue(
                amountBeforeDelete.doubleValue()==
                amountAfterDelete.doubleValue()+money.doubleValue()
        );
        // TODO: 30.01.17 нужно написать проверки для других типов операции
    }
}