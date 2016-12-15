package kartik.com.labtest;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created by macadmin on 2016-12-08.
 */

public class KpCatalogHandler extends DefaultHandler {
    KpCatalog kpCatalog;
    KpCd kpCd;
    boolean kpIsTitle, kpIisArtist, kpIsCountry, kpIsCompany, kpIsPrice, kpIsYear;

    public void startDocument() throws SAXException {
        super.startDocument();
        kpCatalog = new KpCatalog();
        kpCd = new KpCd();
    }

    @Override
    public void startElement(String s, String s1, String s2, Attributes attributes) throws SAXException {
        switch (s2) {
            case "CD":
                kpCd = new KpCd();
                break;
            case "TITLE":
                kpIsTitle = true;
                break;
            case "ARTIST":
                kpIisArtist = true;
                break;
            case "COMPANY":
                kpIsCompany = true;
                break;
            case "COUNTRY":
                kpIsCountry = true;
                break;
            case "PRICE":
                kpIsPrice = true;
                break;
            case "YEAR":
                kpIsYear = true;
                break;
        }
    }


    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case "CD":
                kpCatalog.addItem(kpCd);
            case "TITLE":
                kpIsTitle = false;
                break;
            case "ARTIST":
                kpIisArtist = false;
                break;
            case "COMPANY":
                kpIsCompany = false;
                break;
            case "COUNTRY":
                kpIsCountry = false;
                break;
            case "PRICE":
                kpIsPrice = false;
                break;
            case "YEAR":
                kpIsYear = false;
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String str = new String(ch,start,length);
        if (kpIsTitle) {
            kpCd.setKpTitle(str);
        } else if (kpIsCountry) {
            kpCd.setKpCompany(str);
        } else if (kpIsCompany) {
            kpCd.setKpCountry(str);
        } else if (kpIisArtist) {
            kpCd.setKpArtist(str);
        }  else if (kpIsPrice) {
            kpCd.setKpPrice(Double.parseDouble(str));
        } else if (kpIsYear) {
            kpCd.setKpYear(Integer.parseInt(str));
        }
    }

    public KpCatalog getKpCatalog() {
        return kpCatalog;
    }
}
