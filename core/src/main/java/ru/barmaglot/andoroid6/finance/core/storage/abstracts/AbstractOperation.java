package ru.barmaglot.andoroid6.finance.core.storage.abstracts;

import java.util.Calendar;

import ru.barmaglot.andoroid6.finance.core.storage.interfaces.operation.base.IOperation;
import ru.barmaglot.andoroid6.finance.core.storage.type.OperationType;


public abstract class AbstractOperation implements IOperation {


    private long id;
    private Calendar dateTime; // дата и время выполнения операции (подставлять автоматически при создании, но можно будет изменять в любое время)
    private String description; // доп. информация, которую вводит пользователь
    private OperationType operationType;// тип операции (доход, расход, перевод, конвертация)

    public AbstractOperation(OperationType operationType) {
        this.operationType = operationType;
    }

    public AbstractOperation(long id, Calendar dateTime, String description, OperationType operationType) {
        this.id = id;
        this.dateTime = dateTime;
        this.description = description;
        this.operationType = operationType;
    }

    public AbstractOperation(long id, OperationType operationType) {
        this.id = id;
        this.operationType = operationType;
    }


    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public Calendar getDateTime() {
        return dateTime;
    }

    public void setDateTime(Calendar dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }



}
