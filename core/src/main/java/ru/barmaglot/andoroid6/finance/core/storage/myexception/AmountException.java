package ru.barmaglot.andoroid6.finance.core.storage.myexception;

/**
 * Created by antonpavlov on 01.12.16.
 */

public class AmountException extends Exception {

    public AmountException() {
        super();
    }

    public AmountException(String s) {
        super(s);
    }

    public AmountException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public AmountException(Throwable throwable) {
        super(throwable);
    }
}
