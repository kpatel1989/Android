package kp.com.listview;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created by macadmin on 2016-12-08.
 */

public class KpRssFeedHandler extends DefaultHandler {
    KpRssFeed feed;
    KpListItem item;

    @Override
    public void startElement(String s, String s1, String s2, Attributes attributes) throws SAXException {

    }

    @Override
    public void endElement(String s, String s1, String s2) throws SAXException {
        if (s == "item") {

        }
    }

    @Override
    public void characters(char[] chars, int i, int i1) throws SAXException {

    }

    public KpRssFeed getFeed() {
        return feed;
    }
}
