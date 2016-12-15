package kartik.com.labtest;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class KpCDActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        
        // get references to widgets
        TextView kpTitleTextView = (TextView) findViewById(R.id.title);
        TextView kpArtistTextView = (TextView) findViewById(R.id.artist);
        TextView kpYearTextView = (TextView) findViewById(R.id.year);
        TextView kpCompanyTextView = (TextView) findViewById(R.id.company);
        TextView kpCountryTextView = (TextView) findViewById(R.id.country);
        TextView kpPriceTextView = (TextView) findViewById(R.id.price);
        
        // get the intent
        Intent intent = getIntent();
        
        // get data from the intent
        String country = intent.getStringExtra("country");
        String title = intent.getStringExtra("title");
        String company = intent.getStringExtra("company");
        String artist = intent.getStringExtra("artist");
        Double price = intent.getDoubleExtra("price",0);
        int year = intent.getIntExtra("year",0);
        
        // display data on the widgets
        kpCountryTextView.setText(country);
        kpCompanyTextView.setText(company);
        kpTitleTextView.setText(title);
        kpYearTextView.setText(year+"");
        kpPriceTextView.setText(price+"");
        kpArtistTextView.setText(artist);
    }
}