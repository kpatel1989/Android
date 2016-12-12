package kp.com.listview;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    KpRssFeed rssFeed;
    FileIO fileIO;
    ListView list;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = (ListView) findViewById(R.id.list);
        title = (TextView) findViewById(R.id.textView);

        fileIO = new FileIO(this.getApplicationContext());
        rssFeed = fileIO.readFile();

        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                fileIO.downloadFile();
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                new AsyncTask<Void, Void, Void>() {

                    @Override
                    protected Void doInBackground(Void... voids) {
                        rssFeed = fileIO.readFile();
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void aVoid) {
                        MainActivity.this.updateUI();
                    }
                }.execute();
            }
        }.execute();
    }

    private void updateUI() {
        if (rssFeed == null) {
            title.setText("Unable to get RSS feed");
            return;
        }
        title.setText(rssFeed.getKpTitle());

        // get the items for the feed
        ArrayList<KpListItem> items = rssFeed.getAllItems();

        // create a List of Map<String, ?> objects
        ArrayList<HashMap<String, String>> data =
                new ArrayList<>();
        for (KpListItem item : items) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("pubDate", item.getPubDateFormatted());
            map.put("title", item.getKpTitle());
            data.add(map);
        }

        // create the resource, from, and to variables
        int resource = R.layout.activity_kp_feed_item;
        String[] from = {"pubDate", "title"};
        int[] to = {R.id.displaydate, R.id.displayTitle};

        // create and set the adapter
        SimpleAdapter adapter =
                new SimpleAdapter(this, data, resource, from, to);
        list.setAdapter(adapter);
    }
}
