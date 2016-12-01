package ru.barmaglot.andoroid6.finance.core.storage.interfaces;

import java.util.Currency;
import java.util.Locale;

import static com.sun.tools.doclint.Entity.ne;

/**
 * Created by antonpavlov on 30.11.16.
 */

public class Start {
    public static void main(String[] args) {
        //Currency rub = Currency.getInstance("RUB");
        Currency rub = Currency.getInstance("USD");
        System.out.println(rub.getDisplayName());
        System.out.println(rub.getClass());
        System.out.println(rub.getDefaultFractionDigits());
        System.out.println(rub.getSymbol(new Locale("RUB")));

    }
}
