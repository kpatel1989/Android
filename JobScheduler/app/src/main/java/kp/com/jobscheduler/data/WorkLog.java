package kp.com.jobscheduler.data;

import java.util.ArrayList;

/**
 * Created by macadmin on 2016-12-13.
 */

public class WorkLog {
    private ArrayList<PayCycle> cycles;
    private String jobName;

    public WorkLog(String jobName) {
        this.jobName = jobName;
        this.cycles = new ArrayList<PayCycle>();
    }
}
