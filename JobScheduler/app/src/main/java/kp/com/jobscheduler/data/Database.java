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
 * Created by Kartik on 2016-12-13.
 */

public class Database extends SQLiteOpenHelper{
    public static final String JOB_SCHEDULE_DB = "shifts.db";
    public static final int JOB_SCHEDULE_DB_VERSION = 4;

    public static final String WORKLOG_TABLE_NAME = "WorkLog";
    public static final String WORKLOG_COL_ID = "id";
    public static final String WORKLOG_COL_NAME = "name";

    public static final String WORKLOG_CYCLES_TABLE_NAME = "WorkLogCycles";
    public static final String WORKLOG_CYCLES_COL_WORK_LOG_ID = "worklogId";
    public static final String WORKLOG_CYCLES_COL_CYCLE_ID = "payCycleId";

    public static final String PAYCYCLE_TABLE_NAME = "PayCycle";
    public static final String PAYCYCLE_COL_ID = "id";
    public static final String PAYCYCLE_COL_StartDay = "startDay";
    public static final String PAYCYCLE_COL_EndDay = "endDay";

    public static final String SCHEDULE_TABLE_NAME = "Schedule";
    public static final String SCHEDULE_COL_ID = "id";
    public static final String SCHEDULE_COL_StartDay = "startTime";
    public static final String SCHEDULE_COL_EndDay = "endTime";

    SQLiteDatabase db;

    public Database(Context context) {
        super(context, JOB_SCHEDULE_DB, null, JOB_SCHEDULE_DB_VERSION);
        this.db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        this.dropTables();
        db.execSQL(createWorkLogTable());
        db.execSQL(createPayCycleTable());
        db.execSQL(createScheduleTable());
        HashMap<String,String> map = new HashMap<>();
        map.put(WORKLOG_COL_NAME,"Subway");
        long rowId = insertData(WORKLOG_TABLE_NAME,map);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        onCreate(sqLiteDatabase);
    }

    private void dropTables() {
        db.execSQL("DROP TABLE IF EXISTS " + SCHEDULE_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + WORKLOG_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + WORKLOG_CYCLES_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + PAYCYCLE_TABLE_NAME);
    }

    private String createScheduleTable() {
        return "CREATE TABLE "+ SCHEDULE_TABLE_NAME +" (" +
                SCHEDULE_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                SCHEDULE_COL_StartDay + " TEXT, " +
                SCHEDULE_COL_EndDay + " TEXT)";
    }

    private String createWorkLogTable() {
        return "CREATE TABLE "+ WORKLOG_TABLE_NAME +" (" +
                WORKLOG_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                WORKLOG_COL_NAME + " TEXT)";
    }

    private String createPayCycleTable() {
        return "CREATE TABLE "+ PAYCYCLE_TABLE_NAME +" (" +
                PAYCYCLE_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                PAYCYCLE_COL_EndDay + " TEXT, " +
                PAYCYCLE_COL_StartDay + " TEXT)";
    }

    private String createWorkLogCyclesTable() {
        return "CREATE TABLE "+ WORKLOG_CYCLES_TABLE_NAME +" (" +
                WORKLOG_CYCLES_COL_WORK_LOG_ID + " INTEGER, " +
                WORKLOG_CYCLES_COL_CYCLE_ID + " INTEGER)";
    }

    public long insertData(String tableName, HashMap<String,String> values) {
        ContentValues contentValues = new ContentValues();
        for (String value: values.keySet()) {
            contentValues.put(value, values.get(value));
        }
        if (values.get("id") != null) {
            String whereClause = "id = ?";
            String[] whereArgs = {values.get("id")};
            return db.update(tableName, contentValues, whereClause, whereArgs);
        } else {
            return db.insert(tableName,null,contentValues);
        }
    }

    public long deleteData(String tableName, int id) {
        String whereClause = "id = ?";
        String[] whereArgs = {id+""};
        return db.delete(tableName,whereClause,whereArgs);
    }

