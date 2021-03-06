package ru.barmaglot.andoroid6.finance.core.storage.dao.interfaces;

import java.util.List;

import ru.barmaglot.andoroid6.finance.core.storage.objects.interfaces.source.ISource;
import ru.barmaglot.andoroid6.finance.core.storage.objects.type.OperationType;

/**
 * Created by antonpavlov on 13.12.16.
 */

public interface ISourceDAO extends ICommonDAO<ISource> {
    // TODO: 13.12.16 картавый хочет сделать через лямба выражения/ надо про них почитать
    List<ISource> getListSource(OperationType operationType);
}
