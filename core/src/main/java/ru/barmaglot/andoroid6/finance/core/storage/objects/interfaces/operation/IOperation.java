package ru.barmaglot.andoroid6.finance.core.storage.objects.interfaces.operation;

import java.util.Calendar;

import ru.barmaglot.andoroid6.finance.core.storage.objects.type.OperationType;


public interface IOperation {

    long getId();

    void setId(long id);

    OperationType getOperationType();

    Calendar getDateTime();

    String getDescription();

}
