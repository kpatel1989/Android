package kp.com.exercise4;

import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Spinner spinner;
    EditText name;
    RadioButton male,female;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        submit = (Button) findViewById(R.id.button);
        submit.setOnClickListener(this);
        spinner = (Spinner) findViewById(R.id.spinner);
        String[] values = {"Java", "IOS", "Android"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,values);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        name = (EditText) findViewById(R.id.name);
        male = (RadioButton) findViewById(R.id.male);
        female = (RadioButton) findViewById(R.id.female);
    }

    @Override
    public void onClick(View v) {
        String text = name.getText() + "\n" + (male.isChecked() ? "Male" : "Female") + "\n" + spinner.getSelectedItem().toString();
        Toast.makeText(this.getApplicationContext(),text,Toast.LENGTH_LONG).show();
    }
}
