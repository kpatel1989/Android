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
        switch (s2) {
            case "item":
                item = new KpListItem();
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
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case "item":
                feed.addItem(item);
                item = null;
                isItem = false;
                break;
            case "title":
                isTitle = false;
                break;
            case "description":
                isDescription = false;
                break;
            case "link":
                isLink = false;
                break;
            case "pubDate":
                isPubDate = false;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String str = new String(ch);
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
