package kp.com.assignment;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class KartikQuestion2 extends AppCompatActivity implements View.OnClickListener {

    TextView kpQuestionLabel;
    Button kpNextBtn, kpButton1, kpButton2, kpButton3, kpButton4;
    KpQuestion kpQuestion;
    String kpSelectedAns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kartik_question2);

        kpQuestionLabel = (TextView) findViewById(R.id.question2Label);
        kpButton1 = (Button) findViewById(R.id.button1);
        kpButton1.setOnClickListener(this);
        kpButton2 = (Button) findViewById(R.id.button2);
        kpButton2.setOnClickListener(this);
        kpButton3 = (Button) findViewById(R.id.button3);
        kpButton3.setOnClickListener(this);
        kpButton4 = (Button) findViewById(R.id.button4);
        kpButton4.setOnClickListener(this);

        kpNextBtn = (Button) findViewById(R.id.question2Next);
        kpNextBtn.setOnClickListener(this);

        kpQuestion = ((App)getApplication()).kpQuestionBank.kpGetNextQuestion(1);

        kpQuestionLabel.setText("2. " + kpQuestion.kpQuestion);
        kpButton1.setText(kpQuestion.kpOptions.get(0));
        kpButton2.setText(kpQuestion.kpOptions.get(1));
        kpButton3.setText(kpQuestion.kpOptions.get(2));
        kpButton4.setText(kpQuestion.kpOptions.get(3));
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == kpButton1.getId()) {
            kpClearBgColor();
            kpButton1.setBackgroundColor(Color.GREEN);
            this.kpSelectedAns = kpButton1.getText().toString();
        } else if (v.getId() == kpButton2.getId()) {
            kpClearBgColor();
            kpButton2.setBackgroundColor(Color.GREEN);
            this.kpSelectedAns = kpButton1.getText().toString();
        } else if (v.getId() == kpButton3.getId()) {
            kpClearBgColor();
            kpButton3.setBackgroundColor(Color.GREEN);
            this.kpSelectedAns = kpButton1.getText().toString();
        } else if (v.getId() == kpButton4.getId()) {
            kpClearBgColor();
            kpButton4.setBackgroundColor(Color.GREEN);
            this.kpSelectedAns = kpButton1.getText().toString();
        } else if (v.getId() == kpNextBtn.getId()) {
            if (kpSelectedAns != "") {
                if (kpQuestion.isCorrect(this.kpSelectedAns)) {
                    ((App) getApplication()).kpQuestionBank.kpCorrectAns += 1;
                }
                startActivity(new Intent(this, KartikQuestion3.class));
            }
        }
    }

    private void kpClearBgColor() {
        kpButton1.setBackgroundColor(Color.WHITE);
        kpButton2.setBackgroundColor(Color.WHITE);
        kpButton3.setBackgroundColor(Color.WHITE);
        kpButton4.setBackgroundColor(Color.WHITE);
    }
}
