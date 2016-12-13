package kp.com.jobscheduler.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by macadmin on 2016-12-13.
 */

public class Database extends SQLiteOpenHelper{
    public static final String JOB_SCHEDULE_DB = "shifts";
    public static final String SHIFT_TABLE_NAME = "shifts";
    public static final String CYCLE_TABLE_NAME = "cycle";

    public Database(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
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
