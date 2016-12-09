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
    boolean isItem,isTitle,isDescription,isPubDate,isLink;

    public void startDocument() throws SAXException {
        super.startDocument();
        feed = new KpRssFeed();
        item = new KpListItem();
    }

    @Override
    public void startElement(String s, String s1, String s2, Attributes attributes) throws SAXException {
        switch (s) {
            case "item":
                isItem = true;
                break;
            case "title":
                isTitle = true;
                break;
            case "description":
                isDescription = true;
                break;
            case "link":
                isLink = true;
                break;
            case "pubDate":
                isPubDate = true;
        }
    }

    @Override
    public void endElement(String s, String s1, String s2) throws SAXException {
        if (s.equals("item")) {
            feed.addItem(item);
            item = null;
            isItem = false;
        }
    }

    @Override
    public void characters(char[] chars, int i, int i1) throws SAXException {
        String str = new String(chars,i,i1);
        if (isItem) {
            if (isTitle) {
                item.setKpTitle(str);
            } else if (isPubDate) {
                item.setKpPublishedDate(str);
            } else if (isLink) {
                item.setKplink(str);
            } else if (isDescription) {
                item.setKpDescription(str);
            }
        } else {
            if (isTitle) {
                feed.setKpTitle(str);
            } else if (isPubDate) {
                feed.setKpPubDate(str);
            }
        }
    }

    public KpRssFeed getFeed() {
        return feed;
    }
}
