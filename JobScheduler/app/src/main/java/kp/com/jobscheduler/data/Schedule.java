package kp.com.jobscheduler.data;

import java.io.Serializable;
import java.util.Calendar;
import java.util.HashMap;

/**
 * Created by macadmin on 2016-12-12.
 */

public class Schedule implements Serializable{
    private int id;
    private long startTime;
    private long endTime;

    public Schedule(){

    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public String getStartTimeFormatted() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(this.startTime);
        return (calendar.get(Calendar.MONTH)+1) + "/" + calendar.get(Calendar.DAY_OF_MONTH) + "/" + calendar.get(Calendar.YEAR) + " " + calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE);
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public String getEndTimeFormatted() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(this.endTime);
        return (calendar.get(Calendar.MONTH)+1) + "/" + calendar.get(Calendar.DAY_OF_MONTH) + "/" + calendar.get(Calendar.YEAR) + " " + calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE);
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public Schedule(int id, long startTime, long endTime) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public long getTotalTime() {
        return this.endTime - this.startTime;
    }

    public int getId() {
        return id;
    }
}