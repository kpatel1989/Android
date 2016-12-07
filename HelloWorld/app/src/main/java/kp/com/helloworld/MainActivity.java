package kp.com.helloworld;

import android.app.Activity;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements TextView.OnEditorActionListener,Button.OnClickListener {

    private EditText billAmount;
    private TextView tip, total, percent;
    private Button plusButton, minusButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        billAmount = (EditText) findViewById(R.id.billAmount);
        tip = (TextView) findViewById(R.id.tip);
        percent = (TextView) findViewById(R.id.percent);
        total = (TextView) findViewById(R.id.total);
        plusButton = (Button) findViewById(R.id.plus);
        minusButton = (Button) findViewById(R.id.minus);

        billAmount.setOnEditorActionListener(this);
        plusButton.setOnClickListener(this);
        minusButton.setOnClickListener(this);

        PreferenceManager.setDefaultValues(getApplicationContext(),R.xml.prefrences,true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_options,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i;
        switch (item.getItemId()) {
            case R.id.item1:
                i = new Intent(getApplicationContext(),AboutActivity.class);
                startActivity(i);
                break;
            case R.id.item2:
                i = new Intent(getApplicationContext(), SettingActivity.class);
                startActivity(i);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return  true;
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
