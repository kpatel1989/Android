package kp.com.assignment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button kpStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        kpStart = (Button) findViewById(R.id.start);
        kpStart.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        ((App)getApplication()).kpQuestionBank.kpGenerateRandomQuestions();
        startActivity(new Intent(this,KartikQuestion1.class));
    }
}
