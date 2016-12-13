package kp.com.assignment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class KartikQuestion5 extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {


    TextView kpQuestionLabel;
    Button kpNextBtn;
    ListView kpLlistView;
    String kpSelectedAns;
    View kpPreviousSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kartik_question5);

        kpQuestionLabel = (TextView) findViewById(R.id.question5Label);
        kpNextBtn = (Button) findViewById(R.id.question5Next);
        kpNextBtn.setOnClickListener(this);

        kpQuestionLabel.setText("5. In which country are you studying ?");

        ArrayList<HashMap<String, String>> listItems = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("title","Canada");
        hashMap.put("image",R.drawable.canada+"");
        listItems.add(hashMap);

        hashMap = new HashMap<String, String>();
        hashMap.put("title","United States of America");
        hashMap.put("image",R.drawable.usa+"");
        listItems.add(hashMap);

        hashMap = new HashMap<String, String>();
        hashMap.put("title","India");
        hashMap.put("image",R.drawable.india+"");
        listItems.add(hashMap);

        hashMap = new HashMap<String, String>();
        hashMap.put("title","United Kingdom");
        hashMap.put("image",R.drawable.uk+"");
        listItems.add(hashMap);

        kpLlistView = (ListView) findViewById(R.id.listView);
        KPCustomListViewAdapter adapter = new KPCustomListViewAdapter(this, listItems);
        kpLlistView.setAdapter(adapter);
        kpLlistView.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (kpSelectedAns != "") {
            if (kpSelectedAns == "Canada") {
                ((App) getApplication()).kpQuestionBank.kpCorrectAns += 1;
            }
            int score = ((App) getApplication()).kpQuestionBank.kpCorrectAns;
            String text = "Your score is " + score + "\n";
            switch (score) {
                case 0:
                case 1:
                case 2:
                    text += "Please try again!";
                    break;
                case 3:
                    text += "Good Job";
                    break;
                case 4:
                    text += "Excellent";
                    break;
                case 5:
                    text += "You are a genius";
                    break;
            }
            Toast.makeText(this,text,Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, MainActivity.class));
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                kpSelectedAns = "Canada";
                break;
            case 1:
                kpSelectedAns = "United States of America";
                break;
            case 2:
                kpSelectedAns = "India";
                break;
            case 3:
                kpSelectedAns = "United Kingdom";
                break;
        }
        if (kpPreviousSelected != null) {
            kpPreviousSelected.setBackgroundColor(Color.WHITE);
        }
        view.setBackgroundColor(Color.GREEN);
        kpPreviousSelected = view;
    }
}
    class KPCustomListViewAdapter extends BaseAdapter {

        private Activity activity;
        private ArrayList<HashMap<String, String>> data;
        private static LayoutInflater inflater=null;

        public KPCustomListViewAdapter(Activity activity, ArrayList<HashMap<String, String>> data) {
            this.activity = activity;
            this.data = data;
            inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return this.data.size();
        }

        @Override
        public Object getItem(int position) {
            return this.data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View vi=convertView;
            if(convertView==null)
                vi = inflater.inflate(R.layout.list_view_item, null);

            TextView title = (TextView)vi.findViewById(R.id.list_item_text_view); // title
            ImageView thumb_image=(ImageView)vi.findViewById(R.id.imageView); // thumb image

            HashMap<String, String> listItem = new HashMap<String, String>();
            listItem = data.get(position);

            // Setting all values in listview
            title.setText(listItem.get("title"));
            thumb_image.setImageDrawable(activity.getApplicationContext().getDrawable(Integer.parseInt(listItem.get("image"))));
            return vi;
        }
    }

