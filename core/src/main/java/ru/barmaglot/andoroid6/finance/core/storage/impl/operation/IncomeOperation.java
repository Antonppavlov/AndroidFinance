package ru.barmaglot.andoroid6.finance.core.storage.impl.operation;

import java.math.BigDecimal;
import java.util.Currency;

import ru.barmaglot.andoroid6.finance.core.storage.abstracts.AbstractOperation;
import ru.barmaglot.andoroid6.finance.core.storage.interfaces.operation.IIncomeOperation;
import ru.barmaglot.andoroid6.finance.core.storage.interfaces.source.ISource;
import ru.barmaglot.andoroid6.finance.core.storage.interfaces.storage.IStorage;


public class IncomeOperation extends AbstractOperation implements IIncomeOperation {
    private ISource fromSource;
    private IStorage toStorage;
    private BigDecimal amount;
    private Currency currency;

    @Override
    public ISource getFromSource() {
        return fromSource;
    }

    public void setFromSource(ISource fromSource) {
        this.fromSource = fromSource;
    }

    @Override
    public IStorage getToStorage() {
        return toStorage;
    }

    public void setToStorage(IStorage toStorage) {
        this.toStorage = toStorage;
    }

    @Override
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
