package ru.barmaglot.andoroid6.finance.core.storage.dao.interfaces;

import java.math.BigDecimal;
import java.util.Currency;

import ru.barmaglot.andoroid6.finance.core.storage.exception.CurrencyException;
import ru.barmaglot.andoroid6.finance.core.storage.interfaces.storage.IStorage;

//во время использование ICommonDAO<IStorage>
// передаем в объект IStorage и подставляем вместо Т
public interface IStorageDAO extends ICommonDAO<IStorage> {
    //добавление валюты в определенное хранилище
    boolean addCurrency(IStorage storage, Currency currency,BigDecimal amount) throws CurrencyException;
    boolean deleteCurrency(IStorage storage, Currency currency) throws CurrencyException;//удаление
    boolean updateAmount(IStorage storage,Currency currency ,BigDecimal amount); //обновить значение остатка


}
