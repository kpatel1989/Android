package kp.com.listview;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    KpRssFeed rssFeed;
    KpFileIO kpFileIO;
    ListView list;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = (ListView) findViewById(R.id.list);
        list.setOnItemClickListener(this);

        title = (TextView) findViewById(R.id.textView);

        kpFileIO = new KpFileIO(this.getApplicationContext());
        rssFeed = kpFileIO.readFile();

        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                kpFileIO.downloadFile();
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                new AsyncTask<Void, Void, Void>() {

                    @Override
                    protected Void doInBackground(Void... voids) {
                        rssFeed = kpFileIO.readFile();
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

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        // get the item at the specified position
        KpListItem item = rssFeed.getItem(position);

        // create an intent
        Intent intent = new Intent(this, KpItemActivity.class);

        intent.putExtra("pubdate", item.getKpPublishedDate());
        intent.putExtra("title", item.getKpTitle());
        intent.putExtra("description", item.getKpDescription());
        intent.putExtra("link", item.getKplink());

        this.startActivity(intent);
    }
}
