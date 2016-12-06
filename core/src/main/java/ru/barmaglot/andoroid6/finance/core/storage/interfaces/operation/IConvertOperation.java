package ru.barmaglot.andoroid6.finance.core.storage.interfaces.operation;

import java.math.BigDecimal;
import java.util.Currency;

import ru.barmaglot.andoroid6.finance.core.storage.interfaces.operation.base.IOperation;
import ru.barmaglot.andoroid6.finance.core.storage.interfaces.storage.IStorage;


public interface IConvertOperation extends IOperation {

    IStorage getFromStorage();

    IStorage getToStorage();

    Currency getFromCurrency();

    Currency getToCurrency();

    BigDecimal getFromAmount();

    BigDecimal getToAmount();
}
