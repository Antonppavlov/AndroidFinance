package ru.barmaglot.android6.finance.core.storage.db.synchronizer;

import org.junit.Assert;
import org.junit.Test;

import ru.barmaglot.andoroid6.finance.core.storage.dao.decotation.StorageSynchronizer;
import ru.barmaglot.andoroid6.finance.core.storage.dao.impls.StorageDAO;
import ru.barmaglot.andoroid6.finance.core.storage.objects.interfaces.storage.IStorage;

public class StorageSynchronizerTest {

    private final StorageSynchronizer storageSynchronizer = new StorageSynchronizer(new StorageDAO());

    @Test
    public void getAll(){
        Assert.assertTrue(storageSynchronizer.getAll().size()>1);
    }

    @Test
    public void get(){
        IStorage lastStorageInCollection = storageSynchronizer.getAll().get(storageSynchronizer.getAll().size() - 1);

        IStorage iStorage = storageSynchronizer.get(lastStorageInCollection.getId());

        Assert.assertEquals(
                lastStorageInCollection,
                iStorage
                );

    }

    @Test
    public void add(){

    }

    @Test
    public void update(){

    }

    @Test
    public void delete(){

    }

    @Test
    public void getiStorageDAO(){

    }

    @Test
    public void addCurrency(){

    }

    @Test
    public void deleteCurrency(){

    }

    @Test
    public void updateAmount(){

    }
}