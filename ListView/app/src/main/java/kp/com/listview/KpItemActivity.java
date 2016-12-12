package kp.com.listview;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class KpItemActivity extends Activity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        
        // get references to widgets
        TextView kpTitleTextView = (TextView) findViewById(R.id.titleTextView);
        TextView kpPubDateTextView = (TextView) findViewById(R.id.pubDateTextView);
        TextView kpDescriptionTextView = (TextView) findViewById(R.id.descriptionTextView);
        TextView kpLinkTextView = (TextView) findViewById(R.id.linkTextView);
        
        // get the intent
        Intent intent = getIntent();
        
        // get data from the intent
        String pubDate = intent.getStringExtra("pubdate");
        String title = intent.getStringExtra("title");
        String description = intent.getStringExtra("description").replace('\n', ' ');
        
        // display data on the widgets
        kpPubDateTextView.setText(pubDate);
        kpTitleTextView.setText(title);
        kpDescriptionTextView.setText(description);
        
        // set listener
        kpLinkTextView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // get the intent
        Intent intent = getIntent();
        
        // get the Uri for the link
        String link = intent.getStringExtra("link");
        Uri viewUri = Uri.parse(link);
        
        // create the intent and start it
        Intent viewIntent = new Intent(Intent.ACTION_VIEW, viewUri);
        startActivity(viewIntent);
    }
}