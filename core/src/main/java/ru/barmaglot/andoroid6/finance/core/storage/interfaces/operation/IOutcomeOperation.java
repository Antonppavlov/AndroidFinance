package ru.barmaglot.andoroid6.finance.core.storage.interfaces.operation;


import java.math.BigDecimal;
import java.util.Currency;

import ru.barmaglot.andoroid6.finance.core.storage.interfaces.operation.base.IOperation;
import ru.barmaglot.andoroid6.finance.core.storage.interfaces.source.ISource;
import ru.barmaglot.andoroid6.finance.core.storage.interfaces.storage.IStorage;

public interface IOutcomeOperation extends IOperation {

    IStorage getFromStorage();

    ISource getToSource();

    BigDecimal getAmount();

    Currency getCurrency();
}
