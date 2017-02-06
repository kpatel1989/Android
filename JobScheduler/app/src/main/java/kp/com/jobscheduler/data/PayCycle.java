package kp.com.jobscheduler.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import kp.com.jobscheduler.JobSchedulerApp;

/**
 * Created by macadmin on 2016-12-12.
 */

public class PayCycle implements Serializable {
    public static final String START_DAY_HASH_KEY = "startDay";
    public static final String END_DAY_HASH_KEY = "endDay";
    private int id;
    private long startDay;
    private long endDay;
    private ArrayList<Schedule> shifts;

    public PayCycle() {
        shifts = new ArrayList<>();
    }

    public PayCycle(int id, long startDay, long endDay) {
        shifts = new ArrayList<>();
        this.id = id;
        this.startDay = startDay;
        this.endDay = endDay;
    }

    public long getStartDay() {
        return startDay;
    }

    public void setStartDay(long startDay) {
        this.startDay = startDay;
    }

    public String getStartDayFormatted() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(this.startDay);
        return (calendar.get(Calendar.MONTH)+1) + "/" + calendar.get(Calendar.DAY_OF_MONTH) + "/" + calendar.get(Calendar.YEAR) + " " + calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE);
    }

    public long getEndDay() {
        return endDay;
    }

    public void setEndDay(long endDay) {
        this.endDay = endDay;
    }

    public String getEndDayFormatted() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(this.endDay);
        return (calendar.get(Calendar.MONTH)+1) + "/" + calendar.get(Calendar.DAY_OF_MONTH) + "/" + calendar.get(Calendar.YEAR);
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

    public HashMap<String, String> getHashMap() {
        HashMap<String,String> map = new HashMap<>();
        map.put("startDay",getStartDayFormatted());
        map.put("endDay",getEndDayFormatted());
        return map;
    }

    public int getId() {
        return id;
    }

    public double calculatePay() {
        long totalTime = getTotalTime();
        double hours = Math.floor(totalTime / (1000 * 60 * 60));
        double minutes = Math.ceil((totalTime - (hours * 1000 * 60 * 60))/ (1000 * 60));
        return new WageCalculator().calculateWage(hours,minutes);
    }
}
