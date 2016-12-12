package kp.com.styles;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements View.OnClickListener {

    SharedPreferences prefs;
    Button login;
    EditText username ;
    EditText password;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set the default values for the preferences
        PreferenceManager.setDefaultValues(getActivity(),
                R.xml.settings, false);

        // get the default SharedPreferences object
        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

        // turn on the options menu
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login,
                container, false);

        username = (EditText) view.findViewById(R.id.username);
        password = (EditText) view.findViewById(R.id.password);

        login = (Button) view.findViewById(R.id.login);
        login.setOnClickListener(this);

        if (prefs.getBoolean("saveUserName",false)) {
            username.setText(prefs.getString("username",""));
        }

        if (prefs.getBoolean("savePassword",false)) {
            password.setText(prefs.getString("password", ""));
        }
        // return the View for the layout
        return view;
    }


    @Override
    public void onClick(View view) {
        SharedPreferences.Editor editor = prefs.edit();
        if (prefs.getBoolean("saveUserName",false)) {
            editor.putString("username",username.getText().toString());
        }

        if (prefs.getBoolean("savePassword",false)) {
            editor.putString("password",username.getText().toString());
        }
        Toast.makeText(getActivity().getApplicationContext(),"login/Password saved..",Toast.LENGTH_LONG).show();
        editor.apply();
    }
}
