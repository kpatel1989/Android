package kp.com.assignment;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

public class KartikQuestion4 extends AppCompatActivity implements View.OnClickListener {

    TextView kpQuestionLabel;
    Button kpNextBtn;
    RadioButton kpRadioButton1, kpRadioButton2, kpRadioButton3, kpRadioButton4;
    KpQuestion kpQuestion;
    String kpSelectedAns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kartik_question4);


        kpQuestionLabel = (TextView) findViewById(R.id.question4Label);
        kpRadioButton1 = (RadioButton) findViewById(R.id.radioButton1);
        kpRadioButton1.setOnClickListener(this);
        kpRadioButton2 = (RadioButton) findViewById(R.id.radioButton2);
        kpRadioButton2.setOnClickListener(this);
        kpRadioButton3 = (RadioButton) findViewById(R.id.radioButton3);
        kpRadioButton3.setOnClickListener(this);
        kpRadioButton4 = (RadioButton) findViewById(R.id.radioButton4);
        kpRadioButton4.setOnClickListener(this);

        kpNextBtn = (Button) findViewById(R.id.question4Next);
        kpNextBtn.setOnClickListener(this);

        kpQuestion = ((App)getApplication()).kpQuestionBank.kpGetNextQuestion(3);

        kpQuestionLabel.setText("4. " + kpQuestion.kpQuestion);
        kpRadioButton1.setText(kpQuestion.kpOptions.get(0));
        kpRadioButton2.setText(kpQuestion.kpOptions.get(1));
        kpRadioButton3.setText(kpQuestion.kpOptions.get(2));
        kpRadioButton4.setText(kpQuestion.kpOptions.get(3));
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == kpRadioButton1.getId()) {
            kpClearBgColor();
            kpRadioButton1.setBackgroundColor(Color.GREEN);
            this.kpSelectedAns = kpRadioButton1.getText().toString();
        } else if (v.getId() == kpRadioButton2.getId()) {
            kpClearBgColor();
            kpRadioButton2.setBackgroundColor(Color.GREEN);
            this.kpSelectedAns = kpRadioButton1.getText().toString();
        } else if (v.getId() == kpRadioButton3.getId()) {
            kpClearBgColor();
            kpRadioButton3.setBackgroundColor(Color.GREEN);
            this.kpSelectedAns = kpRadioButton1.getText().toString();
        } else if (v.getId() == kpRadioButton4.getId()) {
            kpClearBgColor();
            kpRadioButton4.setBackgroundColor(Color.GREEN);
            this.kpSelectedAns = kpRadioButton1.getText().toString();
        } else if (v.getId() == kpNextBtn.getId()) {
            if (kpSelectedAns != "") {
                if (kpQuestion.isCorrect(this.kpSelectedAns)) {
                    ((App) getApplication()).kpQuestionBank.kpCorrectAns += 1;
                }
                startActivity(new Intent(this, KartikQuestion5.class));
            }
        }
    }

    private void kpClearBgColor() {
        kpRadioButton1.setBackgroundColor(Color.WHITE);
        kpRadioButton2.setBackgroundColor(Color.WHITE);
        kpRadioButton3.setBackgroundColor(Color.WHITE);
        kpRadioButton4.setBackgroundColor(Color.WHITE);
    }
}
