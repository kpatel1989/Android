package kp.com.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by macadmin on 2016-12-12.
 */

public class KpTaskDb {

    // database constants
    public static final String DB_NAME = "kpTasklist.db";
    public static final int    DB_VERSION = 1;

    // list table constants
    public static final String KP_LIST_TABLE = "kpList";

    public static final String KP_LIST_ID = "_id";
    public static final int    KP_LIST_ID_COL = 0;

    public static final String KP_LIST_NAME = "kpList_name";
    public static final int    KP_LIST_NAME_COL = 1;

    // task table constants
    public static final String KP_TASK_TABLE = "kpTask";

    public static final String KP_TASK_ID = "_id";
    public static final int    KP_TASK_ID_COL = 0;

    public static final String KP_TASK_LIST_ID = "list_id";
    public static final int    KP_TASK_LIST_ID_COL = 1;

    public static final String KP_TASK_NAME = "task_name";
    public static final int    KP_TASK_NAME_COL = 2;

    public static final String KP_TASK_NOTES = "notes";
    public static final int    KP_TASK_NOTES_COL = 3;

    public static final String KP_TASK_COMPLETED = "date_completed";
    public static final int    KP_TASK_COMPLETED_COL = 4;

    public static final String KP_TASK_HIDDEN = "hidden";
    public static final int    KP_TASK_HIDDEN_COL = 5;

    public static final String KP_TASK_PRICE = "price";
    public static final int    KP_TASK_PRICE_COL = 6;

    // CREATE and DROP TABLE statements
    public static final String CREATE_LIST_TABLE =
            "CREATE TABLE " + KP_LIST_TABLE + " (" +
                    KP_LIST_ID   + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    KP_LIST_NAME + " TEXT    UNIQUE)";

    public static final String CREATE_TASK_TABLE =
            "CREATE TABLE " + KP_TASK_TABLE + " (" +
                    KP_TASK_ID         + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    KP_TASK_LIST_ID    + " INTEGER, " +
                    KP_TASK_NAME       + " TEXT, " +
                    KP_TASK_NOTES      + " TEXT, " +
                    KP_TASK_COMPLETED  + " TEXT, " +
                    KP_TASK_HIDDEN     + " TEXT,"  +
                    KP_TASK_PRICE      + " DOUBLE) ";

    public static final String KP_DROP_LIST_TABLE =
            "DROP TABLE IF EXISTS " + KP_LIST_TABLE;

    public static final String KP_DROP_TASK_TABLE =
            "DROP TABLE IF EXISTS " + KP_TASK_TABLE;

    private static class DbHelper extends SQLiteOpenHelper{

        public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(CREATE_LIST_TABLE);
            sqLiteDatabase.execSQL(CREATE_TASK_TABLE);

            // insert lists
            sqLiteDatabase.execSQL("INSERT INTO list VALUES (1, 'Personal')");
            sqLiteDatabase.execSQL("INSERT INTO list VALUES (2, 'Business')");

            // insert sample tasks
            sqLiteDatabase.execSQL("INSERT INTO task VALUES (1, 1, 'Pay bills', " +
                    "'Rent\nPhone\nInternet', '0', '0','0.0')");
            sqLiteDatabase.execSQL("INSERT INTO task VALUES (2, 1, 'Get hair cut', " +
                    "'', '0', '0','0.0')");
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

            Log.d("Task list", "Upgrading db from version "
                    + oldVersion + " to " + newVersion);

