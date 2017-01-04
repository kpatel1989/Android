package kp.com.jobscheduler.data;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by macadmin on 2016-12-12.
 */

public class PayCycle {
    private long startDay;
    private long endDay;
    private ArrayList<Schedule> shifts;

    public PayCycle() {
        shifts = new ArrayList<>();
    }

    public PayCycle(long startDay, long endDay) {
        shifts = new ArrayList<>();
        this.startDay = startDay;
        this.endDay = endDay;
    }

    public long getStartDay() {
        return startDay;
    }

    public void setStartDay(long startDay) {
        this.startDay = startDay;
    }

    public long getEndDay() {
        return endDay;
    }

    public void setEndDay(long endDay) {
        this.endDay = endDay;
    }

    public int addShift(Schedule schedule){
        shifts.add(schedule);
        return shifts.size();
    }

    public void setShifts(ArrayList<Schedule> shifts) {
        this.shifts = shifts;
    }

    public long getTotalTime() {
        long totalTime = 0;
        for (Schedule schedule : shifts) {
            totalTime += schedule.getTotalTime();
        }
        return totalTime;
    }
}
