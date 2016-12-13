package kp.com.assignment;

import android.app.Application;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.xml.sax.SAXException;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

public class App extends Application {

    public QuestionBank questionBank;

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            questionBank = QuestionBank.getInstance(this.getApplicationContext());
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