    public ArrayList<Schedule> getSchedules() {
        ArrayList<Schedule> schedules = new ArrayList<>();
        String[] cols = {SCHEDULE_COL_ID, SCHEDULE_COL_StartDay, SCHEDULE_COL_EndDay};
        Cursor cursor = db.query(SCHEDULE_TABLE_NAME,cols,null,null,null,null,"");
        while (cursor.moveToNext()) {
            schedules.add(new Schedule(Integer.parseInt(cursor.getString(0)), Long.parseLong(cursor.getString(1)), Long.parseLong(cursor.getString(2))));
        }
        cursor.close();
        return schedules;
    }

    public HashMap<String, String> serializeSchedule(Schedule schedule) {
        HashMap<String, String> map = new HashMap<>();
        if (schedule.getId() > 0) {
            map.put(Database.SCHEDULE_COL_ID,String.valueOf(schedule.getId()));
        }
        map.put(Database.SCHEDULE_COL_StartDay,String.valueOf(schedule.getStartTime()));
        map.put(Database.SCHEDULE_COL_EndDay,String.valueOf(schedule.getEndTime()));
        return map;
    }

    public HashMap<String, String> serializePayCycle(PayCycle payCycle) {
        HashMap<String, String> map = new HashMap<>();
        if (payCycle.getId() > 0) {
            map.put(Database.PAYCYCLE_COL_ID,String.valueOf(payCycle.getId()));
        }
        map.put(Database.PAYCYCLE_COL_StartDay,String.valueOf(payCycle.getStartDay()));
        map.put(Database.PAYCYCLE_COL_EndDay,String.valueOf(payCycle.getEndDay()));
        return map;
    }

    public ArrayList<Schedule> getSchedulesInPayCycle(PayCycle payCycle) {
        ArrayList<Schedule> schedules = new ArrayList<>();
        String[] cols = {SCHEDULE_COL_ID,SCHEDULE_COL_StartDay,SCHEDULE_COL_EndDay};
        String selection = SCHEDULE_COL_StartDay + " > ? AND " + SCHEDULE_COL_EndDay + "< ?";
        String[] selectionArgs = {payCycle.getStartDay()+"", payCycle.getEndDay()+""};
        Cursor cursor = db.query(SCHEDULE_TABLE_NAME,cols,selection,selectionArgs,null,null,"");
        cursor.moveToFirst();
        do {
            schedules.add(new Schedule(Integer.parseInt(cursor.getString(0)), Long.parseLong(cursor.getString(0)), Long.parseLong(cursor.getString(1))));
        } while (cursor.moveToNext());
        cursor.close();
        return schedules;
    }

    public ArrayList<PayCycle> getPayCycles() {
        ArrayList<PayCycle> paycycles = new ArrayList<>();
        String[] cols = {PAYCYCLE_COL_ID, PAYCYCLE_COL_StartDay, PAYCYCLE_COL_EndDay};
        Cursor cursor = db.query(PAYCYCLE_TABLE_NAME,cols,null,null,null,null,"");
        while (cursor.moveToNext()) {
            paycycles.add(new PayCycle(Integer.parseInt(cursor.getString(0)), Long.parseLong(cursor.getString(1)), Long.parseLong(cursor.getString(2))));
        }
        cursor.close();
        return paycycles;
    }


    public long getLastPayDay() {
        String orderBy = PAYCYCLE_COL_EndDay + " ASC";
        String[] cols = {PAYCYCLE_COL_ID, PAYCYCLE_COL_StartDay, PAYCYCLE_COL_EndDay};
        Cursor c = db.query(PAYCYCLE_TABLE_NAME,cols,"",null,null,null,orderBy);
        if (c.moveToFirst()) {
            return Long.parseLong(c.getString(2));
        } else {
            Schedule schedule = getFirstSchedule();
            if (schedule != null) {
                return schedule.getStartTime();
            }
        }
        return -1;
    }

    private Schedule getFirstSchedule() {
        String orderBy = SCHEDULE_COL_StartDay + " ASC";
        String[] cols = {SCHEDULE_COL_ID, SCHEDULE_COL_StartDay, SCHEDULE_COL_EndDay};
        Cursor c = db.query(SCHEDULE_TABLE_NAME,cols,"",null,null,null,orderBy);
        if (c.moveToFirst()) {
            return new Schedule(c.getInt(0), Long.parseLong(c.getString(1)), Long.parseLong(c.getString(2)));
        } else {
            return null;
        }
    }
}
