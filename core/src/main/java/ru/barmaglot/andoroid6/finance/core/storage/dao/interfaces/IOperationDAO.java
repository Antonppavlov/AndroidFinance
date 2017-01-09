package ru.barmaglot.andoroid6.finance.core.storage.dao.interfaces;

import java.util.List;

import ru.barmaglot.andoroid6.finance.core.storage.objects.interfaces.operation.IOperation;
import ru.barmaglot.andoroid6.finance.core.storage.objects.type.OperationType;

/**
 * Created by antonpavlov on 22.12.16.
 */

public interface IOperationDAO extends ICommonDAO<IOperation> {

    // получить список операций определенного типа
    List<IOperation> getList(OperationType operationType);

}
