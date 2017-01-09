package ru.barmaglot.android6.finance.core.storage.objects.storage;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.Currency;

import ru.barmaglot.andoroid6.finance.core.storage.exception.CurrencyException;
import ru.barmaglot.andoroid6.finance.core.storage.objects.impl.storage.DefaultStorage;

@RunWith(Parameterized.class)
public class DefaultStorageTest {


    private final DefaultStorage defaultStorage = new DefaultStorage();

    @Parameterized.Parameter
    public Currency currency;


    @Parameterized.Parameters
    public static Collection<Currency> getParameters() {
        return Arrays.asList(
                Currency.getInstance("RUB"),
                Currency.getInstance("EUR"),
                Currency.getInstance("USD")
        );
    }





    @Test
    public void checkAddCurrency() throws CurrencyException {
        defaultStorage.addCurrency(currency);

        Assert.assertEquals(defaultStorage.getAvailableCurrencies().get(0), currency);
    }

    @Test(expected = CurrencyException.class)
    public void checkErrorWhenRepeatAddCurrency() throws CurrencyException {
        defaultStorage.addCurrency(currency);
        defaultStorage.addCurrency(currency);
    }

    @Test
    public void checkGetSumInCurrency() throws CurrencyException {
        defaultStorage.addCurrency(currency);

        BigDecimal sumCurrencyRub = defaultStorage.getCurrencyAmounts().get(currency);
        Assert.assertEquals(sumCurrencyRub, BigDecimal.ZERO);
    }


    @Test
    public void checkUpdateAmount() throws CurrencyException {
        defaultStorage.addCurrency(currency);

        BigDecimal money = BigDecimal.valueOf(31312);
        defaultStorage.updateAmount(money, currency);

        BigDecimal sumCurrencyRub = defaultStorage.getCurrencyAmounts().get(currency);

        Assert.assertEquals(sumCurrencyRub, money);
    }

    @Test(expected = CurrencyException.class)
    public void checkDeleteCurrency() throws CurrencyException {
        defaultStorage.addCurrency(currency);
        defaultStorage.deleteCurrency(currency);


        defaultStorage.getAmount(currency);
    }
}
