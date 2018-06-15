package kp.com.database;

/**
 * Created by macadmin on 2016-12-12.
 */

public class KpList {
    private long id;
    private String name;

    public KpList() {}

    public KpList(String name) {
        this.name = name;
    }

    public KpList(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;   // used for add/edit spinner
    }
}

