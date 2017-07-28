package zechat.android.training.zemoso.zechat.utils;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;

/**
 * Created by vin on 24/7/17.
 */

public class ZeChatApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(getApplicationContext());
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }
}
