package kartik.com.labtest;

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
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    KpCatalog rssFeed;
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
            title.setText("Unable to get RSS kpCatalog");
            return;
        }
        title.setText("Catalog");

        // get the items for the kpCatalog
        ArrayList<KpCd> items = rssFeed.getAllItems();

        // create a List of Map<String, ?> objects
        ArrayList<HashMap<String, Object>> data =
                new ArrayList<>();
        for (KpCd item : items) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("title", item.getKpTitle());
            map.put("country", item.getKpCountry());
            map.put("artist", item.getKpArtist());
            map.put("company", item.getKpCompany());
            map.put("price", item.getKpPrice());
            map.put("year", item.getKpYear());
            data.add(map);
        }

        // create the resource, from, and to variables
        int resource = R.layout.activity_list_item;
        String[] from = {"title","artist","company", "country", "price", "year"};
        int[] to = {R.id.titleItem, R.id.artistItem,R.id.companyItem, R.id.countryItem,R.id.priceItem, R.id.yearItem};

        // create and set the adapter
        SimpleAdapter adapter =
                new SimpleAdapter(this, data, resource, from, to);
        list.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        // get the kpCd at the specified position
        KpCd item = rssFeed.getItem(position);

        // create an intent
        Intent intent = new Intent(this, KpCDActivity.class);

        intent.putExtra("title", item.getKpTitle());
        intent.putExtra("country", item.getKpCountry());
        intent.putExtra("company", item.getKpCompany());
        intent.putExtra("artist", item.getKpArtist());
        intent.putExtra("price", item.getKpPrice());
        intent.putExtra("year", item.getKpYear());

        this.startActivity(intent);
    }
}
