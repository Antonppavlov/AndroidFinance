package ru.barmaglot.andoroid6.finance.core.storage.dao.interfaces;

import java.util.List;

import ru.barmaglot.andoroid6.finance.core.storage.exception.AmountException;
import ru.barmaglot.andoroid6.finance.core.storage.exception.CurrencyException;

//описывает общие действия в бд для всех объектов
public interface ICommonDAO<T> {

    List<T> getAll();

    T get(long id);

    boolean add(T object) throws CurrencyException, AmountException;

    boolean update(T object) throws CurrencyException, AmountException;

    boolean delete(T object) throws AmountException, CurrencyException;



}
