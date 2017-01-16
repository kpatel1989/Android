package kp.com.jobscheduler;

import android.*;
import android.app.Application;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.ArraySet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import kp.com.jobscheduler.data.Database;
import kp.com.jobscheduler.data.PayCycle;
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

    public void savePayCycle(PayCycle payCycle){
        database.insertData(Database.PAYCYCLE_TABLE_NAME,database.serializePayCycle(payCycle));
    }

    public void deleteSchedule(int id) {
        database.deleteData(Database.SCHEDULE_TABLE_NAME,id);
    }

    public Database getDatabase() {
        return database;
    }

    public void setDatabase(Database database) {
        this.database = database;
    }

    public ArrayList<PayCycle> getPayCycles() {
        return database.getPayCycles();
    }
    public long getLastPayDay() {
        return this.database.getLastPayDay();
    }

    public ArrayList<Schedule> getAllSchedules() {
        return database.getSchedules();
    }

}
