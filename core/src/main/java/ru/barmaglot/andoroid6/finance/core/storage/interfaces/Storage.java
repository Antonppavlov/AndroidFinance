package ru.barmaglot.andoroid6.finance.core.storage.interfaces;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;
import java.util.Map;

import ru.barmaglot.andoroid6.finance.core.storage.myexception.AmountException;
import ru.barmaglot.andoroid6.finance.core.storage.myexception.CurrencyException;

// TODO изменить тип BigDecimal на готовый класс по работе с деньгами Money
public interface Storage {

    String getName();// обязывает реализовать свойство name

    // получение баланса (остатка)
    Map<Currency, BigDecimal> getCurrencyAmounts(); // остаток по каждой доступной валюте в хранилище

    BigDecimal getAmount(Currency currency) throws CurrencyException; // остаток по определенной валюте

    BigDecimal getApproxAmount(Currency currency);// примерный остаток в переводе всех денег в одну валюту


    // изменение баланса
    void changeAmount(BigDecimal amount, Currency currency) throws CurrencyException; // изменение баланса по определенной валюте

    void addAmount(BigDecimal amount, Currency currency) throws AmountException, CurrencyException; // добавить сумму в валюте

    void expenseAmount(BigDecimal amount, Currency currency) throws AmountException, CurrencyException;  // отнять сумму в валюте


    // работа с валютой
    void addCurrency(Currency currency) throws CurrencyException; // добавить новую валюту в хранилище

    void deleteCurrency(Currency currency) throws CurrencyException; // удалить валюту из хранилища

    Currency getCurrency(String code)throws CurrencyException; // получить валюту по коду

    List<Currency> getAvailableCurrencies(); // получить все доступные валюты хранилища в отдельной коллекции

}