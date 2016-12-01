package ru.barmaglot.andoroid6.finance.core.storage.interfaces;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;
import java.util.Map;

// TODO: 30.11.16 изменить тип BigDecimal на готовый класс по работе с деньгами Money
public interface Storage {

     String getName();

    //работа с балансом
    Map<Currency,BigDecimal> getCurrencyAmount();
    void changeAmount(BigDecimal amount, Currency currency);
    void addAmount(BigDecimal amount, Currency currency);
    void expenseAmount(BigDecimal amount, Currency currency);
    BigDecimal getApproxAmount(Currency currency);//привести все на хранилище в одну валюту


    //работа с валютой
    List<Currency> getAllCurrency();
    void addCurrency(Currency currency);
    void deleteCurrency(Currency currency);
    Currency getCurrency(String code);



}
