package kp.com.jobscheduler.data;

/**
 * Created by Kartik on 03-Jan-17.
 */

public class WageCalculator {
    private int hoursPerWeek = 20;
    private double perHourWage = 11.40;
    private double extraHourWage = 9.0;
    private String currency = "$";

    public WageCalculator() {
    }

    public double calculateWage(double hours, double minutes) {
        double totalWage = 0.0;
        double totalHours = hours + (minutes/60);
        if (totalHours >= hoursPerWeek) {
            totalWage += (hoursPerWeek * perHourWage);
            totalHours -= hoursPerWeek;
            if (totalHours > 0) {
                totalWage += (totalHours * extraHourWage);
            }
        } else {
            totalWage += (totalHours * perHourWage);
        }
        return totalWage;
    }
}
