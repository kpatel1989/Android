package kp.com.assignment;

import android.app.Application;
import android.util.Log;

import org.xml.sax.SAXException;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

public class App extends Application {

    public QuestionBank kpQuestionBank;

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            kpQuestionBank = QuestionBank.getInstance(this.getApplicationContext());
        } catch (ParserConfigurationException e) {
            Log.d("Xml Parse Error ", e.getMessage());
            throw new RuntimeException(e);
        } catch (SAXException e) {
            Log.d("SAX Parse Error ", e.getMessage());
            throw new RuntimeException(e);
        } catch (IOException e) {
            Log.d("IO Error", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
