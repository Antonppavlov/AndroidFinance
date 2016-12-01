package ru.barmaglot.andoroid6.finance.core.storage.impl;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ru.barmaglot.andoroid6.finance.core.storage.interfaces.Storage;

public class DefaultStorage implements Storage {

    private String name;
    private Map<Currency, BigDecimal> currencyAmount = new HashMap<>();
    private List<Currency> allCurrency = new ArrayList<>();

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Map<Currency, BigDecimal> getCurrencyAmount() {
        return currencyAmount;
    }

    public void setCurrencyAmount(Map<Currency, BigDecimal> currencyAmount) {
        this.currencyAmount = currencyAmount;
    }

    @Override
    public List<Currency> getAllCurrency() {
        return allCurrency;
    }

    public void setAllCurrency(List<Currency> allCurrency) {
        this.allCurrency = allCurrency;
    }


    @Override
    public void changeAmount(BigDecimal amount, Currency currency) {
        currencyAmount.put(currency, amount);
    }

    @Override
    public void addAmount(BigDecimal amount, Currency currency) {
        BigDecimal oldAmount = currencyAmount.get(currency);
        currencyAmount.put(currency,oldAmount.add(amount));
    }

    @Override
    public void expenseAmount(BigDecimal amount, Currency currency) {
        BigDecimal oldAmount = currencyAmount.get(currency);
        currencyAmount.put(currency,oldAmount.subtract(amount));
    }

    @Override
    public void addCurrency(Currency currency) {
        allCurrency.add(currency);
        currencyAmount.put(currency, BigDecimal.ZERO);
    }

    @Override
    public void deleteCurrency(Currency currency) {
        allCurrency.remove(currency);
        currencyAmount.remove(currency);
    }

    @Override
    public BigDecimal getApproxAmount(Currency currency) {
        // TODO: 30.11.16 реализовать метод приведение всех валют хранилища к одной
        throw new UnsupportedOperationException("Not implemented!!!");
    }

    @Override
    public Currency getCurrency(String code) {
        Currency searchCurrency = null;
        for (Currency currency : allCurrency) {
            if (currency.getCurrencyCode().equals(code)) {
                searchCurrency = currency;
                break;
            }
        }
        return searchCurrency;
    }
}
