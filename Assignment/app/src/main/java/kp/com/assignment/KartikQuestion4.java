package kp.com.assignment;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioButton;
import android.widget.TextView;

public class KartikQuestion4 extends AppCompatActivity implements View.OnClickListener {

    TextView questionLabel;
    Button nextBtn;
    RadioButton radioButton1, radioButton2, radioButton3, radioButton4;
    Question question;
    String selectedAns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kartik_question4);


        questionLabel = (TextView) findViewById(R.id.question4Label);
        radioButton1 = (RadioButton) findViewById(R.id.radioButton1);
        radioButton1.setOnClickListener(this);
        radioButton2 = (RadioButton) findViewById(R.id.radioButton2);
        radioButton2.setOnClickListener(this);
        radioButton3 = (RadioButton) findViewById(R.id.radioButton3);
        radioButton3.setOnClickListener(this);
        radioButton4 = (RadioButton) findViewById(R.id.radioButton4);
        radioButton4.setOnClickListener(this);

        nextBtn = (Button) findViewById(R.id.question4Next);
        nextBtn.setOnClickListener(this);

        question = ((App)getApplication()).questionBank.getNextQuestion(3);

        questionLabel.setText("4. " + question.question);
        radioButton1.setText(question.options.get(0));
        radioButton2.setText(question.options.get(1));
        radioButton3.setText(question.options.get(2));
        radioButton4.setText(question.options.get(3));
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == radioButton1.getId()) {
            clearBgColor();
            radioButton1.setBackgroundColor(Color.GREEN);
            this.selectedAns = radioButton1.getText().toString();
        } else if (v.getId() == radioButton2.getId()) {
            clearBgColor();
            radioButton2.setBackgroundColor(Color.GREEN);
            this.selectedAns = radioButton1.getText().toString();
        } else if (v.getId() == radioButton3.getId()) {
            clearBgColor();
            radioButton3.setBackgroundColor(Color.GREEN);
            this.selectedAns = radioButton1.getText().toString();
        } else if (v.getId() == radioButton4.getId()) {
            clearBgColor();
            radioButton4.setBackgroundColor(Color.GREEN);
            this.selectedAns = radioButton1.getText().toString();
        } else if (v.getId() == nextBtn.getId()) {
            if (selectedAns != "") {
                if (question.isCorrect(this.selectedAns)) {
                    ((App) getApplication()).questionBank.correctAns += 1;
                }
                startActivity(new Intent(this, KartikQuestion5.class));
            }
        }
    }

    private void clearBgColor() {
        radioButton1.setBackgroundColor(Color.WHITE);
        radioButton2.setBackgroundColor(Color.WHITE);
        radioButton3.setBackgroundColor(Color.WHITE);
        radioButton4.setBackgroundColor(Color.WHITE);
    }
}
