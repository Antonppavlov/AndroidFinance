package ru.barmaglot.andoroid6.finance.core.storage.abstracts;

import java.sql.Timestamp;

import ru.barmaglot.andoroid6.finance.core.storage.interfaces.operation.base.IOperation;


public abstract class AbstractOperation implements IOperation {

    private long id;
    private String name;
    private Timestamp timestamp;

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

}
