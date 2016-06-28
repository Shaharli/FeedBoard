package com.avigezerit.feedboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Shaharli on 28/06/2016.
 */
public class FeedItemAdapter extends ArrayAdapter<RssItem> {

    public FeedItemAdapter(Context context, ArrayList<RssItem> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RssItem item = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.rss_feed_item_row, parent, false);
        }

        TextView title = (TextView) convertView.findViewById(R.id.rssFeedItemTitleTextView);
        TextView description = (TextView) convertView.findViewById(R.id.rssFeedItemDescTextView);
        TextView letter =  (TextView) convertView.findViewById(R.id.rssFeedItemLetterTextView);

        title.setText(item.getTitle());
        description.setText(item.getDescription());
        letter.setText(item.getTitle().substring(0,1));

        return convertView;
    }
}
