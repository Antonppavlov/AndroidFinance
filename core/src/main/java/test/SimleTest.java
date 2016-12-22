package test;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;

import ru.barmaglot.andoroid6.finance.core.storage.dao.decotation.SourceSynchronizer;
import ru.barmaglot.andoroid6.finance.core.storage.dao.decotation.StorageSynchronizer;
import ru.barmaglot.andoroid6.finance.core.storage.dao.impls.SourceDAO;
import ru.barmaglot.andoroid6.finance.core.storage.dao.impls.StorageDAO;
import ru.barmaglot.andoroid6.finance.core.storage.exception.AmountException;
import ru.barmaglot.andoroid6.finance.core.storage.exception.CurrencyException;
import ru.barmaglot.andoroid6.finance.core.storage.impl.storage.DefaultStorage;
import ru.barmaglot.andoroid6.finance.core.storage.interfaces.ITreeNode;
import ru.barmaglot.andoroid6.finance.core.storage.interfaces.source.ISource;
import ru.barmaglot.andoroid6.finance.core.storage.type.OperationType;


public class SimleTest {
    @Test(expected = CurrencyException.class)
    public void test1() throws CurrencyException {
        DefaultStorage defaultStorage = new DefaultStorage();
        defaultStorage.getAmount(Currency.getInstance("RUB"));
    }

    @Test()
    public void checkGetAmountForCurrency() throws CurrencyException {
        DefaultStorage defaultStorage = new DefaultStorage();
        Currency rub = Currency.getInstance("RUB");
        defaultStorage.addCurrency(rub);
        defaultStorage.addAmount(BigDecimal.TEN, rub);
        Assert.assertEquals(defaultStorage.getAmount(rub),BigDecimal.TEN);
    }

    @Test()
    public void test2() throws CurrencyException {
        DefaultStorage defaultStorage = new DefaultStorage();
        defaultStorage.addCurrency(Currency.getInstance("RUB"));
        defaultStorage.addAmount(BigDecimal.ZERO, Currency.getInstance("RUB"));
    }

    @Test()
    public void test3() throws AmountException, CurrencyException {
        DefaultStorage defaultStorage = new DefaultStorage();
        defaultStorage.addCurrency(Currency.getInstance("RUB"));
        defaultStorage.addAmount(BigDecimal.ONE, Currency.getInstance("RUB"));
    }

    @Test()
    public void test4()  {
        StorageSynchronizer storageSynchronizer = new StorageSynchronizer(new StorageDAO());
        ITreeNode iTreeNode = storageSynchronizer.getAll().get(0).getChilds().get(1);
        Assert.assertEquals(iTreeNode.getName(),"Сбербанк");
    }


    @Test()
    public void test5()  {
        SourceDAO sourceDAO = new SourceDAO();
        Assert.assertEquals(sourceDAO.getAll().get(0).getOperationType(), OperationType.INCOME);
    }


    @Test()
    public void test6()  {
        SourceDAO sourceDAO = new SourceDAO();
        SourceSynchronizer sourceSynchronizer = new SourceSynchronizer(sourceDAO);
        List<ISource> listSource = sourceSynchronizer.getListSource(OperationType.OUTCOME);
        Assert.assertEquals(listSource.get(0).getName(), "Магазин");
    }
}
