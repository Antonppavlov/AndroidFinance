package ru.barmaglot.andoroid6.finance.core.storage.impl.operation;

import java.math.BigDecimal;
import java.util.Currency;

import ru.barmaglot.andoroid6.finance.core.storage.abstracts.AbstractOperation;
import ru.barmaglot.andoroid6.finance.core.storage.interfaces.operation.base.IOperation;
import ru.barmaglot.andoroid6.finance.core.storage.interfaces.storage.IStorage;
import ru.barmaglot.andoroid6.finance.core.storage.type.OperationType;


public class TransferOperation extends AbstractOperation implements IOperation {


    public TransferOperation() {
        super(OperationType.TRANSFER);
    }

    private IStorage fromStorage;// откуда переводим
    private IStorage toStorage; // куда переводим
    private BigDecimal fromAmount;// сумма перевода
    private Currency fromCurrency;// в какой валюте получили деньги

    public IStorage getFromStorage() {
        return fromStorage;
    }

    public void setFromStorage(IStorage fromStorage) {
        this.fromStorage = fromStorage;
    }

    public IStorage getToStorage() {
        return toStorage;
    }

    public void setToStorage(IStorage toStorage) {
        this.toStorage = toStorage;
    }

    public BigDecimal getFromAmount() {
        return fromAmount;
    }

    public void setFromAmount(BigDecimal fromAmount) {
        this.fromAmount = fromAmount;
    }

    public Currency getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(Currency fromCurrency) {
        this.fromCurrency = fromCurrency;
    }
}
