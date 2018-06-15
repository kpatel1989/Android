package kp.com.database;

/**
 * Created by macadmin on 2016-12-12.
 */

public class KpTask {

    private long taskId;
    private long listId;
    private double price;
    private String name;
    private String notes;
    private String completedDate;
    private String hidden;

    public static final String TRUE = "1";
    public static final String FALSE = "0";

    public KpTask() {
        name = "";
        notes = "";
        completedDate = FALSE;
        hidden = FALSE;
    }

    public KpTask(int listId, String name, String notes,
                String completed, String hidden) {
        this.listId = listId;
        this.name = name;
        this.notes = notes;
        this.completedDate = completed;
        this.hidden = hidden;
    }

    public KpTask(int taskId, int listId, String name, String notes,
                String completed, String hidden) {
        this.taskId = taskId;
        this.listId = listId;
        this.name = name;
        this.notes = notes;
        this.completedDate = completed;
        this.hidden = hidden;
    }

    public KpTask(int taskId, int listId, String name, String notes,
                  String completed, String hidden, double price) {
        this.taskId = taskId;
        this.listId = listId;
        this.name = name;
        this.notes = notes;
        this.completedDate = completed;
        this.hidden = hidden;
        this.price = price;
    }

    public long getId() {
        return taskId;
    }

    public void setId(long taskId) {
        this.taskId = taskId;
    }

    public long getListId() {
        return listId;
    }

    public void setListId(long listId) {
        this.listId = listId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getCompletedDate() {
        return completedDate;
    }

    public long getCompletedDateMillis() {
        return Long.parseLong(completedDate);
    }

    public void setCompletedDate(String completedDate) {
        this.completedDate = completedDate;
    }

    public void setCompletedDate(long millis) {
        this.completedDate = Long.toString(millis);
    }

    public String getHidden(){
        return hidden;
    }

    public void setHidden(String hidden) {
        this.hidden = hidden;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
