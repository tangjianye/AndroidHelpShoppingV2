package com.product.helpshopping.db;

import android.content.Context;

import com.product.helpshopping.db.gen.Note;
import com.product.helpshopping.db.gen.NoteDao;

import java.util.List;

import de.greenrobot.dao.query.Query;


/**
 * DBHelper 数据库管理类<br> 负责具体的表的添删改查操作。
 * Created by tangjy on 2015/3/2.
 */
public class DBNoteHelper {
    private static final String TAG = DBNoteHelper.class.getSimpleName();
    private static Context sCtx;
    private static DBNoteHelper sINSTANTCE;
    private NoteDao mNoteDao;

    private DBNoteHelper() {
        mNoteDao = DBManager.getInstance().getNoteDao();
    }

    public static synchronized DBNoteHelper getInstance() {
        if (sINSTANTCE == null) {
            sINSTANTCE = new DBNoteHelper();
        }
        return sINSTANTCE;
    }

    public NoteDao getNoteDao() {
        return mNoteDao;
    }

    /**********************************************************************************************/
    public Note load(long id) {
        return mNoteDao.load(id);
    }

    public Long getKey(Note entity) {
        return mNoteDao.getKey(entity);
    }

    public List<Note> loadAll() {
        return mNoteDao.loadAll();
    }

    public List<Note> loadAllByDate() {
        Query<Note> query = mNoteDao
                .queryBuilder()
                .orderDesc(NoteDao.Properties.Date)
                .build();
        return query.list();
    }

    public Note loadByDate(long date) {
        Query<Note> query = mNoteDao
                .queryBuilder()
                .where(NoteDao.Properties.Date.eq(date))
                .build();
        List<Note> entiys = query.list();
        return (null != entiys && entiys.size() > 0) ? entiys.get(0) : null;
    }

    /**
     * query list with where clause
     * ex: begin_date_time >= ? AND end_date_time <= ?
     *
     * @param where  where clause, include 'where' word
     * @param params query parameters
     * @return
     */

    public List<Note> queryNote(String where, String... params) {
        return mNoteDao.queryRaw(where, params);
    }

    /**
     * insert or update note
     *
     * @param entiy
     * @return insert or update entiy id
     */
    public long save(Note entiy) {
        return mNoteDao.insertOrReplace(entiy);
    }


    public void update(Note oldEntiy, Note newEntiy) {
        long key = mNoteDao.getKey(oldEntiy);
        newEntiy.setId(key);
        mNoteDao.update(newEntiy);
    }

    /**
     * insert or update noteList use transaction
     *
     * @param list
     */
    public void save(final List<Note> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        mNoteDao.getSession().runInTx(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < list.size(); i++) {
                    Note entiy = list.get(i);
                    mNoteDao.insertOrReplace(entiy);
                }
            }
        });
    }

    /**
     * delete all
     */
    public void deleteAll() {
        mNoteDao.deleteAll();
    }
}
