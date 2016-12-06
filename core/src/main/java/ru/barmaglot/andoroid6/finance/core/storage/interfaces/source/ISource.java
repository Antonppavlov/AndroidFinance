package ru.barmaglot.andoroid6.finance.core.storage.interfaces.source;


import ru.barmaglot.andoroid6.finance.core.storage.interfaces.ITreeNode;
import ru.barmaglot.andoroid6.finance.core.storage.type.TypeSource;

public interface ISource extends ITreeNode {

    TypeSource getTypeSource();

}
