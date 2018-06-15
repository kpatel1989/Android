package kartik.com.labtest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by macadmin on 2016-12-08.
 */

public class KpCd {

    private String kpTitle;
    private String kpArtist;
    private String kpCountry;
    private String kpCompany;
    private double kpPrice;
    private int kpYear;

    public String getKpTitle() {
        return kpTitle;
    }

    public void setKpTitle(String kpItem) {
        this.kpTitle = kpItem;
    }

    public String getKpArtist() {
        return kpArtist;
    }

    public void setKpArtist(String kpArtist) {
        this.kpArtist = kpArtist;
    }

    public String getKpCountry() {
        return kpCountry;
    }

    public void setKpCountry(String kpCountry) {
        this.kpCountry = kpCountry;
    }

    public String getKpCompany() {
        return kpCompany;
    }

    public void setKpCompany(String kpCompany) {
        this.kpCompany = kpCompany;
    }

    public double getKpPrice() {
        return kpPrice;
    }

    public void setKpPrice(double kpPrice) {
        this.kpPrice = kpPrice;
    }

    public int getKpYear() {
        return kpYear;
    }

    public void setKpYear(int kpYear) {
        this.kpYear = kpYear;
    }
}
