package kp.com.assignment;

import android.content.Context;
import android.content.res.TypedArray;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by Kartik on 07-Dec-16.
 */

public final class QuestionBank {
    final String QUESTIONS = "<?xml version=\"1.0\" encoding=\"utf-8\"?><questions name=\"questions\"><question><ques>Which of the following framework is not used in iOS ?</ques><options><string>UIKit Framework</string><string>AppKit Framework</string><string>Foundation Framework</string><string>CoreMotion Framework</string></options><answer>AppKit Framework</answer></question><question><ques>iOS stands for ?</ques><options><string>Internetwork Operating System</string><string>iPhone Operating System</string><string>Internet Operating System</string><string>None of Them</string></options><answer>iPhone Operating System</answer></question><question><ques>Which of the following hierarchy is correct?</ques><options><string>UIButton-&gt;UIControl-&gt;UIView-&gt;NSObject-&gt;UIResponder</string><string>UIControl-&gt;UIButton-&gt;UIView-&gt;UIResponder-&gt;NSObject</string><string>UIButton-&gt;UIControl-&gt;UIView-&gt;UIResponder-&gt;NSObject</string><string>None of the Above</string></options><answer>UIButton-&gt;UIControl-&gt;UIView-&gt;UIResponder-&gt;NSObject</answer></question><question><ques>Which of the following iOS frameworks is a commonly used third party Library?</ques><options><string>AVFoundation.framework</string><string>AFNetwork.framework</string><string>Audiotoolbox.framework</string><string>CFNetwork.framework</string></options><answer>AFNetwork.framework</answer></question><question><ques>The application has not been launched or was running but terminated by the device.Determine the current state of App.</ques><options><string>Suspended state</string><string>Background state</string><string>Inactive state</string><string>Not running state</string></options><answer>Not running state</answer></question><question><ques>Flash Applications is  supported in iPhone browsers.Is it true?</ques><options><string>Yes</string><string>No</string><string>It supports flash applications from apple only.</string><string>Supports partially</string></options><answer>Yes</answer></question><question><ques>Application running in foreground but currently not receiving any events.What is the current state of Application?</ques><options><string>Background state</string><string>Inactive State</string><string>Suspended state</string><string>Active State</string></options><answer>Inactive State</answer></question><question><ques>Which of the following statement is wrong ?</ques><options><string>IBAction is a type qualifier used by IB to enable connection user experience elements and app code.</string><string>IBAction resolves to void</string><string>IBAction is a macro defined to denote a method that can be referred to in Interface Build.</string><string>None of them</string></options><answer>None of them</answer></question><question><ques>Application is in background. Not executing any code.What is the current application state?</ques><options><string>Inactive state</string><string>Active state</string><string>Background state</string><string>Suspended state</string></options><answer>Suspended state</answer></question><question><ques>Which of the following statement is wrong?</ques><options><string>IBOutlet are macro defined to denote a variable  that can be referred to in Interface Builder.</string><string>IBOutlet resolves to Id</string><string>IBOutlet resolves to nothing</string><string>IBOutlet is a type qualifier used by Interface Builder as a connection point for sending messages from app code to a user interface element.</string></options><answer>IBOutlet resolves to nothing</answer></question><question><ques>Which of the following is a default UI property?</ques><options><string>assign</string><string>non-atomic</string><string>atomic</string><string>None of them</string></options><answer>atomic</answer></question><question><ques>Multitasking in iOS was introduced in which version?</ques><options><string>iOS 2.0</string><string>iOS 4.0</string><string>iOS 6.0</string><string>iOS 7.0</string></options><answer>iOS 4.0</answer></question><question><ques>Application is running in foreground and receiving events.What is the current App State?</ques><options><string>Inactive state</string><string>Active state</string><string>Background state</string><string>Suspended state</string></options><answer>Inactive state</answer></question></questions>";
    private static QuestionBank questionList;

    int TOTAL_QUESTIONS;
    ArrayList<Integer> randomQuestions;
    ArrayList<Question> questionBank;
    int currentQuestion;
    int correctAns;
    Context context;

    public static QuestionBank getInstance(Context context) throws ParserConfigurationException, SAXException, IOException {
        if (questionList == null) {
            questionList = new QuestionBank(context);
        }
        return questionList;
    }

    private QuestionBank(Context context) throws IOException, SAXException, ParserConfigurationException {
        TOTAL_QUESTIONS = 5;
        currentQuestion = 0;
        this.context = context;
        buildQuestionBank();
        generateRandomQuestions();
        return;
    }

    public void buildQuestionBank() throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        XMLReader xmlreader = parser.getXMLReader();

        // set content handler
        QuestionXmlHandler questionXmlHandler = new QuestionXmlHandler();
        xmlreader.setContentHandler(questionXmlHandler);
        StringReader sr = new StringReader(QUESTIONS);
        InputSource is = new InputSource(sr);
        xmlreader.parse(is);
        questionBank = questionXmlHandler.getQuestions();
        return;
    }

    public void generateRandomQuestions() {
        randomQuestions = new ArrayList<Integer>(TOTAL_QUESTIONS);
        for (int i=0;i<TOTAL_QUESTIONS;i++) {
            int index = new Random().nextInt(questionBank.size());
            if (!randomQuestions.contains(index)) {
                randomQuestions.add(index);
            }
        }
    }

    public Question getNextQuestion(int index) {
        Question ques = null;
        if (index >= 0 && index < TOTAL_QUESTIONS) {
            ques = questionBank.get((int)(randomQuestions.get(index)));
        }
        return ques;
    }
}

final class Question {
    String question;
    ArrayList<String> options;
    String answer;

    Question() {

    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public ArrayList<String> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<String> options) {
        this.options = options;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isCorrect(String selectedOption) {
        return answer == selectedOption;
    }
}

class QuestionXmlHandler extends DefaultHandler {

    ArrayList<Question> questions;
    Question que;
    ArrayList<String> options;
    boolean isOption, isQuestion, isAnswer;
    @Override
    public void startDocument() throws SAXException {
        questions = new ArrayList<>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch (qName) {
            case "ques":
                que = new Question();
                questions.add(que);
                isQuestion = true;
                break;
            case "options":
                options = new ArrayList<>();
                que.setOptions(options);
                isOption = true;
                break;
            case "answer":
                isAnswer = true;
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case "ques":
                isQuestion = false;
                break;
            case "options":
                isOption = false;
                break;
            case "answer":
                isAnswer = false;
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String text = new String(ch);
        if (isQuestion) {
            que.setQuestion(text);
        } else if (isOption) {
            options.add(text);
        } else if (isAnswer) {
            que.setAnswer(text);
        }
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }
}