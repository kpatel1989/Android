package kp.com.assignment;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class KartikQuestion2 extends AppCompatActivity implements View.OnClickListener {

    TextView questionLabel;
    Button nextBtn, button1, button2, button3, button4;
    Question question;
    String selectedAns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kartik_question2);

        questionLabel = (TextView) findViewById(R.id.question2Label);
        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(this);
        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(this);
        button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(this);
        button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(this);

        nextBtn = (Button) findViewById(R.id.question2Next);
        nextBtn.setOnClickListener(this);

        question = ((App)getApplication()).questionBank.getNextQuestion(1);

        questionLabel.setText("2. " + question.question);
        button1.setText(question.options.get(0));
        button2.setText(question.options.get(1));
        button3.setText(question.options.get(2));
        button4.setText(question.options.get(3));
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == button1.getId()) {
            clearBgColor();
            button1.setBackgroundColor(Color.GREEN);
            this.selectedAns = button1.getText().toString();
        } else if (v.getId() == button2.getId()) {
            clearBgColor();
            button2.setBackgroundColor(Color.GREEN);
            this.selectedAns = button1.getText().toString();
        } else if (v.getId() == button3.getId()) {
            clearBgColor();
            button3.setBackgroundColor(Color.GREEN);
            this.selectedAns = button1.getText().toString();
        } else if (v.getId() == button4.getId()) {
            clearBgColor();
            button4.setBackgroundColor(Color.GREEN);
            this.selectedAns = button1.getText().toString();
        } else if (v.getId() == nextBtn.getId()) {
            if (selectedAns != "") {
                if (question.isCorrect(this.selectedAns)) {
                    ((App) getApplication()).questionBank.correctAns += 1;
                }
                startActivity(new Intent(this, KartikQuestion3.class));
            }
        }
    }

    private void clearBgColor() {
        button1.setBackgroundColor(Color.WHITE);
        button2.setBackgroundColor(Color.WHITE);
        button3.setBackgroundColor(Color.WHITE);
        button4.setBackgroundColor(Color.WHITE);
    }
}
