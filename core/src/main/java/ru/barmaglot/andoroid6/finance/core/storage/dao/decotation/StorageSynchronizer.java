package ru.barmaglot.andoroid6.finance.core.storage.dao.decotation;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ru.barmaglot.andoroid6.finance.core.storage.dao.interfaces.IStorageDAO;
import ru.barmaglot.andoroid6.finance.core.storage.exception.AmountException;
import ru.barmaglot.andoroid6.finance.core.storage.exception.CurrencyException;
import ru.barmaglot.andoroid6.finance.core.storage.objects.interfaces.storage.IStorage;
import ru.barmaglot.andoroid6.finance.core.storage.utils.TreeUtils;


public class StorageSynchronizer implements IStorageDAO {
    private TreeUtils<IStorage> treeUtils = new TreeUtils();


    //Все коллекции хранят ссылки на одни и теже объекты, но в разных срезах
    //при удалении нужно удалять из всех коллекций
    private List<IStorage> treeList = new ArrayList<>();//хранит все деревья без раздереления по типа операции
    private Map<Long, IStorage> identityMap = new HashMap<>();// хранит все источники без учета их уровня в дереве //получение по id

    private IStorageDAO iStorageDAO;

    public StorageSynchronizer(IStorageDAO iStorageDAO) {
        this.iStorageDAO = iStorageDAO;
        init();
    }

    private void init() {
        List<IStorage> allStorage = iStorageDAO.getAll();

        for (IStorage storage : allStorage) {
            identityMap.put(storage.getId(), storage);
            treeUtils.addToTree(storage.getParentId(), storage, treeList);
        }
    }


    @Override
    public List<IStorage> getAll() {
        return treeList;
    }

    @Override
    public IStorage get(long id) {
        return identityMap.get(id);
    }

    @Override
    public boolean add(IStorage object) throws CurrencyException {
        return iStorageDAO.add(object);
    }

    @Override
    public boolean update(IStorage object) {
        return iStorageDAO.update(object);
    }

    @Override
    public boolean delete(IStorage object) {
        if (iStorageDAO.delete(object)) {
            removeToCollection(object);
            return true;
        }
        return false;
    }

    private void removeToCollection(IStorage object) {
        identityMap.remove(object.getId());
        if (object.hasParent()) {
            object.getParent().removeChild(object);
        } else {
            treeList.remove(object);
        }
    }

    public IStorageDAO getiStorageDAO() {
        return iStorageDAO;
    }

    @Override
    public boolean addCurrency(IStorage storage, Currency currency, BigDecimal amount) throws CurrencyException, SQLException {
        if (iStorageDAO.addCurrency(storage, currency, amount)) {
            storage.addCurrency(currency);
            addToCollection(storage);
            return true;
        }

        return false;
    }

    private void addToCollection(IStorage storage) {
        identityMap.put(storage.getId(), storage);
        if (storage.hasParent()) {
            if (!storage.getParent().getListChild().contains(storage)) {
                storage.getParent().addChild(storage);
            }
        } else {
            treeList.add(storage);
        }
    }

    @Override
    public boolean deleteCurrency(IStorage storage, Currency currency) throws CurrencyException {
        if (iStorageDAO.deleteCurrency(storage, currency)) {
            storage.deleteCurrency(currency);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateAmount(IStorage storage, Currency currency, BigDecimal amount) {
        if (iStorageDAO.updateAmount(storage, currency, amount)) {
            try {
                storage.updateAmount(amount, currency);
            } catch (AmountException | CurrencyException e) {
                e.printStackTrace();
            }
            return true;
        }

        return false;
    }



    public Map<Long, IStorage> getIdentityMap() {
        return identityMap;
    }
}
