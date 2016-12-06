package ru.barmaglot.andoroid6.finance.core.storage.dao.impls;


import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;

import ru.barmaglot.andoroid6.finance.core.storage.dao.interfaces.IStorageDAO;
import ru.barmaglot.andoroid6.finance.core.storage.interfaces.storage.IStorage;

public class StorageDAO implements IStorageDAO {
    @Override
    public boolean addCurrency(IStorage storage, Currency currency) {
        return false;
    }

    @Override
    public boolean deleteCurrency(IStorage storage, Currency currency) {
        return false;
    }

    @Override
    public boolean updateAmount(IStorage storage, BigDecimal amount) {
        return false;
    }

    @Override
    public List<IStorage> getAll() {
        return null;
    }

    @Override
    public IStorage get(long id) {
        return null;
    }

    @Override
    public boolean update(IStorage object) {
        return false;
    }

    @Override
    public boolean delete(IStorage object) {
        return false;
    }
}
