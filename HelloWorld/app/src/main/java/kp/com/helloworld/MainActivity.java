package kp.com.helloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements TextView.OnEditorActionListener,Button.OnClickListener {

    private EditText billAmount, percent;
    private TextView tip, total;
    private Button plusButton, minusButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        billAmount = (EditText) findViewById(R.id.billAmount);
        tip = (TextView) findViewById(R.id.tip);
        percent = (EditText) findViewById(R.id.percent);
        total = (TextView) findViewById(R.id.total);
        plusButton = (Button) findViewById(R.id.plus);
        minusButton = (Button) findViewById(R.id.minus);

        billAmount.setOnEditorActionListener(this);
        plusButton.setOnClickListener(this);
        minusButton.setOnClickListener(this);

        percent.setText("4.0");
    }

    @Override
    public void onClick(View v) {
        float percent = Float.parseFloat(this.percent.getText().toString());
        if (v == plusButton) {
            this.percent.setText(String.valueOf(percent + 1));
        } else {
            this.percent.setText(String.valueOf(percent - 1));
        }

    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        int bill = Integer.parseInt(billAmount.getText().toString());
        float tip =  bill * Float.parseFloat(percent.getText().toString()) / 100;
        this.tip.setText("$" + tip);
        this.total.setText("$" + (bill + tip));
        return false;
    }
}
