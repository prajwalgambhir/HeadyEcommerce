package com.beebrainy.heady.ecommerce.app;


import io.realm.Realm;

public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
