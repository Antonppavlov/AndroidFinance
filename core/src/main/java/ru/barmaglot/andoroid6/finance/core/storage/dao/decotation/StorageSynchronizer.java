package ru.barmaglot.andoroid6.finance.core.storage.dao.decotation;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;

import ru.barmaglot.andoroid6.finance.core.storage.dao.interfaces.IStorageDAO;
import ru.barmaglot.andoroid6.finance.core.storage.exception.CurrencyException;
import ru.barmaglot.andoroid6.finance.core.storage.interfaces.storage.IStorage;


public  class StorageSynchronizer implements IStorageDAO {

    private IStorageDAO iStorageDAO;
    private List<IStorage> storageList;

    public StorageSynchronizer(IStorageDAO iStorageDAO) {
        this.iStorageDAO = iStorageDAO;
        init();
    }

    private void init() {
        storageList = getAll();
    }


    @Override
    public boolean add(IStorage object) {
        boolean add = iStorageDAO.add(object);
        if(add){
            storageList.add(object);
        }
        return add;
    }

    @Override
    public boolean update(IStorage object) {
        boolean update = iStorageDAO.update(object);
            // TODO: 12.12.16 необходимо придумать как обработать если в коллекции мы изменили но апдейт не прошел
            // TODO: 12.12.16 как откатыаться назад
            //storageList.update(object);
        return update;
    }

    @Override
    public boolean delete(IStorage object) {
        // TODO: 12.12.16
        boolean delete = iStorageDAO.delete(object);
        if(delete){
           storageList.remove(object);
        }
        return delete;
    }

    @Override
    public boolean addCurrency(IStorage storage, Currency currency) throws CurrencyException {
        boolean b = iStorageDAO.addCurrency(storage, currency);
        if (b) {
            storage.addCurrency(currency);
        }
        return b;
    }

    @Override
    public boolean deleteCurrency(IStorage storage, Currency currency) throws CurrencyException {
        boolean b = iStorageDAO.deleteCurrency(storage, currency);
        if (b){
            storage.deleteCurrency(currency);
        }
        return b;
    }

    @Override
    public boolean updateAmount(IStorage storage, BigDecimal amount) {
        boolean b = iStorageDAO.updateAmount(storage, amount);
        return b;
        // TODO: 11.12.16 т.к. в начале записыватся в объект а при успехе в бд необходимо подумать как откатывать при неудаче
    }

    @Override
    public List<IStorage> getAll() {
        return iStorageDAO.getAll();
    }

    @Override
    public IStorage get(long id) {
        return iStorageDAO.get(id);
    }
}
