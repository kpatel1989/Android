package kp.com.assignment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Created by Kartik on 07-Dec-16.
 */

public final class QuestionBank {

    public static QuestionBank QuestionList = new QuestionBank();

    int TOTAL_QUESTIONS;
    ArrayList<Integer> randomQuestions;
    ArrayList<HashMap<String,Question>> questionBank;
    int currentQuestion;
    int correctAns;

    private QuestionBank() {
        TOTAL_QUESTIONS = 5;
        currentQuestion = 0;
        buildQuestionBank();
        generateRandomQuestions();
    }

    public void buildQuestionBank() {

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

    public Object getNextQuestion(int index) {
        Object ques = null;
        if (index >= 0 && index < TOTAL_QUESTIONS) {
            ques = questionBank.get((int)(randomQuestions.get(index)));
        }
        return ques;
    }
}

final class Question {
    String question;
    String[] options;
    String answer;

    Question(String qus,String[] opts, String ans) {
        question = qus;
        options = opts;
        ans = answer;
    }
    public boolean isCorrect(String selectedOption) {
        return answer == selectedOption;
    }
}

