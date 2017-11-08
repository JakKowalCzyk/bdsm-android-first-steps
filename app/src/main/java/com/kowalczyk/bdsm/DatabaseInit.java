package com.kowalczyk.bdsm;

import android.app.Application;
import com.kowalczyk.bdsm.model.DaoMaster;
import com.kowalczyk.bdsm.model.DaoSession;
import org.greenrobot.greendao.database.Database;

/**
 * Created by JKowalczyk on 2017-11-08.
 */
public class DatabaseInit extends Application {

    //NEVER COMMIT THIS
    //ALWAYS NEW VALUE BEFORE COMPILATION - WITH THIS APPROACH HACKER NEVER CAN ACCESS DB
    private static final String DB_SECRET = "<YOUR_SECRET>";
    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this, "secret0.db");
        Database db = devOpenHelper.getEncryptedWritableDb(DB_SECRET);
        daoSession = new DaoMaster(db).newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
