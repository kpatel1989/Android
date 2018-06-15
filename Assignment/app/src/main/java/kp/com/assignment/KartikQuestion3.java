package kp.com.assignment;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class KartikQuestion3 extends AppCompatActivity implements View.OnClickListener {

    TextView kpQuestionLabel;
    Button kpNextBtn;
    CheckBox kpCheckBox1, kpCheckBox2, kpCheckBox3, kpCheckBox4;
    KpQuestion kpQuestion;
    String kpSelectedAns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kartik_question3);

        kpQuestionLabel = (TextView) findViewById(R.id.question3Label);
        kpCheckBox1 = (CheckBox) findViewById(R.id.checkBox1);
        kpCheckBox1.setOnClickListener(this);
        kpCheckBox2 = (CheckBox) findViewById(R.id.checkBox2);
        kpCheckBox2.setOnClickListener(this);
        kpCheckBox3 = (CheckBox) findViewById(R.id.checkBox3);
        kpCheckBox3.setOnClickListener(this);
        kpCheckBox4 = (CheckBox) findViewById(R.id.checkBox4);
        kpCheckBox4.setOnClickListener(this);

        kpNextBtn = (Button) findViewById(R.id.question3Next);
        kpNextBtn.setOnClickListener(this);

        kpQuestion = ((App)getApplication()).kpQuestionBank.kpGetNextQuestion(2);

        kpQuestionLabel.setText("3. " + kpQuestion.kpQuestion);
        kpCheckBox1.setText(kpQuestion.kpOptions.get(0));
        kpCheckBox2.setText(kpQuestion.kpOptions.get(1));
        kpCheckBox3.setText(kpQuestion.kpOptions.get(2));
        kpCheckBox4.setText(kpQuestion.kpOptions.get(3));
    }

    @Override
    public void onClick(View v) {
        boolean isChecked = false;
        if (v.getId() != kpNextBtn.getId()) {
            isChecked = ((CheckBox) v).isChecked();
        }
        if (v.getId() == kpCheckBox1.getId()) {
            kpClearBgColor();
            kpCheckBox1.setBackgroundColor(Color.GREEN);
            this.kpSelectedAns = kpCheckBox1.getText().toString();
        } else if (v.getId() == kpCheckBox2.getId()) {
            kpClearBgColor();
            kpCheckBox2.setBackgroundColor(Color.GREEN);
            this.kpSelectedAns = kpCheckBox1.getText().toString();
        } else if (v.getId() == kpCheckBox3.getId()) {
            kpClearBgColor();
            kpCheckBox3.setBackgroundColor(Color.GREEN);
            this.kpSelectedAns = kpCheckBox1.getText().toString();
        } else if (v.getId() == kpCheckBox4.getId()) {
            kpClearBgColor();
            kpCheckBox4.setBackgroundColor(Color.GREEN);
            this.kpSelectedAns = kpCheckBox1.getText().toString();
        } else if (v.getId() == kpNextBtn.getId()) {
            if (kpSelectedAns != "") {
                if (kpQuestion.isCorrect(this.kpSelectedAns)) {
                    ((App) getApplication()).kpQuestionBank.kpCorrectAns += 1;
                }
                startActivity(new Intent(this, KartikQuestion4.class));
            }
        }
        if (v.getId() != kpNextBtn.getId()) {
            ((CheckBox) v).setChecked(isChecked);
        }
    }


    private void kpClearBgColor() {
        kpCheckBox1.setBackgroundColor(Color.WHITE);
        kpCheckBox2.setBackgroundColor(Color.WHITE);
        kpCheckBox3.setBackgroundColor(Color.WHITE);
        kpCheckBox4.setBackgroundColor(Color.WHITE);

        kpCheckBox1.setChecked(false);
        kpCheckBox2.setChecked(false);
        kpCheckBox3.setChecked(false);
        kpCheckBox4.setChecked(false);
    }
}
