package ru.barmaglot.andoroid6.finance.core.storage.impl.operation;

import java.math.BigDecimal;
import java.util.Currency;

import ru.barmaglot.andoroid6.finance.core.storage.abstracts.AbstractOperation;
import ru.barmaglot.andoroid6.finance.core.storage.interfaces.operation.IConvertOperation;
import ru.barmaglot.andoroid6.finance.core.storage.interfaces.storage.IStorage;



public class ConvertOperation extends AbstractOperation implements IConvertOperation {

   private IStorage fromStorage;
   private IStorage toStorage;
   private Currency fromCurrency;
   private Currency toCurrency;
   private BigDecimal fromAmount;
   private BigDecimal toAmount;

    @Override
    public IStorage getFromStorage() {
        return fromStorage;
    }

    public void setFromStorage(IStorage fromStorage) {
        this.fromStorage = fromStorage;
    }

    @Override
    public IStorage getToStorage() {
        return toStorage;
    }

    public void setToStorage(IStorage toStorage) {
        this.toStorage = toStorage;
    }

    @Override
    public Currency getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(Currency fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    @Override
    public Currency getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(Currency toCurrency) {
        this.toCurrency = toCurrency;
    }

    @Override
    public BigDecimal getFromAmount() {
        return fromAmount;
    }

    public void setFromAmount(BigDecimal fromAmount) {
        this.fromAmount = fromAmount;
    }

    @Override
    public BigDecimal getToAmount() {
        return toAmount;
    }

    public void setToAmount(BigDecimal toAmount) {
        this.toAmount = toAmount;
    }
}
