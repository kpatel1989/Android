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

    TextView questionLabel;
    Button nextBtn;
    CheckBox checkBox1, checkBox2, checkBox3, checkBox4;
    Question question;
    String selectedAns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kartik_question3);

        questionLabel = (TextView) findViewById(R.id.question3Label);
        checkBox1 = (CheckBox) findViewById(R.id.checkBox1);
        checkBox1.setOnClickListener(this);
        checkBox2 = (CheckBox) findViewById(R.id.checkBox2);
        checkBox2.setOnClickListener(this);
        checkBox3 = (CheckBox) findViewById(R.id.checkBox3);
        checkBox3.setOnClickListener(this);
        checkBox4 = (CheckBox) findViewById(R.id.checkBox4);
        checkBox4.setOnClickListener(this);

        nextBtn = (Button) findViewById(R.id.question3Next);
        nextBtn.setOnClickListener(this);

        question = ((App)getApplication()).questionBank.getNextQuestion(2);

        questionLabel.setText("3. " + question.question);
        checkBox1.setText(question.options.get(0));
        checkBox2.setText(question.options.get(1));
        checkBox3.setText(question.options.get(2));
        checkBox4.setText(question.options.get(3));
    }

    @Override
    public void onClick(View v) {
        boolean isChecked = false;
        if (v.getId() != nextBtn.getId()) {
            isChecked = ((CheckBox) v).isChecked();
        }
        if (v.getId() == checkBox1.getId()) {
            clearBgColor();
            checkBox1.setBackgroundColor(Color.GREEN);
            this.selectedAns = checkBox1.getText().toString();
        } else if (v.getId() == checkBox2.getId()) {
            clearBgColor();
            checkBox2.setBackgroundColor(Color.GREEN);
            this.selectedAns = checkBox1.getText().toString();
        } else if (v.getId() == checkBox3.getId()) {
            clearBgColor();
            checkBox3.setBackgroundColor(Color.GREEN);
            this.selectedAns = checkBox1.getText().toString();
        } else if (v.getId() == checkBox4.getId()) {
            clearBgColor();
            checkBox4.setBackgroundColor(Color.GREEN);
            this.selectedAns = checkBox1.getText().toString();
        } else if (v.getId() == nextBtn.getId()) {
            if (selectedAns != "") {
                if (question.isCorrect(this.selectedAns)) {
                    ((App) getApplication()).questionBank.correctAns += 1;
                }
                startActivity(new Intent(this, KartikQuestion4.class));
            }
        }
        if (v.getId() != nextBtn.getId()) {
            ((CheckBox) v).setChecked(isChecked);
        }
    }


    private void clearBgColor() {
        checkBox1.setBackgroundColor(Color.WHITE);
        checkBox2.setBackgroundColor(Color.WHITE);
        checkBox3.setBackgroundColor(Color.WHITE);
        checkBox4.setBackgroundColor(Color.WHITE);

        checkBox1.setChecked(false);
        checkBox2.setChecked(false);
        checkBox3.setChecked(false);
        checkBox4.setChecked(false);
    }
}
