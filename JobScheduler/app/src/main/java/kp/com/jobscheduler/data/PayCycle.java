package kp.com.jobscheduler.data;

/**
 * Created by macadmin on 2016-12-12.
 */

public class PayCycle {
    private long startDay;
    private long endDay;
    private Schedule[] shifts;

    public PayCycle(long startDay, long endDay) {
        this.startDay = startDay;
        this.endDay = endDay;
    }

}
