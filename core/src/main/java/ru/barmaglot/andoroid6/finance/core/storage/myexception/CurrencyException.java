package ru.barmaglot.andoroid6.finance.core.storage.myexception;

/**
 * Created by antonpavlov on 01.12.16.
 */

public class CurrencyException extends Exception {

    public CurrencyException() {
        super();
    }

    public CurrencyException(String s) {
        super(s);
    }

    public CurrencyException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public CurrencyException(Throwable throwable) {
        super(throwable);
    }

}
