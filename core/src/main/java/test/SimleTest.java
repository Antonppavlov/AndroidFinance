package test;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.Currency;

import ru.barmaglot.andoroid6.finance.core.storage.impl.DefaultStorage;
import ru.barmaglot.andoroid6.finance.core.storage.myexception.AmountException;
import ru.barmaglot.andoroid6.finance.core.storage.myexception.CurrencyException;

/**
 * Created by antonpavlov on 01.12.16.
 */

public class SimleTest {
    @Test(expected = CurrencyException.class)
    public void test1() throws CurrencyException {
        DefaultStorage defaultStorage = new DefaultStorage();
        defaultStorage.getAmount(Currency.getInstance("RUB"));
    }

    @Test()
    public void test2() throws CurrencyException {
        DefaultStorage defaultStorage = new DefaultStorage();
        defaultStorage.addCurrency(Currency.getInstance("RUB"));
        defaultStorage.addAmount(BigDecimal.ZERO,Currency.getInstance("RUB"));
    }

    @Test()
    public void test3() throws AmountException, CurrencyException {
        DefaultStorage defaultStorage = new DefaultStorage();
        defaultStorage.addCurrency(Currency.getInstance("RUB"));
        defaultStorage.addAmount(BigDecimal.ONE,Currency.getInstance("RUB"));
    }
}
