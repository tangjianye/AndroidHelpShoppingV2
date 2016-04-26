package com.product.helpshopping.db;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.product.helpshopping.config.GlobalSetting;
import com.product.helpshopping.db.gen.DaoMaster;
import com.product.helpshopping.db.gen.DaoSession;
import com.product.helpshopping.db.gen.NoteDao;


/**
 * DBManager 数据库统一管理类<br> 负责数据库表的统一创建，获取和销毁的动作。不负责具体表的添删改查操作。
 * Created by tangjy on 2015/3/2.
 */
public class DBManager {
    private static final String TAG = DBManager.class.getSimpleName();

    private static Context sCtx;
    private static DBManager sINSTANTCE;
    private SQLiteDatabase mDb;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    private NoteDao mNoteDao;

    private DBManager() {
    }

    public static synchronized DBManager getInstance() {
        if (sINSTANTCE == null) {
            sINSTANTCE = new DBManager();
        }
        return sINSTANTCE;
    }

    public void init(Context context) {
        if (!(context instanceof Application)) {
            throw new AssertionError();
        }
        sCtx = context;

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(sCtx, GlobalSetting.DATABASE_NAME, null);
        mDb = helper.getWritableDatabase();
        mDaoMaster = new DaoMaster(mDb);
        mDaoSession = mDaoMaster.newSession();

        mNoteDao = mDaoSession.getNoteDao();
    }

    public SQLiteDatabase getDb() {
        return mDb;
    }

    public NoteDao getNoteDao() {
        return mNoteDao;
    }

    public void deleteAll() {
        mNoteDao.deleteAll();
    }
}
