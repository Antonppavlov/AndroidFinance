package ru.barmaglot.andoroid6.finance.core.storage.database;


import ru.barmaglot.andoroid6.finance.core.storage.dao.decotation.OperationSynchronizer;
import ru.barmaglot.andoroid6.finance.core.storage.dao.decotation.SourceSynchronizer;
import ru.barmaglot.andoroid6.finance.core.storage.dao.decotation.StorageSynchronizer;
import ru.barmaglot.andoroid6.finance.core.storage.dao.impls.OperationDAO;
import ru.barmaglot.andoroid6.finance.core.storage.dao.impls.SourceDAO;
import ru.barmaglot.andoroid6.finance.core.storage.dao.impls.StorageDAO;

public class Initializer {


    private static SourceSynchronizer sourceSynchronizer;
    private static StorageSynchronizer storageSynchronizer;
    private static OperationSynchronizer operationSynchronizer;


    public static void load(String driverClass, String url) {

        SQLiteConnection.init(driverClass, url);

        sourceSynchronizer = new SourceSynchronizer(new SourceDAO());
        storageSynchronizer = new StorageSynchronizer(new StorageDAO());

        operationSynchronizer = new OperationSynchronizer(
                sourceSynchronizer,
                storageSynchronizer,
                new OperationDAO(
                        sourceSynchronizer.getIdentityMap(),
                        storageSynchronizer.getIdentityMap()
                )

        );
    }


    public static SourceSynchronizer getSourceSynchronizer() {
        return sourceSynchronizer;
    }

    public static StorageSynchronizer getStorageSynchronizer() {
        return storageSynchronizer;
    }

    public static OperationSynchronizer getOperationSynchronizer() {
        return operationSynchronizer;
    }
}
