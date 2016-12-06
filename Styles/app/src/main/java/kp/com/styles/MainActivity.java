package kp.com.styles;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button login;

    EditText username ;
    EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        PreferenceManager.setDefaultValues(getApplicationContext(),R.xml.settings,true);
        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(this);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (preferences.getBoolean("saveUserName",false)) {
            username.setText(preferences.getString("username",""));
        }

        if (preferences.getBoolean("savePassword",false)) {
            password.setText(preferences.getString("password", ""));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i;
        switch (item.getItemId()) {
            case R.id.settings:
                i = new Intent(getApplicationContext(),PrefsActivity.class);
                startActivity(i);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return  true;
    }

    @Override
    public void onClick(View view) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        if (preferences.getBoolean("saveUserName",false)) {
            editor.putString("username",username.getText().toString());
        }

        if (preferences.getBoolean("savePassword",false)) {
            editor.putString("password",username.getText().toString());
        }
        Toast.makeText(getApplicationContext(),"login/Password saved..",Toast.LENGTH_LONG).show();
        editor.apply();
    }
}
