package kp.com.classactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editText;
    Button plusButton,minusButton;
    TextView resultView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.editText);
        plusButton = (Button) findViewById(R.id.plusButton);
        plusButton.setOnClickListener(this);

        minusButton = (Button) findViewById(R.id.minusButton);
        minusButton.setOnClickListener(this);

        resultView = (TextView) findViewById(R.id.result);
    }

    @Override
    public void onClick(View v) {
        int question = Integer.parseInt(editText.getText().toString());
        if (v == plusButton) {
            resultView.setText(String.valueOf(question + 1));
        } else {
            resultView.setText(String.valueOf(question - 1));
        }
    }
}
