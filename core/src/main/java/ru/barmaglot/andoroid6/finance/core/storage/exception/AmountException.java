package ru.barmaglot.andoroid6.finance.core.storage.exception;


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
