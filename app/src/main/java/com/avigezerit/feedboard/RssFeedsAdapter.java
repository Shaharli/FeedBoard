package com.avigezerit.feedboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Shaharli on 28/06/2016.
 */
public class RssFeedsAdapter extends ArrayAdapter<RssFeed> {

    public RssFeedsAdapter(Context context, List<RssFeed> feeds) {
        super(context, 0, feeds);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RssFeed feed = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.rss_edit_feed_item_row, parent, false);
        }

        TextView feedTitle = (TextView) convertView.findViewById(R.id.rssEditFeedTitleTextView);
        TextView feedAddress = (TextView) convertView.findViewById(R.id.rssEditFeedAddressTextView);

        feedTitle.setText(feed.rssFeedTitle);
        feedAddress.setText(feed.rssFeedAddress);

        return convertView;
    }
}
