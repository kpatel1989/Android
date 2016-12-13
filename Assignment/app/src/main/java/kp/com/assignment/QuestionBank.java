package kp.com.assignment;

import android.content.Context;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Random;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by Kartik on 07-Dec-16.
 */

public final class QuestionBank {
    final String KP_QUESTIONS = "<?xml version=\"1.0\" encoding=\"utf-8\"?><kpQuestions name=\"kpQuestions\"><kpQuestion><ques>Which of the following framework is not used in iOS ?</ques><kpOptions><string>UIKit Framework</string><string>AppKit Framework</string><string>Foundation Framework</string><string>CoreMotion Framework</string></kpOptions><kpAnswer>AppKit Framework</kpAnswer></kpQuestion><kpQuestion><ques>iOS stands for ?</ques><kpOptions><string>Internetwork Operating System</string><string>iPhone Operating System</string><string>Internet Operating System</string><string>None of Them</string></kpOptions><kpAnswer>iPhone Operating System</kpAnswer></kpQuestion><kpQuestion><ques>Which of the following hierarchy is correct?</ques><kpOptions><string>UIButton-&gt;UIControl-&gt;UIView-&gt;NSObject-&gt;UIResponder</string><string>UIControl-&gt;UIButton-&gt;UIView-&gt;UIResponder-&gt;NSObject</string><string>UIButton-&gt;UIControl-&gt;UIView-&gt;UIResponder-&gt;NSObject</string><string>None of the Above</string></kpOptions><kpAnswer>UIButton-&gt;UIControl-&gt;UIView-&gt;UIResponder-&gt;NSObject</kpAnswer></kpQuestion><kpQuestion><ques>Which of the following iOS frameworks is a commonly used third party Library?</ques><kpOptions><string>AVFoundation.framework</string><string>AFNetwork.framework</string><string>Audiotoolbox.framework</string><string>CFNetwork.framework</string></kpOptions><kpAnswer>AFNetwork.framework</kpAnswer></kpQuestion><kpQuestion><ques>The application has not been launched or was running but terminated by the device.Determine the current state of App.</ques><kpOptions><string>Suspended state</string><string>Background state</string><string>Inactive state</string><string>Not running state</string></kpOptions><kpAnswer>Not running state</kpAnswer></kpQuestion><kpQuestion><ques>Flash Applications is  supported in iPhone browsers.Is it true?</ques><kpOptions><string>Yes</string><string>No</string><string>It supports flash applications from apple only.</string><string>Supports partially</string></kpOptions><kpAnswer>Yes</kpAnswer></kpQuestion><kpQuestion><ques>Application running in foreground but currently not receiving any events.What is the current state of Application?</ques><kpOptions><string>Background state</string><string>Inactive State</string><string>Suspended state</string><string>Active State</string></kpOptions><kpAnswer>Inactive State</kpAnswer></kpQuestion><kpQuestion><ques>Which of the following statement is wrong ?</ques><kpOptions><string>IBAction is a type qualifier used by IB to enable connection user experience elements and app code.</string><string>IBAction resolves to void</string><string>IBAction is a macro defined to denote a method that can be referred to in Interface Build.</string><string>None of them</string></kpOptions><kpAnswer>None of them</kpAnswer></kpQuestion><kpQuestion><ques>Application is in background. Not executing any code.What is the current application state?</ques><kpOptions><string>Inactive state</string><string>Active state</string><string>Background state</string><string>Suspended state</string></kpOptions><kpAnswer>Suspended state</kpAnswer></kpQuestion><kpQuestion><ques>Which of the following statement is wrong?</ques><kpOptions><string>IBOutlet are macro defined to denote a variable  that can be referred to in Interface Builder.</string><string>IBOutlet resolves to Id</string><string>IBOutlet resolves to nothing</string><string>IBOutlet is a type qualifier used by Interface Builder as a connection point for sending messages from app code to a user interface element.</string></kpOptions><kpAnswer>IBOutlet resolves to nothing</kpAnswer></kpQuestion><kpQuestion><ques>Which of the following is a default UI property?</ques><kpOptions><string>assign</string><string>non-atomic</string><string>atomic</string><string>None of them</string></kpOptions><kpAnswer>atomic</kpAnswer></kpQuestion><kpQuestion><ques>Multitasking in iOS was introduced in which version?</ques><kpOptions><string>iOS 2.0</string><string>iOS 4.0</string><string>iOS 6.0</string><string>iOS 7.0</string></kpOptions><kpAnswer>iOS 4.0</kpAnswer></kpQuestion><kpQuestion><ques>Application is running in foreground and receiving events.What is the current App State?</ques><kpOptions><string>Inactive state</string><string>Active state</string><string>Background state</string><string>Suspended state</string></kpOptions><kpAnswer>Inactive state</kpAnswer></kpQuestion></kpQuestions>";
    private static QuestionBank kpQuestionList;

