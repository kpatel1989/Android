package kp.com.assignment;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class KartikQuestion1 extends AppCompatActivity implements View.OnClickListener {

    TextView kpQuestionLabel, kpOption1, kpOption2, kpOption3, kpOption4;
    Button kpNextBtn;
    KpQuestion kpQuestion;
    String kpSelectedAns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kartik_question1);

        kpQuestionLabel = (TextView) findViewById(R.id.question1);
        kpOption1 = (TextView) findViewById(R.id.ansTextView1);
        kpOption1.setOnClickListener(this);
        kpOption2 = (TextView) findViewById(R.id.ansTextView2);
        kpOption2.setOnClickListener(this);
        kpOption3 = (TextView) findViewById(R.id.ansTextView3);
        kpOption3.setOnClickListener(this);
        kpOption4 = (TextView) findViewById(R.id.ansTextView4);
        kpOption4.setOnClickListener(this);

        kpNextBtn = (Button) findViewById(R.id.question1Next);
        kpNextBtn.setOnClickListener(this);

        kpQuestion = ((App)getApplication()).kpQuestionBank.kpGetNextQuestion(0);

        kpQuestionLabel.setText("1. " + kpQuestion.kpQuestion);
        kpOption1.setText(kpQuestion.kpOptions.get(0));
        kpOption2.setText(kpQuestion.kpOptions.get(1));
        kpOption3.setText(kpQuestion.kpOptions.get(2));
        kpOption4.setText(kpQuestion.kpOptions.get(3));
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == kpOption1.getId()) {
            clearBgColor();
            kpOption1.setBackgroundColor(Color.GREEN);
            this.kpSelectedAns = kpOption1.getText().toString();
        } else if (v.getId() == kpOption2.getId()) {
            clearBgColor();
            kpOption2.setBackgroundColor(Color.GREEN);
            this.kpSelectedAns = kpOption2.getText().toString();
        } else if (v.getId() == kpOption3.getId()) {
            clearBgColor();
            kpOption3.setBackgroundColor(Color.GREEN);
            this.kpSelectedAns = kpOption3.getText().toString();
        } else if (v.getId() == kpOption4.getId()) {
            clearBgColor();
            kpOption4.setBackgroundColor(Color.GREEN);
            this.kpSelectedAns = kpOption4.getText().toString();
        } else if (v.getId() == kpNextBtn.getId()) {
            if (kpSelectedAns != "") {
                if (kpQuestion.isCorrect(this.kpSelectedAns)) {
                    ((App) getApplication()).kpQuestionBank.kpCorrectAns += 1;
                }
                startActivity(new Intent(this, KartikQuestion2.class));
            }
        }
    }

    private void kpCheckAnswer(String selectedAnswer) {
        this.kpSelectedAns = selectedAnswer;
    }

    private void clearBgColor() {
        kpOption1.setBackgroundColor(Color.WHITE);
        kpOption2.setBackgroundColor(Color.WHITE);
        kpOption3.setBackgroundColor(Color.WHITE);
        kpOption4.setBackgroundColor(Color.WHITE);
    }
}
