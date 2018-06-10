package com.beebrainy.heady.ecommerce.app;


import com.facebook.stetho.Stetho;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import io.realm.Realm;

public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        Stetho.initialize(Stetho.newInitializerBuilder(this).enableDumpapp(Stetho
                .defaultDumperPluginsProvider(this)).enableWebKitInspector
                (RealmInspectorModulesProvider.builder(this).build()).build());
    }
}
