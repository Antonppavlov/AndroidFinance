package ru.barmaglot.android6.finance.core.storage.db.dao;

// TODO: 20.12.16 можно общие метод для дао слоев вынести в отдельный класс

import org.junit.Test;

import java.util.List;

import ru.barmaglot.andoroid6.finance.core.storage.dao.impls.SourceDAO;
import ru.barmaglot.andoroid6.finance.core.storage.objects.impl.source.DefaultSource;
import ru.barmaglot.andoroid6.finance.core.storage.objects.interfaces.source.ISource;
import ru.barmaglot.andoroid6.finance.core.storage.objects.type.OperationType;

public class SourceDAOTest {


    SourceDAO sourceDAO = new SourceDAO();

    @Test
    public void name() throws Exception {
        List<ISource> all = sourceDAO.getAll();
        System.out.println(all);
        String testName = "Тестовое хранилище";

        DefaultSource defaultSource = new DefaultSource();
        defaultSource.setName(testName);
        defaultSource.setOperationType(OperationType.INCOME);

        sourceDAO.add(defaultSource);


        ISource iSource = sourceDAO.get(defaultSource.getId());
        System.out.println(iSource.getName());
    }
}