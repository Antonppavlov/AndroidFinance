package ru.barmaglot.andoroid6.finance.core.storage.dao.interfaces;

import java.util.List;

import ru.barmaglot.andoroid6.finance.core.storage.interfaces.ITreeNode;

//описывает общие действия в бд для всех объектов
public interface ICommonDAO<T extends ITreeNode> {

    List<T> getAll();

    T get(long id);

    boolean add(T object);

    boolean update(T object);

    boolean delete(T object);



}
