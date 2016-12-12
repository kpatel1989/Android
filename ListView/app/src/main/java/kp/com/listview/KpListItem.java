package kp.com.listview;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by macadmin on 2016-12-08.
 */

public class KpListItem {

    private String kpTitle;
    private String kpDescription;
    private String kplink;
    private String kpPublishedDate;
    private SimpleDateFormat kpDateOutFormat = new SimpleDateFormat("EEEE, h:mm a (MMM dd)");
    private SimpleDateFormat kpDateInFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z");

    public String getKpTitle() {
        return kpTitle;
    }

    public void setKpTitle(String kpItem) {
        this.kpTitle = kpItem;
    }

    public String getKpDescription() {
        return kpDescription;
    }

    public void setKpDescription(String kpDescription) {
        this.kpDescription = kpDescription;
    }

    public String getKplink() {
        return kplink;
    }

    public void setKplink(String kplink) {
        this.kplink = kplink;
    }

    public String getKpPublishedDate() {
        return kpPublishedDate;
    }

    public void setKpPublishedDate(String kpPublishedDate) {
        this.kpPublishedDate = kpPublishedDate;
    }

    public String getPubDateFormatted() {
        try {
            Date date = kpDateInFormat.parse(kpPublishedDate.trim());
            String pubDateFormatted = kpDateOutFormat.format(date);
            return pubDateFormatted;
        }
        catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
