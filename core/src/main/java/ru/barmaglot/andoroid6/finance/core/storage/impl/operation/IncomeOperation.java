package ru.barmaglot.andoroid6.finance.core.storage.impl.operation;

import java.math.BigDecimal;
import java.util.Currency;

import ru.barmaglot.andoroid6.finance.core.storage.abstracts.AbstractOperation;
import ru.barmaglot.andoroid6.finance.core.storage.interfaces.source.ISource;
import ru.barmaglot.andoroid6.finance.core.storage.interfaces.storage.IStorage;
import ru.barmaglot.andoroid6.finance.core.storage.type.OperationType;


// доход
public class IncomeOperation extends AbstractOperation {

    public IncomeOperation() {
        super(OperationType.INCOME);
    }


    private ISource fromSource; // откула пришли деньги
    private IStorage toStorage; // куда положили деньги
    private BigDecimal fromAmount; // сумма получения
    private Currency fromCurrency; // в какой валюте получили деньги

    public ISource getFromSource() {
        return fromSource;
    }

    public void setFromSource(ISource fromSource) {
        this.fromSource = fromSource;
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
