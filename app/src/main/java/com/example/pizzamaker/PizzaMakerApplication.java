package com.example.pizzamaker;

import android.app.Application;

import com.example.pizzamaker.data.DataManager;

/**
 * Created by Justin Dang on 8/10/2017.
 */

public class PizzaMakerApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DataManager.getInstance().init(this);
    }
}
