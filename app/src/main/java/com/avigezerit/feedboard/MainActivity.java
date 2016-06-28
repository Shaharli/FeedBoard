package com.avigezerit.feedboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import at.theengine.android.simple_rss2_android.RSSItem;
import at.theengine.android.simple_rss2_android.SimpleRss2Parser;
import at.theengine.android.simple_rss2_android.SimpleRss2ParserCallback;

public class MainActivity extends AppCompatActivity {

    ArrayList<RssItem>rssItems;
    List<RssFeed> rssFeeds;
    int feedCount;
    int retrievedFeedCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(fab.getContext(), AddFeedActivity.class);
                startActivity(intent);
            }
        });

        rssItems = new ArrayList<RssItem>();
        rssFeeds = new Database(this).getRssFeed();
        feedCount = rssFeeds.size();
        retrievedFeedCount = 0;

        //check internt connection

//            AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
//            dialog.setTitle("No connection");
//            dialog.setMessage("Please check your internet connection");
//
//            dialog.setNegativeButton("Close", null);
//            dialog.setPositiveButton("Try again", new AlertDialog.OnClickListener() {
//                public void onClick(DialogInterface dialog, int which) {
//                    Log.d("CONNECTION: ", "Clicked on try again");
//                }
//        });

        //show user dialog, try again

        for (int i = 0; i < rssFeeds.size(); i++) {
            GetFeedItems(rssFeeds.get(i).rssFeedAddress);
        }
    }

    public void GetFeedItems(String feedAddress){

        try {

            SimpleRss2Parser parser = new SimpleRss2Parser(feedAddress,
                    new SimpleRss2ParserCallback() {
                        @Override
                        public void onFeedParsed(List<RSSItem> items) {
                            for(int i = 0; i < items.size(); i++){
                                RssItem item = new RssItem();

                                item.setTitle(items.get(i).getTitle());
                                item.setDescription(items.get(i).getDescription());
                                item.setLink(items.get(i).getLink());

                                Log.d("ITEM RECEIVED", items.get(i).getTitle());

                                rssItems.add(item);
                            }
                            PopulateListView();
                        }
                        @Override
                        public void onError(Exception ex) {
                            PopulateListView();
                        }
                    });
            parser.parseAsync();
        }

        catch (Exception e){
            Toast.makeText(this, "Address not found: " +  feedAddress, Toast.LENGTH_LONG).show();
            PopulateListView();
        }
    }

    private void PopulateListView() {
        retrievedFeedCount++;
        if (retrievedFeedCount == feedCount){
            Log.d("FEEDS RETRIEVED", "got all feeds");
            PopulateListView();
            ListView listView = (ListView) findViewById(R.id.rssFeedItemListView);
            listView.setAdapter(new FeedItemAdapter(this, rssItems));

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(MainActivity.this, RssItemViewActivity.class);

                    RssItem item = rssItems.get(position);

                    intent.putExtra("URL", item.getLink().toString());
                    startActivity(intent);
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings){
            Intent intent = new Intent(this, EditRssFeedActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
