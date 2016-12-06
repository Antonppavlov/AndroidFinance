package test;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.Currency;

import ru.barmaglot.andoroid6.finance.core.storage.exception.AmountException;
import ru.barmaglot.andoroid6.finance.core.storage.exception.CurrencyException;
import ru.barmaglot.andoroid6.finance.core.storage.impl.storage.DefaultStorage;


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
        defaultStorage.addAmount(BigDecimal.ZERO, Currency.getInstance("RUB"));
    }

    @Test()
    public void test3() throws AmountException, CurrencyException {
        DefaultStorage defaultStorage = new DefaultStorage();
        defaultStorage.addCurrency(Currency.getInstance("RUB"));
        defaultStorage.addAmount(BigDecimal.ONE, Currency.getInstance("RUB"));
    }
}
