package ru.barmaglot.andoroid6.finance.core.storage.impl.operation;

import java.math.BigDecimal;
import java.util.Currency;

import ru.barmaglot.andoroid6.finance.core.storage.abstracts.AbstractOperation;
import ru.barmaglot.andoroid6.finance.core.storage.interfaces.source.ISource;
import ru.barmaglot.andoroid6.finance.core.storage.interfaces.storage.IStorage;
import ru.barmaglot.andoroid6.finance.core.storage.type.OperationType;


// расход
public class OutcomeOperation extends AbstractOperation {

    public OutcomeOperation() {
        super(OperationType.OUTCOME);
    }

    private IStorage fromStorage; // откуда потратили
    private ISource toSource; // на что потратили
    private BigDecimal fromAmount; // сумму, которую потратили
    private Currency fromCurrency; // в какой валюте потратили


    public IStorage getFromStorage() {
        return fromStorage;
    }

    public void setFromStorage(IStorage fromStorage) {
        this.fromStorage = fromStorage;
    }

    public ISource getToSource() {
        return toSource;
    }

    public void setToSource(ISource toSource) {
        this.toSource = toSource;
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
