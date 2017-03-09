package ru.barmaglot.android6.finance.app;

import android.app.Application;

//будем запускаться 1 раз при каждом запуске приложения
//производить одно действие
//копирует файл базы данных в определенную папку
//чтобы у приложения был доступ к бд
public class AppContext extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
    }


    // если нет файла БД - скопировать его из папки assets

}
