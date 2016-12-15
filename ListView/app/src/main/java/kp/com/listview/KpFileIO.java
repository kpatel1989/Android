package kp.com.listview;

import android.content.Context;
import android.util.Log;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by macadmin on 2016-12-08.
 */

public class KpFileIO {
    private final String URL_STRING = "http://rss.cnn.com/rss/cnn_tech.rss";
    private final String FILENAME = "news_feed.xml";
    private Context kpContext = null;

    public KpFileIO(Context context) {
        this.kpContext = context;
    }

    public void downloadFile(){
        URL url = null;
        try {
            url = new URL(URL_STRING);
            InputStream in = url.openStream();
            FileOutputStream out = kpContext.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            byte[] buffer = new byte[1024];
            int bytes = in.read(buffer);
            while(bytes != -1) {
                out.write(buffer,0,bytes);
                bytes = in.read(buffer);
            }
            out.close();
            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public KpRssFeed readFile() {
        try{
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            XMLReader reader = parser.getXMLReader();

            KpRssFeedHandler handler = new KpRssFeedHandler();
            reader.setContentHandler(handler);

            FileInputStream in =  kpContext.openFileInput(FILENAME);
            InputSource source = new InputSource(in);
            source.setEncoding("UTF-8");
            reader.parse(source);
            return handler.getFeed();
        } catch (Exception e) {
            Log.e("News reader", e.toString());
        }
        return null;
    }
}
