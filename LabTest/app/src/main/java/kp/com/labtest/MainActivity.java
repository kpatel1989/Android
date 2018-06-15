package kp.com.labtest;

import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    TextView result;
    EditText rate, value;
    Button calculate, addBtn, minusBtn;
    Spinner spinner;
    ImageView imageView1, imageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rate = (EditText) findViewById(R.id.rate);
        result = (TextView) findViewById(R.id.result);
        value = (EditText) findViewById(R.id.value);

        calculate = (Button) findViewById(R.id.calculate);
        calculate.setOnClickListener(this);
        addBtn = (Button) findViewById(R.id.addBtn);
        addBtn.setOnClickListener(this);
        minusBtn = (Button) findViewById(R.id.minusBtn);
        minusBtn.setOnClickListener(this);

        spinner = (Spinner) findViewById(R.id.spinner);
        String[] values = {"USD - CAD", "US - INR", "CAD - US", "CAD - INR", "INR - CAD", "INR - US"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        imageView1 = (ImageView) findViewById(R.id.imageView1);
        imageView2 = (ImageView) findViewById(R.id.imageView2);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == addBtn.getId()) {
            float rate = Float.parseFloat(this.rate.getText().toString());
            rate += 0.1;
            this.rate.setText(rate+"");
        }
        else if (view.getId() == minusBtn.getId()) {
            float rate = Float.parseFloat(this.rate.getText().toString());
            rate -= 0.1;
            this.rate.setText(rate+"");
        } else {
            result.setText((Float.parseFloat(value.getText().toString()) * Float.parseFloat(rate.getText().toString()))+"");
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (i) {
            case 0:
                imageView1.setImageDrawable(getDrawable(R.drawable.us));
                imageView2.setImageDrawable(getDrawable(R.drawable.canada));
                rate.setText("1.5");
                break;
            case 1:
                imageView1.setImageDrawable(getDrawable(R.drawable.us));
                imageView2.setImageDrawable(getDrawable(R.drawable.india));
                rate.setText("67");
                break;
            case 2:
                imageView1.setImageDrawable(getDrawable(R.drawable.canada));
                imageView2.setImageDrawable(getDrawable(R.drawable.us));
                rate.setText("0.15");
                break;
            case 3:
                imageView1.setImageDrawable(getDrawable(R.drawable.canada));
                imageView2.setImageDrawable(getDrawable(R.drawable.india));
                rate.setText("50");
                break;
            case 4:
                imageView1.setImageDrawable(getDrawable(R.drawable.india));
                imageView2.setImageDrawable(getDrawable(R.drawable.canada));
                rate.setText("0.05");
                break;
            case 5:
                imageView1.setImageDrawable(getDrawable(R.drawable.india));
                imageView2.setImageDrawable(getDrawable(R.drawable.us));
                rate.setText("0.067");
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
