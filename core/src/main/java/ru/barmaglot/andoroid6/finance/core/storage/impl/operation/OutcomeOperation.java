package ru.barmaglot.andoroid6.finance.core.storage.impl.operation;

import java.math.BigDecimal;
import java.util.Currency;

import ru.barmaglot.andoroid6.finance.core.storage.abstracts.AbstractOperation;
import ru.barmaglot.andoroid6.finance.core.storage.interfaces.operation.IOutcomeOperation;
import ru.barmaglot.andoroid6.finance.core.storage.interfaces.source.ISource;
import ru.barmaglot.andoroid6.finance.core.storage.interfaces.storage.IStorage;

/**
 * Created by antonpavlov on 03.12.16.
 */

public class OutcomeOperation extends AbstractOperation implements IOutcomeOperation {
    private IStorage fromStorage;
    private ISource toSource;
    private BigDecimal amount;
    private Currency currency;

    @Override
    public IStorage getFromStorage() {
        return fromStorage;
    }

    public void setFromStorage(IStorage fromStorage) {
        this.fromStorage = fromStorage;
    }

    @Override
    public ISource getToSource() {
        return toSource;
    }

    public void setToSource(ISource toSource) {
        this.toSource = toSource;
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
