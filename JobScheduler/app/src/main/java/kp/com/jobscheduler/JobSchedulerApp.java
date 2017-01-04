package kp.com.jobscheduler;

import android.app.Application;

import kp.com.jobscheduler.data.Database;
import kp.com.jobscheduler.data.Schedule;

/**
 * Created by Kartik on 22-Dec-16.
 */

public class JobSchedulerApp extends Application {

    Database database;

    public JobSchedulerApp() {
        super();
    }

    public void saveSchedule(Schedule schedule){
        database.insertData(Database.SCHEDULE_TABLE_NAME,database.serializeSchedule(schedule));
    }

    public Database getDatabase() {
        return database;
    }

    public void setDatabase(Database database) {
        this.database = database;
    }
}
