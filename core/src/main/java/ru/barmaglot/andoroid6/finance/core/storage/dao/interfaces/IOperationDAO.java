package ru.barmaglot.andoroid6.finance.core.storage.dao.interfaces;

import java.util.List;

import ru.barmaglot.andoroid6.finance.core.storage.interfaces.operation.base.IOperation;
import ru.barmaglot.andoroid6.finance.core.storage.type.OperationType;

/**
 * Created by antonpavlov on 22.12.16.
 */

public interface IOperationDAO extends ICommonDAO<IOperation> {

    List<IOperation> getList(OperationType operationType);// получить список операций определенного типа

}
