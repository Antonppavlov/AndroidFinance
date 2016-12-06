package ru.barmaglot.andoroid6.finance.core.storage.impl.source;

import ru.barmaglot.andoroid6.finance.core.storage.abstracts.AbstractTreeNode;
import ru.barmaglot.andoroid6.finance.core.storage.interfaces.ITreeNode;
import ru.barmaglot.andoroid6.finance.core.storage.interfaces.source.ISource;
import ru.barmaglot.andoroid6.finance.core.storage.type.TypeSource;


public class DefaultSource extends AbstractTreeNode implements ISource {

    private TypeSource typeSource;

    @Override
    public TypeSource getTypeSource() {
        return typeSource;
    }

    public void setTypeSource(TypeSource typeSource) {
        this.typeSource = typeSource;
    }


    @Override
    public void add(ITreeNode child) {
        // TODO: 03.12.16 применить патерн
        // TODO: 03.12.16 для дочернего элемента устанавливаем тип операции родительского элемента
        if (child instanceof DefaultSource) {
            DefaultSource childSource = (DefaultSource) child;
            childSource.setTypeSource(this.typeSource);
        }
        super.add(child);
    }
}
