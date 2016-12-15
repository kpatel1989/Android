package kp.com.jobscheduler.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.security.KeyFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by macadmin on 2016-12-13.
 */

public class Database extends SQLiteOpenHelper{
    public static final String JOB_SCHEDULE_DB = "shifts.db";
    public static final int JOB_SCHEDULE_DB_VERSION = 1;

    public static final String WORKLOG_TABLE_NAME = "WorkLog";
    public static final String WORKLOG_COL_ID = "id";
    public static final String WORKLOG_COL_NAME = "name";
    public static final String WORKLOG_COL_CYCLES = "cycles";

    public static final String PAYCYCLE_TABLE_NAME = "PayCycle";
    public static final String PAYCYCLE_COL_ID = "id";
    public static final String PAYCYCLE_COL_StartDay = "startDay";
    public static final String PAYCYCLE_COL_EndDay = "endDay";

    public static final String SCHEDULE_TABLE_NAME = "Schedule";
    public static final String SCHEDULE_COL_ID = "id";
    public static final String SCHEDULE_COL_StartDay = "startTime";
    public static final String SCHEDULE_COL_EndDay = "endTime";


    public Database(Context context) {
        super(context, JOB_SCHEDULE_DB, null, JOB_SCHEDULE_DB_VERSION);
        onCreate(getWritableDatabase());
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createWorkLogTable());
        db.execSQL(createPayCycleTable());
        db.execSQL(createScheduleTable());
        int nextInt = getNextId(WORKLOG_TABLE_NAME,WORKLOG_COL_ID);
    }

    private String createScheduleTable() {
        return "CREATE TABLE "+ SCHEDULE_TABLE_NAME +" (" +
                SCHEDULE_COL_ID + " INTEGER PRIMARY KEY " +
                SCHEDULE_COL_StartDay + " TEXT " +
                SCHEDULE_COL_EndDay + " TEXT)";
    }

    private String createPayCycleTable() {
        return "CREATE TABLE "+ WORKLOG_TABLE_NAME +" (" +
                WORKLOG_COL_ID + " INTEGER PRIMARY KEY " +
                WORKLOG_COL_NAME + " TEXT " +
                WORKLOG_COL_CYCLES + " INTEGER)";
    }

    private String createWorkLogTable() {
        return "CREATE TABLE "+ PAYCYCLE_TABLE_NAME +" (" +
                PAYCYCLE_COL_ID + " INTEGER PRIMARY KEY " +
                WORKLOG_COL_NAME + " TEXT " +
                PAYCYCLE_COL_EndDay + " TEXT " +
                PAYCYCLE_COL_StartDay + " TEXT)";
    }

    public void insertData(String tableName, HashMap<String,String> values) {
        ContentValues contentValues = new ContentValues();
        switch (tableName) {
            case WORKLOG_TABLE_NAME:
                contentValues.put(WORKLOG_COL_ID,getNextId(tableName,WORKLOG_COL_ID));
                break;
            case SCHEDULE_TABLE_NAME:
                contentValues.put(SCHEDULE_COL_ID,getNextId(tableName,SCHEDULE_COL_ID));
                break;
            case PAYCYCLE_TABLE_NAME:
                contentValues.put(PAYCYCLE_COL_ID,getNextId(tableName,PAYCYCLE_COL_ID));
                break;
        }
        for (String value: values.keySet()) {
            contentValues.put(value, values.get(value));
        }
    }

    private int getNextId(String tableName, String idCol) {
        String[] cols = new String[1];
        cols[0] = "max("+idCol+")";
        Cursor c = getWritableDatabase().query(tableName,cols,null,null,null,null,"ASC");
        c.moveToFirst();
        return (c.getInt(c.getColumnIndex(idCol))+1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
}
