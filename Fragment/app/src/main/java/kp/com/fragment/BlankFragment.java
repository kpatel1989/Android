package kp.com.fragment;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment implements TextView.OnEditorActionListener, View.OnClickListener {


    private EditText billAmountEditText;
    private TextView percentTextView;
    private Button percentUpButton;
    private Button   percentDownButton;
    private TextView tipTextView;
    private TextView totalTextView;

    // define instance variables that should be saved
    private String billAmountString = "";
    private float tipPercent = .15f;

    // define rounding constants
    private final int ROUND_NONE = 0;
    private final int ROUND_TIP = 1;
    private final int ROUND_TOTAL = 2;

    // set up preferences
    private SharedPreferences preferences;
    private boolean rememberTipPercent = true;
    private int rounding = ROUND_NONE;
    public BlankFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PreferenceManager.setDefaultValues(getActivity(),R.xml.preferences,false);
        preferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        // get references to the widgets
        billAmountEditText = (EditText)
                view.findViewById(R.id.billAmountEditText);
        percentTextView = (TextView) view.findViewById(R.id.percentTextView);
        percentUpButton = (Button) view.findViewById(R.id.percentUpButton);
        percentDownButton = (Button) view.findViewById(R.id.percentDownButton);
        tipTextView = (TextView) view.findViewById(R.id.tipTextView);
        totalTextView = (TextView) view.findViewById(R.id.totalTextView);

        // set the listeners
        billAmountEditText.setOnEditorActionListener(this);
        percentUpButton.setOnClickListener(this);
        percentDownButton.setOnClickListener(this);
        return view;
    }

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        return false;
    }

    @Override
    public void onClick(View view) {

    }
}
