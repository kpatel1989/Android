package kp.com.listview;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by macadmin on 2016-12-08.
 */

public class KpRssFeed {
    private String kpTitle;
    private String kpPubDate;
    private ArrayList<KpListItem> kpItems;
    private SimpleDateFormat kpDateInFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z");

    public KpRssFeed(){
        kpItems = new ArrayList<>();
    }

    public int addItem(KpListItem item) {
        kpItems.add(item);
        return kpItems.size();
    }

    public KpListItem getItem(int index){
        return kpItems.get(index);
    }

    public ArrayList<KpListItem> getAllItems() {
        return kpItems;
    }
    public String getKpTitle() {
        return kpTitle;
    }

    public void setKpTitle(String kpTitle) {
        this.kpTitle = kpTitle;
    }

    public String getKpPubDate() {
        return kpPubDate;
    }

    public void setKpPubDate(String kpPubDate) {
        this.kpPubDate = kpPubDate;
    }

    public long getPubDateinMillis() {
        try {
            Date date = kpDateInFormat.parse(kpPubDate.trim());
            return date.getTime();
        } catch (ParseException e) {

            throw new RuntimeException();
        }

    }
}

