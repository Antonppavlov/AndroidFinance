package ru.barmaglot.andoroid6.finance.core.storage.interfaces.operation.base;

import java.sql.Timestamp;


public interface IOperation {

    long getId();

    String getName();

    Timestamp getTimestamp();

}
