package kp.com.jobscheduler.data;

/**
 * Created by macadmin on 2016-12-12.
 */

public class Schedule {
    private long startTime;
    private long endTime;

    public Schedule(long startTime, long endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }
}