    int KP_TOTAL_QUESTIONS;
    ArrayList<Integer> kpRandomQuestions;
    ArrayList<KpQuestion> kpQuestionBank;
    int kpCurrentQuestion;
    int kpCorrectAns;
    Context kpContext;

    public static QuestionBank getInstance(Context context) throws ParserConfigurationException, SAXException, IOException {
        if (kpQuestionList == null) {
            kpQuestionList = new QuestionBank(context);
        }
        return kpQuestionList;
    }

    private QuestionBank(Context context) throws IOException, SAXException, ParserConfigurationException {
        KP_TOTAL_QUESTIONS = 5;
        kpCurrentQuestion = 0;
        this.kpContext = context;
        kpBuildQuestionBank();
        kpGenerateRandomQuestions();
        return;
    }

    public void kpBuildQuestionBank() throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        XMLReader xmlreader = parser.getXMLReader();

        // set content handler
        KpQuestionXmlHandler questionXmlHandler = new KpQuestionXmlHandler();
        xmlreader.setContentHandler(questionXmlHandler);
        StringReader sr = new StringReader(KP_QUESTIONS);
        InputSource is = new InputSource(sr);
        xmlreader.parse(is);
        kpQuestionBank = questionXmlHandler.getKpQuestions();
        return;
    }

    public void kpGenerateRandomQuestions() {
        kpRandomQuestions = new ArrayList<Integer>(KP_TOTAL_QUESTIONS);
        for (int i = 0; i< KP_TOTAL_QUESTIONS; i++) {
            int index = new Random().nextInt(kpQuestionBank.size());
            if (!kpRandomQuestions.contains(index)) {
                kpRandomQuestions.add(index);
            }
        }
    }

    public KpQuestion kpGetNextQuestion(int index) {
        KpQuestion ques = null;
        if (index >= 0 && index < KP_TOTAL_QUESTIONS) {
            ques = kpQuestionBank.get((int)(kpRandomQuestions.get(index)));
        }
        return ques;
    }
}

final class KpQuestion {
    String kpQuestion;
    ArrayList<String> kpOptions;
    String kpAnswer;

    KpQuestion() {

    }

    public String getKpQuestion() {
        return kpQuestion;
    }

    public void setKpQuestion(String kpQuestion) {
        this.kpQuestion = kpQuestion;
    }

    public ArrayList<String> getKpOptions() {
        return kpOptions;
    }

    public void setKpOptions(ArrayList<String> kpOptions) {
        this.kpOptions = kpOptions;
    }

    public String getKpAnswer() {
        return kpAnswer;
    }

    public void setKpAnswer(String kpAnswer) {
        this.kpAnswer = kpAnswer;
    }

    public boolean isCorrect(String selectedOption) {
        return kpAnswer == selectedOption;
    }
}

class KpQuestionXmlHandler extends DefaultHandler {

    ArrayList<KpQuestion> kpQuestions;
    KpQuestion kpQue;
    ArrayList<String> kpOptions;
    boolean kpIsOption, kpIsQuestion, kpIsAnswer;

    @Override
    public void startDocument() throws SAXException {
        kpQuestions = new ArrayList<>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch (qName) {
            case "ques":
                kpQue = new KpQuestion();
                kpQuestions.add(kpQue);
                kpIsQuestion = true;
                break;
            case "kpOptions":
                kpOptions = new ArrayList<>();
                kpQue.setKpOptions(kpOptions);
                kpIsOption = true;
                break;
            case "kpAnswer":
                kpIsAnswer = true;
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case "ques":
                kpIsQuestion = false;
                break;
            case "kpOptions":
                kpIsOption = false;
                break;
            case "kpAnswer":
                kpIsAnswer = false;
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String text = new String(ch);
        if (kpIsQuestion) {
            kpQue.setKpQuestion(text);
        } else if (kpIsOption) {
            kpOptions.add(text);
        } else if (kpIsAnswer) {
            kpQue.setKpAnswer(text);
        }
    }

    public ArrayList<KpQuestion> getKpQuestions() {
        return kpQuestions;
    }
}