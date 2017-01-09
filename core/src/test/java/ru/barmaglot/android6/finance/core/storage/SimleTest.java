package ru.barmaglot.android6.finance.core.storage;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import ru.barmaglot.andoroid6.finance.core.storage.dao.decotation.SourceSynchronizer;
import ru.barmaglot.andoroid6.finance.core.storage.dao.decotation.StorageSynchronizer;
import ru.barmaglot.andoroid6.finance.core.storage.dao.impls.SourceDAO;
import ru.barmaglot.andoroid6.finance.core.storage.dao.impls.StorageDAO;
import ru.barmaglot.andoroid6.finance.core.storage.objects.interfaces.ITreeNode;
import ru.barmaglot.andoroid6.finance.core.storage.objects.interfaces.source.ISource;
import ru.barmaglot.andoroid6.finance.core.storage.objects.type.OperationType;


public class SimleTest {

    @Test
    public void test4()  {
        StorageSynchronizer storageSynchronizer = new StorageSynchronizer(new StorageDAO());
        ITreeNode iTreeNode = storageSynchronizer.getAll().get(0).getListChild().get(1);
        Assert.assertEquals(iTreeNode.getName(),"Сбербанк");
    }


    @Test
    public void test5()  {
        SourceDAO sourceDAO = new SourceDAO();
        Assert.assertEquals(sourceDAO.getAll().get(0).getOperationType(), OperationType.INCOME);
    }


    @Test
    public void test6()  {
        SourceDAO sourceDAO = new SourceDAO();
        SourceSynchronizer sourceSynchronizer = new SourceSynchronizer(sourceDAO);
        List<ISource> listSource = sourceSynchronizer.getListSource(OperationType.OUTCOME);
        Assert.assertEquals(listSource.get(0).getName(), "Магазин");
    }
}