            Log.d("Task list", "Deleting all data!");
            sqLiteDatabase.execSQL(KpTaskDb.KP_DROP_LIST_TABLE);
            sqLiteDatabase.execSQL(KpTaskDb.KP_DROP_TASK_TABLE);
            onCreate(sqLiteDatabase);
        }
    }


    // database object and database helper object
    private SQLiteDatabase kpDb;
    private DbHelper kpDbHelper;

    // constructor
    public KpTaskDb(Context context) {
        kpDbHelper = new DbHelper(context, DB_NAME, null, DB_VERSION);
    }

    // private methods
    private void openReadableDB() {
        kpDb = kpDbHelper.getReadableDatabase();
    }

    private void openWriteableDB() {
        kpDb = kpDbHelper.getWritableDatabase();
    }

    private void closeDB() {
        if (kpDb != null)
            kpDb.close();
    }

    public ArrayList<KpList> getLists() {
        ArrayList<KpList> lists = new ArrayList<KpList>();
        openReadableDB();
        Cursor cursor = kpDb.query(KP_LIST_TABLE,
                null, null, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                KpList list = new KpList();
                list.setId(cursor.getInt(KP_LIST_ID_COL));
                list.setName(cursor.getString(KP_LIST_NAME_COL));

                lists.add(list);
            }
            cursor.close();
        }
        closeDB();
        return lists;
    }

    public KpList getList(String name) {
        String where = KP_LIST_NAME + "= ?";
        String[] whereArgs = { name };

        openReadableDB();
        Cursor cursor = kpDb.query(KP_LIST_TABLE, null,
                where, whereArgs, null, null, null);
        KpList list = null;
        cursor.moveToFirst();
        list = new KpList(cursor.getInt(KP_LIST_ID_COL),
                cursor.getString(KP_LIST_NAME_COL));
        cursor.close();
        this.closeDB();

        return list;
    }

    public ArrayList<KpTask> getTasks(String listName) {
        String where =
                KP_TASK_LIST_ID + "= ? AND " +
                        KP_TASK_HIDDEN + "!='1'";
        long listID = getList(listName).getId();
        String[] whereArgs = { Long.toString(listID) };

        this.openReadableDB();
        Cursor cursor = kpDb.query(KP_TASK_TABLE, null,
                where, whereArgs,
                null, null, null);
        ArrayList<KpTask> tasks = new ArrayList<KpTask>();
        while (cursor.moveToNext()) {
            tasks.add(getTaskFromCursor(cursor));
        }
        if (cursor != null)
            cursor.close();
        this.closeDB();
        return tasks;
    }
    public KpTask getTask(long id) {
        String where = KP_TASK_ID + "= ?";
        String[] whereArgs = { Long.toString(id) };

        // handle exceptions?
        this.openReadableDB();
        Cursor cursor = kpDb.query(KP_TASK_TABLE,
                null, where, whereArgs, null, null, null);
        cursor.moveToFirst();
        KpTask task = getTaskFromCursor(cursor);
        if (cursor != null)
            cursor.close();
        this.closeDB();

        return task;
    }

    private static KpTask getTaskFromCursor(Cursor cursor) {
        if (cursor == null || cursor.getCount() == 0){
            return null;
        }
        else {
            try {
                KpTask task = new KpTask(
                        cursor.getInt(KP_TASK_ID_COL),
                        cursor.getInt(KP_TASK_LIST_ID_COL),
                        cursor.getString(KP_TASK_NAME_COL),
                        cursor.getString(KP_TASK_NOTES_COL),
                        cursor.getString(KP_TASK_COMPLETED_COL),
                        cursor.getString(KP_TASK_HIDDEN_COL));
                return task;
            }
            catch(Exception e) {
                return null;
            }
        }
    }


    public long insertTask(KpTask task) {
        ContentValues cv = new ContentValues();
        cv.put(KP_TASK_LIST_ID, task.getListId());
        cv.put(KP_TASK_NAME, task.getName());
        cv.put(KP_TASK_NOTES, task.getNotes());
        cv.put(KP_TASK_COMPLETED, task.getCompletedDate());
        cv.put(KP_TASK_HIDDEN, task.getHidden());

        this.openWriteableDB();
        long rowID = kpDb.insert(KP_TASK_TABLE, null, cv);
        this.closeDB();

        return rowID;
    }

    public int updateTask(KpTask task) {
        ContentValues cv = new ContentValues();
        cv.put(KP_TASK_LIST_ID, task.getListId());
        cv.put(KP_TASK_NAME, task.getName());
        cv.put(KP_TASK_NOTES, task.getNotes());
        cv.put(KP_TASK_COMPLETED, task.getCompletedDate());
        cv.put(KP_TASK_HIDDEN, task.getHidden());

        String where = KP_TASK_ID + "= ?";
        String[] whereArgs = { String.valueOf(task.getId()) };

        this.openWriteableDB();
        int rowCount = kpDb.update(KP_TASK_TABLE, cv, where, whereArgs);
        this.closeDB();

        return rowCount;
    }

    public int deleteTask(long id) {
        String where = KP_TASK_ID + "= ?";
        String[] whereArgs = { String.valueOf(id) };

        this.openWriteableDB();
        int rowCount = kpDb.delete(KP_TASK_TABLE, where, whereArgs);
        this.closeDB();

        return rowCount;
    }
}
