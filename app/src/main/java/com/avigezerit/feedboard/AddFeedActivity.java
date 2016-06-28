package com.avigezerit.feedboard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddFeedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_feed);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button saveBtn = (Button) findViewById(R.id.addFeedbutton);
        final EditText feedAddress = (EditText) findViewById(R.id.addFeedAddressEditText);
        final EditText feedTitle = (EditText) findViewById(R.id.addFeedTitleEditText);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (feedAddress.getText().length() < 6) {
                    Toast.makeText(getApplicationContext(), "Rss feed address is too short", Toast.LENGTH_LONG).show();
                    return;
                }

                Database db = new Database(AddFeedActivity.this);

                RssFeed feed = new RssFeed(feedTitle.getText().toString(), feedAddress.getText().toString());

                db.addRssFeed(feed);
                Toast.makeText(getApplicationContext(), "Rss feed added!", Toast.LENGTH_LONG).show();

                feedTitle.setText("");
                feedAddress.setText("");

            }
        });
    }

}
