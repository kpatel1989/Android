package kp.com.assignment;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class KartikQuestion1 extends AppCompatActivity implements View.OnClickListener {

    TextView questionLabel, option1, option2, option3, option4;
    Button nextBtn;
    Question question;
    String selectedAns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kartik_question1);

        questionLabel = (TextView) findViewById(R.id.question1);
        option1 = (TextView) findViewById(R.id.ansTextView1);
        option1.setOnClickListener(this);
        option2 = (TextView) findViewById(R.id.ansTextView2);
        option2.setOnClickListener(this);
        option3 = (TextView) findViewById(R.id.ansTextView3);
        option3.setOnClickListener(this);
        option4 = (TextView) findViewById(R.id.ansTextView4);
        option4.setOnClickListener(this);

        nextBtn = (Button) findViewById(R.id.question1Next);
        nextBtn.setOnClickListener(this);

        question = ((App)getApplication()).questionBank.getNextQuestion(0);

        questionLabel.setText("1. " + question.question);
        option1.setText(question.options.get(0));
        option2.setText(question.options.get(1));
        option3.setText(question.options.get(2));
        option4.setText(question.options.get(3));
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == option1.getId()) {
            clearBgColor();
            option1.setBackgroundColor(Color.GREEN);
            this.selectedAns = option1.getText().toString();
        } else if (v.getId() == option2.getId()) {
            clearBgColor();
            option2.setBackgroundColor(Color.GREEN);
            this.selectedAns = option2.getText().toString();
        } else if (v.getId() == option3.getId()) {
            clearBgColor();
            option3.setBackgroundColor(Color.GREEN);
            this.selectedAns = option3.getText().toString();
        } else if (v.getId() == option4.getId()) {
            clearBgColor();
            option4.setBackgroundColor(Color.GREEN);
            this.selectedAns = option4.getText().toString();
        } else if (v.getId() == nextBtn.getId()) {
            if (selectedAns != "") {
                if (question.isCorrect(this.selectedAns)) {
                    ((App) getApplication()).questionBank.correctAns += 1;
                }
                startActivity(new Intent(this, KartikQuestion2.class));
            }
        }
    }

    private void checkAnswer(String selectedAnswer) {
        this.selectedAns = selectedAnswer;
    }

    private void clearBgColor() {
        option1.setBackgroundColor(Color.WHITE);
        option2.setBackgroundColor(Color.WHITE);
        option3.setBackgroundColor(Color.WHITE);
        option4.setBackgroundColor(Color.WHITE);
    }
}
