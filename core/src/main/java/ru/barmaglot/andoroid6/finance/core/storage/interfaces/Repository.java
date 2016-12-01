package ru.barmaglot.andoroid6.finance.core.storage.interfaces;

import java.util.List;

public interface Repository {

    List<Storage> getAllStorage();

    Storage getStorage(Integer id);

    void addStorage(Storage storage);

    void deleteStorage(Storage storage);


}
