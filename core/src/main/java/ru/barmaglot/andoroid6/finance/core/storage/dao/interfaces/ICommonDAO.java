package ru.barmaglot.andoroid6.finance.core.storage.dao.interfaces;

import java.util.List;

//описывает общие действия в бд для всех объектов
public interface ICommonDAO<T> {

    List<T>getAll();
    T get(long id);
    boolean update(T object);
    boolean delete(T object);

}
