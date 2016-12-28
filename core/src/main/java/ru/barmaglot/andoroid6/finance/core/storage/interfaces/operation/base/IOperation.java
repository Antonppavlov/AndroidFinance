package ru.barmaglot.andoroid6.finance.core.storage.interfaces.operation.base;

import java.util.Calendar;

import ru.barmaglot.andoroid6.finance.core.storage.type.OperationType;


public interface IOperation {

    long getId();

    void setId(long id);

    OperationType getOperationType();

    Calendar getDateTime();

    String getDescription();

}
