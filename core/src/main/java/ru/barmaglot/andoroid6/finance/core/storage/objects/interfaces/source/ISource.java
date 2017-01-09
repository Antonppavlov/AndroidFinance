package ru.barmaglot.andoroid6.finance.core.storage.objects.interfaces.source;


import ru.barmaglot.andoroid6.finance.core.storage.objects.interfaces.ITreeNode;
import ru.barmaglot.andoroid6.finance.core.storage.objects.type.OperationType;

public interface ISource extends ITreeNode {

    OperationType getOperationType();

}
